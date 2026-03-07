-- V2__add_store_off_days.sql
-- 매장 정기 휴무일
CREATE TABLE IF NOT EXISTS store_off_days
(
    id          UUID PRIMARY KEY     DEFAULT gen_random_uuid(),
    store_id    UUID        NOT NULL,
    off_day VARCHAR(10) NOT NULL, -- MONDAY, TUESDAY ...
    created_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- 매장별로 요일이 중복되지 않도록 설정 (필수)
    CONSTRAINT uq_store_day UNIQUE (store_id, off_day),
    CONSTRAINT fk_offday_store FOREIGN KEY (store_id) REFERENCES stores (id)
);