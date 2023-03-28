package com.aid.dto;

import com.aid.entity.UserDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 裴冲
 * @DateTime: 2023/3/28 14:18
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "LoginResDTO", description = "登录信息")
public class LoginResDTO {

    @ApiModelProperty("登录结果")
    private Boolean loginResult;

    @ApiModelProperty("账号信息")
    private UserDO user;
}
