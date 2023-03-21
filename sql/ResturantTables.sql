CREATE TABLE IF NOT EXISTS Staff(
    staff_ID varchar(15) NOT NULL,
    password varchar(60) NOT NULL,
    first_name varchar(20) NOT NULL,
    last_name varchar(20) NOT NULL, 
    job_title varchar(15) NOT NULL, -- waiter, kitchen staff, admin, etc.
    email varchar(30),
    phone varchar(15),
    home_address varchar(15),
    primary key (staff_ID)
);

CREATE TABLE IF NOT EXISTS Items(
    item_id varchar(10) NOT NULL,
    item_name varchar(30) NOT NULL,
    price numeric(5, 2) NOT NULL, 
    item_description varchar(1000),
    ingredients varchar(1000) NOT NULL,
    calories smallint NOT NULL,  -- kCal
    item_category varchar(20) NOT NULL, 
    available boolean NOT NULL, 
    image_path varchar(50),
    primary key (item_id)
);

CREATE TYPE status AS ENUM ('recieved', 'confirmed', 'being prepared', 'ready', 'settled', 'paid'); --combine ready and received?
CREATE TABLE IF NOT EXISTS Orders(
    order_num numeric(6, 0) NOT NULL,
    order_description varchar(100),
    table_num numeric(2, 0) NOT NULL,
    price numeric(9, 2) NOT NULL,
    order_status status,
    waiter_id varchar(15),	 ---- do we need to associate waiter with an order ?????
    foreign key (waiter_id) references Staff(staff_id),
    primary key (order_num)
);


-- do we need to keep done orders separately?
CREATE TABLE IF NOT EXISTS DoneOrders(
    order_num numeric(6, 0),
    cancelled boolean,
    primary key (order_num)
);

CREATE TABLE IF NOT EXISTS Notifications (
    table_Num numeric(5,0),
    request_Type varchar(30),
    dealt_with boolean
);

