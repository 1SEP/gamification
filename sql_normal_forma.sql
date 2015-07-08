CREATE TABLE users (
  id integer,
  login VARCHAR UNIQUE,
  info_id integer,
  
  PRIMARY KEY (user_id),
  FOREGIN KEY (login) REFERENCES auth_data (login),
  FOREGIN KEY (info_id) REFERENCES person_info (info_id),
    
);

CREATE TABLE user_tasks (
  id integer UNIQUE,
  user_id integer ,
  task_id integer,
  info_id integer
  FOREIGN KEY (uesr_id) users (id),
  FOREGIN KEY (task_id) task (id),
  FOREGIN KEY (info_id) person_info (id),
  PRIMARY KEY (id)
 
);
 
CREATE TABLE auth_data (
  login VARCHAR ,
  password_hash VARCHAR
  PRIMARY KEY(login)
);
 
CREATE TABLE person_info(
  id integer NOT NULL UNIQUE,
  first_name NOT NULL VARCHAR,
  last_name NOT NULL VARCHAR,
  rating DOUBLE PRECISION,
  bithday DATE,
  post_id integer,
  role VARCHAR,
  photo VARCHAR,
  PRIMARY KEY (info_id),
  FOREGIN KEY (post_id) REFERENCES post (post_id)
);

CREATE TABLE post (
  post_id integer NOT NULL UNIQUE,
  name VARCHAR ,
  description VARCHAR,
  PRIMARY KEY (post_id)
);

ÑREATE TABLE user_posts(
id integer NIT NULL UNIQUE,
user_id integer NOT NULL,
post_id integer NOT NULL,
PRIMARY (id),
FOREIGN KEY (user_id) REFERENCES users (id),
FOREIGN KEY (post_id) REFERENCES post (post_id)

CREATE TABLE task (
  id integer NOT NULL UNIQUE,
  privated BOOLEAN NOT NULL,
  description VARCHAR ,
  due_data DATE NOT NULL,
  finished BOOLEAN NOT NULL 
  PRIMARY KEY (task_id),
  FOREGIN KEY (step_id) REFERENCES step (step_id)
);

CREATED TABLE task_steps(
   id integer NOT NULL,
   step_id integer,
   task_id integer,
   FOREIGN KEY step_id REFERENCES step(step_id),
   FOREIGN KEY task_id REFERENCES task (id)
);

CREATE TABLE step (
  step_id integer UNIQUE,
  description VARCHAR NOT NULL,
  finished BOOLEAN NOT NULL,
  PRIMARY KEY (step_id),
  FOREGIN KEY (task_id) REFERENCES task (task_id)
);



 
