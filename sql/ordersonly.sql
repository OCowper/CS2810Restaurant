CREATE TABLE IF NOT EXISTS Orders(
    order_Num varchar(15),
    order_Description varchar(100),
    table_Num varchar(15),
    price numeric(1000, 1),
    confirm boolean, 
    primary key (order_Num));
