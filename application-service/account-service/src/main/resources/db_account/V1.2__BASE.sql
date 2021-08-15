CREATE TABLE `t_role`  (
  `id` bigint(0) NOT NULL,
  `name` varchar(255) NULL,
  `remark` varchar(1024) NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`)
);
CREATE TABLE `t_permission`  (
  `id` bigint(0) NOT NULL,
  `parent_id` bigint(0) NOT NULL,
  `parent_ids` json NOT NULL,
  `parent_names` json NOT NULL,
  `path_depth` int(11) NOT NULL,
  `order_num` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `scope` varchar(255) NOT NULL,
  `sn` varchar(1024) NOT NULL,
  `menu_path` varchar(255) NOT NULL,
  `menu_component` varchar(255) NOT NULL,
  `menu_icon` varchar(255) NOT NULL,
  `menu_meta` varchar(255) NOT NULL,
  `enable` tinyint(0) NOT NULL,
  `type` tinyint(0) NOT NULL,
  `remark` varchar(1024) NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`)
);
CREATE TABLE `t_role_permission`  (
  `id` bigint(0) NOT NULL,
  `role_id` bigint(0) NOT NULL,
  `permission_id` bigint(0) NOT NULL,
  `remark` varchar(1024) NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`)
);