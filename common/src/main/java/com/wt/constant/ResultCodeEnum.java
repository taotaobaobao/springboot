package com.wt.constant;

/**
 * 返回错误的code
 */
public enum ResultCodeEnum {
    SUCCESS("200","SUCCESS"),
    ERROR("500","ERROR"),;
    private String code;

    private String mes;



    ResultCodeEnum(String code, String mes){
        this.code=code;
        this.mes=mes;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
