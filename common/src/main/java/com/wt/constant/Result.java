package com.wt.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 返回统一结果信息
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T>{

    private String code;

    private String mes;

    private T data;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    public Result(ResultCodeEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.mes = resultEnum.getMes();
        this.data = data;
        this.dateTime = LocalDateTime.now();
    }

    public Result(ResultCodeEnum resultCodeEnum, T data, String mes) {
        this.code = resultCodeEnum.getCode();
        this.mes = mes;
        this.data = data;
        this.dateTime = LocalDateTime.now();
    }

    public Result(String mes, String code, T data) {
        this.code = mes;
        this.mes = code;
        this.data = data;
        this.dateTime = LocalDateTime.now();
    }


    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCodeEnum.SUCCESS, data);
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultCodeEnum.SUCCESS, null);
    }


    public static <T> Result<T> error(ResultCodeEnum resultCodeEnum, T data) {
        return new Result<>(resultCodeEnum, data);
    }

    public static <T> Result<T> error(String code, String mes) {
        return new Result<>(code, mes, null);
    }

    public static <T> Result<T> error() {
        return new Result<>(ResultCodeEnum.ERROR, null);
    }

    public static <T> Result<T> error(ResultCodeEnum resultCodeEnum) {
        return error(resultCodeEnum,null);
    }


    public static <T> Result<T> error(String mes) {
        return new Result<>(ResultCodeEnum.ERROR, null, mes);
    }


    public Boolean isSuccess() {
        return this.code.equals(ResultCodeEnum.SUCCESS.getCode());
    }

}
