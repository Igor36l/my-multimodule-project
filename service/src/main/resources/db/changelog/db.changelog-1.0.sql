--liquibase formatted sql

--changeset ilebedev:1
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       phone VARCHAR(20),
                       address VARCHAR(255),
                       role VARCHAR(10) DEFAULT USER,
                       gender VARCHAR(10) NOT NULL,
                       birthday DATE,
                       is_seller BOOLEAN DEFAULT FALSE,
                       is_active BOOLEAN DEFAULT TRUE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--changeset ilebedev:2
CREATE TABLE seller (
                        id BIGSERIAL PRIMARY KEY,
                        user_id BIGINT UNIQUE,
                        organization_name VARCHAR(255) NOT NULL,
                        organization_description TEXT,
                        organization_address VARCHAR(255),
                        organization_phone VARCHAR(20),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);

--changeset ilebedev:3
CREATE TABLE category (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          parent_category_id BIGINT,
                          FOREIGN KEY (parent_category_id) REFERENCES category(id)
);

--changeset ilebedev:4
CREATE TABLE product (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         price DECIMAL(10, 2) NOT NULL,
                         stock INT NOT NULL,
                         category_id BIGINT,
                         seller_id BIGINT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (category_id) REFERENCES category(id),
                         FOREIGN KEY (seller_id) REFERENCES seller(id)
);

--changeset ilebedev:5
CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        user_id BIGINT,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        status VARCHAR(50) NOT NULL,
                        total_amount DECIMAL(10, 2) NOT NULL,
                        shipping_address VARCHAR(255),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);

--changeset ilebedev:6
CREATE TABLE review (
                        id BIGSERIAL PRIMARY KEY,
                        user_id BIGINT,
                        product_id BIGINT,
                        rating INT NOT NULL,
                        comment TEXT,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES users(id),
                        FOREIGN KEY (product_id) REFERENCES product(id)
);

--changeset ilebedev:7
CREATE TABLE payment (
                         id BIGSERIAL PRIMARY KEY,
                         order_id BIGINT UNIQUE,
                         payment_method VARCHAR(50) NOT NULL,
                         amount DECIMAL(10, 2) NOT NULL,
                         payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         status VARCHAR(50) NOT NULL,
                         FOREIGN KEY (order_id) REFERENCES orders(id)
);

--changeset ilebedev:8
CREATE TABLE product_image (
                               id BIGSERIAL PRIMARY KEY,
                               product_id BIGINT,
                               image_url VARCHAR(255) NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (product_id) REFERENCES product(id)
);

--changeset ilebedev:9
CREATE TABLE wishlist (
                          id BIGSERIAL PRIMARY KEY,
                          user_id BIGINT,
                          product_id BIGINT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES users(id),
                          FOREIGN KEY (product_id) REFERENCES product(id)
);

--changeset ilebedev:10
CREATE TABLE product_category (
                                  id BIGSERIAL PRIMARY KEY ,
                                  product_id BIGINT,
                                  category_id BIGINT,
                                  FOREIGN KEY (product_id) REFERENCES product(id),
                                  FOREIGN KEY (category_id) REFERENCES category(id)
);

--changeset ilebedev:11
CREATE TABLE wishlist_product (
                                  id BIGSERIAL PRIMARY KEY,
                                  wishlist_id BIGINT REFERENCES wishlist(id),
                                  product_id BIGINT REFERENCES product(id)
)
