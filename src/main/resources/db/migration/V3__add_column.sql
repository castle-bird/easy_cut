-- V3__add_column.sql

-- 1. members 테이블 소프트 삭제 컬럼
ALTER TABLE members ADD COLUMN deleted_at TIMESTAMP;
ALTER TABLE members ADD COLUMN is_deleted BOOLEAN DEFAULT FALSE;

-- 2. stores 테이블 소프트 삭제 컬럼
ALTER TABLE stores ADD COLUMN deleted_at TIMESTAMP;