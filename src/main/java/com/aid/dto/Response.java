package com.aid.dto;

import com.aid.enums.CodeEnum;

/**
 * @Author: 裴冲
 * @DateTime: 2023/2/14 14:15
 * @Description:
 */
public class Response<T> extends Result<T>{

    public static <T> Response<T> successResponse() {
        Response<T> response = new Response();
        response.setSuccess(true);
        response.setMsgCode(CodeEnum.SUCCESS.toString());
        response.setData(null);
        return response;
    }

    public static <T> Response<T> successResponse(T data) {
        Response<T> response = new Response();
        response.setSuccess(true);
        response.setMsgCode(CodeEnum.SUCCESS.toString());
        response.setData(data);
        return response;
    }

    public static <T> Response<T> successResponse(int code) {
        Response<T> response = new Response();
        response.setSuccess(true);
        response.setMsgCode(CodeEnum.SUCCESS.toString());
        response.setData(null);
        response.setCode(code);
        return response;
    }

    public static <T> Response<T> successResponse(String msg) {
        Response<T> response = new Response();
        response.setSuccess(true);
        response.setMsgCode(CodeEnum.SUCCESS.toString());
        response.setData(null);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> successResponse(T data, int code) {
        Response<T> response = new Response();
        response.setSuccess(true);
        response.setMsgCode(CodeEnum.SUCCESS.toString());
        response.setData(data);
        response.setCode(code);
        return response;
    }

    public static <T> Response<T> successResponse(T data, String msg) {
        Response<T> response = new Response();
        response.setSuccess(true);
        response.setMsgCode(CodeEnum.SUCCESS.toString());
        response.setData(data);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> successResponse(int code, String msg) {
        Response<T> response = new Response();
        response.setSuccess(true);
        response.setMsgCode(CodeEnum.SUCCESS.toString());
        response.setData(null);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> successResponse(T data, int code, String msg) {
        Response<T> response = new Response();
        response.setSuccess(true);
        response.setMsgCode(CodeEnum.SUCCESS.toString());
        response.setData(data);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> errorResponse(String msg) {
        Response<T> response = new Response();
        response.setSuccess(false);
        response.setMsgCode(CodeEnum.APPLICATION_ERROR.getCode());
        response.setMsg(msg);
        response.setData(null);
        response.setCode(500);
        return response;
    }

    public static <T> Response<T> errorResponse(T data, String errorCode, String msg) {
        Response<T> response = new Response();
        response.setSuccess(false);
        response.setMsgCode(errorCode);
        response.setMsg(msg);
        response.setData(data);
        response.setCode(500);
        return response;
    }

    public static <T> Response<T> errorResponse(T data, String msg, int code) {
        Response<T> response = new Response();
        response.setSuccess(false);
        response.setMsg(msg);
        response.setData(data);
        response.setCode(code);
        return response;
    }

    public static <T> Response<T> errorResponse(T data, String errorCode, String msg, int code) {
        Response<T> response = new Response();
        response.setSuccess(false);
        response.setMsgCode(errorCode);
        response.setMsg(msg);
        response.setData(data);
        response.setCode(500);
        return response;
    }

    private Response() {
    }

    @Override
    public String toString() {
        return "Response{success=" + this.success + ", header=" + this.header + ", data=" + this.data + ", msgCode=" + this.msgCode + ", code=" + this.code + ", msg=" + this.msg + ", headers=" + this.headers + ", bizExtMap=" + this.bizExtMap + '}';
    }
}
