
CREATE TABLE auth_data (
  login VARCHAR PRIMARY KEY,
  password_hash VARCHAR
);


CREATE TABLE person_info (
  id INT PRIMARY KEY,
  first_name VARCHAR,
  last_name VARCHAR,
  rating DOUBLE PRECISION,
  bithday DATE,
  posts_id INT REFERENCES posts(post_id),
  role VARCHAR,
  photo VARCHAR
) ;

CREATE TABLE posts(
  info_id INT REFERENCES person_info,
  post_id INT REFERENCES post
);

CREATE TABLE post (
  id INT PRIMARY KEY,
  name VARCHAR,
  description VARCHAR
) ;

CREATE TABLE tasks (
  task_id INT REFERENCES task,
  user_id INT REFERENCES users
);

CREATE TABLE task (
  id INT PRIMARY KEY ,
  privated BOOLEAN,
  description VARCHAR,
  due_data DATE,
  steps_id INT REFERENCES steps,
  finished BOOLEAN 
) ;

CREATE TABLE step (
  id INT PRIMARY KEY ,
  task_id INT REFERENCES task,
  description VARCHAR,
  finished BOOLEAN
) ;

CREATE TABLE steps (
  step_id INT REFERENCES step,
  task_id INT REFERENCES task

);

CREATE TABLE users (
  id INT PRIMARY KEY ,
  login VARCHAR REFERENCES auth_data,
  info_id INT REFERENCES person_info,
  tasks_id INT REFERENCES tasks
);