package com.yangyang.demo.exception;

import com.yangyang.demo.common.RespCode;

/**
 * Created by chenshunyang on 16/7/13.
 */
public class ServiceException extends  RuntimeException{

	private static final long serialVersionUID = 7677699696001488561L;
	private RespCode errorCode;

    public ServiceException(RespCode errorCode) {
        super(errorCode.toString());
        this.errorCode = errorCode;
    }

    public ServiceException(String message, RespCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(String message, RespCode errorCode, Throwable exception) {
        super(message, exception);
        this.errorCode = errorCode;
    }

    public RespCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(RespCode errorCode) {
        this.errorCode = errorCode;
    }

    public int getHttpStatus() {
        return this.errorCode.getHttpCode();
    }

    public String toJson() {
        return errorCode.toJson();
    }
}