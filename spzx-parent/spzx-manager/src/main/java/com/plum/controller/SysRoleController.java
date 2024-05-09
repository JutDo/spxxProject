package com.plum.controller;

import com.github.pagehelper.PageInfo;
import com.plum.service.SysRoleService;
import com.plum.spzx.model.dto.system.SysRoleDto;
import com.plum.spzx.model.entity.system.SysRole;
import com.plum.spzx.model.vo.common.Result;
import com.plum.spzx.model.vo.common.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: SysRoleController
 * Package: com.plum.controller
 * description
 * Author: Plum
 * Crete : 2024/5/9 22:55
 * Version 1.0
 */
@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysRole>> findByPage(@RequestBody SysRoleDto sysRoleDto,
                                                @PathVariable(value = "pageNum") Integer pageNum,
                                                @PathVariable(value = "pageSize") Integer pageSize) {
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto, pageNum, pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);

    }

}
