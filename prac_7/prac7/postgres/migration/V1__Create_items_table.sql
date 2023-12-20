CREATE USER user1 WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE db_prac7 TO user1;

CREATE TABLE items (
    id serial PRIMARY KEY,
    name text NOT NULL,
    manufacturer text NOT NULL,
    productType text NOT NULL,
    sellerNumber int NOT NULL,
    cost int NOT NULL,
    amount int NOT NULL
);

INSERT INTO items(name, manufacturer, productType, sellerNumber, cost, amount) VALUES
('The Unwilling in Men', 'Alex Rover', 'Book', 5543, 200, 5),
('The Smooth without a Return', 'Bob Marley', 'Book', 1234, 250, 7),
('The Red in the Light', 'Bob Marley', 'Book', 6578, 400, 3),
('Hidden of Dreaming', 'Kate Yandson', 'Book', 6578, 350, 0),
('A Purple in Name', 'Alex Rover', 'Book', 1234, 100, 2),
('iPhone 11', 'Apple', 'Telephone', 4455, 30000, 15),
('Pixel 6', 'Google', 'Telephone', 4455, 35000, 10),
('Galaxy S9', 'Samsung', 'Telephone', 9999, 20000, 2),
('LG-0.05', 'LG', 'Technic', 1122, 15000, 5),
('LG-0.07', 'LG', 'Technic', 1122, 18000, 7),
('Midea-0.05', 'Midea', 'Technic', 3322, 16000, 5),
('Midea-0.06', 'Midea', 'Technic', 3322, 19000, 2);

GRANT ALL PRIVILEGES ON TABLE items TO user1;
GRANT USAGE, SELECT ON SEQUENCE items_id_seq TO user1;