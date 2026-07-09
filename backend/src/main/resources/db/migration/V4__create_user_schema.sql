CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    cpf VARCHAR(11),
    phone VARCHAR(30),
    email VARCHAR(150),
    password VARCHAR(150),
    birthdate DATE,
    address VARCHAR(255),
    number VARCHAR(20),
    complement VARCHAR(100),
    city VARCHAR(100),
    state VARCHAR(2),
    zip_code VARCHAR(20),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    authority VARCHAR(150) NOT NULL,
);
