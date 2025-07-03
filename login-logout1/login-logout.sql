create database login_logout;

use login_logout;

create table role(
	id bigint primary key auto_increment,
    name varchar(50) not null
);

create table user (
	id bigint primary key auto_increment,
    username varchar(50) not null unique,
    password varchar(255) not null ,
    full_name varchar(100) not null,
    email varchar(100) unique,
    phone varchar(10) unique,
    address varchar(255),
    created_at datetime,
    role_id bigint,
    foreign key (role_id) references role(id)
);

insert into role (id, name) values (1, "admin");
insert into role (id,name) values (2, "user");
select * from role;
select * from user;