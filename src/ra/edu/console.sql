create database manager_phone_store;

use manager_phone_store;

create table account(
    account_id int auto_increment primary key ,
    username varchar(50) not null,
    password varchar(50) not null
);

insert into account(username, password)
values ('vuong1511', 'vuong12345');

create table products(
    product_id int auto_increment primary key,
    name varchar(100) not null ,
    brand varchar(50) ,
    price decimal(10,2) not null ,
    stock int default 0
);

insert into products(name, brand, price, stock)
values ("Iphone14", "Apple", 30000000, 15),
       ("Iphone15", "Apple", 20000000, 15),
       ("SamSung s24 ultra ", "SamSung", 32000000, 15),
       ("Redmi note 14 pro 5G", "Xiaomi", 8700000, 15);

create table customers(
    customer_id int auto_increment primary key ,
    name varchar(100) not null ,
    phone varchar(20) ,
    eamil varchar(100) ,
    address varchar(255)
);

create table invoices(
    invoice_id int auto_increment primary key ,
    customer_id int,
    foreign key (customer_id) references customers(customer_id),
    invoice_date date,
    total decimal(10,2)
);

create table invoice_items(
    item_id int auto_increment primary key,
    invoice_id int,
    product_id int,
    quantity int not null ,
    unit_price decimal(10,2) not null ,
    foreign key (invoice_id) references invoices(invoice_id),
    foreign key (product_id) references products(product_id)

);


DELIMITER //

CREATE PROCEDURE admin_login (
    IN p_username VARCHAR(50),
    IN p_password VARCHAR(50),
    OUT p_result INT
)
BEGIN
    SELECT COUNT(*) INTO p_result
    FROM account
    WHERE username = p_username AND password = p_password;
END //

DELIMITER ;



DELIMITER //

CREATE PROCEDURE get_phone_list ()
BEGIN
    SELECT product_id, name, price, brand, stock FROM products;
END //

CREATE PROCEDURE add_phone (
    IN p_name VARCHAR(100),
    IN p_price DECIMAL(10,2),
    IN p_brand VARCHAR(100),
    IN p_stock INT
)
BEGIN
    INSERT INTO products (name, price, brand, stock)
    VALUES (p_name, p_price, p_brand, p_stock);
END //

DELIMITER ;