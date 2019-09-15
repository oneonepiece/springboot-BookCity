/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 8.0.15 : Database - book_city
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `book_city`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `admin_id` int(11) DEFAULT NULL,
  `admin_name` varchar(150) DEFAULT NULL,
  `admin_pwd` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`admin_id`,`admin_name`,`admin_pwd`) values (1,'123','123');

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `b_name` varchar(600) DEFAULT NULL,
  `author` varchar(150) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `curr_price` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `press` varchar(300) DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `edition` int(11) DEFAULT NULL,
  `page_num` int(11) DEFAULT NULL,
  `word_num` int(11) DEFAULT NULL,
  `print_time` datetime DEFAULT NULL,
  `book_size` int(11) DEFAULT NULL,
  `paper` varchar(150) DEFAULT NULL,
  `image_uri` varchar(300) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `classify_id` int(11) DEFAULT NULL,
  `isbn` varchar(300) DEFAULT NULL,
  `book_num` varchar(64) DEFAULT NULL,
  `status` int(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`b_id`,`b_name`,`author`,`price`,`curr_price`,`discount`,`press`,`publish_time`,`edition`,`page_num`,`word_num`,`print_time`,`book_size`,`paper`,`image_uri`,`order_by`,`classify_id`,`isbn`,`book_num`,`status`) values (1,'斗罗大陆','Alin',55.5,60.5,9.5,'中国邮电出版社','2019-09-06 15:56:20',1,325,100000,'2019-09-06 15:56:20',16,'辣鸡纸','/demo/img/1.png',0,7,'123654','100100100',0),(2,'斗罗大陆','Alin',55.5,60.5,9.5,'中国邮电出版社','2019-09-06 15:57:07',1,325,100000,'2019-09-06 15:57:07',16,'辣鸡纸','/demo/img/1.png',0,7,'123654','100100100',0),(3,'斗罗大陆','Alin',55.5,60.5,9.5,'中国邮电出版社','2019-09-06 15:57:07',1,325,100000,'2019-09-06 15:57:07',16,'辣鸡纸','/demo/img/1.png',0,7,'123654','100100100',0),(4,'斗罗大陆','Alin',55.5,60.5,9.5,'中国邮电出版社','2019-09-06 15:57:07',1,325,100000,'2019-09-06 15:57:07',16,'辣鸡纸','/demo/img/1.png',0,7,'123654','100100100',0),(5,'斗罗大陆','Alin',55.5,60.5,9.5,'中国邮电出版社','2019-09-06 15:57:07',1,325,100000,'2019-09-06 15:57:07',16,'辣鸡纸','/demo/img/1.png',0,7,'123654','100100100',0),(6,'斗罗大陆','Alin',55.5,60.5,9.5,'中国邮电出版社','2019-09-06 15:57:07',1,325,100000,'2019-09-06 15:57:07',16,'辣鸡纸','/demo/img/1.png',0,7,'123654','100100100',0),(7,'斗罗大陆','Alin',55.5,60.5,9.5,'中国邮电出版社','2019-09-06 15:57:07',1,325,100000,'2019-09-06 15:57:07',16,'辣鸡纸','/demo/img/1.png',0,7,'123654','100100100',0),(8,'斗罗大陆','Alin',55.5,60.5,9.5,'中国邮电出版社','2019-09-06 15:57:07',1,325,100000,'2019-09-06 15:57:07',16,'辣鸡纸','/demo/img/1.png',0,7,'123654','100100100',0),(9,'斗罗大陆','Alin',55.5,60.5,9.5,'中国邮电出版社','2019-09-06 15:57:07',1,325,100000,'2019-09-06 15:57:07',16,'辣鸡纸','/demo/img/1.png',0,7,'123654','100100100',0),(10,'斗罗大陆','Alin',55.5,60.5,9.5,'中国邮电出版社','2019-09-06 15:57:07',1,325,100000,'2019-09-06 15:57:07',16,'辣鸡纸','/demo/img/1.png',0,7,'123654','100100100',0),(11,'斗罗大陆','Alin',55.5,60.5,9.5,'中国邮电出版社','2019-09-06 15:57:07',1,325,100000,'2019-09-06 15:57:07',16,'辣鸡纸','/demo/img/1.png',0,7,'123654','100100100',0);

/*Table structure for table `cart_item` */

DROP TABLE IF EXISTS `cart_item`;

CREATE TABLE `cart_item` (
  `cart_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `b_id` int(11) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`cart_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `cart_item` */

insert  into `cart_item`(`cart_item_id`,`quantity`,`b_id`,`u_id`,`order_by`) values (1,2,3,26,NULL),(2,1,5,26,NULL),(3,4,7,26,NULL);

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `category_id` int(11) DEFAULT NULL,
  `category_name` varchar(150) DEFAULT NULL,
  `category_desc` varchar(600) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`category_id`,`category_name`,`category_desc`,`order_by`) values (1,'程序设计','3123',NULL),(2,'办公室用书','21312',NULL),(3,'多媒体','213',NULL),(4,'操作系统','21312',NULL),(5,'分类5','312',NULL);

/*Table structure for table `classify` */

DROP TABLE IF EXISTS `classify`;

CREATE TABLE `classify` (
  `classify_id` int(11) DEFAULT NULL,
  `classify_name` varchar(150) DEFAULT NULL,
  `classify_desc` varchar(600) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `classify` */

insert  into `classify`(`classify_id`,`classify_name`,`classify_desc`,`order_by`,`category_id`) values (2,'品类2','1233123',NULL,2),(5,'品类5','12321',NULL,4),(6,'品类6','3213',NULL,5),(7,'java学习指南','程序设计',NULL,1),(8,'品类8','3213',NULL,2),(9,'品类9','21321312',NULL,3),(10,'品类10','12312',NULL,4),(11,'品类11','3123',NULL,5);

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `o_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_time` datetime DEFAULT NULL,
  `total` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `address` varchar(600) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `o_number` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`o_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `order` */

insert  into `order`(`o_id`,`order_time`,`total`,`status`,`address`,`u_id`,`o_number`) values (1,'2019-09-09 21:25:19',181.5,0,'广西南宁',26,'123abc'),(3,'2019-09-11 00:12:17',60.5,1,'玉林',26,'124abc'),(9,'2019-09-14 15:46:59',302.5,1,NULL,26,'2019-09-14-23-46-67065');

/*Table structure for table `order_item` */

DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
  `order_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `b_id` int(11) DEFAULT NULL,
  `o_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `order_item` */

insert  into `order_item`(`order_item_id`,`quantity`,`subtotal`,`b_id`,`o_id`) values (1,1,60.5,3,1),(2,2,121,4,1),(3,1,60.5,2,3),(6,5,302.5,4,9);

/*Table structure for table `tb_permission` */

DROP TABLE IF EXISTS `tb_permission`;

CREATE TABLE `tb_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` varchar(20) NOT NULL COMMENT '权限id',
  `name` varchar(100) NOT NULL COMMENT '权限名称',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) DEFAULT NULL COMMENT '权限访问路径',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级权限id',
  `type` int(1) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `order_num` int(3) DEFAULT '0' COMMENT '排序',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `status` int(1) NOT NULL COMMENT '状态：1有效；2删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `tb_permission` */

insert  into `tb_permission`(`id`,`permission_id`,`name`,`description`,`url`,`perms`,`parent_id`,`type`,`order_num`,`icon`,`status`,`create_time`,`update_time`) values (32,'1','permit','gai','1','1',1,1,1,'1',1,'2019-08-20 21:53:52','2019-08-20 21:53:55'),(33,'2','delete',NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL);

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `status` int(1) NOT NULL COMMENT '状态：1有效；2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tb_role` */

insert  into `tb_role`(`id`,`role_id`,`name`,`description`,`status`,`create_time`,`update_time`) values (5,'1','noruser','普通用户',1,'2019-08-20 20:52:59','2019-08-20 20:53:08'),(6,'2','manage','管理员',1,'2019-08-22 16:00:51','2019-08-22 16:00:53');

/*Table structure for table `tb_role_permission` */

DROP TABLE IF EXISTS `tb_role_permission`;

CREATE TABLE `tb_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  `permission_id` varchar(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=873 DEFAULT CHARSET=utf8;

/*Data for the table `tb_role_permission` */

insert  into `tb_role_permission`(`id`,`role_id`,`permission_id`) values (869,'1','1'),(871,'2','1'),(872,'2','2');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名(账号)',
  `password` varchar(50) NOT NULL,
  `salt` varchar(128) DEFAULT NULL COMMENT '加密盐值',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `sex` tinyint(3) DEFAULT NULL COMMENT '年龄：1男2女',
  `age` tinyint(3) DEFAULT NULL COMMENT '年龄',
  `status` tinyint(1) NOT NULL COMMENT '用户状态：1有效; 2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`user_id`,`username`,`password`,`salt`,`email`,`phone`,`sex`,`age`,`status`,`create_time`,`update_time`,`last_login_time`) values (24,'1','Lin','d9b1d7db4cd6e70935368a1efb10e377',NULL,'1455941195@qq.com','13777554688',1,18,0,'2019-08-20 20:44:46',NULL,NULL),(25,'2','Gan','d9b1d7db4cd6e70935368a1efb10e377',NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL),(26,'3','user1','12b6e20ad450e28f00cbbad5b5cb1e8a','b4a8cc4e5d8541c846d7d3cf583dc718',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL),(27,'4','user2','8efecea996366148a49202bc73219c24','656e10ede4e12ae2407ce04a91b522a3',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL);

/*Table structure for table `tb_user_role` */

DROP TABLE IF EXISTS `tb_user_role`;

CREATE TABLE `tb_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user_role` */

insert  into `tb_user_role`(`id`,`user_id`,`role_id`) values (2,'1','1'),(3,'2','1'),(4,'3','1'),(5,'3','2');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
