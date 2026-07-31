
INSERT INTO users (id, name, cpf, phone, email, password_hash, birthdate, address, address_number, complement, city, state_code, zip_code)
VALUES (1, 'Jonathan de Oliveira', '83271856088', '(11) 99999-9999', 'admin@gmail.com', '$2a$10$zo6/NafpOB1q/qtykdAk/OWW/0oaSoUhO2l4N/pqKDrzeBoWO1pJK' , '1990-06-06' , 'Rua Exemplo, 123', '123', 'Casa', 'Cidade Exemplo', 'RS', '90000-000');
INSERT INTO users (id, name, cpf, phone, email, password_hash, birthdate, address, address_number, complement, city, state_code, zip_code)
VALUES (2, 'Atendente', '45546550000', '(54) 99999-9999', 'atendente@gmail.com', '$2a$10$zo6/NafpOB1q/qtykdAk/OWW/0oaSoUhO2l4N/pqKDrzeBoWO1pJK' , '1990-06-06' , 'Rua Exemplo, 123', '123', 'Casa', 'Cidade Exemplo', 'RS', '90000-000');

INSERT INTO roles (id, authority) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, authority) VALUES (2, 'ROLE_ATTENDANT');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
