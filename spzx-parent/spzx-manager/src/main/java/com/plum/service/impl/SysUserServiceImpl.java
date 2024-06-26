package com.plum.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.plum.config.GuiguException;
import com.plum.mapper.SysUserMapper;
import com.plum.service.SysUserService;
import com.plum.spzx.model.dto.system.LoginDto;
import com.plum.spzx.model.entity.system.SysUser;
import com.plum.spzx.model.vo.common.ResultCodeEnum;
import com.plum.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: SysUserServiceImpl
 * Package: com.plum.service.impl
 * description
 * Author: Plum
 * Crete : 2024/5/7 23:06
 * Version 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private SysUserMapper sysUserMapper ;

    @Autowired
    private RedisTemplate<String , String> redisTemplate ;

    @Override
    public LoginVo login(LoginDto loginDto) {
        // 校验验证码是否正确
        String captcha = loginDto.getCaptcha();     // 用户输入的验证码
        String codeKey = loginDto.getCodeKey();     // redis中验证码的数据key
        // 从Redis中获取验证码
        String redisCode = redisTemplate.opsForValue().get("user:login:validatecode:" + codeKey);
        if(StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode , captcha)) {
            throw new GuiguException(ResultCodeEnum.VALIDATECODE_ERROR) ;
        }
        // 验证通过删除redis中的验证码
        redisTemplate.delete("user:login:validatecode:" + codeKey) ;
        // 根据用户名查询用户
        SysUser sysUser = sysUserMapper.selectByUserName(loginDto.getUserName());
        if(sysUser == null){
            throw new RuntimeException("用户名或者密码错误");
        }
        String inputPassword = loginDto.getPassword();
        String md5InputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());

        if(!md5InputPassword.equals(sysUser.getPassword())){
            throw new RuntimeException("用户名或者密码错误");
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user:login:"+token,JSON.toJSONString(sysUser),30,TimeUnit.MINUTES);
        LoginVo loginVo =new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");
        // 验证密码是否正确
        // 生成令牌，保存数据到Redis中
        // 构建响应结果对象

        return loginVo;
    }

    @Override
    public String getUserInfo(String token) {
        String userInfo = sysUserMapper.getUserInfo(token);
        return  userInfo;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login:" + token) ;
    }


}
