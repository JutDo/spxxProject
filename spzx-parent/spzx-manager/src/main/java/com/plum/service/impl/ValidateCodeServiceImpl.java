package com.plum.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.plum.service.ValidateCodeService;
import com.plum.spzx.model.vo.system.ValidateCodeVo;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.DrbgParameters;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: ValidateCodeServiceImpl
 * Package: com.plum.service.impl
 * description
 * Author: Plum
 * Crete : 2024/5/8 21:09
 * Version 1.0
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Resource
    private RedisTemplate<String , String> redisTemplate;
    @Override
    public ValidateCodeVo generateValidateCode() {

        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 20);
        String code = circleCaptcha.getCode();
        String imageBase64 = circleCaptcha.getImageBase64();
        String codeKey = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user:login:validatecode:" + codeKey , code , 5 , TimeUnit.MINUTES);
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(codeKey);
        validateCodeVo.setCodeValue("data:image/png;base64"+ imageBase64);

        // 返回数据
        return validateCodeVo;
    }
}
