DROP TABLE IF EXISTS PROJECT_EXHIBITION;
DROP TABLE IF EXISTS EXHIBITION;
DROP TABLE IF EXISTS PROJECT_STUDENT_REGISTER;
DROP TABLE IF EXISTS PROJEKT;
DROP TABLE IF EXISTS STUDENT;
DROP TABLE IF EXISTS STUDENT_LOGIN;
DROP TABLE IF EXISTS STAFF_LOGIN;

CREATE TABLE STAFF_LOGIN
(
    id INT(5) PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(20) UNIQUE KEY NOT NULL,
    hashed_password CHAR(60) NOT NULL
);


CREATE TABLE STUDENT_LOGIN
(
    id INT(5) PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(20) UNIQUE KEY NOT NULL,
    hashed_password CHAR(60) NOT NULL
);

CREATE TABLE STUDENT
(
	id INT(5) UNIQUE KEY NOT NULL,
    reg_no CHAR(12) PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    department ENUM('CSE', 'EEE', 'ECE', 'CIVIL'),
    course ENUM('B.Tech', 'M.Tech') NOT NULL,
    contact_no VARCHAR(10) NOT NULL,
    FOREIGN KEY(id) REFERENCES STUDENT_LOGIN(id)
);

CREATE TABLE PROJEKT
(
    id INT(5) PRIMARY KEY AUTO_INCREMENT,
    project_leader_regno CHAR(12) UNIQUE KEY NOT NULL,
    project_name VARCHAR(100) UNIQUE KEY NOT NULL,
    mentor_name VARCHAR(30) NOT NULL,
    department ENUM('CSE', 'EEE', 'ECE', 'CIVIL') NOT NULL,
    category VARCHAR(30) NOT NULL
);

CREATE TABLE PROJECT_STUDENT_REGISTER
(
    project_id INT(5) NOT NULL,
    student_reg_no CHAR(12) NOT NULL,
    FOREIGN KEY(project_id) REFERENCES PROJEKT(id),
    FOREIGN KEY(student_reg_no) REFERENCES STUDENT(reg_no)
);

CREATE TABLE EXHIBITION
(
    room_id INT(5) PRIMARY KEY AUTO_INCREMENT,
    room_name CHAR(20) UNIQUE KEY NOT NULL,
    capacity INT(5) NOT NULL
);


CREATE TABLE PROJECT_EXHIBITION
(
    room_id INT(5) NOT NULL,
    project_id INT(5) UNIQUE KEY NOT NULL,
    table_no INT(5) CHECK ( table_no > 0 AND table_no < ( SELECT * FROM EXHIBITION WHERE EXHIBITION.room_id = room_id LIMIT 1 ) ),
    FOREIGN KEY(room_id) REFERENCES EXHIBITION(room_id),
    FOREIGN KEY(project_id) REFERENCES PROJEKT(id)
);
