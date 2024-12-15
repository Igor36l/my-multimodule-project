--liquibase formatted sql

--changeset ilebedev:1
ALTER TABLE wishlist RENAME TO bucket;
ALTER TABLE bucket DROP COLUMN created_at;
ALTER TABLE bucket DROP COLUMN updated_at;

--changeset ilebedev:2
ALTER TABLE wishlist_product RENAME TO bucket_product;