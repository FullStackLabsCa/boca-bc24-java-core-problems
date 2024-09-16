CREATE DATABASE bootcamp2;

USE bootcamp2;

CREATE TABLE accounts (
    credit_card_number VARCHAR(16) PRIMARY KEY,  -- Primary key for uniqueness
    card_type VARCHAR(20),
    balance DECIMAL(10, 2),  -- Store balance with two decimal places
    version INT DEFAULT 0  -- Version for optimistic locking
);


CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,  -- Unique transaction ID
    credit_card_number VARCHAR(16),  -- Foreign key to accounts table
    transaction_type VARCHAR(10),
    amount DECIMAL(10, 2),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (credit_card_number) REFERENCES accounts(credit_card_number)
);


SELECT * FROM accounts;
SELECT * FROM transactions;

DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS transactions;

CREATE TABLE accounts (
    credit_card_number VARCHAR(16) PRIMARY KEY,  -- Primary key for uniqueness
    balance DECIMAL(10, 2),  -- Store balance with two decimal places
    version INT DEFAULT 0  -- Version for optimistic locking
);

DELETE FROM accounts;