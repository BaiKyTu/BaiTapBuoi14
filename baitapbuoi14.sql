create database baitapbuoi14;

use baitapbuoi14;

create table user(
email varchar(50) not null,
password varchar(50) not null,
primary key(email)
);

insert into user(email,password) values ("a@gmail.com","123");

select * from user u where u.email = "" and u.password = "";