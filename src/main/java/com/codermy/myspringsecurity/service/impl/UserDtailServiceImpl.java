package com.codermy.myspringsecurity.service.impl;

import com.codermy.myspringsecurity.dao.PermissionDao;
import com.codermy.myspringsecurity.dto.JwtUser;
import com.codermy.myspringsecurity.eneity.TbPermission;
import com.codermy.myspringsecurity.eneity.TbUser;
import com.codermy.myspringsecurity.service.RoleService;
import com.codermy.myspringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author codermy
 * @createTime 2020/7/2
 */
@Service
public class UserDtailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionDao permissionDao;
    @Override
    public JwtUser loadUserByUsername(String userName) throws UsernameNotFoundException {
        TbUser user = userService.getUser(userName);
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        } else if (user.getStatus() == TbUser.Status.LOCKED) {
            throw new LockedException("用户被锁定,请联系管理员");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        List<TbPermission> permissions = permissionDao.listByUserId(user.getId().intValue());


        List<String> collect = permissions.stream().map(TbPermission::getPermission).collect(Collectors.toList());

        for (String authority : collect){
            if (authority != null){
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);
                grantedAuthorities.add(grantedAuthority);
            }
        }
        JwtUser loginUser = new JwtUser(user,grantedAuthorities);

        loginUser.setId(user.getId());
        loginUser.setNickName(user.getNickName());
        return loginUser;
    }
}
