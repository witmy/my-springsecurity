package com.codermy.myspringsecurity.service.impl;

import com.codermy.myspringsecurity.dao.RoleUserDao;
import com.codermy.myspringsecurity.dao.UserDao;
import com.codermy.myspringsecurity.eneity.TbRoleUser;
import com.codermy.myspringsecurity.eneity.TbUser;
import com.codermy.myspringsecurity.service.UserService;
import com.codermy.myspringsecurity.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author codermy
 * @createTime 2020/6/25
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public TbUser getUser(String userName) {
        return userDao.getUser(userName);
    }

    @Override
    public Result<TbUser> getUserByFuzzyUsername(String username, Integer offset, Integer limit) {
        return Result.ok().count(userDao.getUserByFuzzyUsername(username).intValue()).data(userDao.getUserByFuzzyUsernameByPage(username,offset,limit));
    }

    @Override
    public Result<TbUser> getAllUsersByPage(Integer startPosition, Integer limit) {

        return Result.ok().count(userDao.countAllUser().intValue()).data(userDao.getAllUserByPage(startPosition,limit));
    }

    @Override
    public Result<TbUser> save(TbUser user, Integer roleId) {
        if(roleId!= null){
            userDao.save(user);
            TbRoleUser tbRoleUser = new TbRoleUser();
            tbRoleUser.setRoleId(roleId);
            tbRoleUser.setUserId(user.getId().intValue());
            roleUserDao.save(tbRoleUser);
            return Result.ok();
        }
        return Result.error();
    }

    @Override
    public Result<TbUser> updateUser(TbUser tbUser, Integer roleId) {
        if (roleId!=null){
            userDao.updateUser(tbUser);
            TbRoleUser tbRoleUser = new TbRoleUser();
            tbRoleUser.setUserId(tbUser.getId().intValue());
            tbRoleUser.setRoleId(roleId);
            if(roleUserDao.getRoleUserByUserId(tbUser.getId().intValue())!=null){
                roleUserDao.updateTbRoleUser(tbRoleUser);
            }else {
                roleUserDao.save(tbRoleUser);
            }
            return Result.ok().message("成功");
        }else {
            return Result.error().message("失败");
        }
    }

    @Override
    public TbUser getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public TbUser getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

    @Override
    public int deleteUser(Long id) {
        roleUserDao.deleteRoleUserByUserId(id.intValue());
        return userDao.deleteUser(id.intValue());
    }
}
