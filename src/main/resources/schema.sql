-- DROP TABLE store_images;
-- DROP TABLE reviews;
-- DROP TABLE reservations;
-- DROP TABLE time_slots;
-- DROP TABLE stores;
-- DROP TABLE users;


-- 1. 사용자의 정보 (USER)
CREATE TABLE IF NOT EXISTS users
(
    id         UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    name       VARCHAR(50)  NOT NULL,
    phone      VARCHAR(20)  NOT NULL UNIQUE,
    role       VARCHAR(20)  NOT NULL DEFAULT 'CUSTOMER',
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL
);

-- 2. 예약 매장 (STORE)
CREATE TABLE IF NOT EXISTS stores
(
    id          UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    owner_id    UUID      NOT NULL,
    name        VARCHAR(100) NOT NULL,
    description TEXT         NOT NULL,
    location    TEXT         NOT NULL,
    open_time   TIME         NOT NULL,
    close_time  TIME         NOT NULL,
    is_deleted  BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP    NOT NULL,
    updated_at  TIMESTAMP    NOT NULL,
    CONSTRAINT fk_store_owner FOREIGN KEY (owner_id) REFERENCES users (id)
);

-- 3. 매장마다 예약 가능한 시간 (TIME_SLOT)
CREATE TABLE IF NOT EXISTS time_slots
(
    id             UUID PRIMARY KEY   DEFAULT gen_random_uuid(),
    store_id       UUID   NOT NULL,
    available_date DATE      NOT NULL,
    available_time TIME      NOT NULL,
    is_available   BOOLEAN   NOT NULL DEFAULT TRUE,
    created_at     TIMESTAMP NOT NULL,
    updated_at     TIMESTAMP NOT NULL,
    CONSTRAINT fk_timeslot_store FOREIGN KEY (store_id) REFERENCES stores (id)
);

-- 4. 사용자가 예약한 내역 (RESERVATION)
CREATE TABLE IF NOT EXISTS reservations
(
    id           UUID PRIMARY KEY     DEFAULT gen_random_uuid(),
    user_id      UUID     NOT NULL,
    store_id     UUID     NOT NULL,
    time_slot_id UUID     NOT NULL UNIQUE,
    status       VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at   TIMESTAMP   NOT NULL,
    updated_at   TIMESTAMP   NOT NULL,
    CONSTRAINT fk_reservation_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_reservation_store FOREIGN KEY (store_id) REFERENCES stores (id),
    CONSTRAINT fk_reservation_timeslot FOREIGN KEY (time_slot_id) REFERENCES time_slots (id)
);

-- 5. 사용자 리뷰 (REVIEW)
CREATE TABLE IF NOT EXISTS reviews
(
    id             UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reservation_id UUID       NOT NULL UNIQUE,
    user_id        UUID       NOT NULL,
    rating         NUMERIC(2, 1) NOT NULL CHECK (rating >= 0 AND rating <= 5),
    comment        TEXT,
    created_at     TIMESTAMP     NOT NULL,
    CONSTRAINT fk_review_reservation FOREIGN KEY (reservation_id) REFERENCES reservations (id),
    CONSTRAINT fk_review_user FOREIGN KEY (user_id) REFERENCES users (id)
);

-- 6. 매장 이미지 (STORE_IMAGE)
CREATE TABLE IF NOT EXISTS store_images
(
    id            UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    store_id      UUID      NOT NULL,
    path          VARCHAR(255) NOT NULL,
    content_type  VARCHAR(20)  NOT NULL,
    original_name VARCHAR(255) NOT NULL,
    is_main       BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at    TIMESTAMP    NOT NULL,
    CONSTRAINT fk_storeimage_store FOREIGN KEY (store_id) REFERENCES stores (id)
);