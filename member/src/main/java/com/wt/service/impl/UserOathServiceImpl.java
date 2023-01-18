package com.wt.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.wt.constant.Result;
import com.wt.entity.User;
import com.wt.result.UserAuthResult;
import com.wt.service.IUserService;
import com.wt.service.UserOathService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class UserOathServiceImpl implements UserOathService {


    @Autowired
    private IUserService iUserService;

    @Override
    public Result<UserAuthResult> findByUserNameOrPhoneOrMail(String userNameOrPhoneOrMail) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getNickName,userNameOrPhoneOrMail);
        userLambdaQueryWrapper.or().eq(User::getUserMail,userNameOrPhoneOrMail);
        userLambdaQueryWrapper.or().eq(User::getUserMobile,userNameOrPhoneOrMail);
        User user = iUserService.getOne(userLambdaQueryWrapper);
        if(user==null){
            return Result.success(new UserAuthResult());
        }
        return Result.success(this.userAuthResult(user));
    }

    @Override
    public Result<UserAuthResult> findByUserPhone(String userPhone) {
        User user = iUserService.getOne(new LambdaUpdateWrapper<User>().eq(User::getUserMobile, userPhone));
        if(user==null){
            user = new User();
            user.setUserId(IdWorker.getId());
            user.setNickName(RandomUtil.randomString(8));
            user.setUserMobile(userPhone);
            user.setUserRegtime(DateUtil.date());
            ArrayList<String> urls = Lists.newArrayList();
            //百度上随便找的图片
            urls.add("https://img1.baidu.com/it/u=453253244,3693084626&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500");
            urls.add("https://img.bugela.com/uploads/2021/04/26/TX9490_04.jpg");
            urls.add("https://img1.baidu.com/it/u=2193882417,1779081877&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200");
            user.setPic(RandomUtil.randomEle(urls));
            iUserService.save(user);
        }
        return Result.success(this.userAuthResult(user));
    }


    private UserAuthResult userAuthResult(User user){
        UserAuthResult userAuthVo = new UserAuthResult();
        userAuthVo.setId(user.getUserId());
        userAuthVo.setStatus(user.getStatus());
        userAuthVo.setUsername(user.getNickName());
        userAuthVo.setPassword(user.getLoginPassword());
        userAuthVo.setRoles(Collections.singletonList("admin"));
        return userAuthVo;
    }
}
