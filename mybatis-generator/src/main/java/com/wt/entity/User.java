package com.wt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author shangzha
 * @since 2022-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "user_id", type = IdType.ID_WORKER_STR)
    private Long userId;

    @ApiModelProperty(value = "用户昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "真实姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty(value = "用户邮箱")
    @TableField("user_mail")
    private String userMail;

    @ApiModelProperty(value = "登录密码")
    @TableField("login_password")
    private String loginPassword;

    @ApiModelProperty(value = "支付密码")
    @TableField("pay_password")
    private String payPassword;

    @ApiModelProperty(value = "手机号码")
    @TableField("user_mobile")
    private String userMobile;

    @ApiModelProperty(value = "修改时间")
    @TableField("modify_time")
    private Date modifyTime;

    @ApiModelProperty(value = "注册时间")
    @TableField("user_regtime")
    private Date userRegtime;

    @ApiModelProperty(value = "注册IP")
    @TableField("user_regip")
    private String userRegip;

    @ApiModelProperty(value = "最后登录时间")
    @TableField("user_lasttime")
    private Date userLasttime;

    @ApiModelProperty(value = "最后登录IP")
    @TableField("user_lastip")
    private String userLastip;

    @ApiModelProperty(value = "备注")
    @TableField("user_memo")
    private String userMemo;

    @ApiModelProperty(value = "M(男) or F(女)")
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "例如：2009-11-27")
    @TableField("birth_date")
    private String birthDate;

    @ApiModelProperty(value = "头像图片路径")
    @TableField("pic")
    private String pic;

    @ApiModelProperty(value = "状态 1 正常 0 无效")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "用户积分")
    @TableField("score")
    private Integer score;


}
