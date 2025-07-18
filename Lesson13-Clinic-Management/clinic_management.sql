create database clinic_management;

use clinic_management;

create table t_user(
	id bigint auto_increment primary key,
    email varchar(100) not null unique,
    password varchar(100) not null,
    role varchar(100) not null,
    name varchar(250)
);
INSERT INTO t_user(email, password, role, name)
VALUES
('nguoidungkhongten577@gmail.com', 'N/A', 'DOCTOR', 'test');

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
alter table appointment add column doctor_id bigint;

create table doctor(
	id bigint auto_increment primary key,
    name varchar(100) not null,
    email varchar(100) not null,
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
);

create table service(
	id bigint auto_increment primary key,
    name varchar(255) not null,
    price decimal(10,2) not null,
    description text
);

create table medicine(
	id bigint auto_increment primary key,
    name varchar(255) not null,
    unit varchar(50) not null,
    price decimal(10,2) not null,
    description text
);

create table prescription(
	 id BIGINT AUTO_INCREMENT PRIMARY KEY,
    medical_form_id BIGINT NOT NULL,
    total_medicine_price DECIMAL(10, 2) DEFAULT 0,
    foreign key (medical_form_id) references medical_form(id)
);

create table prescription_detail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    prescription_id BIGINT NOT NULL,
    medicine_id BIGINT NOT NULL,
    medicine_name VARCHAR(255) NOT NULL,
    medicine_price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    dosage VARCHAR(255),
    foreign key (prescription_id) references prescription(id),
    foreign key (medicine_id) references medicine(id)
);

create table medical_form(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    doctor_name VARCHAR(255),
    patient_name VARCHAR(255),
    gender VARCHAR(20),
    date_of_birth DATE,
    total_service_price DECIMAL(10,2),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

create table medical_form_service(
	 id BIGINT AUTO_INCREMENT PRIMARY KEY,
    medical_form_id BIGINT,
    service_id BIGINT,
    service_name VARCHAR(255),
    service_price DECIMAL(10,2),
	dosage VARCHAR(255),
    instruction VARCHAR(255),
    FOREIGN KEY (medical_form_id) REFERENCES medical_form(id)
)

select * from doctor;
select * from patient;
select * from appointment;
select * from medical_record;
select * from t_user;
select * from medicine;
select * from medical_form;
select * from medical_form_service;
select * from prescription;
select * from prescription_detail;