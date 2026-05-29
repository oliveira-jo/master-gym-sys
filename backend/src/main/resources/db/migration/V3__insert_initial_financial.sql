INSERT INTO enrollments (student_id, enrollment_date, due_day, status)
VALUES (2, CURRENT_DATE - INTERVAL '90 days', 10, 'ACTIVE');

INSERT INTO enrollments (student_id, enrollment_date, due_day, status)
VALUES (3, CURRENT_DATE - INTERVAL '60 days', 15, 'ACTIVE');

INSERT INTO enrollments_modalities(
   enrollment_id,
   modalitie_id,
   subscription_id,
   start_date
)
SELECT
    en.id,
    mo.id,
    sub.id,
    CURRENT_DATE - INTERVAL '90 days'
FROM enrollments en
JOIN modalities mo ON mo.name = 'Musculação'
JOIN subscriptions sub ON sub.modalitie_id = mo.id AND sub.name = 'Mensal'
WHERE en.student_id = 2;

INSERT INTO enrollments_modalities(
    enrollment_id,
    modalitie_id,
    graduation_id,
    subscription_id,
    start_date
)
SELECT
    en.id,
    mo.id,
    1,
    sub.id,
    CURRENT_DATE - INTERVAL '60 days'
FROM enrollments en
    JOIN modalities mo ON mo.name = 'Jiu-Jitsu'
    JOIN subscriptions sub ON sub.modalitie_id = mo.id AND sub.name = 'Mensal'
WHERE en.student_id = 3;

INSERT INTO payments(
       enrollment_id,
       due_date,
       amount,
       payment_date,
       status
)
SELECT
    en.id,
    CURRENT_DATE - INTERVAL '60 days',
    120.00,
    CURRENT_TIMESTAMP - INTERVAL '58 days',
    'OPEN'
FROM enrollments en
WHERE en.student_id = 2;


INSERT INTO payments(
    enrollment_id,
    due_date,
    amount,
    payment_date,
    status
)
SELECT
    en.id,
    CURRENT_DATE - INTERVAL '30 days',
    120.00,
    CURRENT_TIMESTAMP - INTERVAL '29 days',
    'PAID'
FROM enrollments en
WHERE en.student_id = 2;

INSERT INTO payments(
    enrollment_id,
    due_date,
    amount,
    status
)
SELECT
    en.id,
    CURRENT_DATE - INTERVAL '10 days',
    120.00,
    'OPEN'
FROM enrollments en
WHERE en.student_id = 2;


--
INSERT INTO payments(
    enrollment_id,
    due_date,
    amount,
    payment_date,
    status
)
SELECT
    en.id,
    CURRENT_DATE - INTERVAL '30 days',
    180.00,
    CURRENT_TIMESTAMP - INTERVAL '28 days',
    'PAID'
FROM enrollments en
WHERE en.student_id = 3;

INSERT INTO payments(
    enrollment_id,
    due_date,
    amount,
    status
)
SELECT
    en.id,
    CURRENT_DATE - INTERVAL '15 days',
    120.00,
    'OPEN'
FROM enrollments en
WHERE en.student_id = 2;