CREATE TABLE IF NOT EXISTS Orders(
    order_Num varchar(15),
    order_Description varchar(100),
    table_Num varchar(15),
    price numeric(1000, 1),
    confirm boolean, 
    waiter_id varchar(15),	 
    foreign key (waiter_id) references Staff(staff_id),
    primary key (order_Num));
