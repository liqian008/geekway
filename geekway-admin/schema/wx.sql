/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50169
Source Host           : localhost:3306
Source Database       : wx_1758

Target Server Type    : MYSQL
Target Server Version : 50169
File Encoding         : 65001

Date: 2014-08-08 09:40:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wx_broadcast
-- ----------------------------
DROP TABLE IF EXISTS `wx_broadcast`;
CREATE TABLE `wx_broadcast` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message_type` varchar(20) DEFAULT NULL,
  `msg_id` varchar(200) DEFAULT NULL,
  `media_id` varchar(100) DEFAULT '',
  `content` varchar(500) DEFAULT '',
  `total_count` int(11) DEFAULT '0',
  `filter_count` int(11) DEFAULT '0',
  `sent_count` int(11) DEFAULT '0',
  `error_count` int(11) DEFAULT '0',
  `status` smallint(6) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_broadcast
-- ----------------------------

-- ----------------------------
-- Table structure for wx_command
-- ----------------------------
DROP TABLE IF EXISTS `wx_command`;
CREATE TABLE `wx_command` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `command` varchar(50) DEFAULT NULL,
  `command_type` smallint(6) DEFAULT NULL,
  `material_type` smallint(6) DEFAULT NULL,
  `material_id` int(11) DEFAULT NULL,
  `reply_content` varchar(1000) DEFAULT NULL,
  `row_limit` smallint(6) DEFAULT '4',
  `publish_status` smallint(1) DEFAULT NULL,
  `status` smallint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_command
-- ----------------------------

-- ----------------------------
-- Table structure for wx_command_material
-- ----------------------------
DROP TABLE IF EXISTS `wx_command_material`;
CREATE TABLE `wx_command_material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `command_id` int(11) DEFAULT NULL,
  `material_id` int(11) DEFAULT NULL,
  `top_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_command_material
-- ----------------------------

-- ----------------------------
-- Table structure for wx_customize_menu
-- ----------------------------
DROP TABLE IF EXISTS `wx_customize_menu`;
CREATE TABLE `wx_customize_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT '0',
  `menu_name` varchar(20) DEFAULT NULL,
  `menu_key` varchar(50) DEFAULT NULL,
  `menu_type` varchar(20) DEFAULT '',
  `url` varchar(200) DEFAULT NULL,
  `sort` smallint(6) DEFAULT '0',
  `status` smallint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_customize_menu
-- ----------------------------
INSERT INTO `wx_customize_menu` VALUES ('11', '0', '1758', '1758', 'click', '', '10', '1', '2014-08-07 15:23:18', '2014-08-07 15:23:18');
INSERT INTO `wx_customize_menu` VALUES ('12', '0', '精品游戏', 'jpyx', 'view', 'http://m.1758.com', '20', '1', '2014-08-07 15:23:51', '2014-08-07 15:25:17');

-- ----------------------------
-- Table structure for wx_default_reply
-- ----------------------------
DROP TABLE IF EXISTS `wx_default_reply`;
CREATE TABLE `wx_default_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `new_subscribe_reply` varchar(255) DEFAULT '',
  `re_subscribe_reply` varchar(255) DEFAULT '',
  `text_reply` varchar(255) DEFAULT '',
  `image_reply` varchar(255) DEFAULT '',
  `voice_reply` varchar(255) DEFAULT '',
  `menu_click_reply` varchar(255) DEFAULT '',
  `location_reply` varchar(255) DEFAULT '',
  `video_reply` varchar(255) DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_default_reply
-- ----------------------------
INSERT INTO `wx_default_reply` VALUES ('1', '用户新关注的默认回复', '用户重复关注的默认回复', '文本消息的默认回复', '图片消息的默认回复', '语音消息的默认回复', '按钮事件的默认回复', 'LBS消息的默认回复', '视频消息的默认回复', null, '2014-06-06 21:55:26');

-- ----------------------------
-- Table structure for wx_history_message
-- ----------------------------
DROP TABLE IF EXISTS `wx_history_message`;
CREATE TABLE `wx_history_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg_id` varchar(100) DEFAULT NULL,
  `open_id` varchar(50) DEFAULT NULL,
  `inbox` smallint(6) DEFAULT '0' COMMENT '接收的消息',
  `msg_type` varchar(50) DEFAULT '',
  `full_message` varchar(2000) DEFAULT '' COMMENT '完整消息数据',
  `short_content` varchar(200) DEFAULT '',
  `content` varchar(1000) DEFAULT '' COMMENT '文本消息内容',
  `media_id` varchar(200) DEFAULT '',
  `pic_url` varchar(500) DEFAULT '',
  `format` varchar(20) DEFAULT '' COMMENT 'voice格式',
  `thumb_media_id` varchar(200) DEFAULT '' COMMENT '视频所需',
  `reply_type` smallint(6) DEFAULT '0' COMMENT '自动回复0, 客服回复1，群发消息2',
  `send_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_history_message
-- ----------------------------
INSERT INTO `wx_history_message` VALUES ('43', '1234567890123456', 'fromUser', '0', 'text', '<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName> <CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[liqian]]></Content><MsgId>1234567890123456</MsgId></xml>', '', 'liqian', '', '', '', '', '0', '2012-09-28 19:31:00', '2014-08-07 16:56:35', null);
INSERT INTO `wx_history_message` VALUES ('44', null, 'fromUser', '1', 'text', '<xml><ToUserName><![CDATA[fromUser]]></ToUserName><FromUserName><![CDATA[toUser]]></FromUserName><CreateTime>1407401795</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[文本消息的默认回复]]></Content></xml>', '', '文本消息的默认回复', '', '', '', '', '0', '2014-08-07 16:56:35', '2014-08-07 16:56:35', null);

-- ----------------------------
-- Table structure for wx_material_article
-- ----------------------------
DROP TABLE IF EXISTS `wx_material_article`;
CREATE TABLE `wx_material_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material_type` smallint(6) DEFAULT '0',
  `text_reply` varchar(200) DEFAULT '',
  `title` varchar(50) DEFAULT NULL,
  `short_title` varchar(20) DEFAULT NULL,
  `short_content` varchar(100) DEFAULT '',
  `content` varchar(4000) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `cover_image_url` varchar(200) DEFAULT NULL,
  `cover_thumb_image_url` varchar(200) DEFAULT NULL,
  `subscribe_status` smallint(6) DEFAULT '0',
  `status` smallint(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_material_article
-- ----------------------------


-- ----------------------------
-- Table structure for wx_mp_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_mp_user`;
CREATE TABLE `wx_mp_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(100) DEFAULT NULL,
  `nickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '' COMMENT '昵称',
  `sex` smallint(6) DEFAULT NULL,
  `city` varchar(100) DEFAULT '',
  `province` varchar(100) DEFAULT '',
  `country` varchar(100) DEFAULT '',
  `language` varchar(100) DEFAULT NULL,
  `head_img_url` varchar(500) DEFAULT NULL,
  `subscribe_status` smallint(6) DEFAULT '1',
  `sync_status` smallint(6) DEFAULT '0',
  `status` smallint(1) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_open_id` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_mp_user
-- ----------------------------
INSERT INTO `wx_mp_user` VALUES ('89', 'oxGeHjthnXf92vuaCmkYHmzA-PFo', '冉-Rana', '2', '海淀', '北京', '中国', 'zh_CN', 'http://wx.qlogo.cn/mmopen/PiajxSqBRaELyCNDV0vRPTH9he20GNtZ0J8QTIA4LslOpfrPia8WDEiauo3RJu6Czt59DnYj455riaajwy9MIbUuYw/0', '1', '1', null, '2014-06-08 00:27:30', '2014-06-08 01:12:56');
INSERT INTO `wx_mp_user` VALUES ('91', 'oxGeHjg87dS82dsp4iP4SE1iVujA', '李乾', '1', '朝阳', '北京', '中国', 'zh_CN', 'http://wx.qlogo.cn/mmopen/Q3auHgzwzM5fSr527vWs39Q4Q5sGBTpLn1mOzAQ2GkakJscdp2jnYX4OIeKvmGhpBicvtxf8Ely8WNnOD8Ad7TA/0', '1', '1', null, '2014-06-08 14:08:03', '2014-06-08 15:04:15');
