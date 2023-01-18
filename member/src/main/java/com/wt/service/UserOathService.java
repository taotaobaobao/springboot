package com.wt.service;

import com.wt.constant.Result;
import com.wt.result.UserAuthResult;


public interface UserOathService {

    /**
     * 根据用户名、手机号、邮箱查询
     *
     * @param userNameOrPhoneOrMail
     * @return
     */
    Result<UserAuthResult> findByUserNameOrPhoneOrMail(String userNameOrPhoneOrMail);

    /**
     * 根据手机验证码注册登陆
     * @param userPhone
     * @return
     */
    Result<UserAuthResult> findByUserPhone(String userPhone);
}
