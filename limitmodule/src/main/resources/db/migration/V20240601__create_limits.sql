CREATE TABLE IF NOT EXISTS limits(
    id          BIGSERIAL PRIMARY KEY,
    client_id   BIGINT NOT NULL UNIQUE,
    day_limit  DECIMAL(9,2) DEFAULT 10000.00,
    CONSTRAINT client_id_positive CHECK (client_id > 0),
    CONSTRAINT limit_positive CHECK(day_limit >= 0)
);