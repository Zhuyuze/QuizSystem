create database if not exists quizdata;
use quizdata;


drop table if exists users;
CREATE TABLE IF NOT EXISTS users (
	user_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(50) NOT NULL,
    user_password VARCHAR(50) NOT NULL,
    is_admin bool,
    is_available bool
);

insert into users(user_name, user_password, is_admin, is_available) values ("user1", "1111", false, true),
("user2", "2222", false, true), ("user3", "3333", true, true);
select * from users;

drop table if exists questions;
CREATE TABLE IF NOT EXISTS questions (
	question_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    quiz_id INT,
    question_description VARCHAR(50)
);

insert into questions(quiz_id, question_description) values 
(1, "1+2=?"),
(1, "1+3=?"),
(1, "1+4=?"),
(1, "1+5=?"),
(1, "1+6=?"),
(1, "1+7=?"),
(1, "1+8=?"),
(1, "1+9=?"),
(1, "2+1=?"),
(1, "2+2=?"),
(1, "2+3=?"),
(1, "2+4=?"),
(1, "2+5=?"),
(1, "2+6=?"),
(1, "2+7=?"),
(1, "2+8=?"),
(1, "2+9=?"),
(1, "3+1=?"),
(1, "3+2=?"),
(1, "3+3=?"),

(2, "ab_d"),
(2, "bc_e"),
(2, "cd_f"),
(2, "de_g"),
(2, "ef_h"),
(2, "fg_i"),
(2, "gh_j"),
(2, "hi_k"),
(2, "ij_l"),
(2, "jk_m"),
(2, "kl_n"),
(2, "lm_o"),
(2, "mn_p"),
(2, "no_q"),
(2, "op_r"),
(2, "pq_s"),
(2, "qr_t"),
(2, "rs_u"),
(2, "st_v"),
(2, "tu_w"),

(3, "*"),
(3, "**"),
(3, "***"),
(3, "****"),
(3, "*****"),
(3, "******"),
(3, "*******"),
(3, "********"),
(3, "*********"),
(3, "**********"),
(3, "***********"),
(3, "************"),
(3, "*************"),
(3, "**************"),
(3, "***************"),
(3, "****************"),
(3, "*****************"),
(3, "******************"),
(3, "*******************"),
(3, "********************")
;
select * from questions;

drop table if exists all_options;
CREATE TABLE IF NOT EXISTS all_options (
	option_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    question_id INT,
    option_description VARCHAR(50),
    is_correct bool
);

insert into all_options(question_id, option_description, is_correct) values
(1, "3", true),
(1, "4", false),
(1, "3.0", true),
(2, "3", false),
(2, "4", true),
(3, "5", true),
(3, "4", false),
(3, "5.0", true),
(4, "3", false),
(4, "6", true),
(5, "4", false),
(5, "7", true),
(5, "3", false),
(5, "6+1", true),
(6, "8", true),
(6, "4", false),
(6, "8.0", true),
(7, "3", false),
(7, "9", true),
(8, "10", true),
(8, "4", false),
(8, "10.0", true),
(9, "8", false),
(9, "3", true),
(10, "5", false),
(10, "4", true),
(10, "3", false),
(10, "6-2", true),
(11, "5", true),
(11, "4", false),
(11, "5.0", true),
(12, "3", false),
(12, "6", true),
(13, "7", true),
(13, "4", false),
(13, "7.0", true),
(14, "3", false),
(14, "8", true),
(15, "4", false),
(15, "4+5", true),
(15, "3", false),
(15, "9", true),
(16, "10", true),
(16, "4", false),
(16, "10.0", true),
(17, "3", false),
(17, "11", true),
(18, "4", true),
(18, "5", false),
(18, "4.0", true),
(19, "3", false),
(19, "5", true),
(20, "4", false),
(20, "6", true),
(20, "3", false),
(20, "12/2", true),

(21, "c", true),
(21, "x", false),
(21, "C", true),
(22, "d", true),
(22, "e", false),
(23, "f", false),
(23, "e", true),
(24, "f", true),
(24, "F", true),
(24, "t", false),
(24, "r", false),
(24, "y", false),
(25, "g", true),
(26, "h", true),
(26, "x", false),
(26, "H", true),
(27, "i", true),
(27, "e", false),
(28, "f", false),
(28, "j", true),
(29, "k", true),
(29, "K", true),
(29, "t", false),
(29, "r", false),
(29, "y", false),
(30, "l", true),
(31, "m", true),
(31, "x", false),
(31, "M", true),
(32, "N", true),
(32, "e", false),
(33, "f", false),
(33, "O", true),
(34, "P", true),
(34, "p", true),
(34, "t", false),
(34, "r", false),
(34, "y", false),
(35, "Q", true),
(36, "R", true),
(36, "x", false),
(36, "r", true),
(37, "s", true),
(37, "e", false),
(38, "f", false),
(38, "T", true),
(39, "u", true),
(39, "U", true),
(39, "t", false),
(39, "r", false),
(39, "y", false),
(40, "V", true),

(41, "1", true),
(41, "2", false),
(42, "1", false),
(42, "2", true),
(42, "3", false),
(43, "3", true),
(43, "33/11", true),
(44, "1", false),
(44, "1", false),
(44, "1", false),
(44, "4", true),
(45, "5", true),
(45, "1", false),
(46, "6", true),
(47, "7", true),
(47, "8", false),
(48, "8", true),
(48, "1", false),
(48, "2^3", true),
(49, "1", false),
(49, "9", true),
(50, "10", true),
(51, "1", false),
(51, "11", true),
(52, "11", false),
(52, "14", false),
(52, "12", true),
(53, "4", false),
(53, "13", true),
(54, "14", true),
(54, "13", false),
(54, "12", false),
(54, "11", false),
(54, "10", false),
(55, "15", true),
(56, "15", false),
(56, "16", true),
(57, "17", true),
(57, "1", false),
(57, "13", false),
(58, "15", false),
(58, "18", true),
(58, "3*6", true),
(59, "19", true),
(60, "20", true),
(60, "2*2*5", true)
;
select * from all_options;

drop table if exists quizzes;
CREATE TABLE IF NOT EXISTS quizzes (
	quiz_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    quiz_type INT,
    quiz_description VARCHAR(50)
);

insert into quizzes(quiz_type, quiz_description) values (1, "math"), (2, "alphabet"), (3, "countstar");
select * from quizzes;

drop table if exists feedback;
CREATE TABLE IF NOT EXISTS feedback (
	feedback_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    feedback_comment VARCHAR(50),
    feedback_rating INT
);

insert into feedback(feedback_comment, feedback_rating) values ("Good", 5), ("Normal", 3);

select * from feedback;

drop table if exists submissions;
CREATE TABLE IF NOT EXISTS submissions (
	submission_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id int,
    quiz_id int,
    score int,
    start_time varchar(50),
    end_time varchar(50)
);

insert into submissions(user_id, quiz_id, score, start_Time, end_time) values
(1, 2, 85, "ST", "ET");

drop table if exists submission_details;
CREATE TABLE IF NOT EXISTS submission_details (
	submission_detail_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id int,
    submission_id int,
    question_id int,
    user_choice int
);

insert into submission_details(user_id, submission_id, question_id, user_choice) values
(1, 1, 21, 57),
(1, 1, 21, 59),
(1, 1, 23, 63),
(1, 1, 24, 64),
(1, 1, 24, 65),
(1, 1, 25, 69),
(1, 1, 27, 73),
(1, 1, 30, 82),
(1, 1, 36, 98),
(1, 1, 39, 104)
;
































