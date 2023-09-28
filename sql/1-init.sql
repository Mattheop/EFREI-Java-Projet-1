CREATE TABLE IF NOT EXISTS employee
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    type       ENUM ('developer', 'other') NOT NULL DEFAULT ('other'),
    first_name VARCHAR(30)                 NOT NULL,
    last_name  VARCHAR(30)                 NOT NULL,
    address    VARCHAR(100)                NOT NULL,
    nickname   VARCHAR(30)                 NOT NULL,
    chief        VARCHAR(30)               DEFAULT NULL,
    hobbies    VARCHAR(255)                DEFAULT NULL,
    birth_year INT                         NOT NULL,
    salary     FLOAT                       NOT NULL,
    bonus      FLOAT                       NOT NULL,

    CONSTRAINT ck_salary_positive CHECK ( salary > 0 ),
    CONSTRAINT ck_bonus_positive CHECK ( bonus > 0 ),
    CONSTRAINT ck_birth_year CHECK ( birth_year > 0 )
);