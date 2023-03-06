
CREATE TABLE IF NOT EXISTS Staff(
    staff_ID varchar(15) NOT NULL,
    password varchar(15) NOT NULL,
    first_Name varchar(15) NOT NULL,
    last_Name varchar(15) NOT NULL, 
    job_Title varchar(15) NOT NULL,
    email varchar(15) NOT NULL,
    phone_Number varchar(15),
    address varchar(15),
    primary key (staff_ID)
);


CREATE TABLE IF NOT EXISTS Bookings(
    booking_Num varchar(15),
    guest_Amount numeric(30, 1),
    host_Email varchar(15),
    booking_Time varchar(15),
    booking_Date varchar(15)
    primary key (booking_Num)
);

CREATE TABLE IF NOT EXISTS Covers(
    booking_Num varchar(15),
    cover_Date varchar(15),
    cover_Amount numeric(1000, 1),
    foreign key (booking_Num) references Bookings(booking_Num)
);

CREATE TABLE IF NOT EXISTS Menu(
    item_num varchar(15),
    item_name varchar(30),
    price numeric(5, 2), 
    item_description varchar(1000),
    ingredients varchar(1000),
    calories smallint,
    preparation_time smallint,
    item_category varchar(100), 
    primary key (item_num)
);

