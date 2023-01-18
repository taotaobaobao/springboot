package com.wt.feign.member.client;

import com.wt.constant.Result;
import com.wt.feign.member.result.UserAuthResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "member-server")
public interface FeignMemberClient {

    /**
     * 根据用户名搜索
     * @param userNameOrPhoneOrMail 用户名可以选择:手机号、用户名、邮箱
     * @return
     */
    @GetMapping("/user/findByUserNameOrPhoneOrMail")
    Result<UserAuthResult> findByUserNameOrPhoneOrMail(@RequestParam("userNameOrPhoneOrMail") String userNameOrPhoneOrMail);


}
