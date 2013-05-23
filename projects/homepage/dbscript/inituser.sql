/* create tirgger */
create TRIGGER `insert_after_tuser` AFTER INSERT ON `t_user` FOR EACH ROW insert into cooperate.t_user (C_CREATTIME,C_LASTMODIFIEDTIME,email,english_name,LANGUAGE,PASSWORD,RE_PASSWORD,REAL_NAME,USER_NAME)
values(now(),now(),new.emailAddress,new.username,null,new.pw,new.pw,new.username,new.username);

create TRIGGER `after_taccount_insert` AFTER INSERT ON `t_account` FOR EACH ROW insert into cooperate.t_account (C_CREATTIME,C_LASTMODIFIEDTIME,name) values(now(),now(),new.name) */;;



/* init data */
insert into t_systemproperty(id,systemkey,systemvalue) values (1,'paypal_ipn_url','http://ec2-54-249-155-18.ap-northeast-1.compute.amazonaws.com:8080/homepage/ipn/validate');

insert into t_systemproperty(id,systemkey,systemvalue) values(2,'cooperateUrl','http://ec2-54-249-155-18.ap-northeast-1.compute.amazonaws.com:8080/cooperate/');

