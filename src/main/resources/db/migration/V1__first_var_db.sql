create table if not exists authors (
    id bigint not null,
    name varchar(50), primary key (id)
);

create table if not exists books (
    id bigint not null,
    annotation varchar(100),
    description varchar(1024),
    title varchar(100),
    author_id bigint, primary key (id)
);

create sequence authors_seq start with 1 increment by 50;
create sequence books_seq start with 1 increment by 50;
alter table if exists books add constraint book_author_fk foreign key (author_id) references authors;