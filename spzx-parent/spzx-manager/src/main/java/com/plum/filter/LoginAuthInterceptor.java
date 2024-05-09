package com.plum.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.util.StringUtil;
import com.plum.config.AuthContextUtil;
import com.plum.spzx.model.entity.system.SysUser;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;


/**
 * ClassName: LoginAuthInterceptor
 * Package: com.plum.filter
 * description
 * Author: Plum
 * Crete : 2024/5/8 22:31
 * Version 1.0
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }
        String token = request.getHeader("token");
        if (StringUtil.isEmpty(token)) {
            responseNologInfo(response);
            return false;
        }

        // 如果token不为空，那么此时验证token的合法性
        String sysUserInfoJson = redisTemplate.opsForValue().get("user:login:" + token);
        if(StrUtil.isEmpty(sysUserInfoJson)) {
            responseNologInfo(response) ;
            return false ;
        }
        // 将用户数据存储到ThreadLocal中
        SysUser sysUser = JSON.parseObject(sysUserInfoJson, SysUser.class);
        AuthContextUtil.set(sysUser);


        // 重置Redis中的用户数据的有效时间
        redisTemplate.expire("user:login:" + token , 30 , TimeUnit.MINUTES) ;

        // 放行
        return true ;
    }

    private void responseNologInfo(HttpServletResponse response) {
    }
}
