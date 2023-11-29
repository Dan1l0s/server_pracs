CREATE USER user1 WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE db_prac5 TO user1;

CREATE TABLE books (
    id serial PRIMARY KEY,
    name text NOT NULL,
    author text NOT NULL,
    productType text NOT NULL,
    sellerNumber int NOT NULL,
    cost int NOT NULL
);

INSERT INTO books(name, author, productType, sellerNumber, cost) VALUES
('The Unwilling in Men', 'Alex Rover', 'Book', 5543, 200),
('The Smooth without a Return', 'Bob Marley', 'Book', 1234, 250),
('The Red in the Light', 'Bob Marley', 'Book', 6578, 400),
('Hidden of Dreaming', 'Kate Yandson', 'Book', 6578, 350),
('A Purple in Name', 'Alex Rover', 'Book', 1234, 100);

GRANT ALL PRIVILEGES ON TABLE books TO user1;
GRANT USAGE, SELECT ON SEQUENCE books_id_seq TO user1;