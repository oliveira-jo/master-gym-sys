-- =====================================================
-- V3__insert_seed_data.sql
-- Dados fictícios para demonstração do sistema
-- =====================================================
--
-- Objetivos:
-- - Popular o dashboard
-- - Demonstrar alunos por modalidade
-- - Demonstrar status das matrículas
-- - Demonstrar status dos pagamentos
-- - Demonstrar novos alunos por mês
-- - Demonstrar faturamento mensal
-- - Demonstrar pagamentos abertos e vencidos
--
-- Este arquivo depende do V1 e do V2.
-- =====================================================


-- =====================================================
-- 1. ALUNOS
-- =====================================================

INSERT INTO students (
    name,
    birthdate,
    gender,
    phone,
    email,
    cpf,
    observation,
    address,
    address_number,
    complement,
    city,
    state_code,
    zip_code,
    created_at
)
VALUES
(
    'João Silva',
    '1990-06-06',
    'M',
    '(54) 99911-1001',
    'joao.silva@email.com',
    '10000000001',
    'Objetivo: ganho de massa muscular',
    'Rua das Hortênsias',
    '120',
    NULL,
    'Canela',
    'RS',
    '95680-000',
    CURRENT_TIMESTAMP - INTERVAL '8 months'
),
(
    'Maria Oliveira',
    '1985-08-15',
    'F',
    '(54) 99911-1002',
    'maria.oliveira@email.com',
    '10000000002',
    'Prefere treinar no período da manhã',
    'Rua Borges de Medeiros',
    '245',
    'Apto 202',
    'Gramado',
    'RS',
    '95670-000',
    CURRENT_TIMESTAMP - INTERVAL '8 months'
),
(
    'Carlos Santos',
    '1992-03-20',
    'M',
    '(54) 99911-1003',
    'carlos.santos@email.com',
    '10000000003',
    'Aluno de Jiu-Jitsu',
    'Rua São Pedro',
    '310',
    NULL,
    'Canela',
    'RS',
    '95680-000',
    CURRENT_TIMESTAMP - INTERVAL '7 months'
),
(
    'Ana Paula Martins',
    '1994-02-15',
    'F',
    '(54) 99911-1004',
    'ana.martins@email.com',
    '10000000004',
    'Objetivo: condicionamento físico',
    'Avenida Central',
    '450',
    NULL,
    'Gramado',
    'RS',
    '95670-000',
    CURRENT_TIMESTAMP - INTERVAL '7 months'
),
(
    'Lucas Ferreira',
    '1998-07-22',
    'M',
    '(54) 99911-1005',
    'lucas.ferreira@email.com',
    '10000000005',
    'Treino de hipertrofia',
    'Rua João Pessoa',
    '98',
    'Casa 2',
    'Canela',
    'RS',
    '95680-000',
    CURRENT_TIMESTAMP - INTERVAL '6 months'
),
(
    'Fernanda Costa',
    '1989-11-08',
    'F',
    '(54) 99911-1006',
    'fernanda.costa@email.com',
    '10000000006',
    'Pratica Pilates duas vezes por semana',
    'Rua do Bosque',
    '155',
    NULL,
    'Gramado',
    'RS',
    '95670-000',
    CURRENT_TIMESTAMP - INTERVAL '6 months'
),
(
    'Rafael Almeida',
    '1995-04-19',
    'M',
    '(54) 99911-1007',
    'rafael.almeida@email.com',
    '10000000007',
    'Aluno de Jiu-Jitsu',
    'Rua das Flores',
    '220',
    'Apto 104',
    'Canela',
    'RS',
    '95680-000',
    CURRENT_TIMESTAMP - INTERVAL '5 months'
),
(
    'Juliana Souza',
    '1992-09-30',
    'F',
    '(54) 99911-1008',
    'juliana.souza@email.com',
    '10000000008',
    'Objetivo: qualidade de vida',
    'Rua Bela Vista',
    '75',
    NULL,
    'Gramado',
    'RS',
    '95670-000',
    CURRENT_TIMESTAMP - INTERVAL '5 months'
),
(
    'Bruno Rodrigues',
    '1987-01-12',
    'M',
    '(54) 99911-1009',
    'bruno.rodrigues@email.com',
    '10000000009',
    'Treino funcional',
    'Rua dos Pinheiros',
    '180',
    NULL,
    'Canela',
    'RS',
    '95680-000',
    CURRENT_TIMESTAMP - INTERVAL '4 months'
),
(
    'Camila Rocha',
    '1999-06-25',
    'F',
    '(54) 99911-1010',
    'camila.rocha@email.com',
    '10000000010',
    'Iniciante no Pilates',
    'Rua Três Coroas',
    '90',
    NULL,
    'Gramado',
    'RS',
    '95670-000',
    CURRENT_TIMESTAMP - INTERVAL '4 months'
),
(
    'Diego Pereira',
    '1991-12-03',
    'M',
    '(54) 99911-1011',
    'diego.pereira@email.com',
    '10000000011',
    'Objetivo: emagrecimento',
    'Rua do Lago',
    '340',
    'Casa',
    'Canela',
    'RS',
    '95680-000',
    CURRENT_TIMESTAMP - INTERVAL '3 months'
),
(
    'Patrícia Lima',
    '1986-03-17',
    'F',
    '(54) 99911-1012',
    'patricia.lima@email.com',
    '10000000012',
    'Pratica Yoga',
    'Avenida das Araucárias',
    '510',
    NULL,
    'Gramado',
    'RS',
    '95670-000',
    CURRENT_TIMESTAMP - INTERVAL '3 months'
),
(
    'Gabriel Mendes',
    '2000-08-11',
    'M',
    '(54) 99911-1013',
    'gabriel.mendes@email.com',
    '10000000013',
    'Aluno de Muay Thai',
    'Rua das Acácias',
    '210',
    NULL,
    'Canela',
    'RS',
    '95680-000',
    CURRENT_TIMESTAMP - INTERVAL '2 months'
),
(
    'Larissa Gomes',
    '1996-05-09',
    'F',
    '(54) 99911-1014',
    'larissa.gomes@email.com',
    '10000000014',
    'Treino de força',
    'Rua São José',
    '130',
    NULL,
    'Gramado',
    'RS',
    '95670-000',
    CURRENT_TIMESTAMP - INTERVAL '2 months'
),
(
    'Matheus Carvalho',
    '1993-10-28',
    'M',
    '(54) 99911-1015',
    'matheus.carvalho@email.com',
    '10000000015',
    'Pratica CrossFit',
    'Rua dos Imigrantes',
    '275',
    'Apto 301',
    'Canela',
    'RS',
    '95680-000',
    CURRENT_TIMESTAMP - INTERVAL '50 days'
),
(
    'Beatriz Nunes',
    '1997-01-31',
    'F',
    '(54) 99911-1016',
    'beatriz.nunes@email.com',
    '10000000016',
    'Nova aluna',
    'Rua da Montanha',
    '60',
    NULL,
    'Gramado',
    'RS',
    '95670-000',
    CURRENT_TIMESTAMP - INTERVAL '40 days'
),
(
    'Thiago Moreira',
    '1988-07-14',
    'M',
    '(54) 99911-1017',
    'thiago.moreira@email.com',
    '10000000017',
    'Treino de musculação',
    'Rua das Hortênsias',
    '410',
    NULL,
    'Canela',
    'RS',
    '95680-000',
    CURRENT_TIMESTAMP - INTERVAL '30 days'
),
(
    'Renata Alves',
    '1990-11-20',
    'F',
    '(54) 99911-1018',
    'renata.alves@email.com',
    '10000000018',
    'Pilates duas vezes por semana',
    'Rua São Pedro',
    '88',
    'Casa',
    'Gramado',
    'RS',
    '95670-000',
    CURRENT_TIMESTAMP - INTERVAL '20 days'
),
(
    'Eduardo Barros',
    '1994-04-05',
    'M',
    '(54) 99911-1019',
    'eduardo.barros@email.com',
    '10000000019',
    'Aluno novo',
    'Rua do Bosque',
    '300',
    NULL,
    'Canela',
    'RS',
    '95680-000',
    CURRENT_TIMESTAMP - INTERVAL '12 days'
),
(
    'Vanessa Ribeiro',
    '1995-09-18',
    'F',
    '(54) 99911-1020',
    'vanessa.ribeiro@email.com',
    '10000000020',
    'Objetivo: condicionamento e mobilidade',
    'Avenida Central',
    '620',
    'Apto 405',
    'Gramado',
    'RS',
    '95670-000',
    CURRENT_TIMESTAMP - INTERVAL '5 days'
);


-- =====================================================
-- 2. MATRÍCULAS
-- =====================================================

INSERT INTO enrollments (
    student_id,
    enrollment_date,
    due_day,
    status
)
SELECT
    id,
    created_at::DATE,
    CASE
        WHEN id % 4 = 0 THEN 5
        WHEN id % 4 = 1 THEN 10
        WHEN id % 4 = 2 THEN 15
        ELSE 20
    END,
    CASE
        WHEN email IN (
            'bruno.rodrigues@email.com',
            'renata.alves@email.com'
        ) THEN 'INACTIVE'
        WHEN email = 'matheus.carvalho@email.com'
        THEN 'CANCELED'
        ELSE 'ACTIVE'
    END
FROM students;


-- =====================================================
-- 3. MODALIDADES DAS MATRÍCULAS
-- =====================================================

-- João -> Musculação
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Musculação'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'joao.silva@email.com';

-- Maria -> Funcional
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Funcional'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'maria.oliveira@email.com';

-- Carlos -> Jiu-Jitsu
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    g.id,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Jiu-Jitsu'
JOIN graduations g
    ON g.modality_id = m.id
    AND g.name = 'Azul'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'carlos.santos@email.com';

-- Ana -> Musculação
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Musculação'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'ana.martins@email.com';

-- Lucas -> CrossFit
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'CrossFit'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'lucas.ferreira@email.com';

-- Fernanda -> Pilates
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Pilates'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'fernanda.costa@email.com';

-- Rafael -> Jiu-Jitsu
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    g.id,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Jiu-Jitsu'
JOIN graduations g
    ON g.modality_id = m.id
    AND g.name = 'Branca'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'rafael.almeida@email.com';

-- Juliana -> Funcional
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Funcional'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'juliana.souza@email.com';

-- Bruno -> Boxe
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Boxe'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'bruno.rodrigues@email.com';

-- Camila -> Pilates
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Pilates'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'camila.rocha@email.com';

-- Diego -> Musculação
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Musculação'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'diego.pereira@email.com';

-- Patrícia -> Yoga
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Yoga'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'patricia.lima@email.com';

-- Gabriel -> Muay Thai
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Muay Thai'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'gabriel.mendes@email.com';

-- Larissa -> Musculação
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Musculação'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Trimestral'
WHERE st.email = 'larissa.gomes@email.com';

-- Matheus -> CrossFit
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'CrossFit'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'matheus.carvalho@email.com';

-- Beatriz -> Funcional
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Funcional'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'beatriz.nunes@email.com';

-- Thiago -> Musculação
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Musculação'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'thiago.moreira@email.com';

-- Renata -> Pilates
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Pilates'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'renata.alves@email.com';

-- Eduardo -> Jiu-Jitsu
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    g.id,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Jiu-Jitsu'
JOIN graduations g
    ON g.modality_id = m.id
    AND g.name = 'Branca'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'eduardo.barros@email.com';

-- Vanessa -> Yoga
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Yoga'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'vanessa.ribeiro@email.com';


-- =====================================================
-- 4. SEGUNDA MODALIDADE PARA ALGUNS ALUNOS
-- =====================================================

-- João -> Funcional
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Funcional'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'joao.silva@email.com';

-- Ana -> Pilates
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Pilates'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'ana.martins@email.com';

-- Lucas -> Musculação
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Musculação'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'lucas.ferreira@email.com';

-- Gabriel -> Musculação
INSERT INTO enrollments_modalities (
    enrollment_id,
    modality_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    e.id,
    m.id,
    NULL,
    s.id,
    e.enrollment_date
FROM enrollments e
JOIN students st ON st.id = e.student_id
JOIN modalities m ON m.name = 'Musculação'
JOIN subscriptions s
    ON s.modality_id = m.id
    AND s.name = 'Mensal'
WHERE st.email = 'gabriel.mendes@email.com';

-- =====================================================
-- 5. PAGAMENTOS PAGOS DOS MESES ANTERIORES
-- =====================================================

-- 5 meses atrás
INSERT INTO payments (
    enrollment_id,
    due_date,
    amount,
    payment_amount,
    payment_date,
    observation,
    status
)
SELECT
    e.id,
    (
        date_trunc('month', CURRENT_DATE)
        - INTERVAL '5 months'
        + (e.due_day - 1) * INTERVAL '1 day'
    )::DATE,
    SUM(s.price),
    SUM(s.price),
    date_trunc('month', CURRENT_TIMESTAMP)
        - INTERVAL '5 months'
        + (e.due_day - 2) * INTERVAL '1 day',
    'Pagamento realizado',
    'PAID'
FROM enrollments e
JOIN enrollments_modalities em
    ON em.enrollment_id = e.id
JOIN subscriptions s
    ON s.id = em.subscription_id
WHERE e.status = 'ACTIVE'
GROUP BY e.id, e.due_day;


-- 4 meses atrás
INSERT INTO payments (
    enrollment_id,
    due_date,
    amount,
    payment_amount,
    payment_date,
    observation,
    status
)
SELECT
    e.id,
    (
        date_trunc('month', CURRENT_DATE)
        - INTERVAL '4 months'
        + (e.due_day - 1) * INTERVAL '1 day'
    )::DATE,
    SUM(s.price),
    SUM(s.price),
    date_trunc('month', CURRENT_TIMESTAMP)
        - INTERVAL '4 months'
        + (e.due_day - 2) * INTERVAL '1 day',
    'Pagamento realizado',
    'PAID'
FROM enrollments e
JOIN enrollments_modalities em
    ON em.enrollment_id = e.id
JOIN subscriptions s
    ON s.id = em.subscription_id
WHERE e.status = 'ACTIVE'
GROUP BY e.id, e.due_day;


-- 3 meses atrás
INSERT INTO payments (
    enrollment_id,
    due_date,
    amount,
    payment_amount,
    payment_date,
    observation,
    status
)
SELECT
    e.id,
    (
        date_trunc('month', CURRENT_DATE)
        - INTERVAL '3 months'
        + (e.due_day - 1) * INTERVAL '1 day'
    )::DATE,
    SUM(s.price),
    SUM(s.price),
    date_trunc('month', CURRENT_TIMESTAMP)
        - INTERVAL '3 months'
        + (e.due_day - 2) * INTERVAL '1 day',
    'Pagamento realizado',
    'PAID'
FROM enrollments e
JOIN enrollments_modalities em
    ON em.enrollment_id = e.id
JOIN subscriptions s
    ON s.id = em.subscription_id
WHERE e.status = 'ACTIVE'
GROUP BY e.id, e.due_day;


-- 2 meses atrás
INSERT INTO payments (
    enrollment_id,
    due_date,
    amount,
    payment_amount,
    payment_date,
    observation,
    status
)
SELECT
    e.id,
    (
        date_trunc('month', CURRENT_DATE)
        - INTERVAL '2 months'
        + (e.due_day - 1) * INTERVAL '1 day'
    )::DATE,
    SUM(s.price),
    SUM(s.price),
    date_trunc('month', CURRENT_TIMESTAMP)
        - INTERVAL '2 months'
        + (e.due_day - 2) * INTERVAL '1 day',
    'Pagamento realizado',
    'PAID'
FROM enrollments e
JOIN enrollments_modalities em
    ON em.enrollment_id = e.id
JOIN subscriptions s
    ON s.id = em.subscription_id
WHERE e.status = 'ACTIVE'
GROUP BY e.id, e.due_day;


-- 1 mês atrás
INSERT INTO payments (
    enrollment_id,
    due_date,
    amount,
    payment_amount,
    payment_date,
    observation,
    status
)
SELECT
    e.id,
    (
        date_trunc('month', CURRENT_DATE)
        - INTERVAL '1 month'
        + (e.due_day - 1) * INTERVAL '1 day'
    )::DATE,
    SUM(s.price),
    SUM(s.price),
    date_trunc('month', CURRENT_TIMESTAMP)
        - INTERVAL '1 month'
        + (e.due_day - 2) * INTERVAL '1 day',
    'Pagamento realizado',
    'PAID'
FROM enrollments e
JOIN enrollments_modalities em
    ON em.enrollment_id = e.id
JOIN subscriptions s
    ON s.id = em.subscription_id
WHERE e.status = 'ACTIVE'
GROUP BY e.id, e.due_day;


-- =====================================================
-- 6. PAGAMENTOS DO MÊS ATUAL
-- =====================================================

-- Pagamentos pagos
INSERT INTO payments (
    enrollment_id,
    due_date,
    amount,
    payment_amount,
    payment_date,
    observation,
    status
)
SELECT
    e.id,
    (
        date_trunc('month', CURRENT_DATE)
        + (e.due_day - 1) * INTERVAL '1 day'
    )::DATE,
    SUM(s.price),
    SUM(s.price),
    CURRENT_TIMESTAMP - INTERVAL '2 days',
    'Pagamento realizado',
    'PAID'
FROM enrollments e
JOIN enrollments_modalities em
    ON em.enrollment_id = e.id
JOIN subscriptions s
    ON s.id = em.subscription_id
WHERE
    e.status = 'ACTIVE'
    AND e.id % 3 = 0
GROUP BY e.id, e.due_day;


-- Pagamentos abertos
INSERT INTO payments (
    enrollment_id,
    due_date,
    amount,
    observation,
    status
)
SELECT
    e.id,
    (
        date_trunc('month', CURRENT_DATE)
        + (e.due_day - 1) * INTERVAL '1 day'
    )::DATE,
    SUM(s.price),
    'Aguardando pagamento',
    'OPEN'
FROM enrollments e
JOIN enrollments_modalities em
    ON em.enrollment_id = e.id
JOIN subscriptions s
    ON s.id = em.subscription_id
WHERE
    e.status = 'ACTIVE'
    AND e.id % 3 = 1
GROUP BY e.id, e.due_day;


-- Pagamentos vencidos
INSERT INTO payments (
    enrollment_id,
    due_date,
    amount,
    observation,
    status
)
SELECT
    e.id,
    CURRENT_DATE - INTERVAL '7 days',
    SUM(s.price),
    'Pagamento em atraso',
    'OVERDUE'
FROM enrollments e
JOIN enrollments_modalities em
    ON em.enrollment_id = e.id
JOIN subscriptions s
    ON s.id = em.subscription_id
WHERE
    e.status = 'ACTIVE'
    AND e.id % 3 = 2
GROUP BY e.id;


-- =====================================================
-- 7. PAGAMENTO CANCELADO
-- =====================================================

INSERT INTO payments (
    enrollment_id,
    due_date,
    amount,
    cancelled_at,
    observation,
    status
)
SELECT
    e.id,
    CURRENT_DATE - INTERVAL '15 days',
    SUM(s.price),
    CURRENT_DATE - INTERVAL '10 days',
    'Pagamento cancelado',
    'CANCELED'
FROM enrollments e
JOIN students st
    ON st.id = e.student_id
JOIN enrollments_modalities em
    ON em.enrollment_id = e.id
JOIN subscriptions s
    ON s.id = em.subscription_id
WHERE st.email = 'matheus.carvalho@email.com'
GROUP BY e.id;

