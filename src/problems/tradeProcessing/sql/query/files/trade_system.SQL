CREATE DATABASE trade_system;
USE trade_system;

CREATE TABLE trade_payloads (
    id INT AUTO_INCREMENT PRIMARY KEY,                -- Auto-incrementing unique identifier
    trade_id VARCHAR(20) UNIQUE NOT NULL,             -- Unique identifier for each trade
    status ENUM('valid', 'notvalid') NOT NULL,        -- Validity status (valid or not valid)
    payload TEXT NOT NULL,                            -- The full CSV row stored as text
    created_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,   -- Timestamp for record creation
    updated_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP    -- Timestamp for record updates
        ON UPDATE CURRENT_TIMESTAMP                   -- Automatically updated on record change
);

CREATE TABLE securitiesReference (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cusip VARCHAR(10) NOT NULL,
    security_id CHAR(9) NOT NULL,
    UNIQUE (security_id)  -- Ensures that each security_id is unique
);

CREATE TABLE journal_entry (
    account_id VARCHAR(30) PRIMARY KEY,
    security_id CHAR(9) NOT NULL,
    direction VARCHAR(10) NOT NULL,
    quantity DOUBLE(10, 2) NOT NULL,
    posted_status VARCHAR(20) NOT NULL DEFAULT 'statusPosted',
    created_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,   -- Timestamp for record creation
    updated_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP    -- Timestamp for record updates
        ON UPDATE CURRENT_TIMESTAMP                   -- Automatically updated on record change
);

CREATE TABLE positions (
    position_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id VARCHAR(255),
    security_id VARCHAR(255),
    position DOUBLE,
    version INT DEFAULT 0,  -- Version for optimistic locking
    created_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,   -- Timestamp for record creation
    updated_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP    -- Timestamp for record updates
        ON UPDATE CURRENT_TIMESTAMP,                   -- Automatically updated on record change
    UNIQUE(account_id, security_id)
);
