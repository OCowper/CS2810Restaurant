CREATE IF NOT EXISTS TABLE menu_items (
    id SERIAL PRIMARY KEY,
    dish TEXT NOT NULL,
    description TEXT,
    price MONEY NOT NULL
);
