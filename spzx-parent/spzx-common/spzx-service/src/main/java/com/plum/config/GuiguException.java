package com.plum.config;

import com.plum.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * ClassName: GuiguException
 * Package: com.plum.config
 * description
 * Author: Plum
 * Crete : 2024/5/8 20:54
 * Version 1.0
 */
@Data
public class GuiguException extends RuntimeException{

    private Integer code; //错误状态码
    private String message;//错误信息
    private ResultCodeEnum resultCodeEnum; //封装错误码的状态信息


    public GuiguException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public GuiguException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
