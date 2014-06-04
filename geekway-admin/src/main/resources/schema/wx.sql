/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50165
 Source Host           : localhost
 Source Database       : wx

 Target Server Type    : MySQL
 Target Server Version : 50165
 File Encoding         : utf-8

 Date: 06/04/2014 22:11:52 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ito_product`
-- ----------------------------
DROP TABLE IF EXISTS `ito_product`;
CREATE TABLE `ito_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `out_id` varchar(255) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `origin_price` double DEFAULT NULL,
  `post_fee` double DEFAULT NULL,
  `product_thumb_pic_url` varchar(500) DEFAULT NULL,
  `product_pic_url` varchar(500) DEFAULT NULL,
  `buy_url` varchar(400) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ito_product`
-- ----------------------------
BEGIN;
INSERT INTO `ito_product` VALUES ('47', null, '商品SN', '商品信息', '商品描述', null, null, null, null, null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_7796624d555d65d7be686e8499595b7e.jpg', '', '0', '2014-05-25 14:08:55', '2014-05-25 14:08:55');
COMMIT;

-- ----------------------------
--  Table structure for `ito_product_order`
-- ----------------------------
DROP TABLE IF EXISTS `ito_product_order`;
CREATE TABLE `ito_product_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_sn` varchar(100) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `out_id` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `post_fee` double DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `post_sn` varchar(100) DEFAULT NULL,
  `sku_id` int(11) DEFAULT NULL,
  `sku_name` varchar(200) DEFAULT NULL,
  `sku_properties_name` varchar(200) DEFAULT NULL,
  `pay_type` smallint(6) DEFAULT NULL,
  `pay_status` smallint(6) DEFAULT '0',
  `post_name` varchar(50) DEFAULT NULL,
  `post_code` varchar(20) DEFAULT NULL,
  `post_address` varchar(200) DEFAULT NULL,
  `post_mobile` varchar(20) DEFAULT NULL,
  `post_status` smallint(6) DEFAULT '0',
  `status` smallint(6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ito_product_order`
-- ----------------------------
BEGIN;
INSERT INTO `ito_product_order` VALUES ('51', '20140525151446_dc32b5eb6bc74fd885e0fd117e7061fd', null, null, '47', '商品信息', '商品描述', '1', '0', '0', '0', null, '82', null, '3:7;2:10;1:1;', '1', '0', null, null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `ito_product_sku_value`
-- ----------------------------
DROP TABLE IF EXISTS `ito_product_sku_value`;
CREATE TABLE `ito_product_sku_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `sku_prop_value_id` int(11) DEFAULT NULL,
  `sku_pic_url` varchar(255) DEFAULT NULL,
  `sku_thumb_pic_url` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ito_product_sku_value`
-- ----------------------------
BEGIN;
INSERT INTO `ito_product_sku_value` VALUES ('201', '47', '1', null, null, '2014-05-25 14:08:55', '2014-05-25 14:08:55'), ('202', '47', '2', null, null, '2014-05-25 14:08:55', '2014-05-25 14:08:55'), ('203', '47', '4', null, null, '2014-05-25 14:08:55', '2014-05-25 14:08:55'), ('204', '47', '8', null, null, '2014-05-25 14:08:55', '2014-05-25 14:08:55'), ('205', '47', '10', null, null, '2014-05-25 14:08:55', '2014-05-25 14:08:55'), ('206', '47', '6', null, null, '2014-05-25 14:08:55', '2014-05-25 14:08:55'), ('207', '47', '7', null, null, '2014-05-25 14:08:55', '2014-05-25 14:08:55');
COMMIT;

-- ----------------------------
--  Table structure for `ito_sku`
-- ----------------------------
DROP TABLE IF EXISTS `ito_sku`;
CREATE TABLE `ito_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `out_id` varchar(255) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `properties_name` varchar(2000) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `origin_price` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `buy_url` varchar(400) DEFAULT NULL,
  `sku_pic_url` varchar(500) DEFAULT NULL,
  `sku_thumb_pic_url` varchar(500) DEFAULT NULL,
  `alipay_sku_url` varchar(200) DEFAULT NULL,
  `koudaitong_sku_url` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='商品对应的sku表';

-- ----------------------------
--  Records of `ito_sku`
-- ----------------------------
BEGIN;
INSERT INTO `ito_sku` VALUES ('72', '47', null, null, '3:6;2:4;1:1;', '10', '0', '0', null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_bc3669ca608020c377db2caade40377d.jpg', null, 'http://www.alipay.com', 'http://www.koudaitong.com', '2014-05-25 14:08:55', '2014-05-25 14:09:13'), ('73', '47', null, null, '3:6;2:4;1:2;', '10', '0', '0', null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_7796624d555d65d7be686e8499595b7e.jpg', null, 'http://www.alipay.com', 'http://www.koudaitong.com', '2014-05-25 14:08:55', '2014-05-25 14:09:13'), ('74', '47', null, null, '3:6;2:8;1:1;', '10', '0', '0', null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_7796624d555d65d7be686e8499595b7e.jpg', null, 'http://www.alipay.com', 'http://www.koudaitong.com', '2014-05-25 14:08:55', '2014-05-25 14:09:13'), ('75', '47', null, null, '3:6;2:8;1:2;', '10', '0', '0', null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_7796624d555d65d7be686e8499595b7e.jpg', null, 'http://www.alipay.com', 'http://www.koudaitong.com', '2014-05-25 14:08:55', '2014-05-25 14:09:13'), ('76', '47', null, null, '3:6;2:10;1:1;', '10', '0', '0', null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_7796624d555d65d7be686e8499595b7e.jpg', null, 'http://www.alipay.com', 'http://www.koudaitong.com', '2014-05-25 14:08:55', '2014-05-25 14:09:13'), ('77', '47', null, null, '3:6;2:10;1:2;', '10', '0', '0', null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_7796624d555d65d7be686e8499595b7e.jpg', null, 'http://www.alipay.com', 'http://www.koudaitong.com', '2014-05-25 14:08:55', '2014-05-25 14:09:13'), ('78', '47', null, null, '3:7;2:4;1:1;', '10', '0', '0', null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_7796624d555d65d7be686e8499595b7e.jpg', null, 'http://www.alipay.com', 'http://www.koudaitong.com', '2014-05-25 14:08:55', '2014-05-25 14:09:13'), ('79', '47', null, null, '3:7;2:4;1:2;', '10', '0', '0', null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_7796624d555d65d7be686e8499595b7e.jpg', null, 'http://www.alipay.com', 'http://www.koudaitong.com', '2014-05-25 14:08:55', '2014-05-25 14:09:13'), ('80', '47', null, null, '3:7;2:8;1:1;', '10', '0', '0', null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_7796624d555d65d7be686e8499595b7e.jpg', null, 'http://www.alipay.com', 'http://www.koudaitong.com', '2014-05-25 14:08:55', '2014-05-25 14:09:13'), ('81', '47', null, null, '3:7;2:8;1:2;', '10', '0', '0', null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_7796624d555d65d7be686e8499595b7e.jpg', null, 'http://www.alipay.com', 'http://www.koudaitong.com', '2014-05-25 14:08:55', '2014-05-25 14:09:13'), ('82', '47', null, null, '3:7;2:10;1:1;', '10', '0', '0', null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_7796624d555d65d7be686e8499595b7e.jpg', null, 'http://www.alipay.com', 'http://www.koudaitong.com', '2014-05-25 14:08:55', '2014-05-25 14:09:13'), ('83', '47', null, null, '3:7;2:10;1:2;', '10', '0', '0', null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_7796624d555d65d7be686e8499595b7e.jpg', null, 'http://www.alipay.com', 'http://www.koudaitong.com', '2014-05-25 14:08:55', '2014-05-25 14:09:13');
COMMIT;

-- ----------------------------
--  Table structure for `ito_sku_image`
-- ----------------------------
DROP TABLE IF EXISTS `ito_sku_image`;
CREATE TABLE `ito_sku_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `sku_id` int(6) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(500) DEFAULT '',
  `sku_thumb_pic_url` varchar(500) DEFAULT '',
  `sku_pic_url` varchar(500) DEFAULT '',
  `sort` smallint(6) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COMMENT='sku对应的图片列表，与sku表为多对一的关系';

-- ----------------------------
--  Records of `ito_sku_image`
-- ----------------------------
BEGIN;
INSERT INTO `ito_sku_image` VALUES ('76', '47', '72', '图片1', '图片1', null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_bc3669ca608020c377db2caade40377d.jpg', '10', '2014-05-25 14:17:58', '2014-05-25 14:17:58'), ('77', '47', '72', '图片2', '图片2', null, 'http://localhost:8080/geekway-admin/staticFile//image/20140525/original/1_feec9d57be6604f84a87f95cf3bb2f03.jpg', '20', '2014-05-25 14:18:16', '2014-05-25 14:18:16');
COMMIT;

-- ----------------------------
--  Table structure for `ito_sku_prop`
-- ----------------------------
DROP TABLE IF EXISTS `ito_sku_prop`;
CREATE TABLE `ito_sku_prop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ito_sku_prop`
-- ----------------------------
BEGIN;
INSERT INTO `ito_sku_prop` VALUES ('1', '尺寸', '尺寸', '2014-05-02 21:51:47', '2014-05-02 21:51:50'), ('2', '颜色', '颜色', '2014-05-02 21:52:29', '2014-05-02 21:52:31'), ('3', '材质', '材质', '2014-05-07 23:24:34', '2014-05-07 23:24:37');
COMMIT;

-- ----------------------------
--  Table structure for `ito_sku_prop_value`
-- ----------------------------
DROP TABLE IF EXISTS `ito_sku_prop_value`;
CREATE TABLE `ito_sku_prop_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sku_prop_id` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `sku_pic_url` varchar(500) DEFAULT '',
  `sku_thumb_pic_url` varchar(500) DEFAULT '',
  `sort` smallint(6) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ito_sku_prop_value`
-- ----------------------------
BEGIN;
INSERT INTO `ito_sku_prop_value` VALUES ('1', '1', '20 INCH', '20 Inch 标准登机箱', 'http://wximg.geekway.com.cn/staticFile//image/20140523/original/1_172969001ed07fcafc835aeb0940f698.png', 'http://wximg.jinwanr.com.cn/staticFile//image/20140518/original/1_c2296190dc317ef3d4a012d7996b61bb.jpg', '0', '2014-05-02 21:55:42', '2014-05-23 11:11:53'), ('2', '1', '24 INCH', '小型托运箱', 'http://wximg.geekway.com.cn/staticFile//image/20140523/original/1_a80f540a8f7fe5be6e95cae76a9f62e6.png', 'http://wximg.jinwanr.com.cn/staticFile//image/20140518/original/1_c2296190dc317ef3d4a012d7996b61bb.jpg', '0', '2014-05-02 21:56:25', '2014-05-23 11:11:38'), ('3', '2', '砖红', '砖红', 'http://wximg.geekway.com.cn/staticFile//image/20140523/original/1_f6bd8708cc531cfb7d785f297682f946.png', 'http://wximg.jinwanr.com.cn/staticFile//image/20140518/original/1_c2296190dc317ef3d4a012d7996b61bb.jpg', '0', '2014-05-02 21:56:43', '2014-05-23 11:05:11'), ('4', '2', '芥末黄', '芥末黄', 'http://wximg.geekway.com.cn/staticFile//image/20140523/original/1_91b0bbf12bf2d70142dc76deadd908cb.png', 'http://wximg.jinwanr.com.cn/staticFile//image/20140518/original/1_c2296190dc317ef3d4a012d7996b61bb.jpg', '0', '2014-05-02 21:57:06', '2014-05-23 11:05:44'), ('5', '2', '深林绿', '深林绿', 'http://wximg.geekway.com.cn/staticFile//image/20140523/original/1_0cdc0a596b9c6918e0508523ca393012.png', 'http://wximg.jinwanr.com.cn/staticFile//image/20140518/original/1_c2296190dc317ef3d4a012d7996b61bb.jpg', '0', '2014-05-02 21:57:25', '2014-05-23 11:06:29'), ('6', '3', '简洁光面', '简洁光面', 'http://wximg.geekway.com.cn/staticFile//image/20140523/original/1_2395b126317c9ce89c82dab6322de9bf.png', 'http://wximg.jinwanr.com.cn/staticFile//image/20140518/original/1_c2296190dc317ef3d4a012d7996b61bb.jpg', '0', '2014-05-07 23:25:47', '2014-05-23 11:09:51'), ('7', '3', '细密条纹', '细密条纹', 'http://wximg.geekway.com.cn/staticFile//image/20140523/original/1_1e19615a2b6455062ed3af6b838c553a.png', 'http://wximg.jinwanr.com.cn/staticFile//image/20140518/original/1_c2296190dc317ef3d4a012d7996b61bb.jpg', '0', '2014-05-07 23:25:30', '2014-05-23 11:10:13'), ('8', '2', '赤铜色', '赤铜色', 'http://wximg.geekway.com.cn/staticFile//image/20140523/original/1_5669eca1ec2fc3e2601a048e6217cbdf.png', null, '0', '2014-05-23 11:07:04', '2014-05-23 11:07:04'), ('9', '2', '碳黑色', '碳黑色', 'http://wximg.geekway.com.cn/staticFile//image/20140523/original/1_689bfd1c5c52f48f509fd413241d28c4.png', null, '0', '2014-05-23 11:07:42', '2014-05-23 11:07:42'), ('10', '2', '藏青色', '藏青色', 'http://wximg.geekway.com.cn/staticFile//image/20140523/original/1_eb21527ac17958738cb44428625f4345.png', null, '0', '2014-05-23 11:08:56', '2014-05-23 11:08:56'), ('11', '1', '28 INCH', '28 INCH 大型托运箱', 'http://wximg.geekway.com.cn/staticFile//image/20140523/original/1_cd04e61428b4ecb56190956f7f5e9c95.png', null, '0', '2014-05-23 11:12:20', '2014-05-23 11:12:20');
COMMIT;

-- ----------------------------
--  Table structure for `ito_slider`
-- ----------------------------
DROP TABLE IF EXISTS `ito_slider`;
CREATE TABLE `ito_slider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `slider_thumb_pic_url` varchar(500) DEFAULT NULL,
  `slider_pic_url` varchar(500) DEFAULT NULL,
  `sort` int(11) DEFAULT '0',
  `status` smallint(6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ito_slider`
-- ----------------------------
BEGIN;
INSERT INTO `ito_slider` VALUES ('1', '首屏轮播1', '首屏轮播1', 'http://wximg.geekway.com.cn/staticFile//image/20140522/original/1_1f32d71a505e80bbfb612f97f062f1f3.jpg', 'http://wximg.geekway.com.cn/staticFile//image/20140522/original/1_1f32d71a505e80bbfb612f97f062f1f3.jpg', '10', null, '2014-05-22 23:57:25', null), ('2', '首屏轮播2', '首屏轮播2', 'http://wximg.geekway.com.cn/staticFile//image/20140522/original/1_9188ea903eaac2be901f544c8adbd098.jpg', 'http://wximg.geekway.com.cn/staticFile//image/20140522/original/1_9188ea903eaac2be901f544c8adbd098.jpg', '20', null, '2014-05-22 23:57:43', null), ('3', '首屏轮播3', '首屏轮播3', 'http://wximg.geekway.com.cn/staticFile//image/20140522/original/1_32a27b97cb33915eaf2a58773a61ddbc.jpg', 'http://wximg.geekway.com.cn/staticFile//image/20140522/original/1_32a27b97cb33915eaf2a58773a61ddbc.jpg', '30', null, '2014-05-22 23:58:03', null), ('4', '首屏轮播4', '首屏轮播4', 'http://wximg.geekway.com.cn/staticFile//image/20140522/original/1_60f2d35b5bb18d91113daecb89de6526.jpg', 'http://wximg.geekway.com.cn/staticFile//image/20140522/original/1_60f2d35b5bb18d91113daecb89de6526.jpg', '40', null, '2014-05-22 23:58:23', null);
COMMIT;

-- ----------------------------
--  Table structure for `ito_system_status`
-- ----------------------------
DROP TABLE IF EXISTS `ito_system_status`;
CREATE TABLE `ito_system_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` smallint(6) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ito_system_status`
-- ----------------------------
BEGIN;
INSERT INTO `ito_system_status` VALUES ('1', null, '2014-05-22 23:57:25', null), ('2', null, '2014-05-22 23:57:43', null), ('3', null, '2014-05-22 23:58:03', null), ('4', null, '2014-05-22 23:58:23', null);
COMMIT;

-- ----------------------------
--  Table structure for `ito_wwj_record`
-- ----------------------------
DROP TABLE IF EXISTS `ito_wwj_record`;
CREATE TABLE `ito_wwj_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(100) DEFAULT NULL,
  `wawaji_qrcode` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ito_wwj_record`
-- ----------------------------
BEGIN;
INSERT INTO `ito_wwj_record` VALUES ('47', '1', null, '2014-05-25 14:08:55', '2014-05-25 14:08:55');
COMMIT;

-- ----------------------------
--  Table structure for `klh_daily_sign`
-- ----------------------------
DROP TABLE IF EXISTS `klh_daily_sign`;
CREATE TABLE `klh_daily_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_open_id` varchar(100) DEFAULT NULL,
  `sign_date` date DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `klh_daily_sign`
-- ----------------------------
BEGIN;
INSERT INTO `klh_daily_sign` VALUES ('52', '1', '2014-06-02', '2014-06-02 23:05:51', null);
COMMIT;

-- ----------------------------
--  Table structure for `klh_product`
-- ----------------------------
DROP TABLE IF EXISTS `klh_product`;
CREATE TABLE `klh_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `origin_price` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `product_thumb_pic_url` varchar(255) DEFAULT NULL,
  `product_pic_url` varchar(500) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `klh_product`
-- ----------------------------
BEGIN;
INSERT INTO `klh_product` VALUES ('48', 'XXX箱包', '商品描述: *', '4', '3', '2', null, 'http://wximg.jinwanr.com.cn/staticFile//image/20140511/original/1_29149a6beafbd9fdcd9c24a646b47fd4.jpg', '0', '2014-05-11 00:01:56', '2014-05-11 00:03:43'), ('51', '袁总V5', '商品描述', '100', '90', '80', null, 'http://wximg.jinwanr.com.cn/staticFile//image/20140511/original/1_14be1e726693a6bb298d140d30c45697.jpg', '0', '2014-05-11 22:35:30', '2014-05-11 22:35:30'), ('52', 'xxx-1', '这个一个描述1', '1000', '2000', '100', null, 'http://wximg.jinwanr.com.cn/staticFile//image/20140511/original/1_9a9048672f2472cd9544f91771618845.jpg', '0', '2014-05-11 23:25:13', '2014-05-11 23:25:13'), ('53', 'xxx-2', 'xxx-2描述', '1999', '199', '100', null, 'http://wximg.jinwanr.com.cn/staticFile//image/20140511/original/1_5a2ed5660c471fee4d1b47ffff3ffae2.jpg', '0', '2014-05-11 23:26:56', '2014-05-11 23:26:56');
COMMIT;

-- ----------------------------
--  Table structure for `klh_setting`
-- ----------------------------
DROP TABLE IF EXISTS `klh_setting`;
CREATE TABLE `klh_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bind_realname` smallint(6) DEFAULT '1',
  `bind_mobile` smallint(6) DEFAULT '0',
  `bind_birthday` smallint(6) DEFAULT '0',
  `bind_email` smallint(6) DEFAULT '0',
  `bind_address` smallint(6) DEFAULT '0',
  `bind_score` int(11) DEFAULT '0',
  `sign_score` int(11) DEFAULT '0',
  `vote_score` int(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `klh_setting`
-- ----------------------------
BEGIN;
INSERT INTO `klh_setting` VALUES ('1', '1', '2', '3', '4', '5', '100', '2', '3', '2014-05-28 17:17:35', '2014-05-31 17:38:54');
COMMIT;

-- ----------------------------
--  Table structure for `klh_user_profile`
-- ----------------------------
DROP TABLE IF EXISTS `klh_user_profile`;
CREATE TABLE `klh_user_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_open_id` varchar(100) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_open_id` (`user_open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `klh_user_score_log`
-- ----------------------------
DROP TABLE IF EXISTS `klh_user_score_log`;
CREATE TABLE `klh_user_score_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_open_id` varchar(100) DEFAULT NULL,
  `score_change` int(11) DEFAULT '0',
  `reason` varchar(200) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `klh_user_score_log`
-- ----------------------------
BEGIN;
INSERT INTO `klh_user_score_log` VALUES ('55', '1', '2', '用户签到，增加【2】积分', '2014-06-02 23:05:51', null);
COMMIT;

-- ----------------------------
--  Table structure for `klh_vote`
-- ----------------------------
DROP TABLE IF EXISTS `klh_vote`;
CREATE TABLE `klh_vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `thumb_pic_url` varchar(500) DEFAULT NULL,
  `pic_url` varchar(500) DEFAULT NULL,
  `sort` int(11) DEFAULT '0',
  `max_pick_limit` smallint(6) DEFAULT '1',
  `need_be_buyer` smallint(6) DEFAULT '0',
  `need_bind` smallint(6) DEFAULT '0',
  `max_repeat_limit` int(11) DEFAULT '1',
  `status` smallint(6) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `klh_vote`
-- ----------------------------
BEGIN;
INSERT INTO `klh_vote` VALUES ('48', 'test', '123123123', '', '', '1', '0', '0', null, '1', null, '2014-05-19 17:44:19', null, null, null), ('49', 'test', 'test', null, null, null, '1', '0', null, '1', null, '2014-05-27 14:17:38', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `klh_vote_option`
-- ----------------------------
DROP TABLE IF EXISTS `klh_vote_option`;
CREATE TABLE `klh_vote_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vote_id` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `thumb_pic_url` varchar(500) DEFAULT NULL,
  `pic_url` varchar(500) DEFAULT NULL,
  `sort` int(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `klh_vote_option`
-- ----------------------------
BEGIN;
INSERT INTO `klh_vote_option` VALUES ('49', '49', '123', '234', null, '', '34', '2014-05-27 16:09:39', '2014-05-27 16:09:39');
COMMIT;

-- ----------------------------
--  Table structure for `klh_vote_result`
-- ----------------------------
DROP TABLE IF EXISTS `klh_vote_result`;
CREATE TABLE `klh_vote_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vote_id` int(11) DEFAULT NULL,
  `vote_option_id` int(11) DEFAULT NULL,
  `vote_user_open_id` varchar(100) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `klh_vote_result`
-- ----------------------------
BEGIN;
INSERT INTO `klh_vote_result` VALUES ('48', null, '0', '1', '2014-05-19 17:44:19', null);
COMMIT;

-- ----------------------------
--  Table structure for `wx_article`
-- ----------------------------
DROP TABLE IF EXISTS `wx_article`;
CREATE TABLE `wx_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `short_title` varchar(20) DEFAULT NULL,
  `short_content` varchar(200) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `status` smallint(11) NOT NULL DEFAULT '0',
  `cover_image_url` varchar(200) DEFAULT NULL,
  `cover_thumb_image_url` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `wx_article`
-- ----------------------------
BEGIN;
INSERT INTO `wx_article` VALUES ('1', '第一篇文章', '第一篇文章短标题', '第一篇文章内容概要', '<p><em><span style=\"color:#008000\"><strong><img alt=\"\" src=\"http://localhost:8080/geekway-admin/staticFile//image/20140415/original/1_e2e74e62ff1f650dffab2f30a47c3f68.JPG\" style=\"width:100%\" />第一篇文章</strong></span></em></p>\r\n', null, '1', 'http://localhost:8080/geekway-admin/staticFile//image/20140415/original/1_99531e929e41e643a6ad6856e7e15b3f.JPG', null, null, '2014-04-15 23:26:55'), ('2', '第二篇文章', '第二篇文章——短标题', '第二篇文章——概要', '<p><span style=\"color:#008080\"><span style=\"font-size:16px\"><em><strong>第二篇文章</strong></em></span></span></p>\r\n', null, '1', 'http://localhost:8080/geekway-admin/staticFile//image/20140414/original/1_e6d42eaf2fed53361202abe2509b39eb.JPG', null, null, '2014-04-14 22:54:19'), ('3', 'Moto素组', 'Moto素组', 'Moto素组', '<p><img alt=\"\" src=\"http://localhost:8080/geekway-admin/staticFile//image/20140415/original/1_a827a2f95c2eded55017bf7ca519811f.JPG\" style=\"height:2256px; width:1504px\" /></p>\r\n', null, '1', 'http://localhost:8080/geekway-admin/staticFile//image/20140415/original/1_4cb919063bca7178c5008742748b3a67.JPG', null, null, '2014-04-15 22:19:29'), ('4', '4', '4', '4', null, null, '0', null, null, null, null), ('5', '5', '5', '5', null, null, '0', null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `wx_code_module`
-- ----------------------------
DROP TABLE IF EXISTS `wx_code_module`;
CREATE TABLE `wx_code_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(20) NOT NULL DEFAULT '',
  `module_type` smallint(6) NOT NULL DEFAULT '1' COMMENT 'æ¨¡å—æ•°æ®ç±»åž‹ï¼š 1, å†…éƒ¨æ•°æ®; 2, å¤–éƒ¨æŽ¥å£æ•°æ®',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `wx_code_module`
-- ----------------------------
BEGIN;
INSERT INTO `wx_code_module` VALUES ('1', '动态模块', '1', null, '2014-04-12 15:16:19');
COMMIT;

-- ----------------------------
--  Table structure for `wx_code_module_article`
-- ----------------------------
DROP TABLE IF EXISTS `wx_code_module_article`;
CREATE TABLE `wx_code_module_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  `top_time` datetime DEFAULT NULL COMMENT 'ç½®é¡¶æ—¶é—´',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `wx_code_module_article`
-- ----------------------------
BEGIN;
INSERT INTO `wx_code_module_article` VALUES ('1', '1', '1', '2014-03-29 12:05:59', '2014-03-29 12:05:48', '2014-03-29 12:05:52'), ('2', '1', '2', '2014-03-29 12:06:06', '2014-03-29 12:05:50', '2014-03-29 12:05:56'), ('3', '1', '3', '2014-04-15 21:58:58', null, null), ('4', '1', '4', '2014-04-15 21:59:11', null, null), ('5', '1', '5', '2014-04-15 21:59:15', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `wx_command`
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `wx_command`
-- ----------------------------
BEGIN;
INSERT INTO `wx_command` VALUES ('5', 'this is a test', '1', '2', '1', 'test', '4', null, '1', '2014-05-07 00:30:36', '2014-05-07 00:30:36'), ('7', 'test', '1', '3', '1', null, '4', null, '0', '2014-05-12 23:20:31', '2014-05-12 23:20:31');
COMMIT;

-- ----------------------------
--  Table structure for `wx_command_material`
-- ----------------------------
DROP TABLE IF EXISTS `wx_command_material`;
CREATE TABLE `wx_command_material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `command_id` int(11) DEFAULT NULL,
  `material_id` int(11) DEFAULT NULL,
  `top_time` datetime DEFAULT NULL COMMENT '置顶时间',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_module_article` (`command_id`,`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `wx_command_material`
-- ----------------------------
BEGIN;
INSERT INTO `wx_command_material` VALUES ('16', '5', '6', '2014-05-07 22:49:06', '2014-05-07 22:49:09', '2014-05-07 22:49:11'), ('17', '5', '7', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `wx_customize_menu`
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `wx_customize_menu`
-- ----------------------------
BEGIN;
INSERT INTO `wx_customize_menu` VALUES ('3', '0', '合作案例', 'case', 'click', 'http://www.jinwanr.com.cn', '200', '1', null, '2014-04-19 19:47:19'), ('4', '3', 'Geekway', 'geekway', 'view', 'http://www.geekway.com.cn', '215', '1', null, '2014-05-24 11:20:13'), ('5', '3', '华贸城生活圈', 'huamaolife', 'click', '', '220', '1', null, '2014-04-19 19:49:42'), ('6', '0', '功能介绍', 'func', 'click', '', '100', '1', null, '2014-04-19 19:46:49'), ('7', '3', '易点网络', 'yidian', 'click', '', '210', '1', null, '2014-04-19 19:50:12'), ('8', '0', '关于我们', 'aboutus', 'click', '', '300', '1', null, '2014-04-19 19:47:55'), ('9', '6', '微信平台', 'platform', 'click', '', '110', '1', null, '2014-04-19 19:48:52');
COMMIT;

-- ----------------------------
--  Table structure for `wx_default_reply`
-- ----------------------------
DROP TABLE IF EXISTS `wx_default_reply`;
CREATE TABLE `wx_default_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subscribe_reply` varchar(255) DEFAULT NULL,
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
--  Records of `wx_default_reply`
-- ----------------------------
BEGIN;
INSERT INTO `wx_default_reply` VALUES ('1', '新用户关注', '11', '22', '33', '44', '55', '66', null, '2014-04-05 20:18:14');
COMMIT;

-- ----------------------------
--  Table structure for `wx_event_code`
-- ----------------------------
DROP TABLE IF EXISTS `wx_event_code`;
CREATE TABLE `wx_event_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_type` smallint(6) DEFAULT NULL,
  `event_code` varchar(30) DEFAULT NULL,
  `display_type` smallint(6) DEFAULT '1',
  `reply_content` varchar(200) DEFAULT NULL,
  `module_id` int(11) DEFAULT '0',
  `module_desc` varchar(50) DEFAULT '',
  `row_limit` smallint(6) DEFAULT '4',
  `publish_status` smallint(1) DEFAULT NULL,
  `status` smallint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `module_id` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `wx_event_code`
-- ----------------------------
BEGIN;
INSERT INTO `wx_event_code` VALUES ('3', '1', 'this is a test', '2', '', '1', '动态模块', '4', null, '1', null, '2014-04-12 15:17:17'), ('4', '2', 'welcome', '1', '这是一个按钮点击事件', '0', '', '4', null, '1', '2014-04-13 10:33:22', '2014-04-13 10:33:22'), ('5', '3', '', '1', '关注事件', '0', '', '4', null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `wx_history_message`
-- ----------------------------
DROP TABLE IF EXISTS `wx_history_message`;
CREATE TABLE `wx_history_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(50) DEFAULT NULL,
  `message` varchar(2000) DEFAULT NULL,
  `message_type` smallint(6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `wx_material`
-- ----------------------------
DROP TABLE IF EXISTS `wx_material`;
CREATE TABLE `wx_material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material_type` smallint(6) DEFAULT '0' COMMENT '0: 文本, 1: 图文',
  `message_id` varchar(50) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `short_title` varchar(20) DEFAULT NULL,
  `short_content` varchar(100) DEFAULT '',
  `content` varchar(4000) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `status` smallint(11) DEFAULT '0',
  `cover_image_url` varchar(200) DEFAULT NULL,
  `cover_thumb_image_url` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `wx_material`
-- ----------------------------
BEGIN;
INSERT INTO `wx_material` VALUES ('6', '1', '', '1', '2', 'come on', '3', null, '1', 'http://localhost:8080/geekway-admin/staticFile//image/20140507/original/1_09a702a4fd02c8927d99a8c5e0d73d69.jpg', 'http://localhost:8080/geekway-admin/staticFile//image/20140507/original/1_09a702a4fd02c8927d99a8c5e0d73d69.jpg', '2014-05-07 00:31:30', '2014-05-07 00:31:30'), ('7', '2', '', 'title', 'shortTitle', '图文内容概要', '<p><span style=\"color:#006400\"><span style=\"font-size:16px\"><strong><span style=\"font-family:open sans,sans-serif\"><span style=\"background-color:#FFFFE0\">图文内容详情</span></span></strong></span></span></p>\r\n', null, '1', 'http://localhost:8080/geekway-admin/staticFile//image/20140507/original/1_09a702a4fd02c8927d99a8c5e0d73d69.jpg', 'http://localhost:8080/geekway-admin/staticFile//image/20140507/original/1_09a702a4fd02c8927d99a8c5e0d73d69.jpg', '2014-05-07 00:35:19', '2014-05-07 00:35:19');
COMMIT;

-- ----------------------------
--  Table structure for `wx_material_article`
-- ----------------------------
DROP TABLE IF EXISTS `wx_material_article`;
CREATE TABLE `wx_material_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `short_title` varchar(20) DEFAULT NULL,
  `short_content` varchar(100) DEFAULT '',
  `content` varchar(4000) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `status` smallint(11) DEFAULT '0',
  `cover_image_url` varchar(200) DEFAULT NULL,
  `cover_thumb_image_url` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `wx_material_article`
-- ----------------------------
BEGIN;
INSERT INTO `wx_material_article` VALUES ('1', 'a', 'b', 'c', '<p>d</p>\r\n', null, '1', '', null, '2014-05-12 23:16:39', '2014-05-12 23:16:39'), ('2', '1', '2', '3', '<p>4</p>\r\n', null, '1', '', null, '2014-05-12 23:17:01', '2014-05-12 23:17:01');
COMMIT;

-- ----------------------------
--  Table structure for `wx_material_news`
-- ----------------------------
DROP TABLE IF EXISTS `wx_material_news`;
CREATE TABLE `wx_material_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `row_limit` smallint(6) DEFAULT '4',
  `status` smallint(6) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `wx_material_news`
-- ----------------------------
BEGIN;
INSERT INTO `wx_material_news` VALUES ('1', 'xxxx', null, '4', '1', '2014-05-12 23:22:08', '2014-05-12 23:22:08');
COMMIT;

-- ----------------------------
--  Table structure for `wx_material_news_article`
-- ----------------------------
DROP TABLE IF EXISTS `wx_material_news_article`;
CREATE TABLE `wx_material_news_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `news_id` int(11) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  `top_time` datetime DEFAULT NULL COMMENT 'ç½®é¡¶æ—¶é—´',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `wx_material_news_article`
-- ----------------------------
BEGIN;
INSERT INTO `wx_material_news_article` VALUES ('1', '1', '2', '2014-05-12 23:22:36', '2014-05-12 23:22:22', null), ('2', '1', '1', '2014-05-12 23:22:29', '2014-05-12 23:22:29', null);
COMMIT;

-- ----------------------------
--  Table structure for `wx_mp_user`
-- ----------------------------
DROP TABLE IF EXISTS `wx_mp_user`;
CREATE TABLE `wx_mp_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(100) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `wx_mp_user`
-- ----------------------------
BEGIN;
INSERT INTO `wx_mp_user` VALUES ('18', 'oxGeHjthnXf92vuaCmkYHmzA-PFo', '冉-Rana', '2', '海淀', '北京', '中国', 'zh_CN', 'http://wx.qlogo.cn/mmopen/PiajxSqBRaELyCNDV0vRPTH9he20GNtZ0J8QTIA4LslOpfrPia8WDEiauo3RJu6Czt59DnYj455riaajwy9MIbUuYw/0', '1', '1', null, '2014-05-18 15:56:18', '2014-05-18 22:44:37'), ('19', 'oxGeHjg87dS82dsp4iP4SE1iVujA', '李乾', '1', '朝阳', '北京', '中国', 'zh_CN', 'http://wx.qlogo.cn/mmopen/Q3auHgzwzM5fSr527vWs39Q4Q5sGBTpLn1mOzAQ2GkakJscdp2jnYX4OIeKvmGhpBicvtxf8Ely8WNnOD8Ad7TA/0', '1', '1', null, '2014-05-18 15:56:18', '2014-05-18 22:44:38');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
