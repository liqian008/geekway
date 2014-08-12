/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50169
Source Host           : localhost:3306
Source Database       : security_1758

Target Server Type    : MYSQL
Target Server Version : 50169
File Encoding         : 65001

Date: 2014-08-12 16:56:28
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of admin_resource
-- ----------------------------
INSERT INTO `admin_resource` VALUES ('2', '', '1', '个人中心', '/home/profile', '_target', '1', '0', null, '1', '2013-07-26 20:10:30', '2014-04-04 16:05:11');
INSERT INTO `admin_resource` VALUES ('3', '', '0', '权限管理', '#', '', '1', '100', null, '1', '2013-07-23 11:10:45', '2014-04-20 11:35:24');
INSERT INTO `admin_resource` VALUES ('5', '', '3', '用户列表 ', '/sys/users', '', '1', '0', '', '1', '2013-07-23 11:14:32', '2013-07-23 11:11:35');
INSERT INTO `admin_resource` VALUES ('6', '', '3', '角色列表 ', '/sys/roles', '', '1', '0', '', '1', '2013-07-23 11:14:32', '2013-07-23 11:11:35');
INSERT INTO `admin_resource` VALUES ('7', '', '3', '权限列表', '/sys/resources', '', '1', '0', '', '1', '2013-07-23 11:14:32', '2014-05-10 23:43:21');
INSERT INTO `admin_resource` VALUES ('8', '', '0', '微信管理', '#', '', '1', '200', null, '1', '2014-04-06 09:27:12', '2014-05-28 21:30:47');
INSERT INTO `admin_resource` VALUES ('10', '', '8', '关键词指令管理', '/geekway/commandList', '', '1', '90', null, '1', '2014-04-06 09:27:58', '2014-08-09 18:27:00');
INSERT INTO `admin_resource` VALUES ('11', '', '8', '默认回复管理', '/geekway/defaultReply', '', '1', '250', null, '1', '2014-04-06 09:28:23', '2014-04-20 11:34:16');
INSERT INTO `admin_resource` VALUES ('12', '', '8', '素材管理', '/geekway/materialArticleList', '', '0', '230', null, '1', '2014-04-07 10:35:23', '2014-08-09 18:39:42');
INSERT INTO `admin_resource` VALUES ('13', '', '8', '自定义菜单管理', '/geekway/customizeMenuList', '', '1', '220', null, '1', '2014-04-14 14:48:34', '2014-04-20 11:40:43');
INSERT INTO `admin_resource` VALUES ('25', '', '8', '用户关注素材管理', '/geekway/subscribedMaterialList', '#', '0', '240', null, '1', '2014-05-14 17:43:23', '2014-08-09 18:34:59');
INSERT INTO `admin_resource` VALUES ('26', '', '8', '微信用户管理', '/geekway/mpUserList', '#', '1', '270', null, '1', '2014-05-18 20:40:34', '2014-05-18 20:41:00');
INSERT INTO `admin_resource` VALUES ('35', '', '8', '微信接入配置', '/geekway/wxConfig', '', '1', '80', null, '1', '2014-06-05 13:35:04', '2014-08-09 18:39:06');
INSERT INTO `admin_resource` VALUES ('40', '', '8', '服务号群发管理', '/geekway/broadcastList', '', '1', '400', null, '1', '2014-08-05 17:09:00', '2014-08-06 18:31:16');
INSERT INTO `admin_resource` VALUES ('41', '', '8', '历史消息管理', '/geekway/historyMessageList', '', '1', '410', null, '1', '2014-08-06 17:35:54', '2014-08-09 18:41:18');
INSERT INTO `admin_resource` VALUES ('42', '', '8', '用户关注指令管理', '/geekway/subscribedCommandList', '', '1', '100', null, '1', '2014-08-09 16:46:46', '2014-08-09 16:50:53');
INSERT INTO `admin_resource` VALUES ('43', '', '0', '微信素材管理', 'javascript:void(0)', '', '1', '200', null, '1', '2014-08-09 16:47:35', '2014-08-09 16:48:38');
INSERT INTO `admin_resource` VALUES ('44', '', '43', '图片素材管理', '/geekway/materialImageList', '', '1', '300', null, '1', '2014-08-09 19:36:09', '2014-08-09 19:40:50');
INSERT INTO `admin_resource` VALUES ('45', '', '43', '语音素材管理', '/geekway/materialVoiceList', '', '0', '400', null, '1', '2014-08-09 19:36:41', '2014-08-11 11:14:49');
INSERT INTO `admin_resource` VALUES ('46', '', '43', '单图文管理', '/geekway/materialArticleList', '', '1', '100', null, '1', '2014-08-09 19:37:55', '2014-08-09 19:37:55');
INSERT INTO `admin_resource` VALUES ('47', '', '43', '多图文管理', '/geekway/materialNewsList', '', '1', '200', null, '1', '2014-08-09 19:38:28', '2014-08-09 19:39:18');

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
INSERT INTO `admin_role` VALUES ('1', '权限管理', '1', '2013-07-23 11:09:42', '2014-04-16 10:26:48');
INSERT INTO `admin_role` VALUES ('2', '微信平台管理', '1', '2013-07-26 12:16:41', '2014-04-16 10:26:53');
INSERT INTO `admin_role` VALUES ('3', '个人中心', '1', '2014-04-16 10:27:26', '2014-04-16 10:27:26');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of admin_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `nickname` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `status` smallint(11) DEFAULT '1' COMMENT '0:禁用,1:启用',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`) USING BTREE,
  UNIQUE KEY `uk_nickname` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', 'admin', '超级管理员', '2cfd161423c072eeda26113a6c96f2a663b6b1e834c11dd425028517f8651bf1125e198cffa804ef', '1', '2013-07-18 09:34:52', '2014-06-18 17:47:41', '2013-07-18 09:34:58');
INSERT INTO `admin_user` VALUES ('2', 'geekway', 'geekway', 'b0d17db406076697367f2a88cdc446bea5b106a1e482f72a86d573ea43fa19a67ab9f8183384e14b', '1', '2014-04-16 09:51:33', '2014-06-18 17:50:29', null);
INSERT INTO `admin_user` VALUES ('3', 'sys', 'sys', '37902b7cbaf38d093dc05c968244efcb21f320517a57bd1e00d2d4749f5d9576e6ad7883a68c0797', '1', '2014-04-16 19:30:20', '2014-04-16 19:31:23', null);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
