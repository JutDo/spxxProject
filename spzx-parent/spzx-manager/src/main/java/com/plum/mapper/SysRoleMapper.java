package com.plum.mapper;

import com.plum.spzx.model.dto.system.SysRoleDto;
import com.plum.spzx.model.entity.system.SysRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: SysRoleMapper
 * Package: com.plum.mapper
 * description
 * Author: Plum
 * Crete : 2024/5/9 23:04
 * Version 1.0
 */
@Transactional(rollbackFor = Exception.class)
public interface SysRoleMapper {
    List<SysRole> findByPage(SysRoleDto sysRoleDto);
}
