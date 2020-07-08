package com.codermy.myspringsecurity.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/6/30
 */
@Mapper
public interface RolePermissionDao {
    @Delete("delete from tb_role_permission where permission_id = #{permissionId}")
    int delete(RolePermissionDao rolePermission);

    int save(@Param("roleId")Integer id, @Param("permissionIds") List<Integer> permissionIds);

    @Delete("delete from tb_role_permission where role_id = #{roleId}")
    int deleteRolePermission(Integer roleId);
}
