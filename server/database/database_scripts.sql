CREATE database jobguru;

use jobguru;


CREATE TABLE IF NOT EXISTS `jobguru`.`user` (
    firstName varchar(64),
    lastName varchar(64),
    email varchar(128),
    password varchar(128),
    PRIMARY KEY (email)
);


CREATE TABLE IF NOT EXISTS `jobguru`.`user_job` (
    job_id varchar(128),
    employer_name varchar(128),
    job_title varchar(128),
    job_country varchar(128),
    job_employment_type varchar(128),
    email varchar(128),
    Constraint USERJOB_FK FOREIGN KEY (email) REFERENCES user(email)
);

CREATE TABLE IF NOT EXISTS `jobguru`.`apply_job` (
    job_id varchar(128),
    firstName varchar(64),
    lastName varchar(64),
    email varchar(128),
    phone varchar(64),
    resume mediumBLOB,
    resume_url varchar(1000),
    PRIMARY KEY (email)
);

