package com.aid.transfer;

import com.aid.dto.AidArrangementDTO;
import com.aid.dto.UserDTO;
import com.aid.entity.AidArrangementDO;
import com.aid.manager.UserManager;
import com.aid.mapper.UserMapper;
import com.aid.param.AidArrangementParam;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.NumberUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: 裴冲
 * @DateTime: 2023/2/15 11:32
 * @Description:
 */
@Component
public class AidArrangementTransfer {
    @Resource
    private UserManager userManager;

    public List<AidArrangementDTO> transferDoToDto(List<AidArrangementDO> aidArrangementDOs) {
        if (CollectionUtils.isEmpty(aidArrangementDOs)) {
            return Collections.emptyList();
        }

        //获取所有用户id
        List<Long> userIds = aidArrangementDOs.stream()
                .flatMap(i -> {
                    List<Long> ids = new ArrayList<>();
                    if (Objects.nonNull(i.getYardmanId())) {
                        ids.add(i.getYardmanId());
                    }
                    if (StringUtils.isNotBlank(i.getNurseIds())) {
                        ids.addAll(JSON.parseArray(i.getNurseIds(), Long.class));
                    }
                    return ids.stream();
                })
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        Map<Long, UserDTO> userMap = userManager.getUserMap(userIds);

        return aidArrangementDOs.stream()
                .map(v -> {
                    AidArrangementDTO aidArrangementDTO = new AidArrangementDTO();
                    BeanUtils.copyProperties(v, aidArrangementDTO);
                    aidArrangementDTO.setYardmanUser(userMap.get(v.getYardmanId()));
                    if (StringUtils.isNotBlank(v.getNurseIds())) {
                        List<Long> nurseIds = JSON.parseArray(v.getNurseIds(), Long.class);
                        aidArrangementDTO.setNurseUsers(nurseIds.stream()
                                .map(userMap::get)
                                .collect(Collectors.toList()));
                    }
                    return aidArrangementDTO;
                }).collect(Collectors.toList());
    }


    public static AidArrangementDO transferParamToDo(AidArrangementParam aidArrangementParam) {
        AidArrangementDO aidArrangementDO = new AidArrangementDO();
        if (Objects.isNull(aidArrangementParam)) {
            return aidArrangementDO;
        }

        BeanUtils.copyProperties(aidArrangementParam, aidArrangementDO);
        aidArrangementDO.setNurseIds(JSON.toJSONString(aidArrangementParam.getNurseIds()));
        aidArrangementDO.setIsActive(true);
        aidArrangementDO.setCreatedDate(new Date());
        return aidArrangementDO;
    }
}
