
    create table place (
       categorie varchar(255) not null,
        distance float not null,
        name varchar(255) not null,
        address varchar(255),
        last_update datetime(6),
        query_count_text bigint,
        primary key (categorie, distance, name)
    ) engine=InnoDB
    