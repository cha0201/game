
create table `sys_user` (
	`id` char (96),
	`group_id` char (96),
	`account_id` varchar (300),
	`login_name` varchar (765),
	`password` varchar (765),
	`name` varchar (765),
	`email` varchar (765),
	`mobile` varchar (192),
	`org_id` char (96),
	`no` varchar (384),
	`avatar` varchar (765),
	`user_type` varchar (765),
	`login_ip` varchar (765),
	`login_date` datetime ,
	`state` varchar (765),
	`remark` varchar (765),
	`create_by` varchar (765),
	`create_date` datetime ,
	`update_by` varchar (765),
	`update_date` datetime ,
	`del_flag` char (3)
);
insert into `sys_user` (`id`, `group_id`, `account_id`, `login_name`, `password`, `name`, `email`, `mobile`, `org_id`, `no`, `avatar`, `user_type`, `login_ip`, `login_date`, `state`, `remark`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`) values('501','5d70dce4eef74c3c81f421314d2d7f44','666','admin@shuming.com','$2a$10$.Q7NifyGBn3WL8dqU2m9luNjn62hmuC4auhFIKKAa3DYCVAn0vRD2',NULL,'admin@shuming.com',NULL,'UNIQ',NULL,'http://47.100.227.44:3000/avatar/47897bc11eca4262df377c6f25a7976e.jpeg','SUPER_ADMINISTRATOR','112.64.154.126','2019-09-12 10:44:23',NULL,NULL,NULL,'2019-09-12 10:44:23',NULL,'2019-09-12 10:44:23',NULL);

