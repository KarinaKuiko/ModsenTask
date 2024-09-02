-- liquibase formatted sql

--changeset karina_kuiko:1
CREATE TABLE IF NOT EXISTS books (
    id          BIGSERIAL    PRIMARY KEY,
    isbn        varchar(17)  UNIQUE NOT NULL,
    name        varchar(64)  NOT NULL,
    genre       varchar(64)  NOT NULL,
    description varchar(255) NOT NULL,
    author      varchar(64)  NOT NULL
);