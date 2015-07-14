CREATE SEQUENCE auto_id_posts;
CREATE SEQUENCE auto_id_tasks;
CREATE SEQUENCE auto_id_steps;
CREATE SEQUENCE auto_id_users;

CREATE TABLE posts (
  info_id INT ,
  post_id INT 
);

CREATE TABLE post (
  id INT PRIMARY KEY DEFAULT nextval('auto_id_posts'),
  name VARCHAR,
  description VARCHAR
) ;

CREATE TABLE tasks (
  task_id INT ,
  user_id INT 
);

CREATE TABLE task (
  id INT PRIMARY KEY DEFAULT nextval('auto_id_steps'),
  privated BOOLEAN,
  description VARCHAR,
  due_data DATE,
  steps_id INT ,
  finished BOOLEAN
) ;

CREATE TABLE step (
  id INT PRIMARY KEY DEFAULT nextval('auto_id_steps'),
  task_id INT,
  description VARCHAR,
  finished BOOLEAN
) ;

CREATE TABLE steps (
  step_id INT ,
  task_id INT
);

CREATE TABLE users (
  id INT PRIMARY KEY DEFAULT nextval('auto_id_users'),
  login VARCHAR,
  password_hash VARCHAR,
  first_name VARCHAR,
  last_name VARCHAR,
  rating DOUBLE PRECISION,
  bithday DATE,
  posts_id INT ,
  role VARCHAR,
  photo VARCHAR,
  tasks_id INT 
);

ALTER TABLE posts ADD CONSTRAINT auth_fk1 FOREIGN KEY (info_id) REFERENCES users(id);
ALTER TABLE posts ADD CONSTRAINT auth_fk2 FOREIGN KEY (post_id) REFERENCES post(id);
ALTER TABLE tasks ADD CONSTRAINT auth_fk3 FOREIGN KEY (task_id) REFERENCES task(id);
ALTER TABLE tasks ADD CONSTRAINT auth_fk4 FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE task ADD CONSTRAINT auth_fk5 FOREIGN KEY (steps_id) REFERENCES steps(id);
ALTER TABLE step ADD CONSTRAINT auth_fk6 FOREIGN KEY (task_id) REFERENCES task(id);
ALTER TABLE steps ADD CONSTRAINT auth_fk7 FOREIGN KEY (step_id) REFERENCES step(id);
ALTER TABLE steps ADD CONSTRAINT auth_fk8 FOREIGN KEY (task_id) REFERENCES task(id);
ALTER TABLE users ADD CONSTRAINT auth_fk9 FOREIGN KEY (posts_id) REFERENCES posts(post_id);
ALTER TABLE users ADD CONSTRAINT auth_fk10 FOREIGN KEY (tasks_id) REFERENCES tasks(task_id);