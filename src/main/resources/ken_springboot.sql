/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.11
Source Server Version : 50723
Source Host           : 192.168.1.11:0
Source Database       : ken_springboot

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-09-04 20:52:58
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for `resources`
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `id`   int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32)               DEFAULT NULL,
  `url`  varchar(60)               DEFAULT NULL,
  `type` tinyint(2)                DEFAULT NULL,
  `pid`  int(11)                   DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of resources
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id`        int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32)               DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_resources`
-- ----------------------------
DROP TABLE IF EXISTS `role_resources`;
CREATE TABLE `role_resources` (
  `role_id`      int(11) NOT NULL,
  `resources_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`, `resources_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of role_resources
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`        int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32)               DEFAULT NULL,
  `password`  varchar(32)               DEFAULT NULL,
  `phone`     varchar(15)               DEFAULT NULL,
  `email`     varchar(32)               DEFAULT NULL,
  `img_url`   varchar(60)               DEFAULT NULL,
  `birthday`  date                      DEFAULT NULL,
  `salt`      varchar(32)               DEFAULT NULL,
  `enable`    tinyint(2)       NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
