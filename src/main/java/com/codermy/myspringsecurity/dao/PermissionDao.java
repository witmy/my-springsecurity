package com.codermy.myspringsecurity.dao;

import com.codermy.myspringsecurity.dto.PermissionDto;
import com.codermy.myspringsecurity.eneity.TbPermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/6/26
 */
@Mapper
public interface PermissionDao {
    @Select("select * from tb_permission t")
    List<TbPermission> findAll();

    @Select("select t.id,t.parent_id,t.name from tb_permission t")
    @Result(property = "title",column = "name")
    List<PermissionDto> buildAll();

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into tb_permission(parent_id, name, url, permission, sort, type, create_time, update_time)values(#{parentId}, #{name}, #{url},#{type},#{permission}, #{sort}, now(), now())")
    int save(TbPermission e);

    @Select("select * from tb_permission t where t.id = #{id}")
    TbPermission getTbPermissionById(Integer id);

    int update(TbPermission e);

    @Delete("delete from tb_permission where id = #{id}")
    int deleteById(Integer id);

    @Delete("delete from tb_permission where parent_id = #{parentId}")
    int deleteByParentId(Integer parentId);

    @Select("select p.id,p.parent_id,p.name from tb_permission p inner join tb_role_permission rp on p.id = rp.permission_id where rp.role_id = #{roleId}")
    @Result(property = "title",column = "name")
    List<PermissionDto> listByRoleId(Integer roleId);

    @Select("SELECT DISTINCT sp.*  " +
            "FROM tb_role_user sru " +
            "INNER JOIN tb_role_permission srp ON srp.role_id = sru.role_id " +
            "LEFT JOIN tb_permission sp ON srp.permission_id = sp.id " +
            "WHERE " +
            "sru.user_id = #{userId}")
    List<TbPermission> listByUserId(@Param("userId") Integer userId);
}
