CREATE TABLE `t_account` (
                             `id` bigint(20) NOT NULL,
                             `nick` varchar(255) DEFAULT NULL,
                             `username` varchar(255) NOT NULL,
                             `password` varchar(2048) NOT NULL COMMENT '密文密码',
                             `enable` tinyint(1) NOT NULL DEFAULT '1',
                             `user_status` int(11) NOT NULL DEFAULT '0',
                             `user_status_remark` varchar(1024) DEFAULT NULL,
                             `phone` varchar(15) DEFAULT NULL,
                             `role_id` bigint(20) DEFAULT NULL,
                             `email` varchar(255) DEFAULT NULL,
                             `type` tinyint(1) NOT NULL DEFAULT '10' COMMENT '用户类型,[SUPER_ADMIN(1):超级管理员,USER(10):普通用户]',
                             `phone_auth` tinyint(1) NOT NULL DEFAULT '0' COMMENT '手机号认证',
                             `email_auth` tinyint(1) NOT NULL DEFAULT '0' COMMENT '邮件认证',
                             `remark` varchar(1024) DEFAULT NULL,
                             `last_active_time` datetime DEFAULT NULL COMMENT '最近活跃时间',
                             `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                             `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uq_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_config_system_config` (
                                          `id` bigint(20) NOT NULL COMMENT '系统配置表',
                                          `config_key` varchar(255) NOT NULL,
                                          `value` varchar(255) NOT NULL,
                                          `default_value` varchar(255) NOT NULL COMMENT '默认值，重置后使用',
                                          `label` varchar(255) NOT NULL,
                                          `type` int(11) NOT NULL DEFAULT '0' COMMENT '数据类型，0 文本',
                                          `system_config_group_id` bigint(20) NOT NULL COMMENT '系统配置组',
                                          `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                          `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                                          `remark` varchar(1000) DEFAULT NULL,
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_config_system_config_group` (
                                                `id` bigint(20) NOT NULL COMMENT '系统配置组',
                                                `name` varchar(255) NOT NULL COMMENT '组名',
                                                `description` varchar(255) DEFAULT NULL COMMENT '描述',
                                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                                `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                                                `remark` varchar(1000) DEFAULT NULL,
                                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_permission` (
                                `id` bigint(20) NOT NULL,
                                `parent_id` bigint(20) DEFAULT NULL,
                                `parent_ids` json DEFAULT NULL,
                                `parent_names` json DEFAULT NULL,
                                `path_depth` int(11) NOT NULL DEFAULT '0',
                                `order_num` int(11) NOT NULL DEFAULT '0',
                                `name` varchar(255) NOT NULL,
                                `scope` varchar(255) DEFAULT NULL,
                                `sn` varchar(1024) DEFAULT NULL,
                                `menu_path` varchar(255) NOT NULL,
                                `menu_component` varchar(255) NOT NULL,
                                `menu_icon` varchar(255) DEFAULT NULL,
                                `menu_meta` varchar(255) DEFAULT NULL,
                                `enable` tinyint(4) NOT NULL,
                                `type` tinyint(4) NOT NULL DEFAULT '1',
                                `remark` varchar(1024) DEFAULT NULL,
                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_role` (
                          `id` bigint(20) NOT NULL,
                          `name` varchar(255) DEFAULT NULL,
                          `remark` varchar(1024) DEFAULT NULL,
                          `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                          `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_role_permission` (
                                     `id` bigint(20) NOT NULL,
                                     `role_id` bigint(20) NOT NULL,
                                     `permission_id` bigint(20) NOT NULL,
                                     `remark` varchar(1024) DEFAULT NULL,
                                     `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                     `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_system_log` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `datetime` datetime DEFAULT NULL,
                                `account_id` int(11) DEFAULT NULL,
                                `account_name` varchar(255) DEFAULT NULL,
                                `ip` varchar(255) DEFAULT NULL,
                                `api` varchar(1024) DEFAULT NULL,
                                `log` varchar(1024) DEFAULT NULL,
                                `type` int(11) NOT NULL,
                                `data` json DEFAULT NULL,
                                `remark` varchar(1024) DEFAULT NULL,
                                `option_type` int(11) DEFAULT NULL,
                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                `flag` tinyint(1) NOT NULL DEFAULT '0',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4;