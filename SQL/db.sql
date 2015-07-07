CREATE TABLE 'users' (
  'user_id' INT NOT NULL AUTO_INCREMENT,
  'login' VARCHAR,
  'info_id' INT,
  'task_id' INT,
  PRIMARY KEY  ('user_id')
);
 
CREATE TABLE 'auth_data' (
  'login' VARCHAR,
  'password_hash' VARCHAR
);
 
CREATE TABLE 'person_info' (
  'info_id' INT,
  'first_name' VARCHAR,
  'last_name' VARCHAR,
  'rating' DOUBLE,
  'bithday' DATE,
  'post_id' INT,
  'role' VARCHAR,
  'photo' VARCHAR
);

CREATE TABLE 'post' (
  'post_id' INT,
  'name' VARCHAR,
  'description' VARCHAR
);

CREATE TABLE 'task' (
  'task_id' INT,
  'privated' BOOLEAN,
  'description' VARCHAR,
  'due_data' DATE,
  'step_id' INT,
  'finished' BOOLEAN 
);

CREATE TABLE 'step' (
  'step_id' INT,
  'task_id' INT,
  'description' VARCHAR,
  'finished' BOOLEAN
);


 
 
ALTER TABLE 'auth_data' ADD CONSTRAINT 'auth_fk1' FOREIGN KEY ('login') REFERENCES users('login');
ALTER TABLE 'person_info' ADD CONSTRAINT `info_fk1` FOREIGN KEY ('info_id') REFERENCES users('info_id');
ALTER TABLE 'task' ADD CONSTRAINT 'task_fk1' FOREIGN KEY ('task_id') REFERENCES users('task_id');
ALTER TABLE 'step' ADD CONSTRAINT 'step_fk1' FOREIGN KEY ('step_id') REFERENCES task('step_id');
