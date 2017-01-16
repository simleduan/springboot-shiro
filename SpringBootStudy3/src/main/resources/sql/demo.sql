
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` varchar(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `resource_type` enum('menu','button') DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '1', '用户管理', '0', '0/', 'userInfo:view', 'menu', 'userInfo/userList');
INSERT INTO `sys_permission` VALUES ('2', '1', '用户添加', '1', '0/1', 'userInfo:add', 'button', 'userInfo/userAdd');
INSERT INTO `sys_permission` VALUES ('3', '1', '用户删除', '1', '0/1', 'userInfo:del', 'button', 'userInfo/userDel');
INSERT INTO `sys_permission` VALUES ('4', '1', '维护添加', null, null, 'weihu:add', 'button', 'editAdd');
INSERT INTO `sys_permission` VALUES ('5', '1', '维护查询', null, null, 'weihu:del', 'button', 'editSel');
INSERT INTO `sys_permission` VALUES ('6', '1', '维护修改', null, null, 'weihu:up', 'button', 'editUp');
INSERT INTO `sys_permission` VALUES ('7', '1', '维护删除', null, null, 'weihu:del', 'button', 'editDel');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` varchar(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '1', '管理员', 'admin');
INSERT INTO `sys_role` VALUES ('2', '1', 'VIP会员', 'vip');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `permission_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK_6ov1c9l6b3e2o9d3q889c90l` (`role_id`),
  KEY `FK_8n7kjpptsuop8jvkeh4ls34ct` (`permission_id`),
  CONSTRAINT `FK_6ov1c9l6b3e2o9d3q889c90l` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_8n7kjpptsuop8jvkeh4ls34ct` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('2', '1');
INSERT INTO `sys_role_permission` VALUES ('3', '1');
INSERT INTO `sys_role_permission` VALUES ('4', '1');
INSERT INTO `sys_role_permission` VALUES ('7', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uid` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK_fxu3td9m5o7qov1kbdvmn0g0x` (`role_id`),
  KEY `FK_p2ufwk397udfuakvsnok5wqmf` (`uid`),
  CONSTRAINT `FK_fxu3td9m5o7qov1kbdvmn0g0x` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_p2ufwk397udfuakvsnok5wqmf` FOREIGN KEY (`uid`) REFERENCES `user_info` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('2', '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `state` tinyint(4) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `UK_f2ksd6h8hsjtd57ipfq9myr64` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '管理员', '21232f297a57a5a743894a0e4a801fc3', '8d78869f470951332959580424d4bf4f', '0', 'admin');
INSERT INTO `user_info` VALUES ('2', '管理员', '21232f297a57a5a743894a0e4a801fc3', 'admin', '0', 'fanbin');
INSERT INTO `user_info` VALUES ('4', '阿笨', '202cb962ac59075b964b07152d234b70', '', '0', 'wuneng');
INSERT INTO `user_info` VALUES ('5', '你好', '5d41402abc4b2a76b9719d911017c592', '', '0', 'hello');
INSERT INTO `user_info` VALUES ('6', '露西', 'ecfb2ca9428299f31f0bbb9b5ea28dc3', '', '0', 'lucy');
