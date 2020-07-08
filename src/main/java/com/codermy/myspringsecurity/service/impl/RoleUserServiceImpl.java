package com.codermy.myspringsecurity.service.impl;

import com.codermy.myspringsecurity.dao.RoleUserDao;
import com.codermy.myspringsecurity.eneity.TbRoleUser;
import com.codermy.myspringsecurity.service.RoleUserService;
import com.codermy.myspringsecurity.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/7/1
 */
@Service
public class RoleUserServiceImpl implements RoleUserService {
    @Autowired
    private RoleUserDao roleUserDao;
    @Override
    public Result getTbRoleUserByUserId(Integer userId) {
        List <TbRoleUser> tbRoleUser = roleUserDao.getTbRoleUserByUserId(userId);
        if(tbRoleUser != null){
            return Result.ok().data(tbRoleUser);
        }else{
            return Result.error();
        }
    }
}
