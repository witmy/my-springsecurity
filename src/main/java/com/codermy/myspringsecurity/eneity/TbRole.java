package com.codermy.myspringsecurity.eneity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author codermy
 * @createTime 2020/6/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="TbRole对象", description="角色表")
public class TbRole extends BaseEntity<Integer> {
    private static final long serialVersionUID = -6525908145032868837L;

    private String name;
    private String description;

    @Override
    public String toString() {
        return "TbRole{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
