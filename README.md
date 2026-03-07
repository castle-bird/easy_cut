# ✂️ EASY CUT : 1인 미용실 예약 관리 API

## 1. 프로젝트 개요
> EASY CUT은 1인 미용실 운영의 효율성을 극대화하기 위한 스케줄 관리 및 예약 자동화 API 서비스입니다.
 
단순한 예약 기능을 넘어, 향후 '다인 디자이너 체제'와 '스타일 설정'으로의 유연한 확장을 고려한 객체 지향적 설계를 바탕으로 구축되었습니다.

---

## 2. 주요 기능 (MVP - Minimum Viable Product)
현재 1인 샵 운영에 필수적인 핵심 기능을 우선적으로 제공합니다.
- **실시간 예약 시스템:** 고객이 비어 있는 시간대를 확인하고 즉시 예약할 수 있는 기능 
- **미용사 스케줄 관리:** 휴무일, 오픈시간, 닫는시간 등 미용사의 일정에 따른 예약 가능 시간대 자동 산출 
- **예약 상태 관리:** 접수, 확정, 완료, 취소 등 단계별 상태 추적 및 로그 관리

---

## 3. 기술 스택 (Technical Stack)
**Backend & Core**
- **Language:** Java 17
- **Framework:** Spring Boot 3.5.11
- **Build Tool:** Gradle
- **Batch Processing:** Spring Batch (예약 슬롯 생성 및 데이터 정리용)
- **Security:** Spring Security (인증 및 권한 관리)

**Persistence & Database**
- **Database:** PostgreSQL (Main), H2 (Local/Test)
- **ORM:** Spring Data JPA (Hibernate 6.x)
- **Migration:** Flyway (버전 기반 DB 스키마 형상 관리)
- **Query Optimization:** Querydsl 5.1 (Jakarta) - 타입 세이프한 동적 쿼리 및 조건 조회 최적화

**Infrastructure & Environment**
- **Containerization:** Docker / Docker Compose (PostgreSQL 컨테이너 운용)
- **CI/CD:** GitHub Actions

**Utilities & Tools**
- **Lombok:** 보일러플레이트 코드 제거
- **MapStruct 1.6:** 계층 간 데이터 전송(DTO-Entity)을 위한 고성능 객체 매핑
- **Testing:** JUnit5, Spring Boot Test, Spring Batch Test

---

## 4. 데이터 삭제 및 관리 정책 ⭐⭐
**1. Soft Delete (논리 삭제)**
   - **대상:** `Member`, `Store`, `Reivew`, `Reservation`
   - **사유:** 
     - CS 대응(과거 이력 문의), 통계 데이터 보존 및 리뷰/댓글의 참조 무결성 유지.
     - 예약 노쇼(No-show), 결제 분쟁 등 과거 이력 확인을 위한 데이터 보존.
   - **방식:** `isDeleted` 컬럼 또는 `deletedAt` 타임스탬프를 활용해 조회 시 필터링.

**2. Hard Delete (물리 삭제)**
   - **대상:** `StoreImage`, `StoreOffDay`, `TimeSlot`
   - **사유:** 매장 삭제 시 종속된 부가 정보는 보존 가치가 낮으며, 불필요한 스토리지 점유 방지.
   - **방식:** 매주 1회 **Batch 작업을 통한 일괄 영구 삭제**를 수행하여 DB 성능 최적화.

---

## 5. 기본 API 명세 - V1

### 1. 사용자 (Members)

| 기능 | 메소드 | 엔드포인트 | 설명 |
|------|---------|-------------|------|
| 회원가입 | POST | `/api/members/signup` | 신규 회원 등록 |
| 로그인 | POST | `/api/members/login` | 사용자 로그인 |
| 내 정보 조회 | GET | `/api/members/me` | 로그인한 사용자 정보 조회 |
| 유저 정보 수정 | PATCH | `/api/members/{userId}` | 특정 사용자 정보 수정 |
| 유저 삭제 | DELETE | `/api/members/{userId}` | 특정 사용자 계정 삭제 |


### 2. 매장 (Stores)

| 기능 | 메소드 | 엔드포인트 | 설명 |
|------|---------|-------------|------|
| 매장 등록 | POST | `/api/stores` | 매장 신규 등록 |
| 매장 목록 조회 (다건) | GET | `/api/stores` | 전체 매장 목록 조회 |
| 매장 목록 조회 (단건) | GET | `/api/stores/{storeId}` | 특정 매장 기본 정보 조회 |
| 매장 상세 조회 | GET | `/api/stores/{storeId}/details` | 매장 상세 정보 조회 |
| 매장 수정 | PATCH | `/api/stores/{storeId}` | 매장 정보 수정 |
| 매장 삭제 | DELETE | `/api/stores/{storeId}` | 매장 삭제 |


### 3. 매장 이미지 (Store Images)

| 기능 | 메소드 | 엔드포인트 | 설명 |
|------|---------|-------------|------|
| 매장 이미지 등록 | POST | `/api/stores/{storeId}/images` | 매장 이미지 업로드 |
| 매장 이미지 조회 (다건) | GET | `/api/stores/{storeId}/images` | 매장의 모든 이미지 조회 |
| 매장 이미지 조회 (단건) | GET | `/api/stores/{storeId}/images/{imageId}` | 특정 이미지 조회 |
| 매장 이미지 삭제 | DELETE | `/api/stores/{storeId}/images/{imageId}` | 특정 이미지 삭제 |
| 매장 대표 이미지 조회 | GET | `/api/stores/{storeId}/images/main` | 대표 이미지 조회 |
| 매장 대표 이미지 설정 | POST | `/api/stores/{storeId}/images/main` | 대표 이미지 지정 |


### 4. 매장 휴무일 관리 (Store Off Days)

| 기능 | 메소드 | 엔드포인트 | 설명 |
|------|---------|-------------|------|
| 매장 휴무일 수정 | POST | `/api/stores/{storeId}/offdays` | 매장의 휴무일 등록 또는 수정 |
| 매장 휴무일 조회 (다건) | GET | `/api/stores/{storeId}/offdays` | 매장의 전체 휴무일 목록 조회 |


### 5. 예약 (Reservations)

| 기능 | 메소드 | 엔드포인트 | 설명 |
|------|---------|-------------|------|
| 예약 생성 | POST | `/api/reservations` | 신규 예약 생성 |
| 내 예약 내역 조회 | GET | `/api/reservations/me` | 로그인한 사용자의 예약 내역 조회 |
| 예약 취소 | PATCH | `/api/reservations/{resId}/cancel` | 예약 취소 처리 |
| 예약 완료 | PATCH | `/api/reservations/{resId}/completed` | 예약 완료 처리 |
| 예약 노쇼 | PATCH | `/api/reservations/{resId}/noshow` | 예약 노쇼 처리 |


### 6. 리뷰 (Reviews)

| 기능 | 메소드 | 엔드포인트 | 설명 |
|------|---------|-------------|------|
| 리뷰 작성 | POST | `/api/reviews` | 매장 리뷰 작성 |
| 매장별 리뷰 조회 | GET | `/api/stores/{storeId}/reviews` | 매장별 리뷰 조회 |