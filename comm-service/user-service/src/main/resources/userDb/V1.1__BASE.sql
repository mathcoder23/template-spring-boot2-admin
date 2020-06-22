CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL,
  `nick` varchar(255) NULL,
  `username` varchar(2048) NOT NULL,
  `password` varchar(2048) NOT NULL,
  `username_hash_base64` varchar(100) NOT NULL,
  `password_hash_base64` varchar(255) NOT NULL,
  `oem_id` bigint NULL,
  `enable` tinyint(1) NOT NULL DEFAULT 1,
  `user_status` int(11) NOT NULL DEFAULT 0,
  `user_status_remark` varchar(1024) NULL,
  `phone` varchar(15) NULL,
  `email` varchar(255) NULL,
   `phone_auth`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '手机号认证',
   `email_auth` tinyint(1) NOT NULL DEFAULT 0 COMMENT '邮件认证',
   `remark` varchar(1024) NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `flag` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `username_hash_index`(`username_hash_base64`,`flag`),
  INDEX `phone_index`(`phone`,`flag`)
);