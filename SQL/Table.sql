CREATE SEQUENCE auto_id_posts;
CREATE SEQUENCE auto_id_tasks;
CREATE SEQUENCE auto_id_steps;
CREATE SEQUENCE auto_id_users;

CREATE TABLE posts (
  info_id INT ,
  post_id INT,
  PRIMARY KEY(info_id,post_id)
);

CREATE TABLE post (
  id INT PRIMARY KEY DEFAULT nextval('auto_id_posts'),
  name VARCHAR,
  description VARCHAR
) ;

CREATE TABLE tasks (
  task_id INT ,
  user_id INT,
  PRIMARY KEY(task_id,user_id)
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
  description VARCHAR,
  finished BOOLEAN
) ;


CREATE TABLE users (
  id INT PRIMARY KEY DEFAULT nextval('auto_id_users'),
  login VARCHAR,
  password_hash VARCHAR,
  first_name VARCHAR,
  last_name VARCHAR,
  rating DOUBLE PRECISION,
  birthday DATE,
  role VARCHAR,
  photo VARCHAR
);



ALTER TABLE posts ADD CONSTRAINT auth_fk1 FOREIGN KEY (info_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE ;
ALTER TABLE posts ADD CONSTRAINT auth_fk2 FOREIGN KEY (post_id) REFERENCES post(id) ON UPDATE CASCADE ON DELETE CASCADE ;
ALTER TABLE tasks ADD CONSTRAINT auth_fk3 FOREIGN KEY (task_id) REFERENCES task(id) ON UPDATE CASCADE ON DELETE CASCADE ;
ALTER TABLE tasks ADD CONSTRAINT auth_fk4 FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE ;
ALTER TABLE task ADD CONSTRAINT auth_fk7 FOREIGN KEY (steps_id) REFERENCES step(id) ON UPDATE CASCADE ON DELETE CASCADE ;
