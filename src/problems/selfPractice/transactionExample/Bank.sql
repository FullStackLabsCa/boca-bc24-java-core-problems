CREATE DATABASE IF NOT EXISTS bank;

USE bank;

CREATE TABLE IF NOT EXISTS bank_account (
    account_id INT PRIMARY KEY,
    balance DECIMAL(15, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions_log (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES bank_account(account_id)
);

INSERT INTO bank_account (account_id, balance) VALUES
(1, 500.00),  -- Account A
(2, 300.00);  -- Account B

UPDATE bank_account SET balance = 500.00 WHERE account_id = 1;
UPDATE bank_account SET balance = 500.00 WHERE account_id = 2;
