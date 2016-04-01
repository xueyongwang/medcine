# MySQL-Front 5.1  (Build 2.7)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: medcine
# ------------------------------------------------------
# Server version 5.1.6-alpha-max-log

CREATE DATABASE `medcine` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `medcine`;

#
# Source for table fun_menu
#

CREATE TABLE `fun_menu` (
  `Id` int(11) NOT NULL auto_increment,
  `zid` varchar(100) default NULL COMMENT '自ID',
  `zname` varchar(255) default NULL COMMENT '自名',
  `userLx` varchar(20) default NULL COMMENT '用户类型',
  `fid` varchar(100) default NULL COMMENT '父ID',
  `fname` varchar(255) default NULL COMMENT '父名',
  `zbh` varchar(255) default NULL COMMENT '自编号',
  `fbh` varchar(255) default NULL COMMENT '父编号',
  `leve` int(11) default NULL COMMENT '等级',
  `message` varchar(255) default NULL COMMENT '树枝信息',
  `wh_link` varchar(255) default '' COMMENT '维护链接',
  `app_link` varchar(255) default NULL COMMENT '应用连接',
  `wh_target` varchar(255) default NULL COMMENT '跳向维护页面',
  `app_target` varchar(255) default NULL COMMENT '跳向应用页面',
  `introduce` varchar(255) default '' COMMENT '节点信息简介',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='树形菜单登记';

#
# Dumping data for table fun_menu
#
LOCK TABLES `fun_menu` WRITE;
/*!40000 ALTER TABLE `fun_menu` DISABLE KEYS */;

INSERT INTO `fun_menu` VALUES (1,'fun_menu_1','平台功能菜单','0','-1','','01','-1',0,NULL,'menuWhTree.jsp?zid=fun_menu_1&leve=0&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0',NULL,'windowspp','window1','');
INSERT INTO `fun_menu` VALUES (2,'fun_menu_2','系统支持','0','fun_menu_1','功能菜单','0101','01',1,'-->系统支持','menuWhTree.jsp?leve=1&zid=fun_menu_2&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','','windowspp','window1','系统支持');
INSERT INTO `fun_menu` VALUES (4,'fun_menu_4','系统功能管理','0','fun_menu_2','系统支持','010101','0101',2,'-->系统支持-->系统功能管理','menuWhTree.jsp?leve=2&zid=fun_menu_4&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../function/sys_all_gnlist.jsp','windowspp','window1','登记管理系统功能');
INSERT INTO `fun_menu` VALUES (5,'fun_menu_5','用户管理','0','fun_menu_1','功能菜单','0102','01',1,'-->用户管理','menuWhTree.jsp?leve=1&zid=fun_menu_5&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','','windowspp','window1','查看各类用户信息');
INSERT INTO `fun_menu` VALUES (6,'fun_menu_6','用户信息查询','0','fun_menu_5','用户管理','010201','0102',2,'-->用户管理-->用户信息管理','menuWhTree.jsp?leve=2&zid=fun_menu_6&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../login/userSel/userSel.jsp','windowspp','window1','用户信息查询');
INSERT INTO `fun_menu` VALUES (7,'fun_menu_7','用户信息删除','0','fun_menu_5','用户管理','010202','0102',2,'-->用户管理-->用户基础信息删除','menuWhTree.jsp?leve=2&zid=fun_menu_7&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../login/userDel/userDel.jsp','windowspp','window1','用户基础信息删除');
INSERT INTO `fun_menu` VALUES (8,'fun_menu_8','用户信息修改','0','fun_menu_5','用户管理','010203','0102',2,'-->用户管理-->用户信息修改','menuWhTree.jsp?leve=2&zid=fun_menu_8&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../login/userMode/userMode.jsp','windowspp','window1','用户信息修改');
INSERT INTO `fun_menu` VALUES (15,'fun_menu_15','设置菜单','0','fun_menu_2','系统支持','010102','0101',2,'-->系统支持-->设置菜单','menuWhTree.jsp?leve=2&zid=fun_menu_15&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../wh_tree/jkMkgxMain.jsp','windowspp','window1','设置菜单');
INSERT INTO `fun_menu` VALUES (17,'fun_menu_17','住户功能菜单','2','-1','','01','-1',0,'住户功能菜单','menuWhTree.jsp?leve=0&zid=fun_menu_17&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=2','','windowspp','window1','住户功能菜单');
INSERT INTO `fun_menu` VALUES (18,'fun_menu_18','写字间用户菜单','3','-1','','01','-1',0,'写字间用户菜单','menuWhTree.jsp?leve=0&zid=fun_menu_18&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=3','','windowspp','window1','写字间用户菜单');
INSERT INTO `fun_menu` VALUES (19,'fun_menu_19','普通用户菜单','4','-1','','01','-1',0,'普通用户菜单','menuWhTree.jsp?leve=0&zid=fun_menu_19&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=4','','windowspp','window1','普通用户菜单');
INSERT INTO `fun_menu` VALUES (20,'fun_menu_20','房源信息管理','0','fun_menu_1','平台功能菜单','0103','01',1,'-->房源信息管理','menuWhTree.jsp?leve=1&zid=fun_menu_20&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','','windowspp','window1','房源信息管理');
INSERT INTO `fun_menu` VALUES (21,'fun_menu_21','房源信息登记','0','fun_menu_20','房源信息管理','010301','0103',2,'-->房源信息管理-->房源信息登记','menuWhTree.jsp?leve=2&zid=fun_menu_21&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../house/houseInsert/houseInsert.jsp','windowspp','window1','房源信息登记');
INSERT INTO `fun_menu` VALUES (22,'fun_menu_22','房源信息修改','0','fun_menu_20','房源信息管理','010302','0103',2,'-->房源信息管理-->房源信息修改','menuWhTree.jsp?leve=2&zid=fun_menu_22&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../house/houseMode/houseMode.jsp','windowspp','window1','房源信息修改');
INSERT INTO `fun_menu` VALUES (23,'fun_menu_23','房源信息查询','0','fun_menu_20','房源信息管理','010303','0103',2,'-->房源信息管理-->房源信息删除','menuWhTree.jsp?leve=2&zid=fun_menu_23&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../house/houseSelect/houseSelect.jsp','windowspp','window1','房源信息查询');
INSERT INTO `fun_menu` VALUES (24,'fun_menu_24','合作单位管理','0','fun_menu_1','平台功能菜单','0104','01',1,'-->合作单位信息登记','menuWhTree.jsp?leve=1&zid=fun_menu_24&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','','windowspp','window1','合作单位管理');
INSERT INTO `fun_menu` VALUES (25,'fun_menu_25','合作单位信息登记','0','fun_menu_24','合作单位管理','010401','0104',2,'-->合作单位信息登记-->合作单位信息登记','menuWhTree.jsp?leve=2&zid=fun_menu_25&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../cooperator/cooperatorInsert/cooperatorInsert.jsp','windowspp','window1','合作单位信息登记');
INSERT INTO `fun_menu` VALUES (26,'fun_menu_26','项目信息管理','0','fun_menu_1','平台功能菜单','0105','01',1,'-->惠商网信息介绍','menuWhTree.jsp?leve=1&zid=fun_menu_26&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','','windowspp','window1','项目信息管理');
INSERT INTO `fun_menu` VALUES (27,'fun_menu_27','项目信息登记','0','fun_menu_26','惠商网平台信息管理','010501','0105',2,'-->惠商网信息介绍-->登记惠商网信息','menuWhTree.jsp?leve=2&zid=fun_menu_27&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../business/busInforInsert/busInforInsert.jsp','windowspp','window1','项目信息登记');
INSERT INTO `fun_menu` VALUES (28,'fun_menu_28','招商管理','0','fun_menu_1','平台功能菜单','0106','01',1,'-->招商管理','menuWhTree.jsp?leve=1&zid=fun_menu_28&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','','windowspp','window1','招商管理');
INSERT INTO `fun_menu` VALUES (29,'fun_menu_29','招商信息登记','0','fun_menu_28','招商管理','010601','0106',2,'-->招商管理-->招商信息录入','menuWhTree.jsp?leve=2&zid=fun_menu_29&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../publishAd/publicAdInsert/publicAdInsert.jsp','windowspp','window1','招商信息登记');
INSERT INTO `fun_menu` VALUES (30,'fun_menu_30','配置用户菜单','0','fun_menu_2','系统支持','010103','0101',2,'-->系统支持-->设置用户类型功能菜单','menuWhTree.jsp?leve=2&zid=fun_menu_30&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../function/userLxGnSet.jsp','windowspp','window1','配置用户功能菜单');
INSERT INTO `fun_menu` VALUES (31,'fun_menu_31','商户功能菜单','1','-1','','01','-1',0,'商户功能菜单','menuWhTree.jsp?leve=0&zid=fun_menu_31&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','','windowspp','window1','商户功能菜单');
INSERT INTO `fun_menu` VALUES (32,'fun_menu_32','商户信息管理','1','fun_menu_31','商户功能菜单','0101','01',1,'商户功能菜单-->商户信息管理','menuWhTree.jsp?leve=1&zid=fun_menu_32&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','','windowspp','window1','商户信息管理');
INSERT INTO `fun_menu` VALUES (36,'fun_menu_36','住户信息管理','2','fun_menu_17','住户功能菜单','0101','01',1,'住户功能菜单-->住户信息管理','menuWhTree.jsp?leve=1&zid=fun_menu_36&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=2','','windowspp','window1','住户信息管理');
INSERT INTO `fun_menu` VALUES (37,'fun_menu_37','住户信息查询','2','fun_menu_36','住户信息管理','010101','0101',2,'住户功能菜单-->住户信息管理-->住户信息查询','menuWhTree.jsp?leve=2&zid=fun_menu_37&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=2','../login/userSel/userSel.jsp','windowspp','window1','住户信息查询');
INSERT INTO `fun_menu` VALUES (38,'fun_menu_38','住户信息修改','2','fun_menu_36','住户信息管理','010102','0101',2,'住户功能菜单-->住户信息管理-->住户信息修改','menuWhTree.jsp?leve=2&zid=fun_menu_38&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=2','../login/userMode/userMode.jsp','windowspp','window1','住户信息修改');
INSERT INTO `fun_menu` VALUES (39,'fun_menu_39','住户信息删除nd','2','fun_menu_38','住户信息管理','010103','0101',2,'住户功能菜单-->住户信息管理-->住户信息删除','menuWhTree.jsp?leve=2&zid=fun_menu_39&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=2','../login/userDel/userDel.jsp','windowspp','window1','住户信n除nd');
INSERT INTO `fun_menu` VALUES (40,'fun_menu_40','用户信息管理','3','fun_menu_18','写字间用户菜单','0101','01',1,'写字间用户菜单-->写字间用户信息管理','menuWhTree.jsp?leve=1&zid=fun_menu_40&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=3','','windowspp','window1','写字间用户信息管理');
INSERT INTO `fun_menu` VALUES (41,'fun_menu_41','用户信息查询','3','fun_menu_40','写字间用户信息管理','010101','0101',2,'写字间用户菜单-->写字间用户信息管理-->写字间用户信息查询','menuWhTree.jsp?leve=2&zid=fun_menu_41&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=3','../login/userSel/userSel.jsp','windowspp','window1','写字间用户信息查询');
INSERT INTO `fun_menu` VALUES (42,'fun_menu_42','用户信息修改','3','fun_menu_40','写字间用户信息管理','010102','0101',2,'写字间用户菜单-->写字间用户信息管理-->写字间用户信息修改','menuWhTree.jsp?leve=2&zid=fun_menu_42&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=3','../login/userMode/userMode.jsp','windowspp','window1','写字间用户信息修改');
INSERT INTO `fun_menu` VALUES (43,'fun_menu_43','用户信息删除','3','fun_menu_40','写字间用户信息管理','010103','0101',2,'写字间用户菜单-->写字间用户信息管理-->写字间用户信息删除','menuWhTree.jsp?leve=2&zid=fun_menu_43&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=3','../login/userDel/userDel.jsp','windowspp','window1','写字间用户信息删除');
INSERT INTO `fun_menu` VALUES (44,'fun_menu_44','普通用户信息管理','4','fun_menu_19','普通用户菜单','0101','01',1,'普通用户菜单-->普通用户信息管理','menuWhTree.jsp?leve=1&zid=fun_menu_44&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=4','','windowspp','window1','普通用户信息管理');
INSERT INTO `fun_menu` VALUES (45,'fun_menu_45','普通用户信息查询','4','fun_menu_44','普通用户信息管理','010101','0101',2,'普通用户菜单-->普通用户信息管理-->普通用户信息查询','menuWhTree.jsp?leve=2&zid=fun_menu_45&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=4','../login/userSel/userSel.jsp','windowspp','window1','普通用户信息查询');
INSERT INTO `fun_menu` VALUES (46,'fun_menu_46','普通用户信息修改','4','fun_menu_44','普通用户信息管理','010102','0101',2,'普通用户菜单-->普通用户信息管理-->普通用户信息修改','menuWhTree.jsp?leve=2&zid=fun_menu_46&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=4','../login/userMode/userMode.jsp','windowspp','window1','普通用户信息修改');
INSERT INTO `fun_menu` VALUES (47,'fun_menu_47','普通用户信息删除','4','fun_menu_44','普通用户信息管理','010103','0101',2,'普通用户菜单-->普通用户信息管理-->普通用户信息删除','menuWhTree.jsp?leve=2&zid=fun_menu_47&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=4','../login/userDel/userDel.jsp','windowspp','window1','普通用户信息删除');
INSERT INTO `fun_menu` VALUES (48,'fun_menu_48','活动管理','0','fun_menu_1','平台功能菜单','0107','01',1,'-->活动管理','menuWhTree.jsp?leve=1&zid=fun_menu_48&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','','windowspp','window1','活动管理');
INSERT INTO `fun_menu` VALUES (53,'fun_menu_53','表达式登记','0','fun_menu_48','活动管理','010701','0107',2,'-->活动管理-->表达式登记','menuWhTree.jsp?leve=2&zid=fun_menu_53&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../active/expReg/expReg.jsp','windowspp','window1','表达式登记');
INSERT INTO `fun_menu` VALUES (54,'fun_menu_54','用户类型登记','0','fun_menu_2','系统支持','010104','0101',2,'-->系统支持-->用户类型登记','menuWhTree.jsp?leve=2&zid=fun_menu_54&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../login/userLxReg/userLxReg.jsp','windowspp','window1','用户类型登记');
INSERT INTO `fun_menu` VALUES (55,'fun_menu_55','页面顺序调整','0','fun_menu_2','系统支持','010105','0101',2,'-->系统支持-->页面顺序调整','menuWhTree.jsp?leve=2&zid=fun_menu_55&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../sort/page_select.jsp','windowspp','window1','页面顺序调整');
INSERT INTO `fun_menu` VALUES (56,'fun_menu_56','商品管理','0','fun_menu_1','平台功能菜单','0108','01',1,'-->商品管理','menuWhTree.jsp?leve=1&zid=fun_menu_56&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../wh_tree/index.jsp?leve=1&zid=shop_fl_2&tree_table=shop_fl&add_zd=null&isfun=1&add_value=null','windowspp','window1','商品管理');
INSERT INTO `fun_menu` VALUES (57,'fun_menu_57','商品分类维护','0','fun_menu_56','商品管理','010801','0108',2,'-->商品管理-->商品分类维护','menuWhTree.jsp?leve=2&zid=fun_menu_57&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../wh_tree/index.jsp?tree_table=product_fl_tree&isfun=1&add_zd=null&add_value=null','windowspp','window1','商品分类维护');
INSERT INTO `fun_menu` VALUES (58,'fun_menu_58','企业组织结构','0','fun_menu_5','系统支持','010204','0102',2,'-->系统支持-->企业组织结构','menuWhTree.jsp?leve=2&zid=fun_menu_58&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../function/companyZzjg.jsp','windowspp','window1','设置企业组织结构');
INSERT INTO `fun_menu` VALUES (60,'fun_menu_60','项目登记','0','fun_menu_26','项目信息管理','010502','0105',2,'-->惠商网信息介绍-->项目登记','menuWhTree.jsp?leve=2&zid=fun_menu_60&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../cooperator/xmReg/xmReg.jsp','windowspp','window1','项目登记');
INSERT INTO `fun_menu` VALUES (61,'fun_menu_61','商户登记','1','fun_menu_32','商户信息管理','010101','0101',2,'商户功能菜单-->商户信息管理-->商户登记','menuWhTree.jsp?leve=2&zid=fun_menu_61&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','../shop/shop_manage/shop_dj.jsp','windowspp','window1','商户登记');
INSERT INTO `fun_menu` VALUES (62,'fun_menu_62','商品信息管理','1','fun_menu_31','商户功能菜单','0102','01',1,'商户功能菜单-->商品信息管理','menuWhTree.jsp?leve=1&zid=fun_menu_62&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','','windowspp','window1','商品信息管理');
INSERT INTO `fun_menu` VALUES (63,'fun_menu_63','商品信息登记','1','fun_menu_62','商品信息管理','010201','0102',2,'商户功能菜单-->商品信息管理-->商品信息登记','menuWhTree.jsp?leve=2&zid=fun_menu_63&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','../shop/shop_stock/index.jsp?tree_table=product_fl_tree&isfun=1&add_zd=null&add_value=null','windowspp','window1','商品信息登记');
INSERT INTO `fun_menu` VALUES (64,'fun_menu_64','活动申请','1','fun_menu_62','商品信息管理','010202','0102',2,'商户功能菜单-->商品信息管理-->活动申请','menuWhTree.jsp?leve=2&zid=fun_menu_64&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','../shop/shop_manage/app_act.jsp','windowspp','window1','活动申请');
INSERT INTO `fun_menu` VALUES (65,'fun_menu_65','订单处理','1','fun_menu_62','商品信息管理','010203','0102',2,'商户功能菜单-->商品信息管理-->订单处理','menuWhTree.jsp?leve=2&zid=fun_menu_65&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','../shop/shop_manage/dd_deal.jsp','windowspp','window1','订单处理');
INSERT INTO `fun_menu` VALUES (66,'fun_menu_66','影院管理','1','fun_menu_31','商户功能菜单','0103','01',1,'商户功能菜单-->影院管理','menuWhTree.jsp?leve=1&zid=fun_menu_66&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','','windowspp','window1','影院管理');
INSERT INTO `fun_menu` VALUES (67,'fun_menu_67','电影登记','1','fun_menu_66','影院管理','010301','0103',2,'商户功能菜单-->影院管理-->电影登记','menuWhTree.jsp?leve=2&zid=fun_menu_67&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','../shop/cinama/moviedj.jsp','windowspp','window1','电影登记');
INSERT INTO `fun_menu` VALUES (68,'fun_menu_68','放映厅登记','1','fun_menu_66','影院管理','010302','0103',2,'商户功能菜单-->影院管理-->放映厅登记','menuWhTree.jsp?leve=2&zid=fun_menu_68&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','../shop/cinama/roomReg.jsp','windowspp','window1','放映厅登记');
INSERT INTO `fun_menu` VALUES (69,'fun_menu_69','电影场次登记','1','fun_menu_66','影院管理','010303','0103',2,'商户功能菜单-->影院管理-->电影场次登记','menuWhTree.jsp?leve=2&zid=fun_menu_69&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','../shop/cinama/showinfodj.jsp','windowspp','window1','电影场次登记');
INSERT INTO `fun_menu` VALUES (70,'fun_menu_70','店铺装修管理','1','fun_menu_31','商户功能菜单','0104','01',1,'商户功能菜单-->店铺装修管理','menuWhTree.jsp?leve=1&zid=fun_menu_70&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','','windowspp','window1','店铺装修管理');
INSERT INTO `fun_menu` VALUES (71,'fun_menu_71','店铺主页管理','1','fun_menu_70','店铺装修管理','010401','0104',2,'商户功能菜单-->店铺装修管理-->店铺主页管理','menuWhTree.jsp?leve=2&zid=fun_menu_71&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','../mubanInsert/PageLayout/LayoutManage.jsp?isCreatPageOrFormwork=1&mbLx=1','windowspp','window1','店铺主页管理');
INSERT INTO `fun_menu` VALUES (72,'fun_menu_72','设置店铺介绍页面','1','null','店铺装修管理','','',2,'商户功能菜单-->店铺装修管理-->设置联系我们页面','menuWhTree.jsp?leve=2&zid=fun_menu_72&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','','windowspp','window1','设置店铺介绍页面');
INSERT INTO `fun_menu` VALUES (73,'fun_menu_73','设置店铺地址页面','1','fun_menu_70','店铺装修管理','010402','0104',2,'商户功能菜单-->店铺装修管理-->设置店铺地址页面','menuWhTree.jsp?leve=2&zid=fun_menu_73&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','../mubanInsert/PageLayout/LayoutManage.jsp?isCreatPageOrFormwork=1&mbLx=4','windowspp','window1','设置店铺地址页面');
INSERT INTO `fun_menu` VALUES (74,'fun_menu_74','设置店铺介绍页','1','fun_menu_70','店铺装修管理','010403','0104',2,'商户功能菜单-->店铺装修管理-->设置店铺介绍页','menuWhTree.jsp?leve=2&zid=fun_menu_74&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','../mubanInsert/PageLayout/LayoutManage.jsp?isCreatPageOrFormwork=1&mbLx=3','windowspp','window1','设置店铺介绍页');
INSERT INTO `fun_menu` VALUES (75,'fun_menu_75','前台页面管理','0','fun_menu_1','平台功能菜单','0109','01',1,'-->前台页面管理','menuWhTree.jsp?leve=1&zid=fun_menu_75&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','','windowspp','window1','前台页面管理');
INSERT INTO `fun_menu` VALUES (76,'fun_menu_76','主页面维护','0','fun_menu_75','前台页面管理','010901','0109',2,'-->前台页面管理-->主页面维护','menuWhTree.jsp?leve=2&zid=fun_menu_76&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../mubanInsert/PageLayout/editPageJsp.jsp?layoutBh=model_67&mbOrPage=1&mbLx=20','windowspp','window1','主页面维护');
INSERT INTO `fun_menu` VALUES (77,'fun_menu_77','所有商户页面维护','0','fun_menu_75','前台页面管理','010902','0109',2,'-->前台页面管理-->所有商户页面维护','menuWhTree.jsp?leve=2&zid=fun_menu_77&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../mubanInsert/PageLayout/LayoutManage.jsp?isCreatPageOrFormwork=1&mbLx=2','windowspp','window1','所有商户页面维护');
INSERT INTO `fun_menu` VALUES (78,'fun_menu_78','用户个人中心菜单树','0','fun_menu_5','用户管理','010205','0102',2,'-->用户管理-->用户个人中心菜单树','menuWhTree.jsp?leve=2&zid=fun_menu_78&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../wh_tree/index.jsp?tree_table=user_zone_tree&isfun=1&add_zd=null&add_value=null','windowspp','window1','用户个人中心菜单树');
INSERT INTO `fun_menu` VALUES (79,'fun_menu_79','商家版客服树','0','fun_menu_75','前台页面管理','010903','0109',2,'-->前台页面管理-->商家版客服树','menuWhTree.jsp?leve=2&zid=fun_menu_79&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../wh_tree/index.jsp?tree_table=service_page_tree&isfun=1&add_zd=null&add_value=null','windowspp','window1','商家版客服树');
INSERT INTO `fun_menu` VALUES (80,'fun_menu_80','电影院管理','0','fun_menu_1','平台功能菜单','0110','01',1,'-->电影院管理','menuWhTree.jsp?leve=1&zid=fun_menu_80&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','','windowspp','window1','电影院管理');
INSERT INTO `fun_menu` VALUES (81,'fun_menu_81','影院登记','0','fun_menu_80','电影院管理','011001','0110',2,'-->电影院管理-->影院登记','menuWhTree.jsp?leve=2&zid=fun_menu_81&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../shop/cinama/ciname_dj.jsp','windowspp','window1','影院登记');
INSERT INTO `fun_menu` VALUES (82,'fun_menu_82','电影登记','0','fun_menu_80','电影院管理','011002','0110',2,'-->电影院管理-->电影登记','menuWhTree.jsp?leve=2&zid=fun_menu_82&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../shop/cinama/moviedj.jsp','windowspp','window1','电影登记');
INSERT INTO `fun_menu` VALUES (83,'fun_menu_83','电影放映厅登记','0','fun_menu_80','电影院管理','011003','0110',2,'-->电影院管理-->电影放映厅登记','menuWhTree.jsp?leve=2&zid=fun_menu_83&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../shop/cinama/roomReg.jsp','windowspp','window1','电影放映厅登记');
INSERT INTO `fun_menu` VALUES (84,'fun_menu_84','电影放映场次登记','0','fun_menu_80','电影院管理','011004','0110',2,'-->电影院管理-->电影放映场次登记','menuWhTree.jsp?leve=2&zid=fun_menu_84&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../shop/cinama/showinfodj.jsp','windowspp','window1','电影放映场次登记');
INSERT INTO `fun_menu` VALUES (85,'fun_menu_85','通用版客服中心','0','fun_menu_1','平台功能菜单','0111','01',1,'-->通用版客服中心','menuWhTree.jsp?leve=1&zid=fun_menu_85&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','','windowspp','window1','通用版客服中心');
INSERT INTO `fun_menu` VALUES (86,'fun_menu_86','客服中心问题类别登记通用版','0','fun_menu_85','通用版客服中心','011101','0111',2,'-->通用版客服中心-->通用版客服中心问题类别登记','menuWhTree.jsp?leve=2&zid=fun_menu_86&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../service_mall/con_dj/set_newslb.jsp?lb_id=lb_18','windowspp','window1','通用版客服中心问题类别登记');
INSERT INTO `fun_menu` VALUES (87,'fun_menu_87','问题发布通用版客服中心','0','fun_menu_85','通用版客服中心','011102','0111',2,'-->通用版客服中心-->通用版客服中心问题发布','menuWhTree.jsp?leve=2&zid=fun_menu_87&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../service_mall/con_dj/public_news.jsp','windowspp','window1','通用版客服中心问题发布');
INSERT INTO `fun_menu` VALUES (88,'fun_menu_88','物业管理','0','fun_menu_1','平台功能菜单','0112','01',1,'-->物业管理','menuWhTree.jsp?leve=1&zid=fun_menu_88&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../new_jf/jf_item_dj.jsp','windowspp','window1','物业管理');
INSERT INTO `fun_menu` VALUES (89,'fun_menu_89','物业服务类别管理','0','fun_menu_88','物业管理','011201','0112',2,'-->物业管理-->物业服务类别管理','menuWhTree.jsp?leve=2&zid=fun_menu_89&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../news_manage/news_manage.jsp','windowspp','window1','物业服务类别管理');
INSERT INTO `fun_menu` VALUES (90,'fun_menu_90','发布物业通知','0','fun_menu_88','物业管理','011202','0112',2,'-->物业管理-->发布物业通知','menuWhTree.jsp?leve=2&zid=fun_menu_90&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../news_manage/public_news.jsp','windowspp','window1','发布物业通知');
INSERT INTO `fun_menu` VALUES (91,'fun_menu_91','物业信息维护','0','fun_menu_88','物业管理','011203','0112',2,'-->物业管理-->物业信息维护','menuWhTree.jsp?leve=2&zid=fun_menu_91&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../news_manage/wh_news.jsp','windowspp','window1','物业信息维护');
INSERT INTO `fun_menu` VALUES (92,'fun_menu_92','系统活动登记','0','fun_menu_48','活动管理','010702','0107',2,'-->活动管理-->系统活动登记','menuWhTree.jsp?leve=2&zid=fun_menu_92&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../active/activity_dj.jsp','windowspp','window1','系统活动登记');
INSERT INTO `fun_menu` VALUES (93,'fun_menu_93','活动审核','0','fun_menu_48','活动管理','010703','0107',2,'-->活动管理-->活动审核','menuWhTree.jsp?leve=2&zid=fun_menu_93&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../shop/shop_manage/rec_act.jsp','windowspp','window1','活动审核');
INSERT INTO `fun_menu` VALUES (94,'fun_menu_94','影院登记','1','fun_menu_66','影院管理','010304','0103',2,'商户功能菜单-->影院管理-->影院登记申请','menuWhTree.jsp?leve=2&zid=fun_menu_94&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','../shop/cinama/ciname_dj.jsp','windowspp','window1','影院登记申请');
INSERT INTO `fun_menu` VALUES (95,'fun_menu_95','非普通用户审核','0','fun_menu_5','用户管理','010206','0102',2,'-->用户管理-->非普通用户审核','menuWhTree.jsp?leve=2&zid=fun_menu_95&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../login/checkMess.jsp','windowspp','window1','非普通用户审核');
INSERT INTO `fun_menu` VALUES (96,'fun_menu_96','物业报修处理','0','fun_menu_88','物业管理','011204','0112',2,'-->物业管理-->物业报修处理','menuWhTree.jsp?leve=2&zid=fun_menu_96&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../news/recRepair.jsp','windowspp','window1','物业报修处理');
INSERT INTO `fun_menu` VALUES (97,'fun_menu_97','积分商城','0','fun_menu_1','平台功能菜单','0113','01',1,'-->积分商城','menuWhTree.jsp?leve=1&zid=fun_menu_97&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','','windowspp','window1','积分商城');
INSERT INTO `fun_menu` VALUES (98,'fun_menu_98','积分商品登记','0','fun_menu_97','积分商城','011301','0113',2,'-->积分商城-->积分商品登记','menuWhTree.jsp?leve=2&zid=fun_menu_98&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../new_jf/jf_item_dj.jsp','windowspp','window1','积分商品登记');
INSERT INTO `fun_menu` VALUES (99,'fun_menu_99','店铺所属分类登记','0','fun_menu_56','商品管理','010802','0108',2,'-->商品管理-->店铺所属分类登记','menuWhTree.jsp?leve=2&zid=fun_menu_99&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../wh_tree/index.jsp?leve=1&zid=shop_fl_2&tree_table=shop_fl&add_zd=null&isfun=1&add_value=null','windowspp','window1','店铺所属分类登记');
INSERT INTO `fun_menu` VALUES (100,'fun_menu_100','服务号码信息上传','0','fun_menu_88','物业管理','011205','0112',2,'-->物业管理-->服务号码信息上传','menuWhTree.jsp?leve=2&zid=fun_menu_100&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../news/upload_tel.jsp','windowspp','window1','服务号码信息上传');
INSERT INTO `fun_menu` VALUES (101,'fun_menu_101','服务号码上传2','0','fun_menu_88','物业管理','011206','0112',2,'-->物业管理-->服务号码上传2','menuWhTree.jsp?leve=2&zid=fun_menu_101&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../news/add_tel.jsp','windowspp','window1','服务号码上传2');
INSERT INTO `fun_menu` VALUES (102,'fun_menu_102','影院申请','1','fun_menu_66','影院管理','010305','0103',2,'商户功能菜单-->影院管理-->影院申请','menuWhTree.jsp?leve=2&zid=fun_menu_102&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=1','../shop/cinama/cin_user_app.jsp','windowspp','window1','影院申请');
INSERT INTO `fun_menu` VALUES (103,'fun_menu_103','通用版客服中心维护','0','fun_menu_85','通用版客服中心','011103','0111',2,'-->通用版客服中心-->通用版客服中心维护','menuWhTree.jsp?leve=2&zid=fun_menu_103&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../service_mall/con_dj/wh_news.jsp','windowspp','window1','通用版客服中心维护');
INSERT INTO `fun_menu` VALUES (104,'fun_menu_104','电影院用户审核','0','fun_menu_80','电影院管理','011005','0110',2,'-->电影院管理-->电影院用户审核','menuWhTree.jsp?leve=2&zid=fun_menu_104&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../shop/cinama/cinama_admin_deal.jsp','windowspp','window1','电影院用户审核');
INSERT INTO `fun_menu` VALUES (106,'fun_menu_106','项目展示页面顺序调整','0','fun_menu_26','项目信息管理','010503','0105',2,'-->惠商网信息介绍-->项目展示页面顺序调整','menuWhTree.jsp?leve=2&zid=fun_menu_106&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=0','../sort/page_select2.jsp','windowspp','window1','项目展示页面顺序调整');
INSERT INTO `fun_menu` VALUES (107,'fun_menu_107','ASDF','5','-1','','01','-1',0,'SADF','menuWhTree.jsp?leve=0&zid=fun_menu_107&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=5','','windowspp','window1','SADF');
INSERT INTO `fun_menu` VALUES (108,'fun_menu_108','AEF','5','fun_menu_107','ASDF','0101','01',1,'SADF-->AEF','menuWhTree.jsp?leve=1&zid=fun_menu_108&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=5','','windowspp','window1','ASDF');
INSERT INTO `fun_menu` VALUES (109,'fun_menu_109','asdf','5','fun_menu_107','AEF','010101','0101',2,'SADF-->AEF-->asdf','menuWhTree.jsp?leve=2&zid=fun_menu_109&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx=5','','windowspp','window1','暗示法');
/*!40000 ALTER TABLE `fun_menu` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table userlx
#

CREATE TABLE `userlx` (
  `Id` int(11) NOT NULL auto_increment,
  `userLxName` varchar(20) NOT NULL default '' COMMENT '用户类型',
  `userLxBh` varchar(10) NOT NULL default '' COMMENT '用户类型编号 ',
  `userJs` text NOT NULL COMMENT '用户介绍 ',
  `isSet` varchar(2) default '0' COMMENT '是否设置（1：设置，0：未设置）',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户类型表';

#
# Dumping data for table userlx
#
LOCK TABLES `userlx` WRITE;
/*!40000 ALTER TABLE `userlx` DISABLE KEYS */;

INSERT INTO `userlx` VALUES (1,'用户类型1','0','管理员介绍信息','1');
INSERT INTO `userlx` VALUES (2,'用户类型2','1','商户介绍','1');
INSERT INTO `userlx` VALUES (3,'用户类型3','2','住户','1');
INSERT INTO `userlx` VALUES (4,'用户类型4','3','写字间用户','1');
INSERT INTO `userlx` VALUES (5,'用户类型5','4','用户介绍','1');
INSERT INTO `userlx` VALUES (6,'用户类型6','5','','1');
/*!40000 ALTER TABLE `userlx` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
