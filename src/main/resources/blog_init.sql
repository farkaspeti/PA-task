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
	first_name VARCHAR(30) UNIQUE,
	last_name VARCHAR(30) UNIQUE,
	password VARCHAR(60),
	email VARCHAR(20),
	user_type role
);

CREATE TABLE posts(
	post_id SERIAL PRIMARY KEY,
	user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
	first_name VARCHAR(30) REFERENCES users(first_name) ON DELETE CASCADE,
	last_name VARCHAR(30) REFERENCES users(last_name) ON DELETE CASCADE,
	content VARCHAR(300),
	post_date date
);

CREATE TABLE comments(
	comment_id SERIAL PRIMARY KEY,
	post_id INT REFERENCES posts(post_id) ON DELETE CASCADE,
	user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
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
	label_id INT REFERENCES labels(label_id) ON DELETE CASCADE,
	post_id INT REFERENCES posts(post_id) ON DELETE CASCADE
);

CREATE TABLE posts_logs(
	post_logs_id SERIAL PRIMARY KEY,
	post_id INT REFERENCES posts(post_id) ON DELETE CASCADE,
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


INSERT INTO users(first_name,last_name,password,email,user_type) VALUES ('Péter','Farkas','admin123','admin@admin','ADMIN');
INSERT INTO users(first_name,last_name,password,email,user_type) VALUES ('MyNameIss','User1','user123','user1@user1','USER');
INSERT INTO users(first_name,last_name,password,email,user_type) VALUES ('MyNameIs','User2','user123','user2@user2','USER');



INSERT INTO posts(user_id, first_name,last_name,content, post_date) VALUES (1,'Péter','Farkas','This is a test post','2019-07-15');
INSERT INTO posts(user_id, first_name,last_name,content, post_date) VALUES (2,'MyNameIs','User1','This is my first post woa','2019-07-19');
INSERT INTO posts(user_id, first_name,last_name,content, post_date) VALUES (2,'MyNameIs','User1','This is my second post lol','2019-07-19');
INSERT INTO posts(user_id, first_name,last_name,content, post_date) VALUES (3,'MyNameIss','User2','Stop this please','2019-07-20');
INSERT INTO posts(user_id, first_name,last_name,content, post_date) VALUES (2,'MyNameIs','User1','No thx','2019-07-20');
INSERT INTO posts(user_id, first_name,last_name,content, post_date) VALUES (1,'Péter','Farkas','but you should','2019-07-21');

INSERT INTO comments(post_id, user_id, comment_text, comment_date) VALUES (1,1,'And its one of the finest','2019-07-21');
INSERT INTO comments(post_id, user_id, comment_text, comment_date) VALUES (2,1,'This is a comment','2019-07-15');