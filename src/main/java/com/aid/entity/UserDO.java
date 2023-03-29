package com.aid.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 裴冲
 * @DateTime: 2023/2/17 15:39
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user")
@ApiModel(value = "UserDO", description = "用户信息")
public class UserDO {

    private Long id;

    private String name;

    private Integer age;

    private String sex;

    private Boolean isActive = true;

    private String phone;

    private String passWorld;

    private Integer identity;

}
