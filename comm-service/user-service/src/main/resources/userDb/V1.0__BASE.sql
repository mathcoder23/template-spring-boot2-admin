CREATE TABLE `system_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `datetime` datetime(0) NULL,
  `account_id` int(0) NULL,
  `account_name` varchar(255) NULL,
  `ip` varchar(255) NULL,
  `api` varchar(1024) NULL,
  `log` varchar(1024) NULL,
  `type` integer(0) NOT NULL,
  `data` json NULL,
  `remark` varchar(1024) NULL,
  `option_type` integer NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `flag` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
);