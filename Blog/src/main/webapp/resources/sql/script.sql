create database if not exists jstlDB char set utf8;
use jstlDB ;
create table if not exists Users (
    id int auto_increment primary key ,
    firstName varchar(50) not null ,
    lastName varchar(50) not null ,
    login varchar(100) not null unique ,
    pass varchar(100) not null
);
create table if not exists Posts (
    id int auto_increment primary key ,
    name_post varchar(50) not null ,
    text_post text ,
    date_post date ,
    imgUrl text
);
create table if not exists PostUser (
    userID int not null ,
    postID int not null
);
