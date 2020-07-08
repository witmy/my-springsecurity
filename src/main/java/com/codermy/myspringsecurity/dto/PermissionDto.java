package com.codermy.myspringsecurity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author codermy
 * @createTime 2020/6/28
 */
@Data
public class PermissionDto implements Serializable {
    private Integer id;
    private Integer parentId;
    private String checkArr = "0";
    private String title;

}
