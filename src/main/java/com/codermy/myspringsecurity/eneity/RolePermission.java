package com.codermy.myspringsecurity.eneity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author codermy
 * @createTime 2020/6/30
 */
@Data
@ApiModel(value="RolePermission对象", description="角色菜单关联表")
public class RolePermission {
    private Integer roleId;
    private Integer permissionId;
}
