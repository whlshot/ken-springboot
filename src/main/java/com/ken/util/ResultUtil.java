package com.ken.util;

import com.ken.model.ResultInfo;

public class ResultUtil {

    public static ResultInfo success(Object data) {
        return new ResultInfo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getInfo(), data);
    }

    public static ResultInfo error() {
        return new ResultInfo(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getInfo(), null);
    }
}
