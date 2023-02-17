package com.aid.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 裴冲
 * @DateTime: 2023/2/14 14:23
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestList {

    @ApiModelProperty("当前页码")
    protected long page = 1;
    @ApiModelProperty("每页展示数")
    protected long size = 20;
}
