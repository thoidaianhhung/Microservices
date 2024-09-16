-- Drop the database if it already exists
DROP DATABASE IF EXISTS testing_system;
-- Create database
CREATE DATABASE IF NOT EXISTS testing_system;
USE testing_system;

-- Create table department
DROP TABLE IF EXISTS 	`department`;
CREATE TABLE IF NOT EXISTS `department` (
	id 						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`name` 					NVARCHAR(50) NOT NULL UNIQUE KEY,
    total_member			INT	UNSIGNED,
    `type`					ENUM('Dev','Test','ScrumMaster','PM') NOT NULL,
    created_date			DATETIME DEFAULT NOW()
);

-- create table: account
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`(
	id						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username				VARCHAR(50) NOT NULL UNIQUE KEY,
    department_id 			INT UNSIGNED NOT NULL,
    FOREIGN KEY(department_id) REFERENCES department(id)
);

-- =============================================
-- INSERT DATA 
-- =============================================
-- Add data department
INSERT INTO department(	`name`, 		total_member, 	`type`, 		created_date)
VALUES
						(N'Marketing'	, 		1,		'Dev', 			'2020-03-05'),
						(N'Sale'		, 		2,		'Test', 		'2020-03-05'),
						(N'Bảo vệ'		, 		3,		'ScrumMaster', 	'2020-03-07'),
						(N'Nhân sự'		, 		4,		'PM', 			'2020-03-08'),
						(N'Kỹ thuật'	, 		5,		'Dev', 			'2020-03-10'),
						(N'Tài chính'	, 		6,		'ScrumMaster', 	NOW()		),
						(N'Phó giám đốc', 		7,		'PM', 			NOW()		),
						(N'Giám đốc'	, 		8,		'Test', 		'2020-04-07'),
						(N'Thư kí'		, 		9,		'PM', 			'2020-04-07'),
						(N'Bán hàng'	, 		1,		'Dev', 			'2020-04-09');
                    
-- Add data account
INSERT INTO `account`(	username		,	department_id	)
VALUES 				(	'dangblack'		,   	'5'			),
					(	'quanganh'		,		'1'			),
                    (	'vanchien'		,		'1'			),
                    (	'cocoduongqua'	,		'1'			),
                    (	'doccocaubai'	,   	'2'			),
                    (	'khabanh'		,   	'2'			),
                    (	'huanhoahong'	,   	'2'			),
                    (	'tungnui'		,   	'8'			),
                    (	'duongghuu'		,   	'9'			),
                    (	'vtiaccademy'	,   	'10'		);