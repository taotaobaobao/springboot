package com.wt.service;

import com.wt.constant.Result;
import com.wt.constant.ResultCodeAuthEnum;
import com.wt.dto.UserDto;
import com.wt.feign.member.client.FeignMemberClient;
import com.wt.feign.member.result.UserAuthResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 根据数据库查询用户信息
 */
@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private FeignMemberClient feignMemberClient;


    /**
     * 用户密码模式
     * @param userNameOrPhoneOrMail 用户名可以选择:手机号、用户名、邮箱
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userNameOrPhoneOrMail) throws UsernameNotFoundException {
        Result<UserAuthResult> userAuthResultResult = feignMemberClient.findByUserNameOrPhoneOrMail(userNameOrPhoneOrMail);
        if (!userAuthResultResult.isSuccess()) {//直接返回null
            return null;
        }
        UserAuthResult userAuthResultResultData = userAuthResultResult.getData();
        if (userAuthResultResultData.getId() == null) {
            throw new UsernameNotFoundException(ResultCodeAuthEnum.USERNAME_PASSWORD_ERROR.getMes());
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userAuthResultResultData, userDto);
        SecurityUser securityUser = new SecurityUser(userDto);
        if (!securityUser.getEnabled()) {
            throw new DisabledException(ResultCodeAuthEnum.ACCOUNT_DISABLED.getMes());
        }
        return securityUser;
    }

    /**
     * 手机短信验证码模式
     * @param phone 电话号码
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByPhone(String phone) throws UsernameNotFoundException {

        return null;
    }
}

