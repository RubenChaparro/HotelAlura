CREATE TABLE guests
(
    id             bigint      not null auto_increment,
    name           varchar(50) not null,
    lastname       varchar(50) not null,
    birthday       date        not null,
    country        varchar(50) not null,
    phone          varchar(20) not null,

primary key (id)
);