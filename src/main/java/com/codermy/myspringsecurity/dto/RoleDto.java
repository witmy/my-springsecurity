package com.codermy.myspringsecurity.dto;

import com.codermy.myspringsecurity.eneity.TbRole;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/7/1
 */
public class RoleDto extends TbRole {
    private static final long serialVersionUID = -5784234789156935003L;

    private List<Integer> permissionIds;

    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
