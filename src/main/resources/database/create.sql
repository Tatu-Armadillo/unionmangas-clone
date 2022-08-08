drop database if exists unionmangas;
create database unionmangas;
use unionmangas;

drop table if exists manga;
create table manga (
    id_manga bigint primary key auto_increment,
    main_title varchar(100) not null unique,
    alternatives_title varchar(250),
    description varchar(250) not null,
    status varchar(10) not null,
    evaluation decimal(4,2) not null,
    release_date date not null,
    last_update date not null,
    author_text bigint not null,
    artist_drawing bigint not null
);

drop table if exists author;
create table author ( 
    id_author bigint primary key auto_increment,
    name varchar(100) not null unique,
    age bigint not null,
    birthday date not null    
); 

drop table if exists chapter;
create table chapter (
    id_author bigint primary key auto_increment,
    number bigint not null,
    number_pages bigint not null,
    release_date date not null,
    manga bigint not null
);

drop table if exists genre;
create table genre (
    id_genre bigint primary key auto_increment,
    name varchar(50) unique not null
);

drop table if exists manga_genre;
create table manga_genre (
    id_manga_genre bigint primary key auto_increment,
    manga bigint,
    genre bigint
);

alter table manga add constraint fk_manga_author_text foreign key (author_text) references author (id_author);
alter table manga add constraint fk_manga_artist_drawing foreign key (artist_drawing) references author (id_author);

alter table chapter add constraint fk_chapter_manga foreign key (manga) references manga (id_manga);

alter table manga_genre add constraint fk_manga_genre_manga foreign key (manga) references manga (id_manga);
alter table manga_genre add constraint fk_manga_genre_genre foreign key (genre) references genre (id_genre);
