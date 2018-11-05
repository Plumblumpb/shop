/*
Navicat MySQL Data Transfer

Source Server         : 我的
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : shop_cache

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-05 10:48:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `AGE` int(2) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'ADMIN', 'ADMIN', '10');
