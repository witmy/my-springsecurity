package com.codermy.myspringsecurity.eneity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author codermy
 * @createTime 2020/6/26
 */
@Data
@ApiModel(value="TbPermission对象", description="菜单表")
public class TbPermission extends BaseEntity<Integer> {

    private static final long serialVersionUID = -6525908145032868837L;
    private Integer parentId;
    private String name;
    private Integer type;
    private String url;
    private String permission;
    private Integer sort;

    @Override
    public String toString() {
        return "TbPermission{" +
                "parentId=" + parentId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", permission='" + permission + '\'' +
                ", sort=" + sort +
                '}';
    }
}
