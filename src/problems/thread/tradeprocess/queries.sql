CREATE TABLE trade_payloads (
    trade_payload_id INT AUTO_INCREMENT PRIMARY KEY,
    trade_id VARCHAR(20) UNIQUE,
    status VARCHAR(20),
    status_reason VARCHAR(500),
    payload VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE journal_entry (
    journal_entry_id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20),
    security_id INT,
    direction ENUM('BUY', 'SELL'),
    quantity INT,
    posted_status VARCHAR(20) DEFAULT 'not_posted',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY(security_id) REFERENCES securities_reference(security_reference_id)
);

CREATE TABLE securities_reference (
    security_reference_id INT AUTO_INCREMENT PRIMARY KEY,
    cusip VARCHAR(9) UNIQUE,
    description VARCHAR(100)
);

CREATE TABLE positions (
    position_id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20),
    security VARCHAR(20),
    position INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE (account_number, security)
);


=========================FOR SECURITY REFERENCES=======================

mysql --local-infile=1  -u root -p -h 127.0.0.1 -P 3306

set global local_infile = 1;

LOAD DATA LOCAL INFILE '/Users/Kiran.Virani/Downloads/securityreference.csv'
INTO TABLE securities_reference FIELDS TERMINATED BY ',' LINES TERMINATED
BY '\r\n' IGNORE 1 ROWS (cusip, description);