create database manager_phone_store;

use manager_phone_store;

create table products(
    product_id int auto_increment primary key,
    name varchar(100) not null ,
    brand varchar(50) ,
    price decimal(10,2) not null ,
    stock int default 0
);

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
