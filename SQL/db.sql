CREATE SEQUENCE auto_id_users;
CREATE SEQUENCE auto_id_posts;
CREATE SEQUENCE auto_id_tasks;
CREATE SEQUENCE auto_id_steps;

CREATE TABLE posts (
  info_id INT REFERENCES users,
  post_id INT REFERENCES post
);

CREATE TABLE post (
  id INT PRIMARY KEY DEFAULT nextval('auto_id_posts'),
  name VARCHAR,
  description VARCHAR
) ;

CREATE TABLE tasks (
  task_id INT REFERENCES task,
  user_id INT REFERENCES users
);

CREATE TABLE task (
  id INT PRIMARY KEY DEFAULT nextval('auto_id_steps'),
  privated BOOLEAN,
  description VARCHAR,
  due_data DATE,
  steps_id INT REFERENCES steps,
  finished BOOLEAN 
) ;

CREATE TABLE step (
  id INT PRIMARY KEY DEFAULT nextval('auto_id_steps'),
  task_id INT REFERENCES task,
  description VARCHAR,
  finished BOOLEAN
) ;

CREATE TABLE steps (
  step_id INT REFERENCES step,
  task_id INT REFERENCES task

);

CREATE TABLE users (
  id INT PRIMARY KEY DEFAULT nextval('auto_id_users'),
  login VARCHAR,
  password_hash VARCHAR,
  login VARCHAR REFERENCES auth_data,
  first_name VARCHAR,
  last_name VARCHAR,
  rating DOUBLE PRECISION,
  bithday DATE,
  posts_id INT REFERENCES posts(post_id),
  role VARCHAR,
  photo VARCHAR,
  tasks_id INT REFERENCES tasks
);
