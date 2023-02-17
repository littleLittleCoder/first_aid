package com.aid.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
public class UserDO {

    private Long id;

    private String name;

    private Integer age;

    private Integer sex;

}
