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

