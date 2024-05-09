package com.plum.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plum.mapper.SysRoleMapper;
import com.plum.service.SysRoleService;
import com.plum.service.SysUserService;
import com.plum.spzx.model.dto.system.LoginDto;
import com.plum.spzx.model.dto.system.SysRoleDto;
import com.plum.spzx.model.entity.system.SysRole;
import com.plum.spzx.model.vo.system.LoginVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: SysRoleServiceImpl
 * Package: com.plum.service.impl
 * description
 * Author: Plum
 * Crete : 2024/5/9 22:58
 * Version 1.0
 */

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper ;
    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum , pageSize) ;
        List<SysRole> sysRoleList = sysRoleMapper.findByPage(sysRoleDto) ;
        PageInfo<SysRole> pageInfo = new PageInfo(sysRoleList) ;
        return pageInfo;
    }
}
