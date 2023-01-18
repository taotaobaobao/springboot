package com.wt.controller;

import com.wt.constant.Result;
import com.wt.result.UserAuthResult;
import com.wt.service.UserOathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户controller
 */
@RestController
@RequestMapping("/user")
public class UserOathController {


    @Autowired
    private UserOathService userOathService;


    /**
     * 根据用户名、手机号、邮箱查询
     *
     * @param userNameOrPhoneOrMail
     * @return
     */
    @GetMapping("/userNameOrPhoneOrMail")
    public Result<UserAuthResult> findByUserNameOrPhoneOrMail(@RequestParam("userNameOrPhoneOrMail") String userNameOrPhoneOrMail) {
        return userOathService.findByUserNameOrPhoneOrMail(userNameOrPhoneOrMail);
    }

    /**
     * 手机短信验证码模式
     * @param userPhone
     * @return
     */
    @GetMapping("/userPhone")
    public Result<UserAuthResult> findByUserPhone(@RequestParam("userPhone") String userPhone) {
        return userOathService.findByUserPhone(userPhone);
    }

}
