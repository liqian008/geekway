/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50169
Source Host           : localhost:3306
Source Database       : wx_1758

Target Server Type    : MYSQL
Target Server Version : 50169
File Encoding         : 65001

Date: 2014-08-12 16:56:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wx_access_token
-- ----------------------------
DROP TABLE IF EXISTS `wx_access_token`;
CREATE TABLE `wx_access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `access_token` varchar(600) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_command
-- ----------------------------
DROP TABLE IF EXISTS `wx_command`;
CREATE TABLE `wx_command` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `command` varchar(50) DEFAULT NULL,
  `command_type` smallint(6) DEFAULT NULL,
  `material_type` smallint(6) DEFAULT '0' COMMENT '文本0，单图文1，多图文2，图片3，语音4',
  `material_id` int(11) DEFAULT NULL,
  `reply_content` varchar(1000) DEFAULT NULL,
  `remark` varchar(200) DEFAULT '',
  `row_limit` smallint(6) DEFAULT '4',
  `publish_status` smallint(1) DEFAULT NULL,
  `status` smallint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

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
-- Table structure for wx_default_reply_copy
-- ----------------------------
DROP TABLE IF EXISTS `wx_default_reply_copy`;
CREATE TABLE `wx_default_reply_copy` (
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
-- Table structure for wx_history_message
-- ----------------------------
DROP TABLE IF EXISTS `wx_history_message`;
CREATE TABLE `wx_history_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg_id` varchar(100) DEFAULT NULL,
  `open_id` varchar(50) DEFAULT NULL,
  `inbox` smallint(6) DEFAULT '0' COMMENT '接收的消息',
  `msg_type` varchar(50) DEFAULT '' COMMENT '消息类型',
  `is_system_msg` smallint(6) DEFAULT '0' COMMENT '是否是系统消息',
  `reply_type` smallint(6) DEFAULT '0' COMMENT '自动回复0, 客服回复1，群发消息2',
  `full_message` varchar(2000) DEFAULT '' COMMENT '完整消息数据',
  `content` varchar(1000) DEFAULT '' COMMENT '文本消息内容',
  `media_id` varchar(200) DEFAULT '',
  `pic_url` varchar(500) DEFAULT '',
  `digest` varchar(200) DEFAULT '',
  `format` varchar(20) DEFAULT '' COMMENT 'voice格式',
  `thumb_media_id` varchar(200) DEFAULT '' COMMENT '视频所需',
  `sent_status` smallint(6) DEFAULT '0' COMMENT '发送结果: 0失败, 1成功',
  `sent_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=144 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_material_article
-- ----------------------------
DROP TABLE IF EXISTS `wx_material_article`;
CREATE TABLE `wx_material_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `short_title` varchar(20) DEFAULT NULL,
  `short_content` varchar(100) DEFAULT '',
  `content` varchar(4000) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `cover_image_url` varchar(200) DEFAULT NULL,
  `cover_thumb_image_url` varchar(200) DEFAULT NULL,
  `thumb_media_id` varchar(200) DEFAULT NULL COMMENT '微信返回的thumb_id',
  `status` smallint(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_material_multimedia
-- ----------------------------
DROP TABLE IF EXISTS `wx_material_multimedia`;
CREATE TABLE `wx_material_multimedia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material_type` smallint(6) DEFAULT '0',
  `title` varchar(50) DEFAULT '',
  `remark` varchar(200) DEFAULT '',
  `media_id` varchar(100) DEFAULT NULL,
  `media_url` varchar(200) DEFAULT NULL,
  `status` smallint(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='多媒体素材表（图片/语音/视频）';

-- ----------------------------
-- Table structure for wx_material_news
-- ----------------------------
DROP TABLE IF EXISTS `wx_material_news`;
CREATE TABLE `wx_material_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `cover_image_url` varchar(200) DEFAULT NULL,
  `digest` varchar(50) DEFAULT NULL,
  `status` smallint(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='多图文素材表';

-- ----------------------------
-- Table structure for wx_material_news_article
-- ----------------------------
DROP TABLE IF EXISTS `wx_material_news_article`;
CREATE TABLE `wx_material_news_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `news_id` int(11) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  `top_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fk_newsid_articleid` (`news_id`,`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='多图文&单图文的多对多关联表';

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
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8;
