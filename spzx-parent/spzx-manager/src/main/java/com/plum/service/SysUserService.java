package com.plum.service;

import com.plum.spzx.model.dto.system.LoginDto;
import com.plum.spzx.model.entity.system.SysUser;
import com.plum.spzx.model.vo.system.LoginVo;

/**
 * ClassName: SysUserService
 * Package: com.plum.controller
 * description
 * Author: Plum
 * Crete : 2024/5/7 23:05
 * Version 1.0
 */
public interface SysUserService {
    public abstract LoginVo login(LoginDto loginDto) ;
}
