-- H2 Database initialization script
-- This file will be executed automatically on application startup

-- Insert sample products
INSERT INTO product (product_id, name, price, description) VALUES
(RANDOM_UUID(), 'Laptop', 999.99, 'High-performance laptop'),
(RANDOM_UUID(), 'Mouse', 25.99, 'Wireless mouse'),
(RANDOM_UUID(), 'Keyboard', 79.99, 'Mechanical keyboard');

