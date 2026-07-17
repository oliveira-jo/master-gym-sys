
ALTER TABLE payments
ADD COLUMN payment_amount NUMERIC(10,2);

ALTER TABLE payments
ADD COLUMN observation VARCHAR(300);

UPDATE payments
SET payment_amount = amount
WHERE status = 'PAID'
  AND payment_amount IS NULL;

ALTER TABLE payments
ADD CONSTRAINT chk_payment_amount_positive
CHECK (
    payment_amount IS NULL OR payment_amount >= 0
);

ALTER TABLE payments
ADD CONSTRAINT chk_payment_paid
CHECK (
    status <> 'PAID'
    OR payment_amount IS NOT NULL
);