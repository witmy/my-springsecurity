/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : my-spring-security

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 08/07/2020 08:22:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` int(11) NOT NULL COMMENT '父菜单id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `type` tinyint(1) NOT NULL COMMENT '类型',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '跟新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES (1, 0, '系统管理', '', NULL, 1, 1, '2020-06-24 22:08:12', '2020-06-24 22:08:14');
INSERT INTO `tb_permission` VALUES (2, 1, '用户管理', '/api/getPage?pageName=system/user/user-list', 'user:list', 2, 1, '2020-06-24 22:08:19', '2020-06-24 22:08:21');
INSERT INTO `tb_permission` VALUES (3, 1, '角色管理', '/api/getPage?pageName=system/role/role-list', 'role:list', 3, 1, '2020-06-24 22:09:05', '2020-06-24 22:09:10');
INSERT INTO `tb_permission` VALUES (4, 1, '菜单管理', '/api/getPage?pageName=system/permission/permission-list', 'menu:list', 4, 1, '2020-06-24 22:12:04', '2020-06-24 22:12:07');
INSERT INTO `tb_permission` VALUES (5, 0, '其他管理', NULL, NULL, 5, 1, '2020-06-24 22:13:54', '2020-06-24 22:13:56');
INSERT INTO `tb_permission` VALUES (6, 5, 'SQL监控', 'http://localhost:8080/druid/login.html', NULL, 6, 1, '2020-06-24 22:14:24', '2020-06-24 22:14:27');
INSERT INTO `tb_permission` VALUES (7, 5, 'Swagger文档', 'http://localhost:8080/swagger-ui.html', NULL, 7, 1, '2020-06-24 22:15:58', '2020-06-24 22:16:00');
INSERT INTO `tb_permission` VALUES (8, 2, '用户新增', NULL, 'user:add', 2, 2, '2020-06-24 22:22:16', '2020-06-24 22:22:18');
INSERT INTO `tb_permission` VALUES (9, 2, '用户编辑', NULL, 'user:edit', 2, 2, '2020-06-24 22:22:51', '2020-06-24 22:22:53');
INSERT INTO `tb_permission` VALUES (10, 2, '用户删除', NULL, 'user:del', 2, 2, '2020-06-24 22:23:12', '2020-06-24 22:23:15');
INSERT INTO `tb_permission` VALUES (11, 3, '角色新增', NULL, 'role:add', 3, 2, '2020-06-24 22:23:37', '2020-06-24 22:23:40');
INSERT INTO `tb_permission` VALUES (12, 3, '角色修改', NULL, 'role:edit', 3, 2, '2020-06-24 22:24:36', '2020-06-24 22:24:40');
INSERT INTO `tb_permission` VALUES (13, 3, '角色删除', NULL, 'role:del', 3, 2, '2020-06-24 22:25:45', '2020-06-24 22:25:48');
INSERT INTO `tb_permission` VALUES (14, 4, '菜单新增', NULL, 'menu:add', 4, 2, '2020-06-28 22:18:33', '2020-06-28 22:18:36');
INSERT INTO `tb_permission` VALUES (15, 4, '菜单修改', NULL, 'menu:edit', 4, 2, '2020-06-28 22:18:56', '2020-06-28 22:18:59');
INSERT INTO `tb_permission` VALUES (16, 4, '菜单删除', NULL, 'menu:del', 4, 2, '2020-06-28 22:19:15', '2020-06-28 22:19:17');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, '管理员', '超级管理员，拥有所有权限', '2020-06-23 16:53:29', '2020-06-23 16:53:33');
INSERT INTO `tb_role` VALUES (2, '普通用户', '普通用户，权限有限', '2020-06-23 16:53:54', '2020-07-04 15:55:28');

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission`  (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
INSERT INTO `tb_role_permission` VALUES (1, 1);
INSERT INTO `tb_role_permission` VALUES (1, 2);
INSERT INTO `tb_role_permission` VALUES (1, 3);
INSERT INTO `tb_role_permission` VALUES (1, 4);
INSERT INTO `tb_role_permission` VALUES (1, 5);
INSERT INTO `tb_role_permission` VALUES (1, 6);
INSERT INTO `tb_role_permission` VALUES (1, 7);
INSERT INTO `tb_role_permission` VALUES (1, 8);
INSERT INTO `tb_role_permission` VALUES (1, 9);
INSERT INTO `tb_role_permission` VALUES (1, 10);
INSERT INTO `tb_role_permission` VALUES (1, 11);
INSERT INTO `tb_role_permission` VALUES (1, 12);
INSERT INTO `tb_role_permission` VALUES (1, 13);
INSERT INTO `tb_role_permission` VALUES (1, 14);
INSERT INTO `tb_role_permission` VALUES (1, 15);
INSERT INTO `tb_role_permission` VALUES (1, 16);
INSERT INTO `tb_role_permission` VALUES (2, 1);
INSERT INTO `tb_role_permission` VALUES (2, 2);
INSERT INTO `tb_role_permission` VALUES (2, 3);
INSERT INTO `tb_role_permission` VALUES (2, 4);
INSERT INTO `tb_role_permission` VALUES (2, 5);
INSERT INTO `tb_role_permission` VALUES (2, 6);
INSERT INTO `tb_role_permission` VALUES (2, 7);

-- ----------------------------
-- Table structure for tb_role_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_user`;
CREATE TABLE `tb_role_user`  (
  `user_id` int(32) NOT NULL COMMENT '用户id',
  `role_id` int(32) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role_user
-- ----------------------------
INSERT INTO `tb_role_user` VALUES (1, 1);
INSERT INTO `tb_role_user` VALUES (2, 2);
INSERT INTO `tb_role_user` VALUES (3, 2);
INSERT INTO `tb_role_user` VALUES (4, 2);
INSERT INTO `tb_role_user` VALUES (5, 2);
INSERT INTO `tb_role_user` VALUES (6, 2);
INSERT INTO `tb_role_user` VALUES (7, 2);
INSERT INTO `tb_role_user` VALUES (10, 2);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id值',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `status` tinyint(1) NOT NULL COMMENT '状态',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', '$2a$10$pAuzCLIe6Sl7kXfX6FEQ1uzM79V2njg.KtL9qawg9JkW7e1f417k2', '管理员', '18438382991', 1, '2020-06-23 16:55:07', '2020-06-23 16:55:09');
INSERT INTO `tb_user` VALUES (2, 'test', '$2a$10$pAuzCLIe6Sl7kXfX6FEQ1uzM79V2njg.KtL9qawg9JkW7e1f417k2', '普通用户', '18438382992', 1, '2020-06-23 16:55:29', '2020-06-23 16:55:33');
INSERT INTO `tb_user` VALUES (3, 'test1', '$2a$10$exOfpFK2TNHnAdG/aaVTFeCDLihkg8JfD1qGWKjCOBdicxcQJax5W', '普通用户2', '1212121212121', 1, '2020-06-25 15:44:34', '2020-06-25 15:44:37');
INSERT INTO `tb_user` VALUES (4, 'test2', 'e3ceb5881a0a1fdaad01296d7554868d', '普通用户3', '1212121111', 1, '2020-06-25 15:45:04', '2020-06-25 15:45:07');
INSERT INTO `tb_user` VALUES (5, 'test3', '1a100d2c0dab19c4430e7d73762b3423', '普通用户4', '13131313123', 1, '2020-06-25 15:45:28', '2020-06-25 15:45:30');
INSERT INTO `tb_user` VALUES (6, 'test4', '$2a$10$jNU1gXN.wAPhq5vUmLrCoeyDJbF3ReSnYQ2IulJA99drcMs1w1Som', '封禁用户', '121213131', 0, '2020-06-25 15:46:11', '2020-06-25 15:46:13');
INSERT INTO `tb_user` VALUES (7, 'test5', '5b1b68a9abf4d2cd155c81a9225fd158', '封禁用户2', '143423432', 0, '2020-06-25 15:46:42', '2020-07-01 17:17:14');
INSERT INTO `tb_user` VALUES (10, 'test7', 'e10adc3949ba59abbe56e057f20f883e', '测试添加', '18438382993', 1, '2020-07-01 17:36:46', '2020-07-02 14:38:15');

SET FOREIGN_KEY_CHECKS = 1;
