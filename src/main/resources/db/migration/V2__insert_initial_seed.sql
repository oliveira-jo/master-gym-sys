INSERT INTO modalities (name) VALUES
('Musculação'),
('Funcional'),
('Jiu-Jitsu'),
('Muay Thai'),
('Boxe'),
('CrossFit'),
('Pilates'),
('Yoga');

INSERT INTO subscriptions (modalities_id, name, price) VALUES
(1, 'Mensal', 150.00),
(1, 'Trimestral', 400.00),
(1, 'Anual', 1500.00),
(2, 'Mensal', 120.00),
(2, 'Trimestral', 350.00),
(2, 'Anual', 1200.00),
(3, 'Mensal', 100.00),
(3, 'Trimestral', 280.00),
(3, 'Anual', 1000.00),
(4, 'Mensal', 110.00),
(4, 'Trimestral', 300.00),
(4, 'Anual', 1100.00),
(5, 'Mensal', 90.00),
(5, 'Trimestral', 250.00),
(5, 'Anual', 900.00),
(6, 'Mensal', 130.00),
(6, 'Trimestral', 350.00),
(6, 'Anual', 1300.00),
(7, 'Mensal', 80.00),
(7, 'Trimestral', 220.00),
(7, 'Anual', 800.00),
(8, 'Mensal', 70.00),
(8, 'Trimestral', 200.00),
(8, 'Anual', 700.00);

INSERT INTO graduations (modalities_id, name) 
SELECT id, 'Branca' FROM modalities WHERE name = 'Jiu-Jitsu';
INSERT INTO graduations (modalities_id, name) 
SELECT id, 'Azul' FROM modalities WHERE name = 'Jiu-Jitsu';
INSERT INTO graduations (modalities_id, name) 
SELECT id, 'Roxa' FROM modalities WHERE name = 'Jiu-Jitsu';
INSERT INTO graduations (modalities_id, name) 
SELECT id, 'Marrom' FROM modalities WHERE name = 'Jiu-Jitsu';
INSERT INTO graduations (modalities_id, name) 
SELECT id, 'Preta' FROM modalities WHERE name = 'Jiu-Jitsu';  
