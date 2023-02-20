CREATE TABLE IF NOT EXISTS menu_items (
    id SERIAL PRIMARY KEY,
    dish text NOT NULL,
    description text,
    price money NOT NULL
);

CREATE TABLE IF NOT EXISTS menu (
    id serial PRIMARY KEY,
    name text NOT NULL
);

CREATE TABLE IF NOT EXISTS on_menu (
    menu int REFERENCES menu,
    item int REFERENCES menu_items
);


CREATE TABLE IF NOT EXISTS staff(
    staff_ID text PRIMARY KEY,
    password text NOT NULL,
    first_Name text NOT NULL,
    last_Name text NOT NULL
);

CREATE TABLE IF NOT EXISTS orders(
    order_Num serial PRIMARY KEY,
    order_Description text NOT NULL,
    table_Num int NOT NULL,
    price money NOT NULL,
    confirm boolean DEFAULT false NOT NULL, 
    waiter_id text REFERENCES staff
);
