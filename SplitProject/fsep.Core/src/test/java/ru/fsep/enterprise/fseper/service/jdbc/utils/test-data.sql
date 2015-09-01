INSERT INTO users(password_hash, login, last_name, first_name, rating, birthday, user_role, photo)
    VALUES ('ildar_pas', 'ildar_login', 'Almakayev', 'Ildar', 8.8, '1991-12-31', 'java-dev',
            'http://cs627828.vk.me/v627828952/1121a/dVYbT2kT7ps.jpg');

INSERT INTO task(privated, description, due_data, finished) VALUES (TRUE, 'task-description', '1991-12-31', TRUE );

INSERT INTO post(name, description) VALUES ('post_name', 'post_description');
