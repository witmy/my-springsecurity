package com.codermy.myspringsecurity.dao;

import com.codermy.myspringsecurity.eneity.TbRoleUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/6/26
 */
@Mapper
public interface RoleUserDao {
    //插入
    @Insert("insert into tb_role_user(user_id, role_id) values(#{userId}, #{roleId})")
    int save(TbRoleUser tbRoleUser);
    //通过用户id查询权限id
    @Select("select * from tb_role_user t where t.user_id = #{userId}")
    TbRoleUser getRoleUserByUserId(Integer userId);
    //更新权限
    int updateTbRoleUser(TbRoleUser tbRoleUser);

    @Select("select * from tb_role_user t where t.user_id = #{userId}")
    List<TbRoleUser> getTbRoleUserByUserId(Integer userId);

    @Delete("delete from tb_role_user where user_id = #{userId}")
    int deleteRoleUserByUserId(Integer userId);

    @Select("select * from tb_role_user t where t.role_id = #{roleId}")
    List<TbRoleUser> listAllRoleUserByRoleId(Integer roleId);



}
