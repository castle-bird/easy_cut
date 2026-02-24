-- 1. 사용자의 정보 (USER)
CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,                      -- 유저 ID
    email    VARCHAR(255) NOT NULL UNIQUE,            -- 로그인 이메일 (표준 규격 준수)
    password VARCHAR(255) NOT NULL,                   -- 비밀번호 (암호화 해시 고려)
    name     VARCHAR(50)  NOT NULL,                   -- 사용자 이름
    phone    VARCHAR(20)  NOT NULL,                   -- 연락처
    role     VARCHAR(20)  NOT NULL DEFAULT 'CUSTOMER' -- 사용자 권한
);

-- 2. 예약 매장 (STORE)
CREATE TABLE IF NOT EXISTS stores
(
    id          SERIAL PRIMARY KEY,                  -- 매장 ID
    owner_id    INTEGER      NOT NULL,               -- 유저 ID (원장님)
    name        VARCHAR(100) NOT NULL,               -- 매장 이름
    description TEXT         NOT NULL,               -- 매장 설명
    location    TEXT         NOT NULL,               -- 매장 위치
    open_time   TIME         NOT NULL,               -- 매장 오픈 시간
    close_time  TIME         NOT NULL,               -- 매장 문 닫는 시간
    is_deleted  BOOLEAN      NOT NULL DEFAULT FALSE, -- 매장 삭제(Soft Delete) 여부 추가
    CONSTRAINT fk_store_owner FOREIGN KEY (owner_id) REFERENCES users (id)
);

-- 3. 매장마다 예약 가능한 시간 (TIME_SLOT)
CREATE TABLE IF NOT EXISTS time_slots
(
    id             SERIAL PRIMARY KEY,            -- 예약 가능 시간 ID
    store_id       INTEGER NOT NULL,              -- 매장 ID
    available_date DATE    NOT NULL,              -- 예약 가능 날짜
    available_time TIME    NOT NULL,              -- 예약 가능 시간
    is_available   BOOLEAN NOT NULL DEFAULT TRUE, -- 예약 가능 여부
    CONSTRAINT fk_timeslot_store FOREIGN KEY (store_id) REFERENCES stores (id)
);

-- 4. 사용자가 예약한 내역 (RESERVATION)
CREATE TABLE IF NOT EXISTS reservations
(
    id           SERIAL PRIMARY KEY,                     -- 예약내역 ID
    user_id      INTEGER     NOT NULL,                   -- 유저 ID
    store_id     INTEGER     NOT NULL,                   -- 매장 ID
    time_slot_id INTEGER     NOT NULL UNIQUE,            -- 예약 가능 시간 ID (1:1 매핑 및 중복 차단)
    status       VARCHAR(20) NOT NULL DEFAULT 'PENDING', -- 예약 상태
    CONSTRAINT fk_reservation_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_reservation_store FOREIGN KEY (store_id) REFERENCES stores (id),
    CONSTRAINT fk_reservation_timeslot FOREIGN KEY (time_slot_id) REFERENCES time_slots (id)
);

-- 5. 사용자 리뷰 (REVIEW)
CREATE TABLE IF NOT EXISTS reviews
(
    id             SERIAL PRIMARY KEY,                                         -- 리뷰 ID
    reservation_id INTEGER       NOT NULL UNIQUE,                              -- 예약내역 ID (1:1 매핑으로 중복 리뷰 차단)
    user_id        INTEGER       NOT NULL,                                     -- 유저 ID
    rating         NUMERIC(2, 1) NOT NULL CHECK (rating >= 0 AND rating <= 5), -- 평점 (1~5점 제한)
    comment        TEXT,                                                       -- 리뷰 내용
    CONSTRAINT fk_review_reservation FOREIGN KEY (reservation_id) REFERENCES reservations (id),
    CONSTRAINT fk_review_user FOREIGN KEY (user_id) REFERENCES users (id)
);

-- 6. 매장 이미지 (STORE_IMAGE)
CREATE TABLE IF NOT EXISTS store_images
(
    id            SERIAL PRIMARY KEY,                  -- 이미지 ID
    store_id      INTEGER      NOT NULL,               -- 매장 ID
    path          VARCHAR(255) NOT NULL,               -- 이미지 저장 경로 (AWS S3 URL 등)
    content_type  VARCHAR(20)  NOT NULL,               -- 이미지 컨텐츠 타입 (예: image/jpeg, image/png)
    original_name VARCHAR(255) NOT NULL,               -- 이미지 원본 이름 (사용자가 업로드한 파일명)
    is_main       BOOLEAN      NOT NULL DEFAULT FALSE, -- 썸네일 대표 이미지 여부
    CONSTRAINT fk_storeimage_store FOREIGN KEY (store_id) REFERENCES stores (id)
);
