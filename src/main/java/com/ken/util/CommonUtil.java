package com.ken.util;

import com.ken.model.PageParam;

import java.util.UUID;

public class CommonUtil {

    public static PageParam pageParam(int pageIndex, int pageSize) {
        return new PageParam(pageIndex, pageSize);
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
