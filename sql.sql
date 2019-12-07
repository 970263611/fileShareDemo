/*
 Navicat Premium Data Transfer

 Source Server         : 106.13.143.186
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 106.13.143.186:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 07/12/2019 20:34:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test_file
-- ----------------------------
DROP TABLE IF EXISTS `test_file`;
CREATE TABLE `test_file`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user` int(255) NULL DEFAULT NULL,
  `time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `size` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_file
-- ----------------------------
INSERT INTO `test_file` VALUES ('15847fab-a374-479f-bd00-bc25c13af08f', 'P2P大作业.doc', '/root/test/P2P大作业.doc', 1, '2019-12-07 :12:41:10', '222.129.47.123', 29696);
INSERT INTO `test_file` VALUES ('8b6dcda4-f54f-46d0-a24e-5e5168e17d1d', 'P2P文档共享软件设计文档.docx', '/root/test/P2P文档共享软件设计文档.docx', 1, '2019-12-07 :12:41:18', '222.129.47.123', 882310);
INSERT INTO `test_file` VALUES ('f482128a-3181-486c-a3de-b72ed61a296d', '文档.docx', '/root/test/文档.docx', 1, '2019-12-07 :02:04:08', '113.140.11.124', 152241);

-- ----------------------------
-- Table structure for test_user
-- ----------------------------
DROP TABLE IF EXISTS `test_user`;
CREATE TABLE `test_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` int(255) NULL DEFAULT NULL,
  `inline` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_user
-- ----------------------------
INSERT INTO `test_user` VALUES (1, 'admin', 'admin', 0, 1);
INSERT INTO `test_user` VALUES (6, '123', '123', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
