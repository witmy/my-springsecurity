package com.codermy.myspringsecurity.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.codermy.myspringsecurity.dao.PermissionDao;
import com.codermy.myspringsecurity.dao.RolePermissionDao;
import com.codermy.myspringsecurity.dto.PermissionDto;
import com.codermy.myspringsecurity.eneity.TbPermission;
import com.codermy.myspringsecurity.service.PermissionService;
import com.codermy.myspringsecurity.utils.Result;
import com.codermy.myspringsecurity.utils.TreeUtil;
import com.codermy.myspringsecurity.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author codermy
 * @createTime 2020/6/26
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public Result<TbPermission> getMenuAll() {
        return Result.ok().data(permissionDao.findAll());
    }

    @Override
    public List<PermissionDto> buildMenuAll() {
        return permissionDao.buildAll();
    }

    @Override
    public Result<TbPermission> save(TbPermission permission) {
        return (permissionDao.save(permission) > 0) ? Result.ok().message("请求成功") : Result.error().message("请求失败");
    }

    @Override
    public TbPermission getTbPermissionById(Integer id) {
            return permissionDao.getTbPermissionById(id);
    }

    @Override
    public Result updateTbPermission(TbPermission permission) {
        return (permissionDao.update(permission) > 0) ? Result.ok().message("请求成功") : Result.error().message("请求失败");
    }

    @Override
    public Result delete(Integer id) {
        permissionDao.deleteById(id);
        permissionDao.deleteByParentId(id);
        return Result.ok();
    }

    @Override
    public List<PermissionDto> buildMenuAllByRoleId(Integer roleId) {
        List<PermissionDto> listByRoleId = permissionDao.listByRoleId(roleId);
        List<PermissionDto> permissionDtos = permissionDao.buildAll();
        List<PermissionDto> tree = TreeUtil.tree(listByRoleId, permissionDtos);
        return tree;
    }

    @Override
    public Result<TbPermission> getMenu(Integer userId) {
        List<TbPermission> datas = permissionDao.listByUserId(userId);
        datas = datas.stream().filter(p -> p.getType().equals(1)).collect(Collectors.toList());
        JSONArray array = new JSONArray();
        TreeUtils.setPermissionsTree(0, datas, array);
        return Result.ok().data(array);
    }


}
