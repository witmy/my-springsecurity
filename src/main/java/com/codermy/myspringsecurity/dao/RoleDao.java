package com.codermy.myspringsecurity.dao;

import com.codermy.myspringsecurity.dto.RoleDto;
import com.codermy.myspringsecurity.eneity.TbRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/6/26
 */
@Mapper
public interface RoleDao {

    @Select("select * from tb_role t")
    List<TbRole> getAllRoles();
    //计算所有
    @Select("select count(*) from tb_role t")
    Long countAllRoles();
    //分页查询权限
    @Select("select * from tb_role t limit #{startPosition}, #{limit}")
    List<TbRole> getAllRolesByPage(@Param("startPosition")Integer startPosition, @Param("limit")Integer limit);

    int saveRole(RoleDto roleDto);

    @Select("select * from tb_role t where t.id = #{id}")
    TbRole getById(Integer id);

    int update(RoleDto roleDto);

    @Delete("delete from tb_role where id = #{id}")
    int delete(Integer id);
}
