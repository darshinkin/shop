drop table if exists PRODUCT;

create table PRODUCT (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR(255)
);