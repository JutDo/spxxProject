package com.plum;

import com.plum.spzx.model.entity.system.SysUser;

/**
 * ClassName: Text
 * Package: com.plum
 * description
 * Author: Plum
 * Crete : 2024/5/8 22:18
 * Version 1.0
 */
public class Text {
    private static final ThreadLocal<SysUser> theadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        sysUser.setUserName("hahha");
        sysUser.setAvatar("baidu");
        theadLocal.set(sysUser);
        show();


    }

    private static void show() {
        SysUser sysUser = theadLocal.get();
        System.out.println(sysUser);
    }
}
