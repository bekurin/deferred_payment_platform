CREATE TABLE user
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 고유 ID',
    name                VARCHAR(255) NOT NULL COMMENT '사용자 이름',
    email               VARCHAR(255) NOT NULL COMMENT '사용자 이메일',
    phone_number        VARCHAR(20) COMMENT '사용자 전화번호',
    credit_limit        DECIMAL(10, 2) DEFAULT 0 COMMENT '사용자 신용 한도',
    outstanding_balance DECIMAL(10, 2) DEFAULT 0 COMMENT '미결제 잔액',
    created_at          TIMESTAMP      DEFAULT CURRENT_TIMESTAMP COMMENT '레코드 생성 시각',
    updated_at          TIMESTAMP      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '레코드 수정 시각'
) COMMENT ='사용자 정보 테이블';

CREATE TABLE corporate_user
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '법인 사용자 고유 ID',
    name           VARCHAR(255) NOT NULL COMMENT '법인 사용자 이름',
    email          VARCHAR(255) NOT NULL COMMENT '법인 사용자 이메일',
    phone_number   VARCHAR(20) COMMENT '법인 사용자 전화번호',
    corporation_id BIGINT COMMENT '법인 ID',
    credit_limit   DECIMAL(10, 2) DEFAULT 0 COMMENT '법인 사용자 신용 한도',
    created_at     TIMESTAMP      DEFAULT CURRENT_TIMESTAMP COMMENT '레코드 생성 시각',
    updated_at     TIMESTAMP      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '레코드 수정 시각',
    FOREIGN KEY (corporation_id) REFERENCES corporation (id)
) COMMENT ='법인 사용자 정보 테이블';

CREATE TABLE corporation
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '법인 고유 ID',
    name                VARCHAR(255) NOT NULL COMMENT '법인 이름',
    credit_limit        DECIMAL(10, 2) DEFAULT 0 COMMENT '법인 신용 한도',
    outstanding_balance DECIMAL(10, 2) DEFAULT 0 COMMENT '법인의 미결제 잔액',
    created_at          TIMESTAMP      DEFAULT CURRENT_TIMESTAMP COMMENT '레코드 생성 시각',
    updated_at          TIMESTAMP      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '레코드 수정 시각'
) COMMENT ='법인 정보 테이블';

CREATE TABLE billing_account
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '결제 계정 고유 ID',
    user_id          BIGINT COMMENT '사용자 ID',
    corporation_id   BIGINT COMMENT '법인 사용자 ID',
    billing_address  VARCHAR(255) NOT NULL COMMENT '청구 주소',
    card_number      VARCHAR(31)  NOT NULL COMMENT '카드 번호',
    card_expiry_date DATE         NOT NULL COMMENT '카드 만료 일자',
    card_csv         VARCHAR(15)  NOT NULL COMMENT '카드 csv 번호',
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '레코드 생성 시각',
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '레코드 수정 시각',
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (corporation_id) REFERENCES corporation (id)
) COMMENT ='결제 계정 정보 테이블';

CREATE TABLE Transaction
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '거래 고유 ID',
    user_id           BIGINT COMMENT '사용자 ID',
    corporate_user_id BIGINT COMMENT '법인 사용자 ID',
    amount            DECIMAL(10, 2) NOT NULL COMMENT '거래 금액',
    transaction_date DATE        NOT NULL COMMENT '거래 예정 날짜',
    status           VARCHAR(50) NOT NULL COMMENT '거래 상태',
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '레코드 생성 시각',
    updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '레코드 수정 시각',
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (corporate_user_id) REFERENCES corporate_user (id)
) COMMENT ='거래 정보 테이블';

CREATE TABLE Payment
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '결제 고유 ID',
    transaction_id BIGINT COMMENT '거래 ID',
    payment_date   DATE NOT NULL COMMENT '결제 날짜',
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '레코드 생성 시각',
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '레코드 수정 시각',
    FOREIGN KEY (transaction_id) REFERENCES Transaction (id)
) COMMENT ='결제 정보 테이블';

CREATE TABLE Ledger
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '원장 항목 고유 ID',
    transaction_id BIGINT COMMENT '거래 ID',
    amount         DECIMAL(10, 2) NOT NULL COMMENT '금액',
    type           VARCHAR(50)    NOT NULL COMMENT '항목 유형 (차변/대변)',
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '레코드 생성 시각',
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '레코드 수정 시각',
    FOREIGN KEY (transaction_id) REFERENCES Transaction (id)
) COMMENT ='원장 항목 테이블';

######
create table billing_account
(
    card_expiry_date date,
    corporation_id   bigint,
    created_at       timestamp(6) not null,
    id               bigint primary key auto_increment,
    updated_at       timestamp(6) not null,
    user_id          bigint,
    billing_address  varchar(255),
    card_csv         varchar(255),
    card_number      varchar(255),
    primary key (id)
);
create table corporate_user
(
    credit_limit   numeric(38, 2) not null,
    corporation_id bigint,
    created_at     timestamp(6)   not null,
    id             bigint primary key auto_increment,
    updated_at     timestamp(6)   not null,
    email          varchar(255)   not null,
    name           varchar(255)   not null,
    phone_number   varchar(255)   not null,
    primary key (id)
);
create table corporation
(
    credit_limit        numeric(38, 2) not null,
    outstanding_balance numeric(38, 2) not null,
    created_at          timestamp(6)   not null,
    id                  bigint primary key auto_increment,
    updated_at          timestamp(6)   not null,
    name                varchar(255)   not null,
    primary key (id)
);
create table individual_user
(
    credit_limit        numeric(38, 2) not null,
    outstanding_balance numeric(38, 2) not null,
    created_at          timestamp(6)   not null,
    id                  bigint primary key auto_increment,
    updated_at          timestamp(6)   not null,
    email               varchar(255)   not null,
    name                varchar(255)   not null,
    phone_number        varchar(255)   not null,
    primary key (id)
);
create table ledger
(
    amount         numeric(38, 2) not null,
    type           tinyint        not null check (type between 0 and 1),
    created_at     timestamp(6)   not null,
    deleted_at     timestamp(6)   not null,
    id             bigint primary key auto_increment,
    transaction_id bigint,
    updated_at     timestamp(6)   not null,
    primary key (id)
);
create table payment
(
    payment_date   date         not null,
    created_at     timestamp(6) not null,
    id             bigint primary key auto_increment,
    transaction_id bigint unique,
    updated_at     timestamp(6) not null,
    primary key (id)
);
create table transaction
(
    amount             numeric(38, 2) not null,
    status             tinyint check (status between 0 and 3),
    transaction_date   date           not null,
    billing_account_id bigint,
    corporate_user_id  bigint,
    created_at         timestamp(6)   not null,
    id                 bigint primary key auto_increment,
    updated_at         timestamp(6)   not null,
    user_id            bigint,
    primary key (id)
)
