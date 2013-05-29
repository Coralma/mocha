 USE cooperate;

/*create login view*/
CREATE view `vw_searchpassword` AS SELECT `t_user`.`password` AS `password`,
`t_user`.`user_name` AS `user_name` FROM `t_user`;

CREATE view `vw_searchpermission` AS SELECT `r`.`role_name` AS `user_name` FROM
((`t_role` `r` JOIN `t_role_t_user` `trtu` ON((`r`.`basic_role_id` =
`trtu`.`t_role_basic_role_id`))) JOIN `t_user` `u` ON((`u`.`basic_user_id` =
`trtu`.`user_basic_user_id`))) WHERE (`u`.`user_name` = `u`.`user_name`);

CREATE view `vw_searchrole` AS SELECT `r`.`role_name` AS `role_name` FROM ((
`t_role` `r` JOIN `t_role_t_user` `trtu` ON((`r`.`basic_role_id` =
`trtu`.`t_role_basic_role_id`))) JOIN `t_user` `u` ON((`u`.`basic_user_id` =
`trtu`.`user_basic_user_id`))) WHERE (`u`.`user_name` = `u`.`user_name`);

/*1. init default account*/
INSERT INTO t_account
            (email,
             name)
VALUES     ('root@test.com',
            'root');

/*2. init default role*/
INSERT INTO `t_role`
            (`role_description`,
             `role_name`,
             `basic_role_id`)
VALUES      ('Admin_Role',
             'Admin_Role',
             1);

/*3. init default user*/
INSERT INTO `t_user`
            (`password`,
             `user_name`,
             real_name,
             basic_user_id)
VALUES      ('root',
             'root',
             'root',
             1);

/*4. add role to user*/
INSERT INTO `t_role_t_user`
            (`t_role_basic_role_id`,
             `user_basic_user_id`)
VALUES      (1,
             1);

/*5. add user to default account*/
UPDATE t_user
SET    account = (SELECT account_id
                  FROM   t_account
                  WHERE  name = 'root');  
                  
use homepage;

/*Create Trigger on T_User*/
delimiter $$
DROP TRIGGER IF EXISTS `insert_after_tuser`$$
 /* ??9:35:21 localhost */ CREATE TRIGGER `insert_after_tuser` after INSERT
ON `t_user`
FOR EACH row
begin
  SET @newaccountid=(SELECT ct.account_id FROM `cooperate`.t_account ct INNER
  JOIN `homepage`.t_account ht ON ct.name=ht.name WHERE
  ht.accountid=new.account_accountid LIMIT 1);

  INSERT INTO `cooperate`.t_user
              (c_creattime,
               c_lastmodifiedtime,
               email,
               english_name,
               language,
               password,
               re_password,
               real_name,
               user_name,
               account)
  VALUES     (Now(),
              Now(),
              new.emailaddress,
              new.username,
              NULL,
              new.pw,
              new.pw,
              new.username,
              new.username,
              @newaccountid);
end;$$
delimiter;


/*create trigger on t_account*/
delimiter $$
DROP TRIGGER IF EXISTS `after_taccount_insert`$$
 /* ??12:16:26 localhost */ CREATE TRIGGER `after_taccount_insert` after INSERT
ON `t_account`
FOR EACH row
begin
  INSERT INTO cooperate.t_account
              (c_creattime,
               c_lastmodifiedtime,
               name)
  VALUES     (Now(),
              Now(),
              new.name);
end; $$ 
