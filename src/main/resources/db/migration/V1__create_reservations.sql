CREATE TABLE reservations
(
    id        bigint       not null auto_increment,
    entrydate date         not null,
    outdate   date         not null,
    price     float(40, 2) not null,
    payform   varchar(100) not null,

    primary key (id)
);