CREATE TABLE IF NOT EXISTS Orders(
    order_Num numeric(5, 0),
    order_Description varchar(100),
    table_Num numeric(5, 0),
    price numeric(1000, 1),
    confirm boolean, 
    waiter_id varchar(15),	 
    foreign key (waiter_id) references Staff(staff_id),
    primary key (order_Num));
    
CREATE TABLE IF NOT EXISTS DoneOrders(
    order_Num numeric(5, 0),
    cancelled boolean,
    primary key (order_Num));
