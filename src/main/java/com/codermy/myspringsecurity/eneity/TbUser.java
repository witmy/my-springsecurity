package com.codermy.myspringsecurity.eneity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author codermy
 * @createTime 2020/6/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="TbUser对象", description="用户表")
public class TbUser extends BaseEntity<Long> {
    private static final long serialVersionUID = -6525908145032868837L;
    private String userName;
    private String password;
    private String nickName;
    private String phone;
    private Integer status;
    public interface Status {
        int LOCKED = 0;
        int VALID = 1;
    }
}
