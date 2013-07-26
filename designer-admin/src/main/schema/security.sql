/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50169
Source Host           : localhost:3306
Source Database       : security

Target Server Type    : MYSQL
Target Server Version : 50169
File Encoding         : 65001

Date: 2013-07-26 12:19:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_resource
-- ----------------------------
DROP TABLE IF EXISTS `admin_resource`;
CREATE TABLE `admin_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '编号',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `resource_name` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单URL',
  `url_target` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '页面打开位置',
  `nav_menu` smallint(11) DEFAULT '0' COMMENT '0:不显示在导航菜单中,1:显示在导航菜单中',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `status` smallint(6) DEFAULT '1',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of admin_resource
-- ----------------------------
INSERT INTO `admin_resource` VALUES ('1', '1', '0', '权限管理', '#', null, '1', '0', null, '1', '2013-07-23 11:10:45', '2013-07-23 11:10:48');
INSERT INTO `admin_resource` VALUES ('2', '2', '1', 'userEdit1', '/sys/userEdit', '123', '0', '0', null, '2', '2013-07-23 11:14:35', '2013-07-23 11:11:35');
INSERT INTO `admin_resource` VALUES ('3', '3', '1', '用户列表 ', '/sys/users', '', '1', '0', '', '1', '2013-07-23 11:14:32', '2013-07-23 11:11:35');
INSERT INTO `admin_resource` VALUES ('4', '4', '1', '角色列表 ', '/sys/roles', '', '1', '0', '', '1', '2013-07-23 11:14:32', '2013-07-23 11:11:35');
INSERT INTO `admin_resource` VALUES ('5', '5', '1', '资源列表 ', '/sys/resources', '', '1', '0', '', '1', '2013-07-23 11:14:32', '2013-07-23 11:11:35');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理角色标识',
  `role_name` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称',
  `status` smallint(11) DEFAULT '1' COMMENT '0:禁用,1:启用',
  `create_time` datetime NOT NULL COMMENT '角色创建时间',
  `update_time` datetime NOT NULL COMMENT '角色最近修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_rolename` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', '权限管理角色', '1', '2013-07-23 11:09:42', '2013-07-23 11:09:45');
INSERT INTO `admin_role` VALUES ('2', '2', '1', '2013-07-26 12:16:41', '2013-07-26 12:16:43');
INSERT INTO `admin_role` VALUES ('3', '3', '1', '2013-07-26 12:16:50', '2013-07-26 12:16:55');

-- ----------------------------
-- Table structure for admin_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_resource`;
CREATE TABLE `admin_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `resource_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_roleid_resourceid` (`role_id`,`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of admin_role_resource
-- ----------------------------
INSERT INTO `admin_role_resource` VALUES ('2', '0', '0');
INSERT INTO `admin_role_resource` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `nickname` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) DEFAULT '1' COMMENT '0:禁用,1:启用',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`) USING BTREE,
  UNIQUE KEY `uk_nickname` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', 'admin', '超级管理员', 'b444e82d17a54d53a9e623fa586bf23ad7087df5e6f44848d01150fb0d5d523ea07ed99abdd074fc', '1', '2013-07-18 09:34:52', '2013-07-18 09:34:55', '2013-07-18 09:34:58');

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_userid_roleid` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES ('2', '1', '1');
INSERT INTO `admin_user_role` VALUES ('3', '1', '2');
INSERT INTO `admin_user_role` VALUES ('4', '1', '3');
