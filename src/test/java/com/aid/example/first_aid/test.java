package com.aid.example.first_aid;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;

/**
 * @Author: 裴冲
 * @DateTime: 2023/3/16 14:51
 * @Description:
 */
public class test {
    public static void main(String[] args) {
        boolean a = NumberUtil.isNumber("0.00");
        System.out.println(a);

    }
}
