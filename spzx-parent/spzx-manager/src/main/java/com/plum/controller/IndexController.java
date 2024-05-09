package com.plum.controller;

import com.alibaba.fastjson.JSON;
import com.plum.config.AuthContextUtil;
import com.plum.service.SysUserService;
import com.plum.service.ValidateCodeService;
import com.plum.spzx.model.dto.system.LoginDto;
import com.plum.spzx.model.entity.system.SysUser;
import com.plum.spzx.model.vo.common.Result;
import com.plum.spzx.model.vo.common.ResultCodeEnum;
import com.plum.spzx.model.vo.system.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: IndexController
 * Package: com.plum.controller
 * description
 * Author: Plum
 * Crete : 2024/5/7 23:03
 * Version 1.0
 */


@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private ValidateCodeService validateCodeService;

    @Operation(summary = "登录接口")
    @PostMapping(value = "/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto) ;
        return Result.build(loginVo , ResultCodeEnum.SUCCESS) ;
    }
    @GetMapping(value = "/getUserInfo")
    public Result<SysUser> getUserInfo(@RequestHeader(name = "token") String token) {
        String sysUser = sysUserService.getUserInfo(token) ;
        return Result.build(sysUser , ResultCodeEnum.SUCCESS) ;
    }


    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(value = "token") String token) {
        sysUserService.logout(token) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @GetMapping(value = "/text")
    public Result<LoginVo> login() {

        return Result.build("nihaop",ResultCodeEnum.SUCCESS);
    }
    @GetMapping(value = "getUserInfo")
    public Result<SysUser> getUserInfo() {
        return Result.build(AuthContextUtil.get(), ResultCodeEnum.SUCCESS);
    }
}
