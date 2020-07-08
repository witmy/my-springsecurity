package com.codermy.myspringsecurity.dao;


import com.codermy.myspringsecurity.eneity.TbUser;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/6/25
 */
@Mapper
public interface UserDao {
    //模糊查询用户
    @Select("select count(*) from tb_user t where t.user_name like concat('%',#{userName},'%')")
    Long getUserByFuzzyUsername(@Param("userName") String userName);
    //分页模糊查询
    @Select("select * from tb_user t where t.user_name like concat('%',#{userName},'%') limit #{startPosition},#{limit}")
    List<TbUser>  getUserByFuzzyUsernameByPage(@Param("userName")String userName, @Param("startPosition") Integer offset,@Param("limit") Integer limit);

    //插入用户
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into tb_user(user_name, password, nick_name, phone, status, create_time, update_time) values(#{userName}, #{password}, #{nickName}, #{phone}, #{status}, now(), now())")
    int save(TbUser user);
    //分页返回所有用户
    @Select("SELECT * FROM tb_user t ORDER BY t.id LIMIT #{startPosition}, #{limit}")
    List<TbUser> getAllUserByPage(@Param("startPosition")Integer startPosition,@Param("limit")Integer limit);
    //计算所有用户数量
    @Select("select count(*) from tb_user")
    Long countAllUser();
    //跟新用户
    int updateUser(TbUser tbUser);

    @Select("select * from tb_user t where t.id = #{id}")
    TbUser getUserById(Long id);

    @Select("select * from tb_user t where t.phone = #{phone}")
    TbUser getUserByPhone(String phone);

    @Delete("delete from tb_user where id = #{userId}")
    int deleteUser(int userId);

    //根据用户名查询用户
    @Select("select * from tb_user t where t.user_name = #{userName}")
    TbUser getUser(String userName);
}
