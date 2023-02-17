package com.aid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 裴冲
 * @DateTime: 2023/2/14 14:14
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request<T> {

    private T model;
}
