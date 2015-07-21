CREATE TABLE users (
	id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	login VARCHAR(100),
	password_hash VARCHAR(100),
	first_name VARCHAR(100),
	last_name VARCHAR(100),
	rating DOUBLE,
	birthday DATE,
	role VARCHAR(100),
	photo VARCHAR(100)
);
