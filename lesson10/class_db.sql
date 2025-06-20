create database class_db;

use class_db;

CREATE TABLE IF NOT EXISTS class (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    teacher_name VARCHAR(255),
    address VARCHAR(255)
);

-- Bảng student
CREATE TABLE IF NOT EXISTS student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT,
    gender VARCHAR(10),
    class_id BIGINT,
    FOREIGN KEY (class_id) REFERENCES class(id) ON DELETE SET NULL ON UPDATE CASCADE
);

select * from class;
select * from student;

