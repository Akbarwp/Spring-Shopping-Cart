-- Table Categories
CREATE TABLE categories (
    id                      BIGINT NOT NULL AUTO_INCREMENT,
    name                    VARCHAR(255) NOT NULL,
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE InnoDB;

SELECT * FROM categories;
DESC categories;

-- Table Products
CREATE TABLE products (
    id                      BIGINT NOT NULL AUTO_INCREMENT,
    name                    VARCHAR(255) NOT NULL,
    brand                   VARCHAR(255) NOT NULL,
    price                   DOUBLE(38, 2) NOT NULL,
    inventory               INT NOT NULL,
    description             VARCHAR(255),
    category_id             BIGINT NOT NULL,
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY fk_categories_products (category_id) REFERENCES categories (id)
) ENGINE InnoDB;

SELECT * FROM products;
DESC products;

-- Table Images
CREATE TABLE images (
    id                      BIGINT NOT NULL AUTO_INCREMENT,
    file_name               VARCHAR(255) NOT NULL,
    file_type               VARCHAR(255) NOT NULL,
    image                   MEDIUMBLOB NOT NULL,
    download_url            VARCHAR(255) NOT NULL,
    product_id              BIGINT NOT NULL,
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY fk_products_images (product_id) REFERENCES products (id)
) ENGINE InnoDB;

SELECT * FROM images;
DESC images;

-- Table Carts
CREATE TABLE carts (
    id                      BIGINT NOT NULL AUTO_INCREMENT,
    total_amount            DOUBLE(38, 2) NOT NULL DEFAULT(0),
    user_id                 BIGINT NOT NULL,
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY fk_users_carts (user_id) REFERENCES users (id)
) ENGINE InnoDB;

SELECT * FROM carts;
DESC carts;

-- Table Cart Items
CREATE TABLE cart_items (
    id                      BIGINT NOT NULL AUTO_INCREMENT,
    quantity                INT NOT NULL,
    unit_price              DOUBLE(38, 2) NOT NULL,
    total_price             DOUBLE(38, 2) NOT NULL,
    product_id              BIGINT NOT NULL,
    cart_id                 BIGINT NOT NULL,
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY fk_products_cart_items (product_id) REFERENCES products (id),
    FOREIGN KEY fk_carts_cart_items (cart_id) REFERENCES carts (id)
) ENGINE InnoDB;

SELECT * FROM cart_items;
DESC cart_items;

-- Table Orders
CREATE TABLE orders (
    id                      BIGINT NOT NULL AUTO_INCREMENT,
    order_date              DATE NOT NULL,
    total_amount            DOUBLE(38, 2) NOT NULL DEFAULT(0),
    order_status            ENUM('PENDING', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELLED') NOT NULL DEFAULT('PENDING'),
    user_id                 BIGINT NOT NULL,
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY fk_users_orders (user_id) REFERENCES users (id)
) ENGINE InnoDB;

SELECT * FROM orders;
DESC orders;

-- Table Order Items
CREATE TABLE order_items (
    id                      BIGINT NOT NULL AUTO_INCREMENT,
    quantity                INT NOT NULL,
    price                   DOUBLE(38, 2) NOT NULL,
    total_price             DOUBLE(38, 2) NOT NULL,
    product_id              BIGINT NOT NULL,
    order_id                BIGINT NOT NULL,
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY fk_products_order_items (product_id) REFERENCES products (id),
    FOREIGN KEY fk_orders_order_items (order_id) REFERENCES orders (id)
) ENGINE InnoDB;

SELECT * FROM order_items;
DESC order_items;

-- Table Users
CREATE TABLE users (
    id                      BIGINT NOT NULL AUTO_INCREMENT,
    email                   VARCHAR(255) NOT NULL UNIQUE,
    password                VARCHAR(255) NOT NULL,
    name                    VARCHAR(255) NOT NULL,
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE InnoDB;

SELECT * FROM users;
DESC users;
