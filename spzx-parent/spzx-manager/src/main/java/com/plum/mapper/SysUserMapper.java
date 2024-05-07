package com.plum.mapper;

import com.plum.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: SysUserMapper
 * Package: com.plum.mapper
 * description
 * Author: Plum
 * Crete : 2024/5/7 23:25
 * Version 1.0
 */

public interface SysUserMapper {

    SysUser selectByUserName(String name);
}
