CREATE TABLE IF NOT EXISTS node (
    id SERIAL PRIMARY KEY,
    created_date TIMESTAMP,
    updated_date TIMESTAMP,
    description VARCHAR(50) NOT NULL,
    ip VARCHAR(50) NOT NULL,
    port VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL
);