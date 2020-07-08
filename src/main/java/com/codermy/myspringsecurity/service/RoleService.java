package com.codermy.myspringsecurity.service;

import com.codermy.myspringsecurity.dto.RoleDto;
import com.codermy.myspringsecurity.eneity.TbRole;
import com.codermy.myspringsecurity.eneity.TbUser;
import com.codermy.myspringsecurity.utils.Result;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/6/26
 */
public interface RoleService {

    Result<TbRole> getAllRolesByPage(Integer startPosition, Integer limit);

    Result<TbRole> getAllRoles();

    Result save(RoleDto roleDto);

    TbRole getRoleById(Integer id);

    Result update(RoleDto roleDto);

    Result<TbRole> delete(Integer id);

}
