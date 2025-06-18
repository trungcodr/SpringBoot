create database  school_db;
use school_db;

create table Class(
	id bigint primary key auto_increment,
    name varchar(200) not null,
    description varchar(500)
); 

create table Student(
	id bigint primary key auto_increment,
    name varchar(255) not null,
    email varchar(255) unique,
    class_id bigint,
    constraint fk_class foreign key (class_id) references class(id)
		on delete set null
        on update cascade
);

select * from Class;
select * from student;
