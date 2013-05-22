/*create login view*/
CREATE VIEW `vw_searchpassword` AS select `t_user`.`PASSWORD` 
AS `password`,`t_user`.`USER_NAME` AS `user_name` from `t_user`;

CREATE VIEW `vw_searchpermission` AS select `r`.`ROLE_NAME`
AS `user_name` from ((`t_role` `r` join `t_role_t_user` `trtu` 
on((`r`.`BASIC_ROLE_ID` = `trtu`.`T_ROLE_BASIC_ROLE_ID`))) join `t_user` `u`
on((`u`.`BASIC_USER_ID` = `trtu`.`user_BASIC_USER_ID`))) where (`u`.`USER_NAME` = `u`.`USER_NAME`);

CREATE VIEW `vw_searchrole` AS select `r`.`ROLE_NAME` AS `role_name` 
from ((`t_role` `r` join `t_role_t_user` `trtu` 
on((`r`.`BASIC_ROLE_ID` = `trtu`.`T_ROLE_BASIC_ROLE_ID`))) join 
`t_user` `u` on((`u`.`BASIC_USER_ID` = `trtu`.`user_BASIC_USER_ID`))) 
where (`u`.`USER_NAME` = `u`.`USER_NAME`);


/*1. init default account*/
insert into t_account(email,name) values('root@test.com','root');

/*2. init default role*/
INSERT INTO `t_role`
(`ROLE_DESCRIPTION`,`ROLE_NAME`,`BASIC_ROLE_ID`)
VALUES
('Admin_Role','Admin_Role',1);

/*3. init default user*/
INSERT INTO `t_user`
(`PASSWORD`,`USER_NAME`,REAL_NAME,BASIC_USER_ID)
VALUES
('root','root','root',1);

/*4. add role to user*/
INSERT INTO `t_role_t_user` (`T_ROLE_BASIC_ROLE_ID`,`user_BASIC_USER_ID`) VALUES (1,1);


/*5. add user to default account*/
update t_user set account=(select account_id from t_account where name='root');
