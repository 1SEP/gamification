CREATE TABLE auth_data (
  login VARCHAR PRIMARY KEY,
  password_hash VARCHAR
);

CREATE TABLE person_info (
  info_id INT PRIMARY KEY,
  first_name VARCHAR,
  last_name VARCHAR,
  rating DOUBLE PRECISION,
  bithday DATE,
  post_id INT,
  role VARCHAR,
  photo VARCHAR
) ;

CREATE TABLE post (
  post_id INT PRIMARY KEY,
  name VARCHAR,
  description VARCHAR
) ;

CREATE TABLE task (
  task_id INT PRIMARY KEY ,
  privated BOOLEAN,
  description VARCHAR,
  due_data DATE,
  step_id INT,
  finished BOOLEAN 
) ;

CREATE TABLE step (
  step_id INT PRIMARY KEY ,
  task_id INT REFERENCES task,
  description VARCHAR,
  finished BOOLEAN
) ;

CREATE TABLE users (
  user_id INT PRIMARY KEY ,
  login VARCHAR REFERENCES auth_data,
  info_id INT REFERENCES person_info,
  task_id INT REFERENCES task
);