--liquibase formatted sql

--changeset ilebedev:1
ALTER TABLE bucket_product DROP CONSTRAINT wishlist_product_wishlist_id_fkey;
ALTER TABLE bucket_product RENAME COLUMN wishlist_id TO bucket_id;
ALTER TABLE bucket_product ADD CONSTRAINT bucket_product_bucket_id_fkey FOREIGN KEY (bucket_id) REFERENCES bucket(id);