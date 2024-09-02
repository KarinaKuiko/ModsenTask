-- liquibase formatted sql

--changeset karina_kuiko:1
CREATE TABLE IF NOT EXISTS library (
                                     id          BIGSERIAL    PRIMARY KEY,
                                     book_id        BIGSERIAL  NOT NULL,
                                     taken        timestamp  ,
                                     need_return       timestamp,
                                     status varchar(32) NOT NULL,
                                     FOREIGN KEY (book_id) REFERENCES books (id)
);