package com.aid.transfer;

import com.aid.dto.AidRecordDTO;
import com.aid.dto.AidRecordParam;
import com.aid.dto.UserDTO;
import com.aid.entity.AidRecordDO;
import com.aid.manager.UserManager;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: 裴冲
 * @DateTime: 2023/2/15 10:48
 * @Description:
 */
@Component
public class AidRecordTransfer {
    @Resource
    private UserManager userManager;


    public List<AidRecordDTO> transferDoToDto(List<AidRecordDO> aidRecordDOs) {
        if (CollectionUtils.isEmpty(aidRecordDOs)) {
            return Collections.emptyList();
        }

        List<Long> ids = aidRecordDOs.stream()
                .map(AidRecordDO::getOperatorId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        Map<Long, UserDTO> userMap = userManager.getUserMap(ids);

        return aidRecordDOs.stream()
                .map(i -> {
                    AidRecordDTO aidRecordDTO = new AidRecordDTO();
                    BeanUtils.copyProperties(i, aidRecordDTO);
                    aidRecordDTO.setOperator(userMap.get(i.getOperatorId()));
                    return aidRecordDTO;
                }).collect(Collectors.toList());

    }


    public static AidRecordDO transferDtoToDo(AidRecordParam param) {
        AidRecordDO aidRecordDO = new AidRecordDO();
        if (Objects.isNull(param)) {
            return aidRecordDO;
        }

        BeanUtils.copyProperties(param, aidRecordDO);
        aidRecordDO.setIsActive(true);
        aidRecordDO.setCreatedDate(new Date());
        return aidRecordDO;
    }
}
