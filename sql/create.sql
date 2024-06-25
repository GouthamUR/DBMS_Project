CREATE TABLE customer
(
    customer_id int PRIMARY KEY,
    customer_name varchar(30) NOT NULL,
    vehicle_id int 
);

CREATE TABLE inventory
(
    vehicle_id int PRIMARY KEY,
    quantity int NOT NULL
);

CREATE TABLE vehicle 
(
    vehicle_id int PRIMARY KEY,
    brand varchar(30),
    colour varchar(30),
    price int NOT NULL
);

CREATE TABLE test_drive_inventory
(
    vehicle_id int,
    customer_id int PRIMARY KEY
);

CREATE TABLE rating
(
    sale_id int PRIMARY KEY,
    employee_id int,
    rating int NOT NULL
);

CREATE TABLE employee
(
    employee_id int PRIMARY KEY,
    employee_name varchar(30) NOT NULL,
    salary int NOT NULL
);

CREATE TABLE sale
(
    sale_id int PRIMARY KEY,
    vehicle_id int,
    customer_id int,
    employee_id int,
    date_of_sale date
);

CREATE TABLE custpassword
(
    customer_id int PRIMARY KEY,
    pass varchar(20)  
);

CREATE TABLE emp_password(
    employee_id int PRIMARY KEY,
    pass varchar(20)
);