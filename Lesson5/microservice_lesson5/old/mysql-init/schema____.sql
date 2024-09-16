-- Drop the database if it already exists
DROP DATABASE IF EXISTS TestingSystem;
-- Create database
CREATE DATABASE IF NOT EXISTS TestingSystem;
USE TestingSystem;

-- Create table Department
DROP TABLE IF EXISTS 	`Department`;
CREATE TABLE IF NOT EXISTS `Department` (
	id 						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`name` 					NVARCHAR(50) NOT NULL UNIQUE KEY,
    total_member			INT	UNSIGNED,
    `type`					ENUM('Dev','Test','ScrumMaster','PM') NOT NULL,
    created_date			DATETIME DEFAULT NOW()
);

-- create table: Account
DROP TABLE IF EXISTS `Account`;
CREATE TABLE `Account`(
	id						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username				VARCHAR(50) NOT NULL UNIQUE KEY,
    department_id 			INT UNSIGNED NOT NULL,
    FOREIGN KEY(department_id) REFERENCES Department(id)
);
