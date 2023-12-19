CREATE DATABASE db12;

CREATE USER user1 WITH ENCRYPTED PASSWORD 'password';

ALTER ROLE user1 SET client_encoding TO 'utf8';
ALTER ROLE user1 SET default_transaction_isolation TO 'read committed';
ALTER ROLE user1 SET timezone TO 'UTC';

GRANT ALL PRIVILEGES ON DATABASE db12 TO user1;