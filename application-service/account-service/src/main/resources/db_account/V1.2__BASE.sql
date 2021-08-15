CREATE TABLE `t_role`  (
  `id` bigint(0) NOT NULL,
  `name` varchar(255) NULL,
  `remark` varchar(1024) NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`)
);
create table template_demo.t_permission
(
	id bigint not null
		primary key,
	parent_id bigint null,
	parent_ids json null,
	parent_names json null,
	path_depth int default 0 not null,
	order_num int default 0 not null,
	name varchar(255) not null,
	scope varchar(255) null,
	sn varchar(1024) null,
	menu_path varchar(255) not null,
	menu_component varchar(255) not null,
	menu_icon varchar(255) null,
	menu_meta varchar(255) null,
	enable tinyint not null,
	type tinyint default 1 not null,
	remark varchar(1024) null,
	create_time datetime default CURRENT_TIMESTAMP null,
	update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
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