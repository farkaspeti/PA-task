DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS posts CASCADE;
DROP TABLE IF EXISTS comments CASCADE;
DROP TABLE IF EXISTS labels CASCADE;
DROP TABLE IF EXISTS messages_users CASCADE;
DROP TABLE IF EXISTS labels_posts CASCADE;
DROP TABLE IF EXISTS posts_logs CASCADE;

DROP TYPE IF EXISTS role;
CREATE TYPE role AS ENUM('USER','ADMIN');

CREATE TABLE users(
	user_id SERIAL PRIMARY KEY,
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	password VARCHAR(60),
	email VARCHAR(20),
	user_type role
);

CREATE TABLE posts(
	post_id SERIAL PRIMARY KEY,
	user_id INT REFERENCES users(user_id),
	content VARCHAR(300),
	post_date date
);

CREATE TABLE comments(
	comment_id SERIAL PRIMARY KEY,
	post_id INT REFERENCES posts(post_id),
	user_id INT REFERENCES users(user_id),
	comment_text VARCHAR(300),
	comment_date date
);

CREATE TABLE labels(
	label_id SERIAL PRIMARY KEY,
	label_content VARCHAR(15)
);

CREATE TABLE messages_users(
	message_id SERIAL PRIMARY KEY,
	user_id_sender INT REFERENCES users(user_id),
	message_content VARCHAR(300),
	user_id_receiver INT REFERENCES users(user_id),
	message_date date
);

CREATE TABLE labels_posts(
	label_id INT REFERENCES labels(label_id),
	post_id INT REFERENCES posts(post_id)
);

CREATE TABLE posts_logs(
	post_logs_id SERIAL PRIMARY KEY,
	post_id INT REFERENCES posts(post_id),
	content VARCHAR(300)
);

CREATE OR REPLACE FUNCTION posts_function() RETURNS trigger AS '  
    BEGIN
        INSERT INTO posts_logs(post_id, content) VALUES (new.post_id,new.content);
        INSERT INTO labels_posts(post_id) VALUES (new.post_id);
    RETURN null;
    END
' LANGUAGE plpgsql;

CREATE TRIGGER post_trigger
    	AFTER INSERT ON posts
   	FOR EACH ROW EXECUTE PROCEDURE posts_function();


INSERT INTO users(user_id,first_name,last_name,password,email,user_type) VALUES (1,'Péter','Farkas','admin123','admin@admin','ADMIN');

