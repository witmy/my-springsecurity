package com.codermy.myspringsecurity;

import com.codermy.myspringsecurity.dao.PermissionDao;
import com.codermy.myspringsecurity.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author codermy
 * @createTime 2020/6/26
 */
public class md5 {
    public static void main(String[] args) {

        String ad = "444444";
        System.out.println(MD5.crypt(ad));
        System.out.println(new BCryptPasswordEncoder().encode(ad));

    }
}
