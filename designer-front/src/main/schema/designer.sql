/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50165
 Source Host           : localhost
 Source Database       : designer

 Target Server Type    : MySQL
 Target Server Version : 50165
 File Encoding         : utf-8

 Date: 11/15/2013 13:11:43 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tb_access_token_info`
-- ----------------------------
DROP TABLE IF EXISTS `tb_access_token_info`;
CREATE TABLE `tb_access_token_info` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `access_token` varchar(200) DEFAULT '',
  `refresh_token` varchar(200) DEFAULT '',
  `token_type` varchar(20) DEFAULT '',
  `thirdparty_uid` varchar(100) DEFAULT NULL,
  `expire_in` bigint(20) DEFAULT NULL,
  `creae_time` datetime DEFAULT NULL,
  `upate_time` datetime DEFAULT NULL,
  `thirdparty_type` smallint(6) DEFAULT '0' COMMENT '1:SinaWeibo, 2:QQ, 3:Renren',
  `thirdparty_uname` varchar(50) DEFAULT NULL,
  `sync_album` smallint(6) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_album`
-- ----------------------------
DROP TABLE IF EXISTS `tb_album`;
CREATE TABLE `tb_album` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `link` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `status` smallint(6) DEFAULT '1',
  `cover_large_img` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `cover_medium_img` varchar(500) COLLATE utf8_unicode_ci DEFAULT '',
  `cover_small_img` varchar(500) COLLATE utf8_unicode_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `tb_album_slide`
-- ----------------------------
DROP TABLE IF EXISTS `tb_album_slide`;
CREATE TABLE `tb_album_slide` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `album_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `is_cover` smallint(6) DEFAULT NULL,
  `slide_large_img` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `slide_medium_img` varchar(500) COLLATE utf8_unicode_ci DEFAULT '',
  `slide_small_img` varchar(500) COLLATE utf8_unicode_ci DEFAULT '',
  `status` smallint(6) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `tb_comment`
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `album_id` int(11) DEFAULT NULL,
  `album_slide_id` int(11) DEFAULT NULL,
  `title` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comment` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `from_id` int(11) NOT NULL,
  `to_id` int(11) DEFAULT NULL,
  `designer_id` int(11) DEFAULT NULL,
  `user_head_img` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nickname` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `status` smallint(6) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`,`from_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `tb_message`
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `message_type` int(4) NOT NULL,
  `source_id` bigint(20) DEFAULT NULL,
  `from_id` int(11) DEFAULT NULL,
  `to_id` int(11) DEFAULT NULL,
  `unread` smallint(6) DEFAULT '1',
  `status` smallint(6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100019 DEFAULT CHARSET=utf8;



-- ----------------------------
--  Table structure for `tb_tag`
-- ----------------------------
DROP TABLE IF EXISTS `tb_tag`;
CREATE TABLE `tb_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `status` smallint(6) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `tb_tag_album`
-- ----------------------------
DROP TABLE IF EXISTS `tb_tag_album`;
CREATE TABLE `tb_tag_album` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `tag_id` int(11) NOT NULL,
  `album_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `nickname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `gender` smallint(6) DEFAULT NULL,
  `email` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `head_img` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` smallint(6) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `designer_status` smallint(6) DEFAULT '0',
  `designer_identifer` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `designer_realname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `designer_mobile` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `designer_introduction` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `designer_company` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `designer_taobao_homepage` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `designer_apply_time` datetime DEFAULT NULL,
  `designer_pass_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `tb_user_fan`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_fan`;
CREATE TABLE `tb_user_fan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `fan_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `tb_user_favorite`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_favorite`;
CREATE TABLE `tb_user_favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `favorite_album_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `tb_user_follow`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_follow`;
CREATE TABLE `tb_user_follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `follow_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
