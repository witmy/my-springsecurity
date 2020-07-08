package com.codermy.myspringsecurity.controller;

import com.codermy.myspringsecurity.service.RoleUserService;
import com.codermy.myspringsecurity.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author codermy
 * @createTime 2020/7/1
 */
@Controller
@RequestMapping("/system/roleuser")
@Api(tags = "用户角色相关关接口")
public class RoleUserController {
    @Autowired
    private RoleUserService roleUserService;

    @PostMapping("/index")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('role:list','user:list')")
    @ApiOperation(value = "通过用户id返回角色")
    public Result getRoleUserByUserId(Integer userId) {
        return roleUserService.getTbRoleUserByUserId(userId);
    }
}
