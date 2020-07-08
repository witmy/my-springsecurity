package com.codermy.myspringsecurity.service;

import com.codermy.myspringsecurity.utils.Result;

/**
 * @author codermy
 * @createTime 2020/7/1
 */
public interface RoleUserService {
    Result getTbRoleUserByUserId(Integer userId);
}
