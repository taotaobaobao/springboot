package com.wt.constant;

/**
 * security返回信息
 */
public enum ResultCodeAuthEnum {
    USERNAME_PASSWORD_ERROR("40001","用户名或者密码错误"),
    ACCOUNT_DISABLED("40002","帐户已禁用"),

            ;
    private String code;

    private String mes;



    ResultCodeAuthEnum(String code, String mes){
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
