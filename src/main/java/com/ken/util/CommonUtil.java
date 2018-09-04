package com.ken.util;

import com.ken.model.PageParam;

public class CommonUtil {

    public static PageParam pageParam(int pageIndex, int pageSize) {
        return new PageParam(pageIndex, pageSize);
    }
}
