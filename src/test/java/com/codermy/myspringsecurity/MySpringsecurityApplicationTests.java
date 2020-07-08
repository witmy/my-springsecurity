package com.codermy.myspringsecurity;

import com.codermy.myspringsecurity.dao.PermissionDao;
import com.codermy.myspringsecurity.dto.PermissionDto;
import com.codermy.myspringsecurity.utils.TreeUtil;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class MySpringsecurityApplicationTests {
    @Autowired
    private PermissionDao permissionDao;
    @Test
    void contextLoads() {
        List<PermissionDto> listByRoleId = permissionDao.listByRoleId(2);
        List<PermissionDto> permissionDtos = permissionDao.buildAll();
        List<PermissionDto> tree = TreeUtil.tree(listByRoleId, permissionDtos);
        System.out.println(tree);
    }

}
