CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    cpf VARCHAR(11),
    phone VARCHAR(30),
    email VARCHAR(150) NOT NULL,
    password_hash VARCHAR(150),
    birthdate DATE,
    address VARCHAR(255),
    address_number VARCHAR(20),
    complement VARCHAR(100),
    city VARCHAR(100),
    state_code VARCHAR(2),
    zip_code VARCHAR(20),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    authority VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, role_id),

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

