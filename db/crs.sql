/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : localhost:3306
 Source Schema         : crs

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 12/12/2022 11:36:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for actv
-- ----------------------------
DROP TABLE IF EXISTS `actv`;
CREATE TABLE `actv`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `actv_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动主题',
  `actv_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '活动内容',
  `cmty_id` bigint(20) NULL DEFAULT NULL COMMENT '社团id',
  `deadline` datetime NULL DEFAULT NULL COMMENT '报名截止日期',
  `is_closed` tinyint(1) NULL DEFAULT NULL COMMENT '是否关闭',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `created_user` bigint(255) NULL DEFAULT NULL COMMENT '创建人',
  `updated_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of actv
-- ----------------------------

-- ----------------------------
-- Table structure for actv_review
-- ----------------------------
DROP TABLE IF EXISTS `actv_review`;
CREATE TABLE `actv_review`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `actv_id` bigint(20) NULL DEFAULT NULL COMMENT '活动id',
  `review_id` bigint(20) NULL DEFAULT NULL COMMENT '审核人id',
  `stauts` tinyint(1) NULL DEFAULT 0 COMMENT '审核状态',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `created_user` bigint(255) NULL DEFAULT NULL COMMENT '创建人',
  `updated_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of actv_review
-- ----------------------------

-- ----------------------------
-- Table structure for cmty_actv_user
-- ----------------------------
DROP TABLE IF EXISTS `cmty_actv_user`;
CREATE TABLE `cmty_actv_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cmty_id` bigint(20) NULL DEFAULT NULL COMMENT '社团id',
  `actv_id` bigint(20) NULL DEFAULT NULL COMMENT '活动id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '审核状态 0-未审核；1-已审核',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `created_user` bigint(255) NULL DEFAULT NULL COMMENT '创建人',
  `updated_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cmty_actv_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_cmty
-- ----------------------------
DROP TABLE IF EXISTS `sys_cmty`;
CREATE TABLE `sys_cmty`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cmty_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '社团名称',
  `manager_id` bigint(20) NULL DEFAULT NULL COMMENT '社团团长',
  `cmty_type` tinyint(1) NULL DEFAULT NULL COMMENT '社团种类',
  `coll_id` bigint(20) NULL DEFAULT NULL COMMENT '所属学院',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `created_user` bigint(255) NULL DEFAULT NULL COMMENT '创建人',
  `updated_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_cmty
-- ----------------------------

-- ----------------------------
-- Table structure for sys_coll
-- ----------------------------
DROP TABLE IF EXISTS `sys_coll`;
CREATE TABLE `sys_coll`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coll_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学院名称',
  `created_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `created_user` bigint(255) NULL DEFAULT NULL COMMENT '创建人',
  `updated_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_coll
-- ----------------------------
INSERT INTO `sys_coll` VALUES (1, '信息学院', '2022-12-05 10:20:10', 0, NULL, 1, NULL);
INSERT INTO `sys_coll` VALUES (2, '经管学院', '2022-12-06 09:35:15', 0, NULL, 1, NULL);
INSERT INTO `sys_coll` VALUES (3, '动科学院', '2022-12-06 09:35:43', 0, NULL, 1, NULL);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '具体信息',
  `status` int(1) NULL DEFAULT NULL COMMENT '0 正常 1 异常',
  `result` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '返回结果',
  `time` int(11) NULL DEFAULT NULL COMMENT '花费时间 ms',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口url',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问方ip',
  `exception` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常信息',
  `created_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `created_user` bigint(255) NULL DEFAULT NULL COMMENT '创建人',
  `updated_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `created_user` bigint(255) NULL DEFAULT NULL COMMENT '创建人',
  `updated_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `created_user` bigint(255) NULL DEFAULT NULL COMMENT '创建人',
  `updated_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '社团管理员', '2022-12-02 09:40:47', 0, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (2, '社团团长', '2022-12-05 08:38:20', 0, NULL, 1, NULL);
INSERT INTO `sys_role` VALUES (3, '辅导员', '2022-12-05 08:42:42', 0, NULL, 1, NULL);
INSERT INTO `sys_role` VALUES (4, '普通用户', '2022-12-05 08:43:01', 0, NULL, 1, NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单id',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `created_user` bigint(255) NULL DEFAULT NULL COMMENT '创建人',
  `updated_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sno` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `coll_id` bigint(20) NULL DEFAULT NULL COMMENT '所属学院',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(2) NULL DEFAULT 0 COMMENT '是否已删除',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `created_user` bigint(255) NULL DEFAULT NULL COMMENT '创建人',
  `updated_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '000000', 'admin', 'default.png', 1, '274f84705249467593036dd547e05225', 'd564742c826b48d7838e9655c5e204ee', '13711111111', '2022-12-06 16:57:23', 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `created_user` bigint(255) NULL DEFAULT NULL COMMENT '创建人',
  `updated_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1, '2022-12-06 11:47:37', 0, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
