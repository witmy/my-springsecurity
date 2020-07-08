package com.codermy.myspringsecurity.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codermy.myspringsecurity.eneity.TbPermission;

import java.util.List;

public class TreeUtils {

    /**
     * 菜单树
     *
     * @param parentId
     * @param permissionsAll
     * @param array
     */
    public static void setPermissionsTree(Integer parentId, List<TbPermission> permissionsAll, JSONArray array) {
        for (TbPermission per : permissionsAll) {
            if (per.getParentId().equals(parentId)) {
                String string = JSONObject.toJSONString(per);
                JSONObject parent = (JSONObject) JSONObject.parse(string);
                array.add(parent);
                if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
                    JSONArray child = new JSONArray();
                    parent.put("child", child);
                    setPermissionsTree(per.getId(), permissionsAll, child);
                }
            }
        }
    }
}
