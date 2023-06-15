CREATE TABLE guests
(
    id             bigint       not null auto_increment,
    name           varchar(100) not null,
    lastname       varchar(100) not null,
    email          varchar(100) not null unique,
    document       int(25)      not null,
    birthday       varchar(100) not null,
    country        varchar(100) not null,
    phone          varchar(100) not null,
    activate       tinyint      not null,
    id_reservation bigint       not null,
    primary key (id),
    foreign key (id_reservation) references reservations (id)
);