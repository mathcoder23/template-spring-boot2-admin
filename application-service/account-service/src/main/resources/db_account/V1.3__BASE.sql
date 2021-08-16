ALTER TABLE `template_demo`.`t_account`
    ADD COLUMN `type` tinyint(1) NOT NULL DEFAULT 10 COMMENT '用户类型,[SUPER_ADMIN(1):超级管理员,USER(10):普通用户]' AFTER `email`;