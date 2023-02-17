package com.aid.manager;

import com.aid.dto.UserDTO;
import com.aid.entity.UserDO;
import com.aid.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: 裴冲
 * @DateTime: 2023/2/17 14:45
 * @Description:
 */
@Component
public class UserManager {

    @Resource
    private UserMapper userMapper;

    /**
     * 通过用户id查询用户信息
     *
     * @param userIds
     * @return
     */
    public Map<Long, UserDTO> getUserMap(List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyMap();
        }
        List<UserDO> userDOList = userMapper.selectBatchIds(userIds);
        if (CollectionUtils.isEmpty(userDOList)) {
            return Collections.emptyMap();
        }

        return userDOList.stream()
                .map(i -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(i, userDTO);
                    return userDTO;
                })
                .collect(Collectors.toMap(UserDTO::getId, Function.identity()));
    }
}
