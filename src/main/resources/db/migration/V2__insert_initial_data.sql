-- Tabla person
INSERT INTO person (name, last_name, dni, phone, email)
VALUES ('Juan', 'Pérez', '1234567890', '0987654321', 'juan.perez@example.com'),
       ('María', 'González', '2345678901', '0998765432', 'maria.gonzalez@example.com'),
       ('Carlos', 'Rodríguez', '3456789012', '0976543210', 'carlos.rodriguez@example.com'),
       ('Ana', 'López', '4567890123', '0965432109', 'ana.lopez@example.com'),
       ('Pedro', 'Martínez', '5678901234', '0954321098', 'pedro.martinez@example.com');

-- Tabla user
INSERT INTO user (user, password, person_id)
VALUES ('juan123', '$2a$12$VFLJSmXjI2kdYk05A53mkO2DZR/7k.Lb0qSeMLKw9r.e9vVN0miM6', 1),
       ('maria456', '$2a$12$VFLJSmXjI2kdYk05A53mkO2DZR/7k.Lb0qSeMLKw9r.e9vVN0miM6', 2),
       ('carlos789', '$2a$12$VFLJSmXjI2kdYk05A53mkO2DZR/7k.Lb0qSeMLKw9r.e9vVN0miM6', 3),
       ('ana321', '$2a$12$VFLJSmXjI2kdYk05A53mkO2DZR/7k.Lb0qSeMLKw9r.e9vVN0miM6', 4),
       ('pedro654', '$2a$12$VFLJSmXjI2kdYk05A53mkO2DZR/7k.Lb0qSeMLKw9r.e9vVN0miM6', 5);

-- Tabla role
INSERT INTO role (role, state)
VALUES ('Admin', true),
       ('User', true),
       ('Editor', true),
       ('Reviewer', true),
       ('Guest', true);

-- Tabla user_role
INSERT INTO user_role (user, role)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5);

-- Tabla competition
INSERT INTO competition (name, description)
VALUES ('Football Championship', 'A yearly football competition'),
       ('Basketball Tournament', 'Regional basketball tournament'),
       ('Swimming Contest', 'National swimming championship'),
       ('Tennis Open', 'Professional tennis tournament'),
       ('Athletics Meet', 'Track and field competition');

-- Tabla competition_role
INSERT INTO competition_role (role, competition)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5);

-- Tabla pay_method
INSERT INTO pay_method (type, description)
VALUES ('Credit Card', 'Payment via credit card'),
       ('Debit Card', 'Payment via debit card'),
       ('Cash', 'Cash payment'),
       ('Bank Transfer', 'Payment via bank transfer'),
       ('Digital Wallet', 'Payment via digital wallet');

-- Tabla bill
INSERT INTO bill (ruc, date, discount, total, person_id, pay_method_id)
VALUES ('1234567890123', '2024-12-18 15:00:00', 10.50, 89.50, 1, 1),
       ('2345678901234', '2024-12-19 16:00:00', 15.75, 134.25, 2, 2),
       ('3456789012345', '2024-12-20 17:00:00', 20.00, 180.00, 3, 3),
       ('4567890123456', '2024-12-21 18:00:00', 25.25, 224.75, 4, 4),
       ('5678901234567', '2024-12-22 19:00:00', 30.50, 269.50, 5, 5);

-- Tabla classification
INSERT INTO classification (group_name)
VALUES ('Electronics'),
       ('Clothing'),
       ('Food'),
       ('Books'),
       ('Sports Equipment');

-- Tabla supplier
INSERT INTO supplier (ruc, phone, country, email, currency)
VALUES ('9876543210123', '0987654321', 'Ecuador', 'supplier1@example.com', 'USD'),
       ('8765432101234', '0976543210', 'Colombia', 'supplier2@example.com', 'COP'),
       ('7654321012345', '0965432109', 'Peru', 'supplier3@example.com', 'PEN'),
       ('6543210123456', '0954321098', 'Chile', 'supplier4@example.com', 'CLP'),
       ('5432101234567', '0943210987', 'Argentina', 'supplier5@example.com', 'ARS');

-- Tabla product
INSERT INTO product (stock, unit_price, unit, iva, classification_id, supplier_id)
VALUES (50, 199.99, 'Piece', true, 1, 1),
       (75, 49.99, 'Unit', true, 2, 2),
       (100, 9.99, 'Kg', false, 3, 3),
       (25, 29.99, 'Unit', true, 4, 4),
       (60, 89.99, 'Set', true, 5, 5);

-- Tabla bill_item
INSERT INTO bill_item (quantity, price, subtotal, product_id, bill_id)
VALUES (2, 199.99, 399.98, 1, 1),
       (3, 49.99, 149.97, 2, 2),
       (5, 9.99, 49.95, 3, 3),
       (1, 29.99, 29.99, 4, 4),
       (4, 89.99, 359.96, 5, 5);