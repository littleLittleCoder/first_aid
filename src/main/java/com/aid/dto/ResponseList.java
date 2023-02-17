package com.aid.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 裴冲
 * @DateTime: 2023/2/14 14:23
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseList<T> {

    @ApiModelProperty("业务数据列表")
    protected List<T> content = new ArrayList();
    @ApiModelProperty("当前页码")
    protected long page;
    @ApiModelProperty("每页展示数")
    protected long size;
    @ApiModelProperty("列表总数")
    protected long total;
    @ApiModelProperty("列表总页数")
    protected int totalPage = 0;
    @ApiModelProperty("是否最后一页")
    protected int isLast = 0;
}
