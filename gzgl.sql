/*
Navicat MySQL Data Transfer

Source Server         : eason
Source Server Version : 50621
Source Host           : 127.0.0.1:3306
Source Database       : gzgl

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-05-20 18:10:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `icon_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', '/upload/20160515154025379k6ueqt711.jpg');

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `attendance_id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` varchar(255) NOT NULL,
  `employee_name` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `month` varchar(255) DEFAULT NULL,
  `over_hour` varchar(255) DEFAULT NULL,
  `chidao` varchar(11) DEFAULT NULL,
  `zaotui` varchar(11) DEFAULT NULL,
  `kuang_gong` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`attendance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attendance
-- ----------------------------

-- ----------------------------
-- Table structure for count_way
-- ----------------------------
DROP TABLE IF EXISTS `count_way`;
CREATE TABLE `count_way` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position_id` varchar(255) NOT NULL,
  `position_name` varchar(255) DEFAULT NULL,
  `oh_moneny` varchar(255) DEFAULT NULL,
  `cd_moneny` varchar(255) DEFAULT NULL,
  `zt_moneny` varchar(255) DEFAULT NULL,
  `kg_moneny` varchar(255) DEFAULT NULL,
  `secure_reduce` varchar(255) DEFAULT NULL,
  `basic_wage` varchar(255) DEFAULT NULL,
  `percent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of count_way
-- ----------------------------

-- ----------------------------
-- Table structure for deduction
-- ----------------------------
DROP TABLE IF EXISTS `deduction`;
CREATE TABLE `deduction` (
  `deduction_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id` varchar(255) NOT NULL,
  `position_id` varchar(255) NOT NULL,
  `department_name` varchar(255) DEFAULT NULL,
  `position_name` varchar(255) DEFAULT NULL,
  `secure_reduce` varchar(255) DEFAULT NULL,
  `traffic_wage` varchar(255) DEFAULT NULL,
  `tax_reduce` varchar(255) DEFAULT NULL,
  `total_reduce` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`deduction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of deduction
-- ----------------------------

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `department_id` varchar(255) NOT NULL,
  `department_name` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_id` varchar(255) NOT NULL,
  `position_id` varchar(255) NOT NULL,
  `position_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `graduate_school` varchar(255) DEFAULT NULL,
  `shcool_record` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `edit_time` varchar(255) DEFAULT NULL,
  `icon_path` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `pasword` varchar(255) DEFAULT '000000',
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `position_id` varchar(255) NOT NULL,
  `department_id` varchar(255) NOT NULL,
  `position_name` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `basic_wage` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `department_name` varchar(255) DEFAULT NULL,
  `secure_reduce` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position
-- ----------------------------

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
  `salary_id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` varchar(255) NOT NULL,
  `position_id` varchar(255) NOT NULL,
  `employee_name` varchar(255) DEFAULT NULL,
  `position_name` varchar(255) DEFAULT NULL,
  `month` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `basic_wage` varchar(255) DEFAULT NULL,
  `overtime_wage` varchar(255) DEFAULT NULL,
  `sellmoney_get` varchar(255) DEFAULT NULL,
  `total_wage` varchar(255) DEFAULT NULL,
  `total_reduce` varchar(255) DEFAULT NULL,
  `real_wage` varchar(255) DEFAULT NULL,
  `edit_time` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`salary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1392 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salary
-- ----------------------------

-- ----------------------------
-- Table structure for sell
-- ----------------------------
DROP TABLE IF EXISTS `sell`;
CREATE TABLE `sell` (
  `sell_id` varchar(255) NOT NULL,
  `employee_id` varchar(255) NOT NULL,
  `employee_name` varchar(255) DEFAULT NULL,
  `sell_year` varchar(255) DEFAULT NULL,
  `sell_month` varchar(255) DEFAULT NULL,
  `sell_money` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sell_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sell
-- ----------------------------
