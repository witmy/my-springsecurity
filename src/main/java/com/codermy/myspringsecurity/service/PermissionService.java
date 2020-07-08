package com.codermy.myspringsecurity.service;

import com.codermy.myspringsecurity.dto.PermissionDto;
import com.codermy.myspringsecurity.eneity.TbPermission;
import com.codermy.myspringsecurity.utils.Result;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/6/26
 */
public interface PermissionService {

    Result<TbPermission> getMenuAll();

    List<PermissionDto> buildMenuAll();

    Result<TbPermission> save(TbPermission permission);

    TbPermission getTbPermissionById(Integer id);

    Result updateTbPermission(TbPermission permission);

    Result delete(Integer id);

    List<PermissionDto> buildMenuAllByRoleId(Integer roleId);

    Result<TbPermission> getMenu(Integer userId);
}
