package com.ken.util;

public enum ResultEnum {

    SUCCESS(200, "操作成功"), ERROR(500, "系统报错"), NO_LOGIN(102, "用户没有登录"), NO_AUTHORITY(403,
            "用户没有权限"), NO_EXITS(104, "用户不存在");

    private int code;
    private String info;

    ResultEnum(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
