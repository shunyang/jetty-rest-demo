package com.yangyang.demo.common;

import com.google.common.collect.ImmutableMap;
import com.yangyang.demo.utils.JsonUtils;

/**
 * Response Code
 */
public enum  RespCode {

    OK(200, 0, "success"),
    // 通用错误
    SERVER_ERROR(500, 5000, "bad server"),
    BUSINESS_ERROR(500, 5001, "business error"),


    REQUEST_METHOD_NOT_SUPPORT(405, 4001, "request method not supported"),
    REQUEST_API_NOT_FOUND(404, 4002, "request api not found"),
    HTTP_MEDIA_TYPE_NOT_SUPPORT(406, 4003, "http media type not supported"),
    PARAMETER_ERROR(400, 4004, "parameter invalid"),
    AUTHORIZATION_FAIL(401, 4005, "authorization fail"),
    TOKEN_EXPIRATION(401, 4006, "token expiration"),
    FORBIDDEN_ERROR(403, 4007, "access forbidden"),

    // 业务错误
    USERNAME_NOT_EXIST(400, 4100, "username not exist"),
    PASSWORD_ERROR(400, 4101, "wrong password"),
    UID_TOKEN_ERROR(400, 4102, "devid not match token parse");


    private int httpCode;
    private int code;
    private String message;

    RespCode (int httpCode, int code, String message) {
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJson() {
        return JsonUtils.toJson(ImmutableMap.of("code", code, "message", message));
    }

    @Override
    public String toString() {
        return String.format("[httpCode:%s, code:%s, message:%s]", httpCode, code, message);
    }
}
