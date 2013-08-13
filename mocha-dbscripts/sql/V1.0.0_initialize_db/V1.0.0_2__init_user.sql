/*create login view*/
CREATE view vw_searchpassword AS SELECT t_user.password AS password,
t_user.user_name AS user_name FROM t_user;

/*1. init default account*/
INSERT INTO t_account(account_id,email,name) VALUES('-1', 'root@test.com','root');

/*2. init default role*/
INSERT INTO t_role (role_description, role_name, basic_role_id) VALUES ('Admin_Role','Admin_Role', 1);

/*3. init default user*/
INSERT INTO t_user(basic_user_id,password,user_name,real_name, email) VALUES ('-1', 'root','root','Administrator', 'webmaster@mocha-platform.com');
INSERT INTO t_user(basic_user_id,password,user_name,real_name, email) VALUES ('-2', 'vance','vance','Vance', 'vancezhao@gmail.com');
INSERT INTO t_user(basic_user_id,password,user_name,real_name, email) VALUES ('-3', 'coral','coral','Coral', 'maqujun@gmail.com');
INSERT INTO t_user(basic_user_id,password,user_name,real_name, email) VALUES ('-4', 'sam','sam','Sam', 'mildredcrasto@hotmail.com');


/*4. add user to default account*/
UPDATE t_user SET account = (SELECT account_id FROM t_account WHERE  name = 'root');
                  
