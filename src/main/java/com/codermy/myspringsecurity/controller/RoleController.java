package com.codermy.myspringsecurity.controller;

import com.codermy.myspringsecurity.dto.RoleDto;
import com.codermy.myspringsecurity.eneity.TbPermission;
import com.codermy.myspringsecurity.eneity.TbRole;
import com.codermy.myspringsecurity.service.RoleService;
import com.codermy.myspringsecurity.utils.PageTableRequest;
import com.codermy.myspringsecurity.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author codermy
 * @createTime 2020/6/26
 */
@Controller
@RequestMapping("/system/role")
@Api(tags = "角色相关接口")
public class RoleController {
    @Autowired
    private RoleService roleService;


    @GetMapping("/index")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('role:list')")
    @ApiOperation(value = "分页返回角色列表")
    public Result list(PageTableRequest request) {
        request.countOffset();
        return roleService.getAllRolesByPage(request.getOffset(), request.getLimit());
    }

    @GetMapping("/all")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('role:list')")
    @ApiOperation(value = "角色列表")
    public Result<TbRole> getAll(){
        return roleService.getAllRoles();
    }

    @GetMapping(value = "/add")
    @PreAuthorize("hasAnyAuthority('role:add')")
    @ApiOperation(value = "添加角色页面")
    public String addRole(Model model) {
        model.addAttribute("tbRole",new TbRole());
        return "/system/role/role-add";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('role:add')")
    @ApiOperation(value = "添加角色")
    public Result saveRole(@RequestBody RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @GetMapping(value = "/edit")
    @PreAuthorize("hasAnyAuthority('role:edit')")
    @ApiOperation(value = "修改角色页面")
    public String editRole(Model model, TbRole role) {
        model.addAttribute("tbRole",roleService.getRoleById(role.getId()));
        return "/system/role/role-edit";
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('role:edit')")
    @ApiOperation(value = "修改角色")
    public Result updateRole(@RequestBody RoleDto roleDto) {
        return roleService.update(roleDto);
    }

    @GetMapping(value = "/delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('role:del')")
    @ApiOperation(value = "删除角色")
    public Result<TbRole> deleteRole(RoleDto roleDto) {
        return roleService.delete(roleDto.getId());
    }
}
