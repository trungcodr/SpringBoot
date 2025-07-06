create database clinic_management;

use clinic_management;

create table patient (
	 id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(10), -- lưu "Nam", "Nu", "Khac"
    dob DATE,
    phone VARCHAR(10),
    address varchar(200)
);

create table appointment(
 id bigint auto_increment primary key,
 patient_id bigint not null,
 time datetime not null,
 reason varchar(255),
 note text
);
alter table appointment add column status varchar(20) not null default 'PENDING';

create table doctor(
	id bigint auto_increment primary key,
    name varchar(100) not null,
    position varchar(150)
);
insert into doctor(name,position) values ("Nguyen Dinh Thai","Co xuong khop");


create table medical_record(
	id bigint auto_increment primary key,
    patient_id bigint not null,
    doctor_id bigint not null,
    date datetime not null,
    diagnosis text,
    symptoms text,
    prescription text,
    note text
)

select * from patient;
select * from appointment;
select * from medical_record;