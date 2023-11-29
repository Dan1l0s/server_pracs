CREATE TABLE clients (
    id serial PRIMARY KEY,
    name text NOT NULL,
    email text NOT NULL,
    login text NOT NULL,
    password text NOT NULL
);

INSERT INTO clients(name, email, login, password) VALUES
('Danila Antonov', 'danilos01pr@gmail.com', 'dan1l0s', 'qwerty'),
('Richard Gilmartin', 'scathach@gmail.com', 'dmcg', 'gthcjyjxrf'),
('Ilya Popov', 'orn0tme@mail.ru', 'elern', 'gfhjkm');

GRANT ALL PRIVILEGES ON TABLE clients TO user1;
GRANT USAGE, SELECT ON SEQUENCE clients_id_seq TO user1;