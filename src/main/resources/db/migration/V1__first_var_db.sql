CREATE TABLE IF NOT EXISTS authors (
    id BIGSERIAL,
    name VARCHAR(255) NOT NULL,
    constraint authors_pk primary key (id)
);

CREATE TABLE IF NOT EXISTS books (
    id BIGSERIAL,
    title VARCHAR(255) NOT NULL,
    annotation VARCHAR(255) NOT NULL,
    description VARCHAR(1024) NOT NULL,
    author_id BIGINT NOT NULL,
    constraint books_pk primary key (id),
    constraint books_authors_fk foreign key (author_id) references authors (id) on update cascade on delete cascade
);