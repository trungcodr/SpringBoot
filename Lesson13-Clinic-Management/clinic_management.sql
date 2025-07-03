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