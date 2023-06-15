CREATE TABLE users
(
    id_user  bigint       not null,
    login    varchar(100) not null,
    password varchar(300) not null,

    primary key (id_user),
    foreign key (id_user) references guests (id)
);