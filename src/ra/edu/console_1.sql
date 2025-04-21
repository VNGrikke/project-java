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
     productid int auto_increment primary key,
     name varchar(100) not null ,
     brand varchar(50) ,
     price decimal(10,2) not null ,
     stock int default 0,
     status boolean default true
);
;
insert into products(name, brand, price, stock)
values ('Iphone 14', 'Apple', 30000000, 15 ),
       ('Iphone 15', 'Apple', 20000000, 15),
       ('SamSung s24 ultra', 'SamSung', 32000000, 15),
       ('Redmi note 14 pro 5G', 'Xiaomi', 8700000, 15);

create table customers(
      customer_id int auto_increment primary key ,
      name varchar(100) not null ,
      phone varchar(20) ,
      email varchar(100) ,
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
    productid int,
    quantity int not null ,
    unit_price decimal(10,2) not null ,
    foreign key (invoice_id) references invoices(invoice_id),
    foreign key (productid) references products(productid)
);


DELIMITER //

CREATE PROCEDURE admin_login(
    IN p_username VARCHAR(50),
    IN p_password VARCHAR(255),
    OUT p_account_id INT
)
BEGIN
    SELECT account_id INTO p_account_id
    FROM account
    WHERE username = p_username AND password = p_password;

    IF p_account_id IS NULL THEN
        SET p_account_id = 0;
    END IF;
END //

DELIMITER ;



DELIMITER //

-- Lấy danh sách điện thoại
CREATE PROCEDURE get_phone_list()
BEGIN
    SELECT productid, name, price, brand, stock, status FROM Products;
END //

-- Thêm điện thoại
CREATE PROCEDURE add_phone(
    IN p_name VARCHAR(100),
    IN p_price DOUBLE,
    IN p_brand VARCHAR(50),
    IN p_stock INT
)
BEGIN
    INSERT INTO Products (name, price, brand, stock )
    VALUES (p_name, p_price, p_brand, p_stock);
END //

-- Cập nhật điện thoại
CREATE PROCEDURE update_phone(
    IN p_product_id INT,
    IN p_name VARCHAR(100),
    IN p_price DOUBLE,
    IN p_brand VARCHAR(50),
    IN p_stock INT
)
BEGIN
    UPDATE Products
    SET name = p_name, price = p_price, brand = p_brand, stock = p_stock
    WHERE productid = p_product_id;
END //

-- Xóa điện thoại
CREATE PROCEDURE delete_phone(
    IN p_product_id INT
)
BEGIN
    DELETE FROM Products WHERE productid = p_product_id;
END //

-- Tìm kiếm theo hãng
CREATE PROCEDURE find_phone_by_brand(
    IN p_brand VARCHAR(50)
)
BEGIN
    SELECT productid, name, price, brand, stock, status
    FROM Products
    WHERE brand LIKE CONCAT('%', p_brand, '%');
END //

-- Tìm kiếm theo khoảng giá
CREATE PROCEDURE find_phone_by_price_range(
    IN p_min_price DOUBLE,
    IN p_max_price DOUBLE
)
BEGIN
    SELECT productid, name, price, brand, stock, status
    FROM Products
    WHERE price BETWEEN p_min_price AND p_max_price;
END //

-- Tìm kiếm theo tồn kho
CREATE PROCEDURE find_phone_by_stock(
    IN p_min_stock INT,
    IN p_max_stock INT
)
BEGIN
    SELECT productid, name, price, brand, stock, status
    FROM Products
    WHERE stock BETWEEN p_min_stock AND p_max_stock;
END //

DELIMITER ;


DELIMITER //
-- Lấy danh sách khách hàng
CREATE PROCEDURE get_customer_list()
BEGIN
    SELECT customer_id, name, email, phone, address FROM Customers;
END //

-- Thêm khách hàng
CREATE PROCEDURE add_customer(
    IN p_name VARCHAR(100),
    IN p_email VARCHAR(100),
    IN p_phone VARCHAR(15),
    IN p_address VARCHAR(255)
)
BEGIN
    INSERT INTO Customers (name, email, phone, address)
    VALUES (p_name, p_email, p_phone, p_address);
END //

-- Cập nhật khách hàng
CREATE PROCEDURE update_customer(
    IN p_customer_id INT,
    IN p_name VARCHAR(100),
    IN p_email VARCHAR(100),
    IN p_phone VARCHAR(15),
    IN p_address VARCHAR(255)
)
BEGIN
    UPDATE Customers
    SET name = p_name, email = p_email, phone = p_phone, address = p_address
    WHERE customer_id = p_customer_id;
END //

-- Xóa khách hàng
CREATE PROCEDURE delete_customer(
    IN p_customer_id INT
)
BEGIN
    DELETE FROM Customers WHERE customer_id = p_customer_id;
END //

DELIMITER ;