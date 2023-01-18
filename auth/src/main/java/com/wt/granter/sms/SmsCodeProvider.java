package com.wt.granter.sms;

import com.wt.constant.RedisConstant;
import com.wt.service.UserServiceImpl;
import com.wt.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;

/**
 * 手机短信验证登录逻辑
 */
@Slf4j
public class SmsCodeProvider implements AuthenticationProvider {


    private UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeToken smsCodeToken = (SmsCodeToken) authentication;
        String phone = (String) smsCodeToken.getPrincipal();
        String code = (String) smsCodeToken.getCredentials();
        String smsCode = RedisConstant.SMS_CODE_PREFIX + phone;
        String redisCode = RedisUtil.get(smsCode);
        log.info("当前手机号:【{}】,手机收到的验证码:【{}】",smsCode,redisCode);
        if (StringUtils.isBlank(redisCode) || !code.equals(redisCode)) {
            throw new InternalAuthenticationServiceException("验证码错误");
        }
        UserServiceImpl userService = (UserServiceImpl) userDetailsService;
        UserDetails userDetails = userService.loadUserByPhone(phone);
        SmsCodeToken result = new SmsCodeToken(userDetails,authentication.getCredentials(),new HashSet<>());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeToken.class.isAssignableFrom(authentication);
    }


    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
