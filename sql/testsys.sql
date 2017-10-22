-- 数据库建表语句
CREATE TABLE t_testentity(
    id INTEGER PRIMARY KEY autoincrement,
    description varchar(200),
    te_iden int
);
-- 创建选择题表
CREATE TABLE t_choice(
    id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    uuid VARCHAR(128) NOT NULL,
    question_text  TEXT(512) NOT NULL,
    choice_a  TEXT(128) NOT NULL,
    choice_b  TEXT(128) NOT NULL,
    choice_c  TEXT(128) NOT NULL,
    choice_d  TEXT(128) NOT NULL,
    right_answer  TEXT(16) NOT NULL,
    create_time DATETIME NOT NULL,
    update_time DATETIME NOT NULL
);