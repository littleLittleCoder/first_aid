package com.aid.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ResponseHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 裴冲
 * @DateTime: 2023/2/14 14:15
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    @ApiModelProperty("成功标识")
    protected boolean success = true;
    @ApiModelProperty("响应体header对象")
    protected ResponseHeader header;
    @ApiModelProperty("业务对象")
    protected T data;
    @ApiModelProperty("业务运行msg code")
    protected String msgCode;
    @ApiModelProperty("业务结果code")
    protected int code = 200;
    @ApiModelProperty("业务运行msg")
    protected String msg;
    @ApiModelProperty("业务运行msg detail")
    protected String msgDetail;
    protected Map<String, String> headers = new HashMap();
    protected Map bizExtMap;
}
