/*
 Navicat Premium Data Transfer

 Source Server         : MYSQL_LOCAL
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : manager_user

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 22/09/2023 17:20:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `id` int NOT NULL,
  `company_type_id` int NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES (1, 1, 'Company A', 1);
INSERT INTO `company` VALUES (2, 1, 'Company B', 1);
INSERT INTO `company` VALUES (3, 2, 'Company C', 1);

-- ----------------------------
-- Table structure for company_enrollment
-- ----------------------------
DROP TABLE IF EXISTS `company_enrollment`;
CREATE TABLE `company_enrollment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_id` int NULL DEFAULT NULL,
  `webservice_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `extra_field_1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `extra_field_2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `extra_field_3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `verify_status` tinyint NULL DEFAULT NULL,
  `company_type_id` int NULL DEFAULT NULL,
  `company_other` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of company_enrollment
-- ----------------------------
INSERT INTO `company_enrollment` VALUES (1, 3, '2,3,5', '5454683432821744', '2673161863185612', 'extra field 1.5', '', 'extra field 3.5', 0, 2, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (2, 1, '1', '5166266261144341', '4171137562552163', '', '', '', 0, 1, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (3, 1, '3', '6353267756781217', '4427463734514227', '', '', '', 0, 1, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (4, 1, '3,4', NULL, '', '', '', '', 0, 1, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (5, NULL, '5', NULL, '', '', '', '', 0, 3, 'Company Other 12', NULL, NULL);
INSERT INTO `company_enrollment` VALUES (6, 3, '3', NULL, '', '', '', '', 0, 2, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (7, 1, '3,4', NULL, '', '', '', '', 0, 1, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (8, NULL, '1,3', NULL, '', '', '', '', 0, 3, 'Company other 12312395', NULL, NULL);
INSERT INTO `company_enrollment` VALUES (9, NULL, '1', NULL, '', '', '', '', 0, 1, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (10, NULL, '1', NULL, '', '', '', '', 0, 1, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (11, 3, '1', NULL, '', '', '', '', 0, 2, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (12, 1, '1,3', NULL, '', '', '', '', 0, 1, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (13, 1, '2,4,5', '2382725866771677', '6281874325541547', '', '', '', 1, 1, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (14, 3, '3,4,5', '4284415373521273', '1361771156822245', '', '', '', 1, 2, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (15, 1, '1,2', NULL, '', '', '', '', 0, 1, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (16, 1, '2,3', NULL, '', '', '', '', 0, 1, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (17, NULL, '1,2,3', NULL, '', '', '', '', 0, 2, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (18, 1, '1,3,4,5', NULL, '', '', '', '', 0, 1, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (19, 1, '1,3,4,5', NULL, '', '', '', '', 0, 1, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (20, 1, '1,3', '2511127527335237', '2348358386626718', '', '', '', 1, 1, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (21, 1, '1,2,4', NULL, '', '', '', '', 0, 1, NULL, NULL, NULL);
INSERT INTO `company_enrollment` VALUES (22, 1, '1,2,3,4', '8568731314528186', '5321556574657586', '', '', '', 1, 1, NULL, NULL, '2023-09-20 23:41:17');
INSERT INTO `company_enrollment` VALUES (23, 1, '1', '5275181265452831', '4464738417323386', '', '', '', 1, 1, NULL, '2023-09-19 16:39:23', '2023-09-19 16:48:58');
INSERT INTO `company_enrollment` VALUES (24, 1, '1', '1861112315431551', '3315841366581858', '', '', '', 1, 1, NULL, '2023-09-19 16:51:44', '2023-09-19 16:52:39');
INSERT INTO `company_enrollment` VALUES (25, 1, '2', '4443476426838178', '3612681634724413', '', '', '', 1, 1, NULL, '2023-09-20 09:24:44', '2023-09-20 09:26:17');
INSERT INTO `company_enrollment` VALUES (26, 1, '1,2', '1227857775124662', '6234715362813711', '', '', '', 1, 1, NULL, '2023-09-20 09:35:27', '2023-09-20 09:54:08');
INSERT INTO `company_enrollment` VALUES (27, 1, '1,2', '3126284248624231', '2432738521332662', '', '', '', 1, 1, NULL, '2023-09-20 10:18:39', '2023-09-20 10:19:31');
INSERT INTO `company_enrollment` VALUES (28, NULL, '1,2,3,5', '4436111424748318', '1177713265365765', '', '', '', 1, 3, '', '2023-09-20 10:27:51', '2023-09-20 14:49:56');
INSERT INTO `company_enrollment` VALUES (29, 1, '2', '7332738245335722', '4615518656835811', '', '', '', 1, 1, NULL, '2023-09-20 10:31:56', '2023-09-20 10:32:43');
INSERT INTO `company_enrollment` VALUES (30, 1, '2,3,4', '1878366168381154', '3458446553313551', '', '', '', 1, 1, NULL, '2023-09-20 10:38:27', '2023-09-20 13:36:43');
INSERT INTO `company_enrollment` VALUES (31, 3, '1,4', '5126363451774417', '7881825681467175', '', '', '', 1, 2, NULL, '2023-09-21 16:52:51', '2023-09-21 20:14:07');
INSERT INTO `company_enrollment` VALUES (32, 1, '1', '6375123475467882', '4534514536485837', '', '', '', 1, 1, NULL, '2023-09-21 21:14:29', '2023-09-21 21:15:32');

-- ----------------------------
-- Table structure for company_enrollment_history
-- ----------------------------
DROP TABLE IF EXISTS `company_enrollment_history`;
CREATE TABLE `company_enrollment_history`  (
  `id` int NOT NULL,
  `company_id` int NULL DEFAULT NULL,
  `webservice_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `extra_field_1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `extra_field_2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `extra_field_3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `verify_status` tinyint NULL DEFAULT NULL,
  `company_type_id` int NULL DEFAULT NULL,
  `company_other` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of company_enrollment_history
-- ----------------------------

-- ----------------------------
-- Table structure for company_type
-- ----------------------------
DROP TABLE IF EXISTS `company_type`;
CREATE TABLE `company_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_other` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of company_type
-- ----------------------------
INSERT INTO `company_type` VALUES (1, 'CompanyTypeA', 0);
INSERT INTO `company_type` VALUES (2, 'CompanyTypeB', 0);
INSERT INTO `company_type` VALUES (3, 'Other', 1);

-- ----------------------------
-- Table structure for configuration
-- ----------------------------
DROP TABLE IF EXISTS `configuration`;
CREATE TABLE `configuration`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `source` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of configuration
-- ----------------------------
INSERT INTO `configuration` VALUES (1, 'send_otp', '2', 1, 'ADMIN');
INSERT INTO `configuration` VALUES (2, 'send_otp', '2', 1, 'USER');

-- ----------------------------
-- Table structure for count_otp
-- ----------------------------
DROP TABLE IF EXISTS `count_otp`;
CREATE TABLE `count_otp`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `count_otp` int NULL DEFAULT NULL,
  `is_used` tinyint NULL DEFAULT NULL,
  `otp1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `otp2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of count_otp
-- ----------------------------

-- ----------------------------
-- Table structure for history_access
-- ----------------------------
DROP TABLE IF EXISTS `history_access`;
CREATE TABLE `history_access`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `trace_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `content` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `endpoint` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1167 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of history_access
-- ----------------------------
INSERT INTO `history_access` VALUES (1133, '-241346351822708542', '[SendSMS]', 'Send SMS to phone 123984496817 successfully, message: The verification number forgot password is: 212686', '2023-09-21 20:13:11', '');
INSERT INTO `history_access` VALUES (1134, '-4433293787657998326', '[SendEmail]', 'Send mail from nxtung95@gmail.com, to: nxtung2321312395@gmail.com, subject: Your OTP number, message:Your new password is: o^*61CvN', '2023-09-21 20:13:19', '');
INSERT INTO `history_access` VALUES (1135, '-4433293787657998326', '[SendSMS]', 'Send SMS to phone 123984496817 successfully, message: Your new password is: o^*61CvN', '2023-09-21 20:13:19', '');
INSERT INTO `history_access` VALUES (1136, '-3452516125888715862', '[SendSMS]', 'Send SMS to phone 123984496817 successfully, message: The verification number for verifying login is: 316527', '2023-09-21 20:14:01', '');
INSERT INTO `history_access` VALUES (1137, '-255668161129141645', '[SendEmail]', 'Send mail from nxtung95@gmail.com, to: nxtung2321312395@gmail.com, subject: 123984496817, message:Here is your clientId and secretKey, please dont provide to anyone!:\r\nClientID: 5126363451774417\r\nSecretID: 7881825681467175', '2023-09-21 20:14:07', '');
INSERT INTO `history_access` VALUES (1138, '-255668161129141645', '[SendSMS]', 'Send SMS to phone 123984496817 successfully, message: Here is your clientId and secretKey, please dont provide to anyone!:\r\nClientID: 5126363451774417\r\nSecretID: 7881825681467175', '2023-09-21 20:14:07', '');
INSERT INTO `history_access` VALUES (1139, '-4855954349275258131', '[SendSMS]', 'Send SMS to phone 123984496817 successfully, message: The verification number reset password is: 437823', '2023-09-21 20:19:25', '');
INSERT INTO `history_access` VALUES (1140, '-3608343055936967730', '[SendSMS]', 'Send SMS to phone 123984496817 successfully, message: The verification number for verifying login is: 215372', '2023-09-21 20:20:04', '');
INSERT INTO `history_access` VALUES (1141, '-429703420960943307', '[SendSMS]', 'Send SMS to phone 0984496817 successfully, message: The verification number for verifying login is: 722757', '2023-09-21 21:13:53', '');
INSERT INTO `history_access` VALUES (1142, '1753517318675151574', '[SendEmail]', 'Send mail from nxtung95@gmail.com, to: nxtung300195@gmail.com, subject: Please verify your account!, message:Please access URL to verify your account: <a href=\'localhost:8080/app/login\'>localhost:8080/app/login</a>.\nYour password: 5GeL^r&6', '2023-09-21 21:14:29', '');
INSERT INTO `history_access` VALUES (1143, '5621641862636357328', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: The verification number for verifying login is: 441182', '2023-09-21 21:15:22', '');
INSERT INTO `history_access` VALUES (1144, '4864249909412200459', '[SendEmail]', 'Send mail from nxtung95@gmail.com, to: nxtung300195@gmail.com, subject: 9984496817, message:Here is your clientId and secretKey, please dont provide to anyone!:\r\nClientID: 6375123475467882\r\nSecretID: 4534514536485837', '2023-09-21 21:15:32', '');
INSERT INTO `history_access` VALUES (1145, '4864249909412200459', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: Here is your clientId and secretKey, please dont provide to anyone!:\r\nClientID: 6375123475467882\r\nSecretID: 4534514536485837', '2023-09-21 21:15:32', '');
INSERT INTO `history_access` VALUES (1146, '-2383808506355304757', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: The verification number for verifying login is: 511212', '2023-09-21 21:15:54', '');
INSERT INTO `history_access` VALUES (1147, '9215934891989342518', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: The verification number forgot password is: 874217', '2023-09-21 21:16:35', '');
INSERT INTO `history_access` VALUES (1148, '3074475317540758808', '[SendEmail]', 'Send mail from nxtung95@gmail.com, to: nxtung300195@gmail.com, subject: Your OTP number, message:Your new password is: y$rQE@03', '2023-09-21 21:16:40', '');
INSERT INTO `history_access` VALUES (1149, '3074475317540758808', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: Your new password is: y$rQE@03', '2023-09-21 21:16:40', '');
INSERT INTO `history_access` VALUES (1150, '3179309c933fdd88', 'EXCEPTION', 'For input string: \"\"', '2023-09-21 21:17:37', 'findById');
INSERT INTO `history_access` VALUES (1151, '553968c374682daa', 'EXCEPTION', 'For input string: \"\"', '2023-09-21 21:18:14', 'findById');
INSERT INTO `history_access` VALUES (1152, '-3117595456467470368', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: The verification number forgot password is: 586115', '2023-09-21 21:18:42', '');
INSERT INTO `history_access` VALUES (1153, '-867886056767054663', '[SendEmail]', 'Send mail from nxtung95@gmail.com, to: nxtung300195@gmail.com, subject: Your OTP number, message:Your new password is: 9iK3*$oY', '2023-09-21 21:18:48', '');
INSERT INTO `history_access` VALUES (1154, '-867886056767054663', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: Your new password is: 9iK3*$oY', '2023-09-21 21:18:48', '');
INSERT INTO `history_access` VALUES (1155, '-1272441030512875190', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: The verification number forgot password is: 417471', '2023-09-21 21:25:26', '');
INSERT INTO `history_access` VALUES (1156, '3896845518354819328', '[SendEmail]', 'Send mail from nxtung95@gmail.com, to: nxtung300195@gmail.com, subject: Your OTP number, message:Your new password is: $nH8H&1f', '2023-09-21 21:26:25', '');
INSERT INTO `history_access` VALUES (1157, '3896845518354819328', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: Your new password is: $nH8H&1f', '2023-09-21 21:26:25', '');
INSERT INTO `history_access` VALUES (1158, '8840595172567825196', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: The verification number for verifying login is: 152846', '2023-09-21 21:30:24', '');
INSERT INTO `history_access` VALUES (1159, '1421698190165420125', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: The verification number for verifying login is: 656867', '2023-09-22 16:04:46', '');
INSERT INTO `history_access` VALUES (1160, '-5046125337035753560', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: The verification number for verifying login is: 867622', '2023-09-22 16:09:03', '');
INSERT INTO `history_access` VALUES (1161, '670290594565395056', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: The verification number reset password is: 688234', '2023-09-22 16:09:30', '');
INSERT INTO `history_access` VALUES (1162, '-6313459852162193505', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: The verification number for verifying login is: 563628', '2023-09-22 16:16:46', '');
INSERT INTO `history_access` VALUES (1163, '-6095536061939629324', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: The verification number reset password is: 882728', '2023-09-22 16:17:25', '');
INSERT INTO `history_access` VALUES (1164, '1858155424389390460', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: The verification number for verifying login is: 876477', '2023-09-22 16:38:30', '');
INSERT INTO `history_access` VALUES (1165, '3109862947212207957', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: The verification number reset password is: 118324', '2023-09-22 16:46:09', '');
INSERT INTO `history_access` VALUES (1166, '9116256490157992965', '[SendSMS]', 'Send SMS to phone 9984496817 successfully, message: The verification number reset password is: 483184', '2023-09-22 16:46:39', '');

-- ----------------------------
-- Table structure for manage_public_key
-- ----------------------------
DROP TABLE IF EXISTS `manage_public_key`;
CREATE TABLE `manage_public_key`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `enrollment_id` int NULL DEFAULT NULL,
  `service_group_id` int NULL DEFAULT NULL,
  `service_id_list` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `public_key` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manage_public_key
-- ----------------------------
INSERT INTO `manage_public_key` VALUES (1, 22, 1, '1,2', 'clientId', 'clientSecret', NULL, 1, NULL, '2023-09-20 14:49:12');
INSERT INTO `manage_public_key` VALUES (22, 23, 1, '1', '1276467734452274', '4811355251236768', NULL, 1, '2023-09-19 16:39:23', '2023-09-19 16:39:23');
INSERT INTO `manage_public_key` VALUES (23, 24, 1, '1', '4561658467263155', '7825748123167231', NULL, 1, '2023-09-19 16:51:44', '2023-09-19 16:51:44');
INSERT INTO `manage_public_key` VALUES (24, 25, 1, '2', '1764466254883874', '8784284817361843', NULL, 1, '2023-09-20 09:24:44', '2023-09-20 09:24:44');
INSERT INTO `manage_public_key` VALUES (25, 26, 1, '1,2', '2712172758271522', '8115344182524733', NULL, 1, '2023-09-20 09:35:27', '2023-09-20 09:35:27');
INSERT INTO `manage_public_key` VALUES (26, 27, 1, '1,2', '7431811828843686', '5516788224321787', NULL, 1, '2023-09-20 10:18:39', '2023-09-20 10:18:39');
INSERT INTO `manage_public_key` VALUES (27, 28, 1, '1,2', '5338423837855274', '8625476115185584', NULL, 1, '2023-09-20 10:27:51', '2023-09-20 14:49:56');
INSERT INTO `manage_public_key` VALUES (28, 29, 1, '2', '5242831778417763', '4522583723534434', NULL, 1, '2023-09-20 10:31:56', '2023-09-20 10:31:56');
INSERT INTO `manage_public_key` VALUES (29, 30, 1, '2', '5828867751445821', '2816246253743133', NULL, 1, '2023-09-20 10:38:27', '2023-09-20 13:36:43');
INSERT INTO `manage_public_key` VALUES (30, 30, 2, '3', '3368441531476267', '2313721647154635', NULL, 1, '2023-09-20 11:33:04', '2023-09-20 13:36:43');
INSERT INTO `manage_public_key` VALUES (31, 30, 3, '4', '1451776442424544', '4253333511673147', NULL, 1, '2023-09-20 13:36:43', '2023-09-20 13:36:43');
INSERT INTO `manage_public_key` VALUES (32, 28, 2, '3', '6726528272851383', '8236465784676141', NULL, 1, '2023-09-20 13:42:49', '2023-09-20 14:49:56');
INSERT INTO `manage_public_key` VALUES (33, 28, 3, '5', '5838745231676748', '7423512813367528', NULL, 1, '2023-09-20 13:42:49', '2023-09-20 14:49:56');
INSERT INTO `manage_public_key` VALUES (34, 22, 2, '3', '6181618442867228', '8618181153611637', NULL, 1, '2023-09-20 14:49:12', '2023-09-20 14:49:12');
INSERT INTO `manage_public_key` VALUES (35, 22, 3, '4', '3157567114874536', '1872883682268323', NULL, 1, '2023-09-20 14:49:12', '2023-09-20 14:49:12');
INSERT INTO `manage_public_key` VALUES (36, 31, 1, '1', '1224864666445128', '2816157563865356', NULL, 1, '2023-09-21 16:52:52', '2023-09-21 16:53:03');
INSERT INTO `manage_public_key` VALUES (37, 31, 3, '4', '4576344127853887', '4627321234331463', NULL, 1, '2023-09-21 16:53:03', '2023-09-21 16:53:03');
INSERT INTO `manage_public_key` VALUES (38, 32, 1, '1', '5475437841651555', '2282814414456272', NULL, 1, '2023-09-21 21:14:29', '2023-09-21 21:14:29');

-- ----------------------------
-- Table structure for manage_public_key_history
-- ----------------------------
DROP TABLE IF EXISTS `manage_public_key_history`;
CREATE TABLE `manage_public_key_history`  (
  `id` int NOT NULL,
  `enrollment_id` int NULL DEFAULT NULL,
  `service_group_id` int NULL DEFAULT NULL,
  `service_id_list` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `public_key` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manage_public_key_history
-- ----------------------------

-- ----------------------------
-- Table structure for phone_country_code
-- ----------------------------
DROP TABLE IF EXISTS `phone_country_code`;
CREATE TABLE `phone_country_code`  (
  `id` int NOT NULL,
  `iso` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `nicename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `iso3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `numcode` int NULL DEFAULT NULL,
  `phonecode` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of phone_country_code
-- ----------------------------
INSERT INTO `phone_country_code` VALUES (1, 'AF', 'AFGHANISTAN', 'Afghanistan', 'AFG', 4, 93);
INSERT INTO `phone_country_code` VALUES (2, 'AL', 'ALBANIA', 'Albania', 'ALB', 8, 355);
INSERT INTO `phone_country_code` VALUES (3, 'DZ', 'ALGERIA', 'Algeria', 'DZA', 12, 213);
INSERT INTO `phone_country_code` VALUES (4, 'AS', 'AMERICAN SAMOA', 'American Samoa', 'ASM', 16, 1684);
INSERT INTO `phone_country_code` VALUES (5, 'AD', 'ANDORRA', 'Andorra', 'AND', 20, 376);
INSERT INTO `phone_country_code` VALUES (6, 'AO', 'ANGOLA', 'Angola', 'AGO', 24, 244);
INSERT INTO `phone_country_code` VALUES (7, 'AI', 'ANGUILLA', 'Anguilla', 'AIA', 660, 1264);
INSERT INTO `phone_country_code` VALUES (8, 'AQ', 'ANTARCTICA', 'Antarctica', NULL, NULL, 0);
INSERT INTO `phone_country_code` VALUES (9, 'AG', 'ANTIGUA AND BARBUDA', 'Antigua and Barbuda', 'ATG', 28, 1268);
INSERT INTO `phone_country_code` VALUES (10, 'AR', 'ARGENTINA', 'Argentina', 'ARG', 32, 54);
INSERT INTO `phone_country_code` VALUES (11, 'AM', 'ARMENIA', 'Armenia', 'ARM', 51, 374);
INSERT INTO `phone_country_code` VALUES (12, 'AW', 'ARUBA', 'Aruba', 'ABW', 533, 297);
INSERT INTO `phone_country_code` VALUES (13, 'AU', 'AUSTRALIA', 'Australia', 'AUS', 36, 61);
INSERT INTO `phone_country_code` VALUES (14, 'AT', 'AUSTRIA', 'Austria', 'AUT', 40, 43);
INSERT INTO `phone_country_code` VALUES (15, 'AZ', 'AZERBAIJAN', 'Azerbaijan', 'AZE', 31, 994);
INSERT INTO `phone_country_code` VALUES (16, 'BS', 'BAHAMAS', 'Bahamas', 'BHS', 44, 1242);
INSERT INTO `phone_country_code` VALUES (17, 'BH', 'BAHRAIN', 'Bahrain', 'BHR', 48, 973);
INSERT INTO `phone_country_code` VALUES (18, 'BD', 'BANGLADESH', 'Bangladesh', 'BGD', 50, 880);
INSERT INTO `phone_country_code` VALUES (19, 'BB', 'BARBADOS', 'Barbados', 'BRB', 52, 1246);
INSERT INTO `phone_country_code` VALUES (20, 'BY', 'BELARUS', 'Belarus', 'BLR', 112, 375);
INSERT INTO `phone_country_code` VALUES (21, 'BE', 'BELGIUM', 'Belgium', 'BEL', 56, 32);
INSERT INTO `phone_country_code` VALUES (22, 'BZ', 'BELIZE', 'Belize', 'BLZ', 84, 501);
INSERT INTO `phone_country_code` VALUES (23, 'BJ', 'BENIN', 'Benin', 'BEN', 204, 229);
INSERT INTO `phone_country_code` VALUES (24, 'BM', 'BERMUDA', 'Bermuda', 'BMU', 60, 1441);
INSERT INTO `phone_country_code` VALUES (25, 'BT', 'BHUTAN', 'Bhutan', 'BTN', 64, 975);
INSERT INTO `phone_country_code` VALUES (26, 'BO', 'BOLIVIA', 'Bolivia', 'BOL', 68, 591);
INSERT INTO `phone_country_code` VALUES (27, 'BA', 'BOSNIA AND HERZEGOVINA', 'Bosnia and Herzegovina', 'BIH', 70, 387);
INSERT INTO `phone_country_code` VALUES (28, 'BW', 'BOTSWANA', 'Botswana', 'BWA', 72, 267);
INSERT INTO `phone_country_code` VALUES (29, 'BV', 'BOUVET ISLAND', 'Bouvet Island', NULL, NULL, 0);
INSERT INTO `phone_country_code` VALUES (30, 'BR', 'BRAZIL', 'Brazil', 'BRA', 76, 55);
INSERT INTO `phone_country_code` VALUES (31, 'IO', 'BRITISH INDIAN OCEAN TERRITORY', 'British Indian Ocean Territory', NULL, NULL, 246);
INSERT INTO `phone_country_code` VALUES (32, 'BN', 'BRUNEI DARUSSALAM', 'Brunei Darussalam', 'BRN', 96, 673);
INSERT INTO `phone_country_code` VALUES (33, 'BG', 'BULGARIA', 'Bulgaria', 'BGR', 100, 359);
INSERT INTO `phone_country_code` VALUES (34, 'BF', 'BURKINA FASO', 'Burkina Faso', 'BFA', 854, 226);
INSERT INTO `phone_country_code` VALUES (35, 'BI', 'BURUNDI', 'Burundi', 'BDI', 108, 257);
INSERT INTO `phone_country_code` VALUES (36, 'KH', 'CAMBODIA', 'Cambodia', 'KHM', 116, 855);
INSERT INTO `phone_country_code` VALUES (37, 'CM', 'CAMEROON', 'Cameroon', 'CMR', 120, 237);
INSERT INTO `phone_country_code` VALUES (38, 'CA', 'CANADA', 'Canada', 'CAN', 124, 1);
INSERT INTO `phone_country_code` VALUES (39, 'CV', 'CAPE VERDE', 'Cape Verde', 'CPV', 132, 238);
INSERT INTO `phone_country_code` VALUES (40, 'KY', 'CAYMAN ISLANDS', 'Cayman Islands', 'CYM', 136, 1345);
INSERT INTO `phone_country_code` VALUES (41, 'CF', 'CENTRAL AFRICAN REPUBLIC', 'Central African Republic', 'CAF', 140, 236);
INSERT INTO `phone_country_code` VALUES (42, 'TD', 'CHAD', 'Chad', 'TCD', 148, 235);
INSERT INTO `phone_country_code` VALUES (43, 'CL', 'CHILE', 'Chile', 'CHL', 152, 56);
INSERT INTO `phone_country_code` VALUES (44, 'CN', 'CHINA', 'China', 'CHN', 156, 86);
INSERT INTO `phone_country_code` VALUES (45, 'CX', 'CHRISTMAS ISLAND', 'Christmas Island', NULL, NULL, 61);
INSERT INTO `phone_country_code` VALUES (46, 'CC', 'COCOS (KEELING) ISLANDS', 'Cocos (Keeling) Islands', NULL, NULL, 672);
INSERT INTO `phone_country_code` VALUES (47, 'CO', 'COLOMBIA', 'Colombia', 'COL', 170, 57);
INSERT INTO `phone_country_code` VALUES (48, 'KM', 'COMOROS', 'Comoros', 'COM', 174, 269);
INSERT INTO `phone_country_code` VALUES (49, 'CG', 'CONGO', 'Congo', 'COG', 178, 242);
INSERT INTO `phone_country_code` VALUES (50, 'CD', 'CONGO, THE DEMOCRATIC REPUBLIC OF THE', 'Congo, the Democratic Republic of the', 'COD', 180, 242);
INSERT INTO `phone_country_code` VALUES (51, 'CK', 'COOK ISLANDS', 'Cook Islands', 'COK', 184, 682);
INSERT INTO `phone_country_code` VALUES (52, 'CR', 'COSTA RICA', 'Costa Rica', 'CRI', 188, 506);
INSERT INTO `phone_country_code` VALUES (53, 'CI', 'COTE D\'IVOIRE', 'Cote D\'Ivoire', 'CIV', 384, 225);
INSERT INTO `phone_country_code` VALUES (54, 'HR', 'CROATIA', 'Croatia', 'HRV', 191, 385);
INSERT INTO `phone_country_code` VALUES (55, 'CU', 'CUBA', 'Cuba', 'CUB', 192, 53);
INSERT INTO `phone_country_code` VALUES (56, 'CY', 'CYPRUS', 'Cyprus', 'CYP', 196, 357);
INSERT INTO `phone_country_code` VALUES (57, 'CZ', 'CZECH REPUBLIC', 'Czech Republic', 'CZE', 203, 420);
INSERT INTO `phone_country_code` VALUES (58, 'DK', 'DENMARK', 'Denmark', 'DNK', 208, 45);
INSERT INTO `phone_country_code` VALUES (59, 'DJ', 'DJIBOUTI', 'Djibouti', 'DJI', 262, 253);
INSERT INTO `phone_country_code` VALUES (60, 'DM', 'DOMINICA', 'Dominica', 'DMA', 212, 1767);
INSERT INTO `phone_country_code` VALUES (61, 'DO', 'DOMINICAN REPUBLIC', 'Dominican Republic', 'DOM', 214, 1809);
INSERT INTO `phone_country_code` VALUES (62, 'EC', 'ECUADOR', 'Ecuador', 'ECU', 218, 593);
INSERT INTO `phone_country_code` VALUES (63, 'EG', 'EGYPT', 'Egypt', 'EGY', 818, 20);
INSERT INTO `phone_country_code` VALUES (64, 'SV', 'EL SALVADOR', 'El Salvador', 'SLV', 222, 503);
INSERT INTO `phone_country_code` VALUES (65, 'GQ', 'EQUATORIAL GUINEA', 'Equatorial Guinea', 'GNQ', 226, 240);
INSERT INTO `phone_country_code` VALUES (66, 'ER', 'ERITREA', 'Eritrea', 'ERI', 232, 291);
INSERT INTO `phone_country_code` VALUES (67, 'EE', 'ESTONIA', 'Estonia', 'EST', 233, 372);
INSERT INTO `phone_country_code` VALUES (68, 'ET', 'ETHIOPIA', 'Ethiopia', 'ETH', 231, 251);
INSERT INTO `phone_country_code` VALUES (69, 'FK', 'FALKLAND ISLANDS (MALVINAS)', 'Falkland Islands (Malvinas)', 'FLK', 238, 500);
INSERT INTO `phone_country_code` VALUES (70, 'FO', 'FAROE ISLANDS', 'Faroe Islands', 'FRO', 234, 298);
INSERT INTO `phone_country_code` VALUES (71, 'FJ', 'FIJI', 'Fiji', 'FJI', 242, 679);
INSERT INTO `phone_country_code` VALUES (72, 'FI', 'FINLAND', 'Finland', 'FIN', 246, 358);
INSERT INTO `phone_country_code` VALUES (73, 'FR', 'FRANCE', 'France', 'FRA', 250, 33);
INSERT INTO `phone_country_code` VALUES (74, 'GF', 'FRENCH GUIANA', 'French Guiana', 'GUF', 254, 594);
INSERT INTO `phone_country_code` VALUES (75, 'PF', 'FRENCH POLYNESIA', 'French Polynesia', 'PYF', 258, 689);
INSERT INTO `phone_country_code` VALUES (76, 'TF', 'FRENCH SOUTHERN TERRITORIES', 'French Southern Territories', NULL, NULL, 0);
INSERT INTO `phone_country_code` VALUES (77, 'GA', 'GABON', 'Gabon', 'GAB', 266, 241);
INSERT INTO `phone_country_code` VALUES (78, 'GM', 'GAMBIA', 'Gambia', 'GMB', 270, 220);
INSERT INTO `phone_country_code` VALUES (79, 'GE', 'GEORGIA', 'Georgia', 'GEO', 268, 995);
INSERT INTO `phone_country_code` VALUES (80, 'DE', 'GERMANY', 'Germany', 'DEU', 276, 49);
INSERT INTO `phone_country_code` VALUES (81, 'GH', 'GHANA', 'Ghana', 'GHA', 288, 233);
INSERT INTO `phone_country_code` VALUES (82, 'GI', 'GIBRALTAR', 'Gibraltar', 'GIB', 292, 350);
INSERT INTO `phone_country_code` VALUES (83, 'GR', 'GREECE', 'Greece', 'GRC', 300, 30);
INSERT INTO `phone_country_code` VALUES (84, 'GL', 'GREENLAND', 'Greenland', 'GRL', 304, 299);
INSERT INTO `phone_country_code` VALUES (85, 'GD', 'GRENADA', 'Grenada', 'GRD', 308, 1473);
INSERT INTO `phone_country_code` VALUES (86, 'GP', 'GUADELOUPE', 'Guadeloupe', 'GLP', 312, 590);
INSERT INTO `phone_country_code` VALUES (87, 'GU', 'GUAM', 'Guam', 'GUM', 316, 1671);
INSERT INTO `phone_country_code` VALUES (88, 'GT', 'GUATEMALA', 'Guatemala', 'GTM', 320, 502);
INSERT INTO `phone_country_code` VALUES (89, 'GN', 'GUINEA', 'Guinea', 'GIN', 324, 224);
INSERT INTO `phone_country_code` VALUES (90, 'GW', 'GUINEA-BISSAU', 'Guinea-Bissau', 'GNB', 624, 245);
INSERT INTO `phone_country_code` VALUES (91, 'GY', 'GUYANA', 'Guyana', 'GUY', 328, 592);
INSERT INTO `phone_country_code` VALUES (92, 'HT', 'HAITI', 'Haiti', 'HTI', 332, 509);
INSERT INTO `phone_country_code` VALUES (93, 'HM', 'HEARD ISLAND AND MCDONALD ISLANDS', 'Heard Island and Mcdonald Islands', NULL, NULL, 0);
INSERT INTO `phone_country_code` VALUES (94, 'VA', 'HOLY SEE (VATICAN CITY STATE)', 'Holy See (Vatican City State)', 'VAT', 336, 39);
INSERT INTO `phone_country_code` VALUES (95, 'HN', 'HONDURAS', 'Honduras', 'HND', 340, 504);
INSERT INTO `phone_country_code` VALUES (96, 'HK', 'HONG KONG', 'Hong Kong', 'HKG', 344, 852);
INSERT INTO `phone_country_code` VALUES (97, 'HU', 'HUNGARY', 'Hungary', 'HUN', 348, 36);
INSERT INTO `phone_country_code` VALUES (98, 'IS', 'ICELAND', 'Iceland', 'ISL', 352, 354);
INSERT INTO `phone_country_code` VALUES (99, 'IN', 'INDIA', 'India', 'IND', 356, 91);
INSERT INTO `phone_country_code` VALUES (100, 'ID', 'INDONESIA', 'Indonesia', 'IDN', 360, 62);
INSERT INTO `phone_country_code` VALUES (101, 'IR', 'IRAN, ISLAMIC REPUBLIC OF', 'Iran, Islamic Republic of', 'IRN', 364, 98);
INSERT INTO `phone_country_code` VALUES (102, 'IQ', 'IRAQ', 'Iraq', 'IRQ', 368, 964);
INSERT INTO `phone_country_code` VALUES (103, 'IE', 'IRELAND', 'Ireland', 'IRL', 372, 353);
INSERT INTO `phone_country_code` VALUES (104, 'IL', 'ISRAEL', 'Israel', 'ISR', 376, 972);
INSERT INTO `phone_country_code` VALUES (105, 'IT', 'ITALY', 'Italy', 'ITA', 380, 39);
INSERT INTO `phone_country_code` VALUES (106, 'JM', 'JAMAICA', 'Jamaica', 'JAM', 388, 1876);
INSERT INTO `phone_country_code` VALUES (107, 'JP', 'JAPAN', 'Japan', 'JPN', 392, 81);
INSERT INTO `phone_country_code` VALUES (108, 'JO', 'JORDAN', 'Jordan', 'JOR', 400, 962);
INSERT INTO `phone_country_code` VALUES (109, 'KZ', 'KAZAKHSTAN', 'Kazakhstan', 'KAZ', 398, 7);
INSERT INTO `phone_country_code` VALUES (110, 'KE', 'KENYA', 'Kenya', 'KEN', 404, 254);
INSERT INTO `phone_country_code` VALUES (111, 'KI', 'KIRIBATI', 'Kiribati', 'KIR', 296, 686);
INSERT INTO `phone_country_code` VALUES (112, 'KP', 'KOREA, DEMOCRATIC PEOPLE\'S REPUBLIC OF', 'Korea, Democratic People\'s Republic of', 'PRK', 408, 850);
INSERT INTO `phone_country_code` VALUES (113, 'KR', 'KOREA, REPUBLIC OF', 'Korea, Republic of', 'KOR', 410, 82);
INSERT INTO `phone_country_code` VALUES (114, 'KW', 'KUWAIT', 'Kuwait', 'KWT', 414, 965);
INSERT INTO `phone_country_code` VALUES (115, 'KG', 'KYRGYZSTAN', 'Kyrgyzstan', 'KGZ', 417, 996);
INSERT INTO `phone_country_code` VALUES (116, 'LA', 'LAO PEOPLE\'S DEMOCRATIC REPUBLIC', 'Lao People\'s Democratic Republic', 'LAO', 418, 856);
INSERT INTO `phone_country_code` VALUES (117, 'LV', 'LATVIA', 'Latvia', 'LVA', 428, 371);
INSERT INTO `phone_country_code` VALUES (118, 'LB', 'LEBANON', 'Lebanon', 'LBN', 422, 961);
INSERT INTO `phone_country_code` VALUES (119, 'LS', 'LESOTHO', 'Lesotho', 'LSO', 426, 266);
INSERT INTO `phone_country_code` VALUES (120, 'LR', 'LIBERIA', 'Liberia', 'LBR', 430, 231);
INSERT INTO `phone_country_code` VALUES (121, 'LY', 'LIBYAN ARAB JAMAHIRIYA', 'Libyan Arab Jamahiriya', 'LBY', 434, 218);
INSERT INTO `phone_country_code` VALUES (122, 'LI', 'LIECHTENSTEIN', 'Liechtenstein', 'LIE', 438, 423);
INSERT INTO `phone_country_code` VALUES (123, 'LT', 'LITHUANIA', 'Lithuania', 'LTU', 440, 370);
INSERT INTO `phone_country_code` VALUES (124, 'LU', 'LUXEMBOURG', 'Luxembourg', 'LUX', 442, 352);
INSERT INTO `phone_country_code` VALUES (125, 'MO', 'MACAO', 'Macao', 'MAC', 446, 853);
INSERT INTO `phone_country_code` VALUES (126, 'MK', 'MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF', 'Macedonia, the Former Yugoslav Republic of', 'MKD', 807, 389);
INSERT INTO `phone_country_code` VALUES (127, 'MG', 'MADAGASCAR', 'Madagascar', 'MDG', 450, 261);
INSERT INTO `phone_country_code` VALUES (128, 'MW', 'MALAWI', 'Malawi', 'MWI', 454, 265);
INSERT INTO `phone_country_code` VALUES (129, 'MY', 'MALAYSIA', 'Malaysia', 'MYS', 458, 60);
INSERT INTO `phone_country_code` VALUES (130, 'MV', 'MALDIVES', 'Maldives', 'MDV', 462, 960);
INSERT INTO `phone_country_code` VALUES (131, 'ML', 'MALI', 'Mali', 'MLI', 466, 223);
INSERT INTO `phone_country_code` VALUES (132, 'MT', 'MALTA', 'Malta', 'MLT', 470, 356);
INSERT INTO `phone_country_code` VALUES (133, 'MH', 'MARSHALL ISLANDS', 'Marshall Islands', 'MHL', 584, 692);
INSERT INTO `phone_country_code` VALUES (134, 'MQ', 'MARTINIQUE', 'Martinique', 'MTQ', 474, 596);
INSERT INTO `phone_country_code` VALUES (135, 'MR', 'MAURITANIA', 'Mauritania', 'MRT', 478, 222);
INSERT INTO `phone_country_code` VALUES (136, 'MU', 'MAURITIUS', 'Mauritius', 'MUS', 480, 230);
INSERT INTO `phone_country_code` VALUES (137, 'YT', 'MAYOTTE', 'Mayotte', NULL, NULL, 269);
INSERT INTO `phone_country_code` VALUES (138, 'MX', 'MEXICO', 'Mexico', 'MEX', 484, 52);
INSERT INTO `phone_country_code` VALUES (139, 'FM', 'MICRONESIA, FEDERATED STATES OF', 'Micronesia, Federated States of', 'FSM', 583, 691);
INSERT INTO `phone_country_code` VALUES (140, 'MD', 'MOLDOVA, REPUBLIC OF', 'Moldova, Republic of', 'MDA', 498, 373);
INSERT INTO `phone_country_code` VALUES (141, 'MC', 'MONACO', 'Monaco', 'MCO', 492, 377);
INSERT INTO `phone_country_code` VALUES (142, 'MN', 'MONGOLIA', 'Mongolia', 'MNG', 496, 976);
INSERT INTO `phone_country_code` VALUES (143, 'MS', 'MONTSERRAT', 'Montserrat', 'MSR', 500, 1664);
INSERT INTO `phone_country_code` VALUES (144, 'MA', 'MOROCCO', 'Morocco', 'MAR', 504, 212);
INSERT INTO `phone_country_code` VALUES (145, 'MZ', 'MOZAMBIQUE', 'Mozambique', 'MOZ', 508, 258);
INSERT INTO `phone_country_code` VALUES (146, 'MM', 'MYANMAR', 'Myanmar', 'MMR', 104, 95);
INSERT INTO `phone_country_code` VALUES (147, 'NA', 'NAMIBIA', 'Namibia', 'NAM', 516, 264);
INSERT INTO `phone_country_code` VALUES (148, 'NR', 'NAURU', 'Nauru', 'NRU', 520, 674);
INSERT INTO `phone_country_code` VALUES (149, 'NP', 'NEPAL', 'Nepal', 'NPL', 524, 977);
INSERT INTO `phone_country_code` VALUES (150, 'NL', 'NETHERLANDS', 'Netherlands', 'NLD', 528, 31);
INSERT INTO `phone_country_code` VALUES (151, 'AN', 'NETHERLANDS ANTILLES', 'Netherlands Antilles', 'ANT', 530, 599);
INSERT INTO `phone_country_code` VALUES (152, 'NC', 'NEW CALEDONIA', 'New Caledonia', 'NCL', 540, 687);
INSERT INTO `phone_country_code` VALUES (153, 'NZ', 'NEW ZEALAND', 'New Zealand', 'NZL', 554, 64);
INSERT INTO `phone_country_code` VALUES (154, 'NI', 'NICARAGUA', 'Nicaragua', 'NIC', 558, 505);
INSERT INTO `phone_country_code` VALUES (155, 'NE', 'NIGER', 'Niger', 'NER', 562, 227);
INSERT INTO `phone_country_code` VALUES (156, 'NG', 'NIGERIA', 'Nigeria', 'NGA', 566, 234);
INSERT INTO `phone_country_code` VALUES (157, 'NU', 'NIUE', 'Niue', 'NIU', 570, 683);
INSERT INTO `phone_country_code` VALUES (158, 'NF', 'NORFOLK ISLAND', 'Norfolk Island', 'NFK', 574, 672);
INSERT INTO `phone_country_code` VALUES (159, 'MP', 'NORTHERN MARIANA ISLANDS', 'Northern Mariana Islands', 'MNP', 580, 1670);
INSERT INTO `phone_country_code` VALUES (160, 'NO', 'NORWAY', 'Norway', 'NOR', 578, 47);
INSERT INTO `phone_country_code` VALUES (161, 'OM', 'OMAN', 'Oman', 'OMN', 512, 968);
INSERT INTO `phone_country_code` VALUES (162, 'PK', 'PAKISTAN', 'Pakistan', 'PAK', 586, 92);
INSERT INTO `phone_country_code` VALUES (163, 'PW', 'PALAU', 'Palau', 'PLW', 585, 680);
INSERT INTO `phone_country_code` VALUES (164, 'PS', 'PALESTINIAN TERRITORY, OCCUPIED', 'Palestinian Territory, Occupied', NULL, NULL, 970);
INSERT INTO `phone_country_code` VALUES (165, 'PA', 'PANAMA', 'Panama', 'PAN', 591, 507);
INSERT INTO `phone_country_code` VALUES (166, 'PG', 'PAPUA NEW GUINEA', 'Papua New Guinea', 'PNG', 598, 675);
INSERT INTO `phone_country_code` VALUES (167, 'PY', 'PARAGUAY', 'Paraguay', 'PRY', 600, 595);
INSERT INTO `phone_country_code` VALUES (168, 'PE', 'PERU', 'Peru', 'PER', 604, 51);
INSERT INTO `phone_country_code` VALUES (169, 'PH', 'PHILIPPINES', 'Philippines', 'PHL', 608, 63);
INSERT INTO `phone_country_code` VALUES (170, 'PN', 'PITCAIRN', 'Pitcairn', 'PCN', 612, 0);
INSERT INTO `phone_country_code` VALUES (171, 'PL', 'POLAND', 'Poland', 'POL', 616, 48);
INSERT INTO `phone_country_code` VALUES (172, 'PT', 'PORTUGAL', 'Portugal', 'PRT', 620, 351);
INSERT INTO `phone_country_code` VALUES (173, 'PR', 'PUERTO RICO', 'Puerto Rico', 'PRI', 630, 1787);
INSERT INTO `phone_country_code` VALUES (174, 'QA', 'QATAR', 'Qatar', 'QAT', 634, 974);
INSERT INTO `phone_country_code` VALUES (175, 'RE', 'REUNION', 'Reunion', 'REU', 638, 262);
INSERT INTO `phone_country_code` VALUES (176, 'RO', 'ROMANIA', 'Romania', 'ROM', 642, 40);
INSERT INTO `phone_country_code` VALUES (177, 'RU', 'RUSSIAN FEDERATION', 'Russian Federation', 'RUS', 643, 70);
INSERT INTO `phone_country_code` VALUES (178, 'RW', 'RWANDA', 'Rwanda', 'RWA', 646, 250);
INSERT INTO `phone_country_code` VALUES (179, 'SH', 'SAINT HELENA', 'Saint Helena', 'SHN', 654, 290);
INSERT INTO `phone_country_code` VALUES (180, 'KN', 'SAINT KITTS AND NEVIS', 'Saint Kitts and Nevis', 'KNA', 659, 1869);
INSERT INTO `phone_country_code` VALUES (181, 'LC', 'SAINT LUCIA', 'Saint Lucia', 'LCA', 662, 1758);
INSERT INTO `phone_country_code` VALUES (182, 'PM', 'SAINT PIERRE AND MIQUELON', 'Saint Pierre and Miquelon', 'SPM', 666, 508);
INSERT INTO `phone_country_code` VALUES (183, 'VC', 'SAINT VINCENT AND THE GRENADINES', 'Saint Vincent and the Grenadines', 'VCT', 670, 1784);
INSERT INTO `phone_country_code` VALUES (184, 'WS', 'SAMOA', 'Samoa', 'WSM', 882, 684);
INSERT INTO `phone_country_code` VALUES (185, 'SM', 'SAN MARINO', 'San Marino', 'SMR', 674, 378);
INSERT INTO `phone_country_code` VALUES (186, 'ST', 'SAO TOME AND PRINCIPE', 'Sao Tome and Principe', 'STP', 678, 239);
INSERT INTO `phone_country_code` VALUES (187, 'SA', 'SAUDI ARABIA', 'Saudi Arabia', 'SAU', 682, 966);
INSERT INTO `phone_country_code` VALUES (188, 'SN', 'SENEGAL', 'Senegal', 'SEN', 686, 221);
INSERT INTO `phone_country_code` VALUES (189, 'CS', 'SERBIA AND MONTENEGRO', 'Serbia and Montenegro', NULL, NULL, 381);
INSERT INTO `phone_country_code` VALUES (190, 'SC', 'SEYCHELLES', 'Seychelles', 'SYC', 690, 248);
INSERT INTO `phone_country_code` VALUES (191, 'SL', 'SIERRA LEONE', 'Sierra Leone', 'SLE', 694, 232);
INSERT INTO `phone_country_code` VALUES (192, 'SG', 'SINGAPORE', 'Singapore', 'SGP', 702, 65);
INSERT INTO `phone_country_code` VALUES (193, 'SK', 'SLOVAKIA', 'Slovakia', 'SVK', 703, 421);
INSERT INTO `phone_country_code` VALUES (194, 'SI', 'SLOVENIA', 'Slovenia', 'SVN', 705, 386);
INSERT INTO `phone_country_code` VALUES (195, 'SB', 'SOLOMON ISLANDS', 'Solomon Islands', 'SLB', 90, 677);
INSERT INTO `phone_country_code` VALUES (196, 'SO', 'SOMALIA', 'Somalia', 'SOM', 706, 252);
INSERT INTO `phone_country_code` VALUES (197, 'ZA', 'SOUTH AFRICA', 'South Africa', 'ZAF', 710, 27);
INSERT INTO `phone_country_code` VALUES (198, 'GS', 'SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS', 'South Georgia and the South Sandwich Islands', NULL, NULL, 0);
INSERT INTO `phone_country_code` VALUES (199, 'ES', 'SPAIN', 'Spain', 'ESP', 724, 34);
INSERT INTO `phone_country_code` VALUES (200, 'LK', 'SRI LANKA', 'Sri Lanka', 'LKA', 144, 94);
INSERT INTO `phone_country_code` VALUES (201, 'SD', 'SUDAN', 'Sudan', 'SDN', 736, 249);
INSERT INTO `phone_country_code` VALUES (202, 'SR', 'SURINAME', 'Suriname', 'SUR', 740, 597);
INSERT INTO `phone_country_code` VALUES (203, 'SJ', 'SVALBARD AND JAN MAYEN', 'Svalbard and Jan Mayen', 'SJM', 744, 47);
INSERT INTO `phone_country_code` VALUES (204, 'SZ', 'SWAZILAND', 'Swaziland', 'SWZ', 748, 268);
INSERT INTO `phone_country_code` VALUES (205, 'SE', 'SWEDEN', 'Sweden', 'SWE', 752, 46);
INSERT INTO `phone_country_code` VALUES (206, 'CH', 'SWITZERLAND', 'Switzerland', 'CHE', 756, 41);
INSERT INTO `phone_country_code` VALUES (207, 'SY', 'SYRIAN ARAB REPUBLIC', 'Syrian Arab Republic', 'SYR', 760, 963);
INSERT INTO `phone_country_code` VALUES (208, 'TW', 'TAIWAN, PROVINCE OF CHINA', 'Taiwan, Province of China', 'TWN', 158, 886);
INSERT INTO `phone_country_code` VALUES (209, 'TJ', 'TAJIKISTAN', 'Tajikistan', 'TJK', 762, 992);
INSERT INTO `phone_country_code` VALUES (210, 'TZ', 'TANZANIA, UNITED REPUBLIC OF', 'Tanzania, United Republic of', 'TZA', 834, 255);
INSERT INTO `phone_country_code` VALUES (211, 'TH', 'THAILAND', 'Thailand', 'THA', 764, 66);
INSERT INTO `phone_country_code` VALUES (212, 'TL', 'TIMOR-LESTE', 'Timor-Leste', NULL, NULL, 670);
INSERT INTO `phone_country_code` VALUES (213, 'TG', 'TOGO', 'Togo', 'TGO', 768, 228);
INSERT INTO `phone_country_code` VALUES (214, 'TK', 'TOKELAU', 'Tokelau', 'TKL', 772, 690);
INSERT INTO `phone_country_code` VALUES (215, 'TO', 'TONGA', 'Tonga', 'TON', 776, 676);
INSERT INTO `phone_country_code` VALUES (216, 'TT', 'TRINIDAD AND TOBAGO', 'Trinidad and Tobago', 'TTO', 780, 1868);
INSERT INTO `phone_country_code` VALUES (217, 'TN', 'TUNISIA', 'Tunisia', 'TUN', 788, 216);
INSERT INTO `phone_country_code` VALUES (218, 'TR', 'TURKEY', 'Turkey', 'TUR', 792, 90);
INSERT INTO `phone_country_code` VALUES (219, 'TM', 'TURKMENISTAN', 'Turkmenistan', 'TKM', 795, 7370);
INSERT INTO `phone_country_code` VALUES (220, 'TC', 'TURKS AND CAICOS ISLANDS', 'Turks and Caicos Islands', 'TCA', 796, 1649);
INSERT INTO `phone_country_code` VALUES (221, 'TV', 'TUVALU', 'Tuvalu', 'TUV', 798, 688);
INSERT INTO `phone_country_code` VALUES (222, 'UG', 'UGANDA', 'Uganda', 'UGA', 800, 256);
INSERT INTO `phone_country_code` VALUES (223, 'UA', 'UKRAINE', 'Ukraine', 'UKR', 804, 380);
INSERT INTO `phone_country_code` VALUES (224, 'AE', 'UNITED ARAB EMIRATES', 'United Arab Emirates', 'ARE', 784, 971);
INSERT INTO `phone_country_code` VALUES (225, 'GB', 'UNITED KINGDOM', 'United Kingdom', 'GBR', 826, 44);
INSERT INTO `phone_country_code` VALUES (226, 'US', 'UNITED STATES', 'United States', 'USA', 840, 1);
INSERT INTO `phone_country_code` VALUES (227, 'UM', 'UNITED STATES MINOR OUTLYING ISLANDS', 'United States Minor Outlying Islands', NULL, NULL, 1);
INSERT INTO `phone_country_code` VALUES (228, 'UY', 'URUGUAY', 'Uruguay', 'URY', 858, 598);
INSERT INTO `phone_country_code` VALUES (229, 'UZ', 'UZBEKISTAN', 'Uzbekistan', 'UZB', 860, 998);
INSERT INTO `phone_country_code` VALUES (230, 'VU', 'VANUATU', 'Vanuatu', 'VUT', 548, 678);
INSERT INTO `phone_country_code` VALUES (231, 'VE', 'VENEZUELA', 'Venezuela', 'VEN', 862, 58);
INSERT INTO `phone_country_code` VALUES (232, 'VN', 'VIET NAM', 'Viet Nam', 'VNM', 704, 84);
INSERT INTO `phone_country_code` VALUES (233, 'VG', 'VIRGIN ISLANDS, BRITISH', 'Virgin Islands, British', 'VGB', 92, 1284);
INSERT INTO `phone_country_code` VALUES (234, 'VI', 'VIRGIN ISLANDS, U.S.', 'Virgin Islands, U.s.', 'VIR', 850, 1340);
INSERT INTO `phone_country_code` VALUES (235, 'WF', 'WALLIS AND FUTUNA', 'Wallis and Futuna', 'WLF', 876, 681);
INSERT INTO `phone_country_code` VALUES (236, 'EH', 'WESTERN SAHARA', 'Western Sahara', 'ESH', 732, 212);
INSERT INTO `phone_country_code` VALUES (237, 'YE', 'YEMEN', 'Yemen', 'YEM', 887, 967);
INSERT INTO `phone_country_code` VALUES (238, 'ZM', 'ZAMBIA', 'Zambia', 'ZMB', 894, 260);
INSERT INTO `phone_country_code` VALUES (239, 'ZW', 'ZIMBABWE', 'Zimbabwe', 'ZWE', 716, 263);

-- ----------------------------
-- Table structure for reset_password
-- ----------------------------
DROP TABLE IF EXISTS `reset_password`;
CREATE TABLE `reset_password`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `encode_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of reset_password
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ADMIN');
INSERT INTO `role` VALUES (2, 'USER');

-- ----------------------------
-- Table structure for server_key
-- ----------------------------
DROP TABLE IF EXISTS `server_key`;
CREATE TABLE `server_key`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `public_key` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `private_key` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of server_key
-- ----------------------------
INSERT INTO `server_key` VALUES (1, '-----BEGIN PUBLIC KEY-----\r\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXtPo/tToEtyrHbW4MxpVBuSLF\r\n1J2g9V6obQ9GmHrs+P27/qKO2axn37D6eBhuKpXR7aHRymRxYzuiATdUnZHkPrak\r\nXY2MAALzK2K/fPyKQVFnfkLfaTSw2gKmXsnng8+xKmYUiZ97e6+GTgpya+C4omSt\r\nPaOkyvWykCekFeNzhQIDAQAB\r\n-----END PUBLIC KEY-----', '-----BEGIN RSA PRIVATE KEY-----\r\nMIICXwIBAAKBgQCXtPo/tToEtyrHbW4MxpVBuSLF1J2g9V6obQ9GmHrs+P27/qKO\r\n2axn37D6eBhuKpXR7aHRymRxYzuiATdUnZHkPrakXY2MAALzK2K/fPyKQVFnfkLf\r\naTSw2gKmXsnng8+xKmYUiZ97e6+GTgpya+C4omStPaOkyvWykCekFeNzhQIDAQAB\r\nAoGBAJTE+RM4mSi+psji2yTUKNJGx37RPbL2bFkmefB03zc8BTcyQh9r/tHuXxOA\r\nahS+1iUDr8iXfWBkO7pnDud/jeyPVOMsIhrpKHbs+sGGqMvPl/t1nbdFu87MeVeT\r\nXM9qCjU66ML+OiiPz8OoUH2gYEO3hHIyD3aFW7Btc+xCNC8BAkEAzm1EGbpgtZUP\r\nXcA0etIPJmR17RHbd0U7xUYvxmARSJA+yjZbwgcz5QQ2NbC6PRTdTVgf93UDOBQH\r\nt98Ion7NkQJBALwjotSCiNqYlzjgCRU2gCuf3LhVuN6gvqz6ZU4RNHMSU567h1vR\r\nSzKTNTs2PodcpGET+E3OvgsSyFUy+pycXLUCQQC7R4KbXO5Uj5HLWo/CRkWcbVRX\r\n6vWTtepORikd7xZ/IO72gm6pD+PTCQktmNK+i+ljPGp0FWMAQ5vlA6JP2n8hAkEA\r\nh/1ONkNAPplrPS4bAp1JWb8MoKU9opYYHRpE3X/sJH0LukGcno6SxzTGlk0oN1mM\r\na6suoLcegQyLh6H4qfkPvQJBAMGWyaPmJeqS/XX1m/kXkj5LYRuc4suAMKKpbtMV\r\nOmYonZIeIdcp3QoZZKWuIgWSLLCuORmxAQU53DNBY8n/5+g=\r\n-----BEGIN RSA PRIVATE KEY-----\r\nMIICXwIBAAKBgQCXtPo/tToEtyrHbW4MxpVBuSLF1J2g9V6obQ9GmHrs+P27/qKO\r\n2axn37D6eBhuKpXR7aHRymRxYzuiATdUnZHkPrakXY2MAALzK2K/fPyKQVFnfkLf\r\naTSw2gKmXsnng8+xKmYUiZ97e6+GTgpya+C4omStPaOkyvWykCekFeNzhQIDAQAB\r\nAoGBAJTE+RM4mSi+psji2yTUKNJGx37RPbL2bFkmefB03zc8BTcyQh9r/tHuXxOA\r\nahS+1iUDr8iXfWBkO7pnDud/jeyPVOMsIhrpKHbs+sGGqMvPl/t1nbdFu87MeVeT\r\nXM9qCjU66ML+OiiPz8OoUH2gYEO3hHIyD3aFW7Btc+xCNC8BAkEAzm1EGbpgtZUP\r\nXcA0etIPJmR17RHbd0U7xUYvxmARSJA+yjZbwgcz5QQ2NbC6PRTdTVgf93UDOBQH\r\nt98Ion7NkQJBALwjotSCiNqYlzjgCRU2gCuf3LhVuN6gvqz6ZU4RNHMSU567h1vR\r\nSzKTNTs2PodcpGET+E3OvgsSyFUy+pycXLUCQQC7R4KbXO5Uj5HLWo/CRkWcbVRX\r\n6vWTtepORikd7xZ/IO72gm6pD+PTCQktmNK+i+ljPGp0FWMAQ5vlA6JP2n8hAkEA\r\nh/1ONkNAPplrPS4bAp1JWb8MoKU9opYYHRpE3X/sJH0LukGcno6SxzTGlk0oN1mM\r\na6suoLcegQyLh6H4qfkPvQJBAMGWyaPmJeqS/XX1m/kXkj5LYRuc4suAMKKpbtMV\r\nOmYonZIeIdcp3QoZZKWuIgWSLLCuORmxAQU53DNBY8n/5+g=\r\n-----END RSA PRIVATE KEY-----');

-- ----------------------------
-- Table structure for service_history
-- ----------------------------
DROP TABLE IF EXISTS `service_history`;
CREATE TABLE `service_history`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `request_ref_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `response_ref_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `request` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `response` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of service_history
-- ----------------------------
INSERT INTO `service_history` VALUES (1, 'REQ_01234_20230809105400', 'REQ0123420230911205503', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ1MjY4OTksImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiOTM3NmQ1NjMtM2ZkNi00YmI1LWFkNjYtNzI1Mzc5NzkxN2U3IiwiY2xpZW50X2lkIjoiMDEyMzQifQ.phIVNk0j2-_m1bf3fs-sU39iuk8Qw-vtUmn983rhdy6W282b8Qsmlxhcj1cRjyH7I16MK3-4tTjW1a3Ud-OYTQ, serviceId: 1, requestRefNo: REQ_01234_20230809105400', '{\"requestRefNo\":\"REQ_01234_20230809105400\",\"responseRefNo\":\"REQ0123420230911205503\",\"serviceName\":\"Service A\",\"servicePublicKey\":\"abcdef\",\"message\":\"Success\"}', '2023-09-11 20:55:04');
INSERT INTO `service_history` VALUES (2, 'REQ_01234_20230809105400', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDU5MjEsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNmMxZWYwZDctMzk0OC00YTllLWJhOTYtODZmNmM0YmZkYmIzIiwiY2xpZW50X2lkIjoiMDEyMzQifQ.IwWd7vBpWQbGFPRXftmknyTuIiSNBRYIFDPBazZtmBqsov89SM9f6AZCtp1C9MPkWH9-gww1TVr8_On0N7iIDA, serviceId: 1, requestRefNo: REQ_01234_20230809105400', '{\"requestRefNo\":\"REQ_01234_20230809105400\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 18:52:07');
INSERT INTO `service_history` VALUES (3, 'REQ_01234_20230809105401', 'RES_01234_20230912185220', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDU5MjEsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNmMxZWYwZDctMzk0OC00YTllLWJhOTYtODZmNmM0YmZkYmIzIiwiY2xpZW50X2lkIjoiMDEyMzQifQ.IwWd7vBpWQbGFPRXftmknyTuIiSNBRYIFDPBazZtmBqsov89SM9f6AZCtp1C9MPkWH9-gww1TVr8_On0N7iIDA, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"responseRefNo\":\"RES_01234_20230912185220\",\"serviceName\":\"Service A\",\"servicePublicKey\":\"abcdef\",\"message\":\"Success\"}', '2023-09-12 18:52:21');
INSERT INTO `service_history` VALUES (4, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDc1MjEsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiMWVhNDE4ZWQtZjljMS00MmYwLWJkYzYtNTM0MWE5NjVjMjYzIiwiY2xpZW50X2lkIjoiMDEyMzQifQ.iDH-H-Qd6nEICmndtLHfanjZh-_HWz0LmGm18vIjzs8, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 19:18:46');
INSERT INTO `service_history` VALUES (5, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDc1MjEsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiMWVhNDE4ZWQtZjljMS00MmYwLWJkYzYtNTM0MWE5NjVjMjYzIiwiY2xpZW50X2lkIjoiMDEyMzQifQ.iDH-H-Qd6nEICmndtLHfanjZh-_HWz0LmGm18vIjzs8, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 19:18:58');
INSERT INTO `service_history` VALUES (6, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDg4MjEsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiZWQ2ZDhmYWEtMWVkNC00NjgwLWE2ZDctZGY4MjIxYjM3ZjFiIiwiY2xpZW50X2lkIjoiMDEyMzQifQ.a0169L97piVfUH07faEIwyLHZCTWBwOAtoUtOhK9e3E, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 19:40:25');
INSERT INTO `service_history` VALUES (7, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDkwODQsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNDg5NWE2NTgtNGVlYS00YWVkLWFiYTgtNWI1YzZiNTdjZDJmIiwiY2xpZW50X2lkIjoiMDEyMzQifQ.edWNoR_djrJTZm7faAP3wAsItn6-p3ZyEn4hGbpaFaY, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 19:44:47');
INSERT INTO `service_history` VALUES (8, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDkxNjksImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiZTNkMDBlYjgtZjRhMi00MDkwLWEwMDQtYWY3NTYzNDhhODU0IiwiY2xpZW50X2lkIjoiMDEyMzQifQ.Dhi4qy8zLoYYBxtIJNzivFcD2XhpX8_aJ0ySFMgc3qWsKbwKP89puPAglRF5t41yfMc8NHBKpwkOc5l1Zfr3sw, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 19:48:55');
INSERT INTO `service_history` VALUES (9, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDkxNjksImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiZTNkMDBlYjgtZjRhMi00MDkwLWEwMDQtYWY3NTYzNDhhODU0IiwiY2xpZW50X2lkIjoiMDEyMzQifQ.Dhi4qy8zLoYYBxtIJNzivFcD2XhpX8_aJ0ySFMgc3qWsKbwKP89puPAglRF5t41yfMc8NHBKpwkOc5l1Zfr3sw, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 19:48:58');
INSERT INTO `service_history` VALUES (10, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDkxNjksImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiZTNkMDBlYjgtZjRhMi00MDkwLWEwMDQtYWY3NTYzNDhhODU0IiwiY2xpZW50X2lkIjoiMDEyMzQifQ.Dhi4qy8zLoYYBxtIJNzivFcD2XhpX8_aJ0ySFMgc3qWsKbwKP89puPAglRF5t41yfMc8NHBKpwkOc5l1Zfr3sw, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 19:49:43');
INSERT INTO `service_history` VALUES (11, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDk0MjQsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiMjdkNzIzMjQtN2ZlYy00NDhjLTk2MWItYmFlZmMyOTY0ZTg2IiwiY2xpZW50X2lkIjoiMDEyMzQifQ.llYQlDOGmC6S1M4_od54Q0dZnW7CRrucNu49VrLr16QT9DVRPU-HaeO0nBswmGVjgOJnFHZXLjrWIhaaDM0GDQ, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 19:50:28');
INSERT INTO `service_history` VALUES (12, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDk1NDgsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiM2M0NDg5OGMtY2E0Ni00MzJmLTkxNTEtZjY1MmEzMWM2YTA4IiwiY2xpZW50X2lkIjoiMDEyMzQifQ.jyxm-jhXz3S6nb8TcjnJVByhoXMO3wwYQse97KwsRwhdCZBGqk-tTVfZrYWfy3qaM31hFjCV-4jEMC88qK7Ktg, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 19:52:31');
INSERT INTO `service_history` VALUES (13, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDk1ODIsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiYTkzZTAzODItMjAxZi00YmY1LTkwZjktZGQwNzhhNGIxYzA0IiwiY2xpZW50X2lkIjoiMDEyMzQifQ.oY-ijJUBjmW9-66WU7FENWVI-d6E3RoHqW5wgYBH1vT-zRBjtcDg9_4AV4GdTufFJBNR8WPOSojJZ3KLc-D4Ng, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 19:53:07');
INSERT INTO `service_history` VALUES (14, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDk1ODIsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiYTkzZTAzODItMjAxZi00YmY1LTkwZjktZGQwNzhhNGIxYzA0IiwiY2xpZW50X2lkIjoiMDEyMzQifQ.oY-ijJUBjmW9-66WU7FENWVI-d6E3RoHqW5wgYBH1vT-zRBjtcDg9_4AV4GdTufFJBNR8WPOSojJZ3KLc-D4Ng, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 19:54:32');
INSERT INTO `service_history` VALUES (15, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDk2NzUsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiMjdlOGExNjgtNWEyYy00OTgyLTk3ZTItYTYzYzQxNGJjNTUyIiwiY2xpZW50X2lkIjoiMDEyMzQifQ.erKyvaQvk8AnSWsMiREws0eF797ywSvmbwdcKMrfLLr8lj_C4XZhbesV2c1LPZO-onWLfHYKszaDuGFOnh_qSQ, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 19:54:37');
INSERT INTO `service_history` VALUES (16, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDk3MTUsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiYmZjMzdjOGItYzcwOC00ZmNjLWI3NTktNjJlOWEyYTQ4OGJkIiwiY2xpZW50X2lkIjoiMDEyMzQifQ.BlcJrlQ6Mv3r-eRtfYko2Y9MzLDBvPN7B9-IAMwjQm-puP2ogWCrAsBrLw-5kt8DWwX0bACVWlwaP6Matyb-Yg, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 19:55:18');
INSERT INTO `service_history` VALUES (17, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTQ2MDk3NDYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiMDNjYTkyYmUtZTZhNS00OTg5LWFlNzAtNDk0MGVhZjM4YzZmIiwiY2xpZW50X2lkIjoiMDEyMzQifQ.fsHapf5KPZNhp1um21k4MEQSSsV7KwRPJym4uMELpSxD9iOuHg5b5YsO4qKra7GxLjaT5Sgb_zb44iGstHbYTA, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-12 19:55:48');
INSERT INTO `service_history` VALUES (18, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUyMjI4NjMsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNzA0YzY4OWEtMmU3YS00ZGFiLWFmN2ItZTZlYzIxMTQxNzVhIiwiY2xpZW50X2lkIjoiMDEyMzQifQ.hT-J8UaITV2ep2GdtyZTjKc6LvYwyKxYx20OxOjFDnHchl3w_p_qCOjWx5z4b-jetMqukS7eqZY-jBdEUJlYqg, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-19 22:14:58');
INSERT INTO `service_history` VALUES (19, 'REQ_01234_20230809105401', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUzNzQyMzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNTI3Zjc3ZWItZmNkZS00NjlmLTgxYzQtMzBkYWIzZTM1MTlhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQifQ.BodGORF0dJGeCFiEdCG6OgXWCOtkW3vaNzBBZLVVToFbZ7vw8mxKknE3pcLPw8n-WLp78nSVy6-Egp-tTm-rhA, serviceId: 1, requestRefNo: REQ_01234_20230809105401', '{\"requestRefNo\":\"REQ_01234_20230809105401\",\"message\":\"Exist requestRefNo!\"}', '2023-09-21 16:17:30');
INSERT INTO `service_history` VALUES (20, 'REQ_01234_202308091054123', 'RES_clientId_20230921161738', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUzNzQyMzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNTI3Zjc3ZWItZmNkZS00NjlmLTgxYzQtMzBkYWIzZTM1MTlhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQifQ.BodGORF0dJGeCFiEdCG6OgXWCOtkW3vaNzBBZLVVToFbZ7vw8mxKknE3pcLPw8n-WLp78nSVy6-Egp-tTm-rhA, serviceId: 1, requestRefNo: REQ_01234_202308091054123', '{\"requestRefNo\":\"REQ_01234_202308091054123\",\"responseRefNo\":\"RES_clientId_20230921161738\",\"serviceName\":\"Service A\",\"remoteService\":\"\",\"message\":\"Success\"}', '2023-09-21 16:17:38');
INSERT INTO `service_history` VALUES (21, 'REQ_01234_202308091054123', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUzNzQyMzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNTI3Zjc3ZWItZmNkZS00NjlmLTgxYzQtMzBkYWIzZTM1MTlhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQifQ.BodGORF0dJGeCFiEdCG6OgXWCOtkW3vaNzBBZLVVToFbZ7vw8mxKknE3pcLPw8n-WLp78nSVy6-Egp-tTm-rhA, serviceId: 1, requestRefNo: REQ_01234_202308091054123', '{\"requestRefNo\":\"REQ_01234_202308091054123\",\"message\":\"Exist requestRefNo!\"}', '2023-09-21 16:26:37');
INSERT INTO `service_history` VALUES (22, 'REQ_01234_202308091054124', 'RES_clientId_20230921162641', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUzNzQyMzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNTI3Zjc3ZWItZmNkZS00NjlmLTgxYzQtMzBkYWIzZTM1MTlhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQifQ.BodGORF0dJGeCFiEdCG6OgXWCOtkW3vaNzBBZLVVToFbZ7vw8mxKknE3pcLPw8n-WLp78nSVy6-Egp-tTm-rhA, serviceId: 1, requestRefNo: REQ_01234_202308091054124', '{\"requestRefNo\":\"REQ_01234_202308091054124\",\"responseRefNo\":\"RES_clientId_20230921162641\",\"serviceName\":\"Service A\",\"remoteService\":\"\",\"message\":\"Success\"}', '2023-09-21 16:26:42');
INSERT INTO `service_history` VALUES (23, 'REQ_01234_202308091054124', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUzNzQyMzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNTI3Zjc3ZWItZmNkZS00NjlmLTgxYzQtMzBkYWIzZTM1MTlhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQifQ.BodGORF0dJGeCFiEdCG6OgXWCOtkW3vaNzBBZLVVToFbZ7vw8mxKknE3pcLPw8n-WLp78nSVy6-Egp-tTm-rhA, serviceId: 1, requestRefNo: REQ_01234_202308091054124', '{\"requestRefNo\":\"REQ_01234_202308091054124\",\"message\":\"Exist requestRefNo!\"}', '2023-09-21 16:31:36');
INSERT INTO `service_history` VALUES (24, 'REQ_01234_202308091054125', 'RES_clientId_20230921163141', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUzNzQyMzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNTI3Zjc3ZWItZmNkZS00NjlmLTgxYzQtMzBkYWIzZTM1MTlhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQifQ.BodGORF0dJGeCFiEdCG6OgXWCOtkW3vaNzBBZLVVToFbZ7vw8mxKknE3pcLPw8n-WLp78nSVy6-Egp-tTm-rhA, serviceId: 1, requestRefNo: REQ_01234_202308091054125', '{\"requestRefNo\":\"REQ_01234_202308091054125\",\"responseRefNo\":\"RES_clientId_20230921163141\",\"serviceName\":\"Service A\",\"remoteService\":\"\",\"message\":\"Success\"}', '2023-09-21 16:31:43');
INSERT INTO `service_history` VALUES (25, 'REQ_01234_202308091054125', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUzNzQyMzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNTI3Zjc3ZWItZmNkZS00NjlmLTgxYzQtMzBkYWIzZTM1MTlhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQifQ.BodGORF0dJGeCFiEdCG6OgXWCOtkW3vaNzBBZLVVToFbZ7vw8mxKknE3pcLPw8n-WLp78nSVy6-Egp-tTm-rhA, serviceId: 1, requestRefNo: REQ_01234_202308091054125', '{\"requestRefNo\":\"REQ_01234_202308091054125\",\"message\":\"Exist requestRefNo!\"}', '2023-09-21 16:35:31');
INSERT INTO `service_history` VALUES (26, 'REQ_01234_202308091054126', 'RES_clientId_20230921163536', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUzNzQyMzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNTI3Zjc3ZWItZmNkZS00NjlmLTgxYzQtMzBkYWIzZTM1MTlhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQifQ.BodGORF0dJGeCFiEdCG6OgXWCOtkW3vaNzBBZLVVToFbZ7vw8mxKknE3pcLPw8n-WLp78nSVy6-Egp-tTm-rhA, serviceId: 1, requestRefNo: REQ_01234_202308091054126', '{\"requestRefNo\":\"REQ_01234_202308091054126\",\"responseRefNo\":\"RES_clientId_20230921163536\",\"serviceName\":\"Service A\",\"message\":\"Success\"}', '2023-09-21 16:35:38');
INSERT INTO `service_history` VALUES (27, 'REQ_01234_202308091054126', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUzNzQyMzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNTI3Zjc3ZWItZmNkZS00NjlmLTgxYzQtMzBkYWIzZTM1MTlhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQifQ.BodGORF0dJGeCFiEdCG6OgXWCOtkW3vaNzBBZLVVToFbZ7vw8mxKknE3pcLPw8n-WLp78nSVy6-Egp-tTm-rhA, serviceId: 1, requestRefNo: REQ_01234_202308091054126', '{\"requestRefNo\":\"REQ_01234_202308091054126\",\"message\":\"Exist requestRefNo!\"}', '2023-09-21 16:40:25');
INSERT INTO `service_history` VALUES (28, 'REQ_01234_202308091054127', 'RES_clientId_20230921164028', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUzNzQyMzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNTI3Zjc3ZWItZmNkZS00NjlmLTgxYzQtMzBkYWIzZTM1MTlhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQifQ.BodGORF0dJGeCFiEdCG6OgXWCOtkW3vaNzBBZLVVToFbZ7vw8mxKknE3pcLPw8n-WLp78nSVy6-Egp-tTm-rhA, serviceId: 1, requestRefNo: REQ_01234_202308091054127', '{\"requestRefNo\":\"REQ_01234_202308091054127\",\"responseRefNo\":\"RES_clientId_20230921164028\",\"serviceName\":\"Service A\",\"message\":\"Success\"}', '2023-09-21 16:40:30');
INSERT INTO `service_history` VALUES (29, 'REQ_01234_202308091054127', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUzNzQyMzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNTI3Zjc3ZWItZmNkZS00NjlmLTgxYzQtMzBkYWIzZTM1MTlhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQifQ.BodGORF0dJGeCFiEdCG6OgXWCOtkW3vaNzBBZLVVToFbZ7vw8mxKknE3pcLPw8n-WLp78nSVy6-Egp-tTm-rhA, serviceId: 1, requestRefNo: REQ_01234_202308091054127', '{\"requestRefNo\":\"REQ_01234_202308091054127\",\"message\":\"Exist requestRefNo!\"}', '2023-09-21 16:41:36');
INSERT INTO `service_history` VALUES (30, 'REQ_01234_202308091054128', 'RES_clientId_20230921164140', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUzNzQyMzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNTI3Zjc3ZWItZmNkZS00NjlmLTgxYzQtMzBkYWIzZTM1MTlhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQifQ.BodGORF0dJGeCFiEdCG6OgXWCOtkW3vaNzBBZLVVToFbZ7vw8mxKknE3pcLPw8n-WLp78nSVy6-Egp-tTm-rhA, serviceId: 1, requestRefNo: REQ_01234_202308091054128', '{\"requestRefNo\":\"REQ_01234_202308091054128\",\"responseRefNo\":\"RES_clientId_20230921164140\",\"serviceName\":\"Service A\",\"remoteService\":\"Hello world service A!\",\"message\":\"Success\"}', '2023-09-21 16:41:42');
INSERT INTO `service_history` VALUES (31, 'REQ_01234_202308091054128', '', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUzNzQyMzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNTI3Zjc3ZWItZmNkZS00NjlmLTgxYzQtMzBkYWIzZTM1MTlhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQifQ.BodGORF0dJGeCFiEdCG6OgXWCOtkW3vaNzBBZLVVToFbZ7vw8mxKknE3pcLPw8n-WLp78nSVy6-Egp-tTm-rhA, serviceId: 2, requestRefNo: REQ_01234_202308091054128', '{\"requestRefNo\":\"REQ_01234_202308091054128\",\"message\":\"Exist requestRefNo!\"}', '2023-09-21 16:42:28');
INSERT INTO `service_history` VALUES (32, 'REQ_01234_202308091054129', 'RES_clientId_20230921164232', 'Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiUkVTT1VSQ0VfU0VSVklDRV9PQVVUSDIiXSwic2NvcGUiOlsicmVhZCJdLCJleHAiOjE2OTUzNzQyMzYsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiNTI3Zjc3ZWItZmNkZS00NjlmLTgxYzQtMzBkYWIzZTM1MTlhIiwiY2xpZW50X2lkIjoiY2xpZW50SWQifQ.BodGORF0dJGeCFiEdCG6OgXWCOtkW3vaNzBBZLVVToFbZ7vw8mxKknE3pcLPw8n-WLp78nSVy6-Egp-tTm-rhA, serviceId: 2, requestRefNo: REQ_01234_202308091054129', '{\"requestRefNo\":\"REQ_01234_202308091054129\",\"responseRefNo\":\"RES_clientId_20230921164232\",\"serviceName\":\"Service B\",\"remoteService\":\"Hello world service B!\",\"message\":\"Success\"}', '2023-09-21 16:42:32');

-- ----------------------------
-- Table structure for technical_development_kit
-- ----------------------------
DROP TABLE IF EXISTS `technical_development_kit`;
CREATE TABLE `technical_development_kit`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `file_name` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `order_number` int NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of technical_development_kit
-- ----------------------------
INSERT INTO `technical_development_kit` VALUES (1, 'Technical service A', 'file-example_PDF_500_kB.pdf', 1, 1, '2023-09-21 10:28:55', '2023-09-21 10:28:58');
INSERT INTO `technical_development_kit` VALUES (2, 'Technical service B', 'file-sample_150kB.pdf', 1, 2, '2023-09-19 10:29:54', '2023-09-19 10:29:58');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_enrollment_id` int NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  `phone_country_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `verify_status` tinyint NULL DEFAULT NULL COMMENT '//0: Not verified, 1: Verified',
  `status` tinyint NULL DEFAULT NULL COMMENT '0: inactive, 1: active',
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL,
  `is_first_login` tinyint NULL DEFAULT NULL COMMENT '0: first login, 1: otherwise',
  `is_force_password` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (62, NULL, 'Nghiem Xuan Tung', '$2a$10$/fdwzcTnGG2fSl1kCw1lsOGjHw2aZnJkC.fpRyPZ1YZrKr6eGZQZO', 1, NULL, '0984496817', 'nxtung95@gmail.com', 1, 1, '2023-07-30 13:05:20', '2023-09-21 16:57:25', 1, 0);
INSERT INTO `user` VALUES (105, 32, 'Nghiem Xuan Tung', '$2a$10$wvHChmq3ZoM45svlFoqxFud77WJ/JX9Ie6kLrnZ96NU7uWEyflYTq', 2, '44', '9984496817', 'nxtung300195@gmail.com', 1, 1, '2023-09-21 21:14:29', '2023-09-22 16:46:41', 1, 0);

-- ----------------------------
-- Table structure for verification_code
-- ----------------------------
DROP TABLE IF EXISTS `verification_code`;
CREATE TABLE `verification_code`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `verify_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_used` tinyint NULL DEFAULT NULL COMMENT '0: not use, 1: used',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `wrong_number` int NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL,
  `expired_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of verification_code
-- ----------------------------

-- ----------------------------
-- Table structure for webservice
-- ----------------------------
DROP TABLE IF EXISTS `webservice`;
CREATE TABLE `webservice`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `webservice_group_id` int NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `order_number` int NULL DEFAULT NULL,
  `service_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of webservice
-- ----------------------------
INSERT INTO `webservice` VALUES (1, 1, 'Service A', 1, 1, 'http://localhost:8083/service/serviceA', NULL, NULL);
INSERT INTO `webservice` VALUES (2, 1, 'Service B', 1, 2, 'http://localhost:8083/service/serviceB', NULL, NULL);
INSERT INTO `webservice` VALUES (3, 2, 'Service C', 1, 1, NULL, NULL, NULL);
INSERT INTO `webservice` VALUES (4, 3, 'Service D', 1, 1, NULL, NULL, NULL);
INSERT INTO `webservice` VALUES (5, 3, 'Service E', 1, 2, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for webservice_group
-- ----------------------------
DROP TABLE IF EXISTS `webservice_group`;
CREATE TABLE `webservice_group`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` int NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `order_number` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of webservice_group
-- ----------------------------
INSERT INTO `webservice_group` VALUES (1, 1, 'Service Group A', 1);
INSERT INTO `webservice_group` VALUES (2, 1, 'Service Group B', 2);
INSERT INTO `webservice_group` VALUES (3, 1, 'Service Group C', 3);

-- ----------------------------
-- Triggers structure for table company_enrollment
-- ----------------------------
DROP TRIGGER IF EXISTS `trigger_before_company_enrollment_update`;
delimiter ;;
CREATE TRIGGER `trigger_before_company_enrollment_update` BEFORE UPDATE ON `company_enrollment` FOR EACH ROW INSERT INTO company_enrollment_history

SET

 id = OLD.id,

 company_id = OLD.company_id,
 
 webservice_id = OLD.webservice_id,
 
 client_id = OLD.client_id,
 
 client_secret = OLD.client_secret,
 
 extra_field_1 = OLD.extra_field_1,
 
 extra_field_2 = OLD.extra_field_2,
 
 extra_field_3 = OLD.extra_field_3,
 
 verify_status = OLD.verify_status,
 
 company_type_id = OLD.company_type_id,
 
 company_other = OLD.company_other,
 
 created_date = OLD.created_date,
 
 updated_date = NOW()
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table manage_public_key
-- ----------------------------
DROP TRIGGER IF EXISTS `before_manage_public_key_history`;
delimiter ;;
CREATE TRIGGER `before_manage_public_key_history` BEFORE UPDATE ON `manage_public_key` FOR EACH ROW INSERT INTO manage_public_key_history

SET

 id = OLD.id,

 enrollment_id = OLD.enrollment_id,
 
 service_group_id = OLD.service_group_id,
 
 service_id_list = OLD.service_id_list,
 
 client_id = OLD.client_id,
 
 client_secret = OLD.client_secret,
 
 public_key = OLD.public_key,
 
 `status` = OLD.`status`,
 
 created_date = OLD.created_date,
 
 updated_date = NOW()
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
