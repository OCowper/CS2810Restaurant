CREATE TABLE IF NOT EXISTS menu_items (
    id SERIAL PRIMARY KEY,
    dish TEXT NOT NULL,
    description TEXT,
    price MONEY NOT NULL
);

CREATE TABLE IF NOT EXISTS menu (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS on_menu (
    menu INT REFERENCES menu,
    item INT REFERENCES menu_items
);