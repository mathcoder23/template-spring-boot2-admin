INSERT INTO `t_account` (`id`,
                         `nick`,
                         `username`,
                         `password`,
                         `role_id`,
                         `type`,
                         `enable`)
VALUES (1,
        '超级管理员',
        'admin',
        '{MD5}103c74e7163bb88410bf53ab21c7ab60', 1, 1, 10);

INSERT INTO t_permission (id, parent_id, parent_ids, parent_names, path_depth, order_num, name, scope, sn,
                                        menu_path, menu_component, menu_icon, menu_meta, enable, type, remark,
                                        create_time, update_time)
VALUES (1, null, null, null, 0, 0, '系統管理', '""', 'system', '/acl', 'views/acl', 'el-icon-s-tools', 'a', 1, 1, null,
        '2021-08-15 16:54:42', '2021-08-15 17:05:04');
INSERT INTO t_permission (id, parent_id, parent_ids, parent_names, path_depth, order_num, name, scope, sn,
                                        menu_path, menu_component, menu_icon, menu_meta, enable, type, remark,
                                        create_time, update_time)
VALUES (2, 1, '[1]', null, 0, 0, '用戶管理', null, null, 'user', 'views/acl/user/UserAccount', 'el-icon-user-solid', null,
        1, 1, null, '2021-08-15 17:03:23', '2021-08-15 17:05:28');
INSERT INTO t_permission (id, parent_id, parent_ids, parent_names, path_depth, order_num, name, scope, sn,
                                        menu_path, menu_component, menu_icon, menu_meta, enable, type, remark,
                                        create_time, update_time)
VALUES (3, 1, '[1]', null, 0, 30, '权限管理', null, null, 'permission', 'views/acl/permission/Permission', 'el-icon-lock',
        null, 1, 1, null, '2021-08-15 17:04:00', '2021-08-15 17:05:28');
INSERT INTO t_permission (id, parent_id, parent_ids, parent_names, path_depth, order_num, name, scope, sn,
                                        menu_path, menu_component, menu_icon, menu_meta, enable, type, remark,
                                        create_time, update_time)
VALUES (1426834263495544832, 1, null, null, 0, 10, '角色管理', null, null, 'role', 'views/acl/role/Role', 'el-icon-menu',
        null, 1, 1, null, '2021-08-15 17:12:52', '2021-08-15 17:12:52');

INSERT INTO t_role (id, name, remark, create_time, update_time)
VALUES (1, '超級管理員', null, '2021-08-15 16:50:40', '2021-08-15 16:50:40');

INSERT INTO t_role_permission (id, role_id, permission_id, remark, create_time, update_time)
VALUES (1, 1, 1, null, '2021-08-15 16:56:05', '2021-08-15 16:56:05');
INSERT INTO t_role_permission (id, role_id, permission_id, remark, create_time, update_time)
VALUES (2, 1, 2, null, '2021-08-15 17:04:19', '2021-08-15 17:04:19');
INSERT INTO t_role_permission (id, role_id, permission_id, remark, create_time, update_time)
VALUES (3, 1, 3, null, '2021-08-15 17:04:19', '2021-08-15 17:04:19');
INSERT INTO t_role_permission (id, role_id, permission_id, remark, create_time, update_time)
VALUES (4, 1, 1426834263495544832, null, '2021-08-15 17:46:30', '2021-08-15 17:46:30');