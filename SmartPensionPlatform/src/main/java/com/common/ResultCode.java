package com.common;

public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    UNAUTHORIZED(401, "暂未登录"),
    FORBIDDEN(403, "没有相关权限"),
	VALIDATE_FAILED(404, "用户名或者密码有误"),
	USER_NOT_FOUND(405,"用户信息不存在");
	
    private long code;
    private String message;
 
    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }
 
    public long getCode() {
        return code;
    }
 
    public String getMessage() {
        return message;
    }
}

