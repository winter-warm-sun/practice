drop database if exists zzuli;
create database zzuli;
use zzuli;
create table user(
    uid int primary key auto_increment,
    username varchar(30),
    password varchar(30),
    phone varchar(30));

create table restaurant(
    rid int primary key auto_increment,
    username varchar(30),
    password varchar(30),
    name varchar(50),
    address varchar(50),
    specialty varchar(50),
    distance int);

create table order_(
    oid int primary key auto_increment,
    uid int,
    rid int,
    time datetime,
    isDone int,
    foreign key (uid) references user(uid),
    foreign key (rid) references restaurant(rid));

insert into user values
            (null,'190001','190001','15522222222'),
            (null,'190002','100000','13011111111');

insert into restaurant values
            (null,'123','123','俏江南','腾达大厦店','火锅',11),
            (null,'456','456','唐廊烤鸭店','当代商城店','烤鸭',12);

insert into order_ values
            (null,1,2,now(),0),
            (null,2,1,now(),0);
