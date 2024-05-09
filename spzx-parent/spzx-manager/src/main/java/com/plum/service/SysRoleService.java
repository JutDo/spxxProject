package com.plum.service;

import com.github.pagehelper.PageInfo;
import com.plum.spzx.model.dto.system.SysRoleDto;
import com.plum.spzx.model.entity.system.SysRole;

/**
 * ClassName: SysRoleService
 * Package: com.plum.service
 * description
 * Author: Plum
 * Crete : 2024/5/9 22:57
 * Version 1.0
 */
public interface SysRoleService {
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize);
}
