package com.aid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

/**
 * @Author: 裴冲
 * @DateTime: 2023/3/28 14:26
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginParam {

    private String phone;

    private String passWorld;
}
