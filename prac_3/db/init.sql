CREATE DATABASE IF NOT EXISTS appDB;
CREATE USER IF NOT EXISTS 'user'@'%' IDENTIFIED BY 'password';
GRANT SELECT,UPDATE,INSERT ON appDB.* TO 'user'@'%';
FLUSH PRIVILEGES;

USE appDB;

CREATE TABLE users (
  user_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_login VARCHAR(20) NOT NULL,
  user_realname VARCHAR(40) NOT NULL
);

INSERT INTO users (user_login, user_realname) VALUES
    ('Dan1l0s', 'Danila Antonov'), 
    ('dmcg', 'Richard Gilmartin'), 
    ('Orn0tme', 'Ilya Popov'), 
    ('Pat1ss0nchick', 'Ilya Andriakhin');

CREATE TABLE IF NOT EXISTS posts (
  post_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  post_date VARCHAR(40) NOT NULL,
  post_text VARCHAR(300) NOT NULL,
  user_id INT(11) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO posts (post_date, post_text, user_id) VALUES
    ('2023-10-01', 'Hello everyone!', 1), 
    ('2023-10-02', 'Bye-bye everyone :C', 1),
    ('2023-10-10', 'I am doing rschir #3', 1) 
    ('2023-10-05', 'Tired of watching anime', 2), 
    ('2023-10-06', 'What`s that, comedy and ecchi? Sold.', 2), 
    ('2023-10-07', 'Hey, can anyone hear me? :(', 3), 
    ('2023-10-08', 'Sorry guys, I won`t come today', 4);