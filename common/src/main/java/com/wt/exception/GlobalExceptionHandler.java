package com.wt.exception;


import com.wt.constant.Result;
import com.wt.constant.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    public Result<String> bizExceptionHandler(BizException e) {
        return Result.error(ResultCodeEnum.ERROR,e.getMes());
    }


    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        log.error("发生异常信息:", e);
        return  Result.error(ResultCodeEnum.ERROR, "系统异常");
    }


}
