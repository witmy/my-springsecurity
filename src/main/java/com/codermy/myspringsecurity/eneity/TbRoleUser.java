package com.codermy.myspringsecurity.eneity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author codermy
 * @createTime 2020/6/26
 */
@Data
@ApiModel(value="TbRoleUser对象", description="用户角色关联表")
public class TbRoleUser {

    private Integer userId;
    private Integer roleId;
}
