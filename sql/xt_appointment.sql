/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50544
Source Host           : localhost:3306
Source Database       : endemo

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2016-01-28 17:12:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xt_appointment
-- ----------------------------
DROP TABLE IF EXISTS `xt_appointment`;
CREATE TABLE `xt_appointment` (
  `tid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `account` varchar(24) NOT NULL,
  `number` double NOT NULL,
  `phone` varchar(24) DEFAULT NULL,
  `date` date NOT NULL,
  `way` varchar(64) NOT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  `status` varchar(24) DEFAULT NULL,
  `reserve` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of xt_appointment
-- ----------------------------
