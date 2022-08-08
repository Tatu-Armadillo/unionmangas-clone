drop database if exists unionmangas;
create database unionmangas;
use unionmangas;

drop table if exists manga;
create table manga (
    id_manga bigint primary key auto_increment,
    main_title varchar(100) not null unique,
    alternatives_title varchar(250),
    link_image varchar(500),
    blob_image mediumblob,
    description varchar(250) not null,
    status varchar(10) not null,
    evaluation decimal(4,2) not null,
    release_date date not null,
    last_update date not null,
    author bigint not null
);

drop table if exists author;
create table author ( 
    id_author bigint primary key auto_increment,
    name varchar(100) not null unique,
    age bigint not null,
    birthdate date not null    
); 

drop table if exists chapter;
create table chapter (
    id_chapter bigint primary key auto_increment,
    number bigint not null,
    number_chapter bigint not null,
    release_date date not null,
    link_chapter varchar(500),
    blob_chapter mediumblob,
    manga bigint not null
);

drop table if exists genre;
create table genre (
    id_genre bigint primary key auto_increment,
    name varchar(50) unique not null,
    age_group varchar(9) not null,
    description varchar(250)
);

drop table if exists manga_genre;
create table manga_genre (
    id_manga_genre bigint primary key auto_increment,
    manga bigint not null,
    genre bigint not null
);

alter table manga add constraint fk_manga_author foreign key (author) references author (id_author);

alter table chapter add constraint fk_chapter_manga foreign key (manga) references manga (id_manga);

alter table manga_genre add constraint fk_manga_genre_manga foreign key (manga) references manga (id_manga);
alter table manga_genre add constraint fk_manga_genre_genre foreign key (genre) references genre (id_genre);
