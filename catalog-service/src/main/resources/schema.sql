DROP TABLE IF EXISTS book;

CREATE TABLE book (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    author varchar(255) NOT NULL,
    isbn varchar(255) NOT NULL UNIQUE ,
    price float NOT NULL,
    title VARCHAR(250) NOT NULL,
    created_date timestamp NOT NULL ,
    last_modified_date timestamp NOT NULL,
    version integer NOT NULL
);