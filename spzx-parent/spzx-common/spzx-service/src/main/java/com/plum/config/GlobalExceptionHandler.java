package com.plum.config;

import com.plum.spzx.model.vo.common.Result;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.plum.config
 * description
 * Author: Plum
 * Crete : 2024/5/8 20:50
 * Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception exception){
        exception.printStackTrace();
        return Result.build(null,201,"出现了异常");
    }

    @ExceptionHandler(value = GuiguException.class)     // 处理自定义异常
    @ResponseBody
    public Result error(GuiguException exception) {
        exception.printStackTrace();
        return Result.build(null , exception.getResultCodeEnum()) ;
    }

}
