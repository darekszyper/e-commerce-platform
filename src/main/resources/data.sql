-- Products
INSERT INTO product (name, description, price)
VALUES ('Product 1', 'Description for Product 1', 50.0),
       ('Product 2', 'Description for Product 2', 30.0),
       ('Product 3', 'Description for Product 3', 25.0),
       ('Product 4', 'Description for Product 4', 60.0),
       ('Product 5', 'Description for Product 5', 40.0);

-- Users
INSERT INTO users (username)
VALUES ('user1'),
       ('user2'),
       ('user3');

-- Carts
INSERT INTO cart (user_id)
VALUES (1), -- Assign cart to user1
       (2), -- Assign cart to user2
       (3); -- Assign cart to user3;

-- Orders
INSERT INTO orders (user_id, order_date, total_amount)
VALUES (1, '2023-10-15 08:00:00', 80.0),
       (2, '2023-10-15 09:30:00', 110.0),
       (3, '2023-10-15 11:45:00', 65.0);