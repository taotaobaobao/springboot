package com.wt.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 角色
     */
    private List<String> roles;

}
