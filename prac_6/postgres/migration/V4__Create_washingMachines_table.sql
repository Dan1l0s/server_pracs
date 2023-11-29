CREATE TABLE washingMachines (
    id serial PRIMARY KEY,
    manufacturer text NOT NULL,
    tankCapacity real NOT NULL,
    productType text NOT NULL,
    sellerNumber int NOT NULL,
    cost int NOT NULL,
    amount int NOT NULL
);

INSERT INTO washingMachines(manufacturer, tankCapacity, productType, sellerNumber, cost, amount) VALUES
('Samsung', '0.5', 'Technic', 1000, 16000, 1),
('Bosch', '0.6', 'Technic', 1001, 19000, 10),
('LG', '0.5', 'Technic', 3213, 15000, 5),
('Panasonic', '0.7', 'Technic', 1030, 18000, 8);

GRANT ALL PRIVILEGES ON TABLE washingMachines TO user1;
GRANT USAGE, SELECT ON SEQUENCE washingMachines_id_seq TO user1;