CREATE TABLE students (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    birthdate DATE,
    genre VARCHAR(1) CHECK (genre IN ('M', 'F')),
    phone VARCHAR(30),
    email VARCHAR(150),
    observations TEXT,
    address VARCHAR(255),
    number VARCHAR(20),
    complement VARCHAR(100),
    city VARCHAR(100),
    state VARCHAR(2),
    zip_code VARCHAR(20),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE modalities (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE graduations (
    id BIGSERIAL PRIMARY KEY,
    modalities_id BIGINT NOT NULL REFERENCES modalities(id),
    name VARCHAR(100) NOT NULL,
    UNIQUE (modalities_id, name)
);

CREATE TABLE subscriptions (
    id BIGSERIAL PRIMARY KEY,
    modalities_id BIGINT NOT NULL REFERENCES modalities(id),
    name VARCHAR(100) NOT NULL,
    price NUMERIC(10, 2) NOT NULL CHECK(price >= 0),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    UNIQUE (modalities_id, name)
);

CREATE TABLE enrollments (
    id BIGSERIAL PRIMARY KEY,
    students_id BIGINT NOT NULL REFERENCES students(id),
    enrollment_date DATE NOT NULL DEFAULT CURRENT_DATE,
    due_day INTEGER NOT NULL CHECK(due_day BETWEEN 1 AND 31),
    closing_date DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    CHECK (status IN ('ACTIVE', 'INACTIVE', 'CANCELED'))
);


CREATE TABLE enrollments_modalities (
    enrollments_id BIGINT NOT NULL REFERENCES enrollments(id) ON DELETE CASCADE,
    modalities_id BIGINT NOT NULL REFERENCES modalities(id) ON DELETE CASCADE,
    graduations_id BIGINT NOT NULL REFERENCES graduations(id) ON DELETE CASCADE,
    subscriptions_id BIGINT NOT NULL REFERENCES subscriptions(id) ON DELETE CASCADE,
    start_date DATE NOT NULL DEFAULT CURRENT_DATE,
    end_date DATE,
    UNIQUE (enrollments_id, modalities_id)
);

CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    enrollments_id BIGINT NOT NULL REFERENCES enrollments(id),
    due_date DATE NOT NULL,
    amount NUMERIC(10, 2) NOT NULL CHECK(amount >= 0),
    payment_date TIMESTAMP,
    canceled_date DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'OPEN',
    CHECK (status IN ('OPEN', 'PAID', 'CANCELED', 'OVERDUE')),
    UNIQUE (enrollments_id, due_date)
);


CREATE TABLE attendances (
    id BIGSERIAL PRIMARY KEY,
    enrollments_id BIGINT NOT NULL REFERENCES enrollments(id),
    attendance_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    end_date TIMESTAMP
);