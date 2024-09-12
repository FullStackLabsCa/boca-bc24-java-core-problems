USE employeeTemp;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);


-- Batch insert for users table
INSERT INTO users (name, email) VALUES
('John Doe', 'john.doe@example.com'),
('Jane Smith', 'jane.smith@example.com'),
('Robert Brown', 'robert.brown@example.com'),
('Emily Davis', 'emily.davis@example.com'),
('Michael Wilson', 'michael.wilson@example.com'),
('Alice Johnson', 'alice.johnson@example.com'),
('Bob White', 'bob.white@example.com'),
('Charlie Black', 'charlie.black@example.com'),
('Jay Shah', 'jayshah@example.com');

CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    order_date DATETIME,
    amount DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert sample orders
INSERT INTO orders (user_id, order_date, amount) VALUES
(1, NOW(), 150.00),
(2, NOW(), 200.00),
(3, NOW(), 250.00),
(4, NOW(), 350.00),
(5, NOW(), 450.00),
(6, NOW(), 400.00),
(7, NOW(), 550.00),
(8, NOW(), 500.00),
(9, NOW(), 950.00);

DESCRIBE users;
SELECT * FROM users;


DESCRIBE orders;
SELECT * FROM users;

SELECT * FROM users;
