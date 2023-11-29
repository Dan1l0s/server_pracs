CREATE USER user1 WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE db_prac6 TO user1;

CREATE TABLE books (
    id serial PRIMARY KEY,
    name text NOT NULL,
    author text NOT NULL,
    productType text NOT NULL,
    sellerNumber int NOT NULL,
    cost int NOT NULL,
    amount int NOT NULL
);

INSERT INTO books(name, author, productType, sellerNumber, cost, amount) VALUES
('Metro 2033', 'Dmitriy Gluhovskiy', 'Book', 3283, 600, 5),
('Metro 2034', 'Dmitriy Gluhovskiy', 'Book', 3283, 650, 8),
('Metro 2035', 'Dmitriy Gluhovskiy', 'Book', 3283, 600, 10),
('Harry Potter and Chamber of secrets', 'J.K. Rowling', 'Book', 3284, 650, 2),
('Harry Potter and Azkaban prisoner', 'J.K. Rowling', 'Book', 1234, 100, 0);

GRANT ALL PRIVILEGES ON TABLE books TO user1;
GRANT USAGE, SELECT ON SEQUENCE books_id_seq TO user1;