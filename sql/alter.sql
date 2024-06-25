ALTER TABLE customer
    add FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id);

ALTER TABLE rating
    add FOREIGN KEY (employee_id) REFERENCES employee(employee_id);

ALTER TABLE rating
    add FOREIGN KEY (sale_id) REFERENCES sale(sale_id);

ALTER TABLE inventory
    add FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id);

ALTER TABLE test_drive_inventory
    add FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id);

ALTER TABLE test_drive_inventory
    add FOREIGN KEY (customer_id) REFERENCES customer(customer_id);

ALTER TABLE sale
    add FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id);

ALTER TABLE sale 
    add FOREIGN KEY (customer_id) REFERENCES customer(customer_id);

ALTER TABLE sale 
    add FOREIGN KEY (employee_id) REFERENCES employee(employee_id);

ALTER TABLE emp_password
    add FOREIGN KEY (employee_id) REFERENCES employee(employee_id);

ALTER TABLE custpassword 
   add FOREIGN KEY (customer_id) REFERENCES customer(customer_id);