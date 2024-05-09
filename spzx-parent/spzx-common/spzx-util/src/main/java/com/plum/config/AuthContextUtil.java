package com.plum.config;

import com.plum.spzx.model.entity.system.SysUser;

/**
 * ClassName: AuthContextUtil
 * Package: com.plum.config
 * description
 * Author: Plum
 * Crete : 2024/5/8 22:30
 * Version 1.0
 */
public class AuthContextUtil {
    // 创建一个ThreadLocal对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>() ;

    // 定义存储数据的静态方法
    public static void set(SysUser sysUser) {
        threadLocal.set(sysUser);
    }

    // 定义获取数据的方法
    public static SysUser get() {
        return threadLocal.get() ;
    }

    // 删除数据的方法
    public static void remove() {
        threadLocal.remove();
    }
}
