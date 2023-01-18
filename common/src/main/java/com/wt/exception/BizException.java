package com.wt.exception;

import com.wt.constant.ResultCodeEnum;
import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class BizException extends RuntimeException {

    private String code;

    private String mes;


    public BizException(String message) {
        super(message);
        this.code = ResultCodeEnum.ERROR.getCode();
        this.mes = message;
    }

    public BizException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMes());
        this.code = resultCodeEnum.getCode();
        this.mes = resultCodeEnum.getMes();
    }


}
