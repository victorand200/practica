CREATE TABLE IF NOT EXISTS person
(
    person_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(60)  NOT NULL,
    last_name  VARCHAR(60)  NOT NULL,
    dni       VARCHAR(10)  UNIQUE NOT NULL,
    phone     VARCHAR(10)  NOT NULL,
    email     VARCHAR(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS user
(
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    person_id BIGINT NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person(person_id)
);

CREATE TABLE  IF NOT EXISTS role
(
    role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role    VARCHAR(60) NOT NULL,
    state   BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role
(
    user BIGINT NOT NULL,
    role BIGINT NOT NULL,
    PRIMARY KEY (user, role),
    FOREIGN KEY (user) REFERENCES user(user_id),
    FOREIGN KEY (role) REFERENCES role(role_id)
);

CREATE TABLE IF NOT EXISTS competition
(
    competition_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(60) NOT NULL,
    description    VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS competition_role
(
    role BIGINT NOT NULL,
    competition BIGINT NOT NULL,
    PRIMARY KEY (role, competition),
    FOREIGN KEY (role) REFERENCES role(role_id),
    FOREIGN KEY (competition) REFERENCES competition(competition_id)
);

CREATE TABLE IF NOT EXISTS pay_method
(
    pay_method_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(60) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS bill
(
    bill_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ruc VARCHAR(15) NOT NULL,
    date DATETIME NOT NULL,
    discount DECIMAL(10,2) NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    person_id BIGINT NOT NULL,
    pay_method_id BIGINT NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person(person_id),
    FOREIGN KEY (pay_method_id) REFERENCES pay_method(pay_method_id)
);

CREATE TABLE IF NOT EXISTS classification
(
    classification_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    group_name VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS supplier
(
    supplier_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ruc VARCHAR(15) UNIQUE NOT NULL,
    phone VARCHAR(10) NOT NULL,
    country VARCHAR(60) NOT NULL,
    email VARCHAR(120) NOT NULL,
    currency VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    stock INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    unit VARCHAR(60) NOT NULL,
    iva BOOLEAN NOT NULL,
    classification_id BIGINT NOT NULL,
    supplier_id BIGINT NOT NULL,
    FOREIGN KEY (classification_id) REFERENCES classification(classification_id),
    FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id)
);

CREATE TABLE IF NOT EXISTS bill_item
(
    bill_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    product_id BIGINT NOT NULL,
    bill_id BIGINT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(product_id),
    FOREIGN KEY (bill_id) REFERENCES bill(bill_id)
);