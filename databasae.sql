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