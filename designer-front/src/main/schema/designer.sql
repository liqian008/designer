/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50165
 Source Host           : localhost
 Source Database       : designer

 Target Server Version : 50165
 File Encoding         : utf-8

 Date: 07/29/2013 11:01:03 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;


DROP DATABASE designer;
CREATE DATABASE designer;
use designer;
-- ----------------------------
--  Table structure for `tb_album`
-- ----------------------------
DROP TABLE IF EXISTS `tb_album`;
CREATE TABLE `tb_album` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT '1',
  `cover_img` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `fk_album_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `tb_album`
-- ----------------------------
BEGIN;
INSERT INTO `tb_album` VALUES ('1', 'name1', 'mark1', '3', '2013-07-18 20:38:18', '2013-06-30 20:38:22', '1', '../img/demo/demo1.jpg'), ('2', 'name2', 'mark2', '3', '2013-06-21 20:38:42', '2013-06-30 20:38:45', '1', '../img/sample/demo2.jpg'), ('3', 'asdf', 'asdf', '4', '2013-06-21 20:39:06', '2013-06-30 20:39:10', '1', './img/demo/hands-plant-870x450.jpg'), ('4', 'xvzxcv', 'zxcvxc', '4', '2013-07-21 23:31:37', '2013-07-19 23:31:40', '1', './img/sample/gal1-2.jpg'), ('5', 'wer', 'attt', '5', '2013-07-01 23:32:19', '2013-07-01 23:32:16', '1', './img/sample/gal1-5.jpg'), ('6', '3', '123', '6', '2013-07-01 23:32:41', '2013-07-10 23:32:38', '1', 'http://localhost:8080/designer-front/img/demo/demo6.jpg'), ('16', 'title', 'remark', '3', '2013-07-20 11:34:08', '2013-07-20 11:34:10', '1', 'http://localhost:8080/designer-front/img/demo/demo7.jpg'), ('17', 'title', 'remark', '3', '2013-07-20 11:34:13', '2013-07-20 11:34:15', '1', 'http://localhost:8080/designer-front/img/demo/demo8.jpg');
COMMIT;

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
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT '1',
  `slide_img` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `tb_album_slide`
-- ----------------------------
BEGIN;
INSERT INTO `tb_album_slide` VALUES ('1', '五谷丰登', 'desc', '1', '1', '2013-07-03 00:03:03', '2013-07-03 00:03:07', '1', 'http://localhost:8080/designer-front/img/demo/demo7.jpg'), ('2', '五羊开泰', 'desc2', '1', '1', '2013-07-12 00:03:35', '2013-07-26 00:03:38', '1', 'http://localhost:8080/designer-front/img/demo/demo1.jpg'), ('3', '龙马精神', 'desc3', '1', '1', '2013-07-18 00:04:02', '2013-07-30 00:04:04', '1', 'http://localhost:8080/designer-front/img/demo/demo3.jpg'), ('4', '观音坐莲', 'desc4', '1', '1', '2013-07-03 00:20:01', '2013-07-03 00:20:03', '1', 'http://localhost:8080/designer-front/img/demo/demo4.jpg'), ('5', '瑕不掩玉', 'desc5', '1', '1', '2013-07-05 00:20:31', '2013-07-03 00:20:35', '1', 'http://localhost:8080/designer-front/img/demo/demo5.jpg'), ('6', '巧夺天工', 'desc6', '2', '1', '2013-07-10 00:21:13', '2013-07-19 00:21:16', '1', 'http://localhost:8080/designer-front/img/demo/demo6.jpg'), ('11', '1', '1', '2', '1', null, null, null, 'http://localhost:8080/designer-front/img/demo/demo7.jpg'), ('12', '2', '2', '2', '1', null, null, null, 'http://localhost:8080/designer-front/img/demo/demo8.jpg'), ('13', null, null, '17', '3', null, null, null, 'http://localhost:8080/designer-front/img/demo/hands-plant-870x450.jpg'), ('14', null, null, '17', '3', null, null, null, 'http://localhost:8080/designer-front/img/sample/gal1-5.jpg');
COMMIT;

-- ----------------------------
--  Table structure for `tb_comment`
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comment` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT '1',
  `album_id` int(11) DEFAULT NULL,
  `album_content_id` int(11) DEFAULT NULL,
  `user_head_img` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nickname` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `tb_comment`
-- ----------------------------
BEGIN;
INSERT INTO `tb_comment` VALUES ('1', 'That\'s it，bootstrap!', 'That\'s it，bootstrap!', '4', '2013-07-02 18:55:56', '2013-07-02 18:56:10', '1', '1', null, 'http://localhost:8080/designer-front/assets/img/avatar2.jpg', 'test'), ('2', 'bootstrap的第一个demo！', 'bootstrap的第一个demo！', '5', '2013-07-02 18:55:56', '2013-07-02 18:56:10', '1', '1', null, 'http://localhost:8080/designer-front/assets/img/avatar4.jpg', '大树'), ('3', 'bootstrap, 准备战斗！', 'bootstrap, 准备战斗！', '4', '2013-07-02 18:55:56', '2013-07-02 18:56:10', '1', '1', null, 'http://localhost:8080/designer-front/assets/img/avatar4.jpg', '乔丹'), ('4', null, '123123', '3', '2013-07-02 18:55:56', '2013-07-02 18:56:10', '1', '1', '1', null, '猫王'), ('5', null, 'Hello, bootstrap', '3', '2013-07-02 18:55:56', '2013-07-02 18:56:10', null, '1', '1', null, 'liqian'), ('6', null, 'great bootstrap', '3', '2013-07-02 18:57:56', '2013-07-02 18:57:56', null, '1', '1', null, 'liqian'), ('9', null, '长腿美女', '3', '2013-07-02 19:47:09', '2013-07-02 19:47:09', null, '6', '6', null, 'liqian'), ('10', null, '性感美女', '3', '2013-07-02 19:47:37', '2013-07-02 19:47:37', null, '5', '5', null, 'liqian'), ('11', null, '杀死比尔', '3', '2013-07-02 19:49:15', '2013-07-02 19:49:15', null, '2', '2', null, 'liqian');
COMMIT;

-- ----------------------------
--  Table structure for `tb_message`
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `message_type` smallint(4) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `tb_user_message`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_message`;
CREATE TABLE `tb_user_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message_id` bigint(20) NOT NULL,
  `from_id` int(11) NOT NULL,
  `to_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `password` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` smallint(6)) DEFAULT 1,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT '1',
  `head_img` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `designer_status` smallint(6) DEFAULT '0',
  `designer_identifer` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `designer_realname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `designer_mobile` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `designer_introduction` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL;
  `designer_company` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `designer_taobao_homepage` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `designer_apply_time`  datetime NULL DEFAULT NULL ,
  `designer_pass_time`  datetime NULL DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `tb_user`
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES ('3', 'liqian', 'liqian', 'email', '2013-07-02 18:55:48', '2013-07-02 18:55:45', '1', 'http://localhost:8080/designer-front/assets/img/avatar.jpg', 'liqian'), ('4', 'liqian1', 'liqian', 'email', '2013-07-02 18:55:48', '2013-07-02 18:55:45', '1', 'http://localhost:8080/designer-front/assets/img/avatar.jpg', 'liqian1'), ('5', 'liqian2', 'liqian', 'email', '2013-07-02 18:55:48', '2013-07-02 18:55:45', '1', 'http://localhost:8080/designer-front/assets/img/avatar.jpg', 'liqian2'), ('6', 'test', 'liqian', 'email', '2013-07-02 18:55:48', '2013-07-02 18:55:45', '1', 'http://localhost:8080/designer-front/assets/img/avatar.jpg', 'test'), ('7', 'test', 'liqian', 'email', '2013-07-02 18:55:48', '2013-07-02 18:55:45', '1', 'http://localhost:8080/designer-front/assets/img/avatar.jpg', 'test'), ('8', 'burce', 'bruce', null, '2013-07-02 18:55:48', '2013-07-02 18:55:45', null, null, 'bruce_lee');
COMMIT;

-- ----------------------------
-- Table structure for tb_access_token_info
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `tb_feed`
-- ----------------------------
DROP TABLE IF EXISTS `tb_feed`;
CREATE TABLE `tb_feed` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `content` text,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_feed_index_live`
-- ----------------------------
DROP TABLE IF EXISTS `tb_feed_index_live`;
CREATE TABLE `tb_feed_index_live` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `feed_id` bigint(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_feed_index_tag`
-- ----------------------------
DROP TABLE IF EXISTS `tb_feed_index_tag`;
CREATE TABLE `tb_feed_index_tag` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `feed_id` bigint(20) NOT NULL,
  `tag_id` int(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS = 1;
