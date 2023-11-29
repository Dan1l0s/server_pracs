CREATE TABLE telephones (
    id serial PRIMARY KEY,
    model text NOT NULL,
    manufacturer text NOT NULL,
    productType text NOT NULL,
    batteryCapacity int NOT NULL,
    sellerNumber int NOT NULL,
    cost int NOT NULL,
    amount int NOT NULL
);

INSERT INTO telephones(model, manufacturer, productType, batteryCapacity, sellerNumber, cost, amount) VALUES
('Galaxy Note 20', 'Samsung', 'Telephone', 4500, 1000, 80000, 5),
('iPhone 15 Pro Max', 'Apple', 'Telephone', 3300, 3234, 150000, 2),
('Pixel 6', 'Google', 'Telephone', 5000, 2280, 5000, 0);

GRANT ALL PRIVILEGES ON TABLE telephones TO user1;
GRANT USAGE, SELECT ON SEQUENCE telephones_id_seq TO user1;