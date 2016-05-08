/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50544
Source Host           : localhost:3306
Source Database       : endemo

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2016-02-18 09:48:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xt_cash
-- ----------------------------
DROP TABLE IF EXISTS `xt_cash`;
CREATE TABLE `xt_cash` (
  `tid` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` double NOT NULL,
  `date` date NOT NULL,
  `node_number` varchar(20) DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL,
  `staff` varchar(64) DEFAULT NULL,
  `state` varchar(24) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `lrrq` varchar(50) DEFAULT NULL,
  `xgrq` varchar(50) DEFAULT NULL,
  `lrrDm` bigint(20) DEFAULT NULL,
  `xgrDm` bigint(20) DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  `bz2` varchar(200) DEFAULT NULL,
  `gsdm` varchar(50) DEFAULT NULL,
  `reserve` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of xt_cash
-- ----------------------------
