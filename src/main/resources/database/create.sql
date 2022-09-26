drop database if exists unionmangas;
create database unionmangas;
use unionmangas;

drop table if exists mangas;
create table mangas (
    id_manga bigint primary key auto_increment,
    main_title varchar(100) not null unique,
    alternatives_title varchar(250),
    link_image varchar(500) not null,
    blob_image mediumblob,
    volume_quantity int default 0 ,
    description varchar(1000) not null,
    status varchar(10) not null,
    rating decimal(4,2) not null default 0.0,
    release_date date not null,
    last_update date not null
);

drop table if exists authors;
create table authors ( 
    id_author bigint primary key auto_increment,
    name varchar(100) not null unique,
    pseudonym varchar(100),
    age int not null,
    birthdate date not null    
); 

drop table if exists chapters;
create table chapters (
    id_chapter bigint primary key auto_increment,
    volume int not null,
    number_chapter int not null,
    title_chapter varchar(50) not null,
    release_date date not null,
    pages int not null,
    link_pages varchar(500),
    blob_pages mediumblob,
    manga bigint not null
);

drop table if exists genres;
create table genres (
    id_genre bigint primary key auto_increment,
    name varchar(50) unique not null,
    age_group varchar(9) not null,
    description varchar(250)
);

drop table if exists mangas_genres;
create table mangas_genres (
    id_manga_genre bigint primary key auto_increment,
    manga bigint not null,
    genre bigint not null
);

drop table if exists mangas_authors;
create table mangas_authors (
    id_manga_author bigint primary key auto_increment,
    manga bigint not null,
    author bigint not null
);

alter table chapters add constraint fk_chapters_mangas foreign key (manga) references mangas (id_manga);

alter table mangas_genres add constraint fk_mangas_genres_manga foreign key (manga) references mangas (id_manga);
alter table mangas_genres add constraint fk_mangas_genres_genre foreign key (genre) references genres (id_genre);

alter table mangas_authors add constraint fk_mangas_authors_manga foreign key (manga) references mangas (id_manga);
alter table mangas_authors add constraint fk_mangas_authors_author foreign key (author) references authors (id_author);
