 create table Sys_User
 (
    id int primary key auto_increment,
    username varchar(32),
    password varchar(32)
 );

 ###角色表
 create table Sys_Role
 (
    id int primary key auto_increment,
    name varchar(32)
 );

 ###权限表
 create table Sys_Permission
 (
    id int primary key auto_increment,
    name varchar(32),
    description varchar(256),
    url varchar(256),
    pid int
 );

 ###用户角色表
 create table Sys_user_role
 (
    id int primary key auto_increment,
    sys_user_id int,
    sys_role_id int
 );

 ###角色权限表
 create table Sys_role_permission
 (
   id int primary key auto_increment,
   role_id int,
   permission_id int
 );

 insert into Sys_User(id,username,password) values(1,'admin','admin');
 insert into Sys_User(id,username,password) values(2,'abel','abel');

 insert into Sys_Role(id,name) values(1,'ROLE_ADMIN');
 insert into Sys_Role(id,name) values(2,'ROLE_USER');

 insert into Sys_user_role(SYS_USER_ID,SYS_ROLE_ID) values(1,1);
 insert into Sys_user_role(SYS_USER_ID,SYS_ROLE_ID) values(2,2);

 BEGIN;
 INSERT INTO `Sys_Permission` VALUES('1','ROLE_HOME','home','/',null),('2','ROLE_ADMIN','ABel','admin',null);
 COMMIT;

 BEGIN;
 INSERT INTO `Sys_role_permission` VALUES('1','1','1'),('2','1','2'),('3','2','1');
 COMMIT