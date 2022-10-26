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
    pages_quantity int not null,
    link_pages varchar(500),
    blob_pages mediumblob,
    manga bigint not null
);

drop table if exists categories;
create table categories (
    id_category bigint primary key auto_increment,
    name varchar(50) unique not null,
    age_group varchar(9) not null,
    description varchar(250)
);

drop table if exists permission;
create table permission (
	id_permission bigint primary key auto_increment,
    description varchar(255) unique
);

drop table if exists users;
create table users(
	id_user bigint primary key auto_increment,
    user_name varchar(255) unique not null,
    full_name varchar(255) not null,
    password varchar(255) not null,
    account_non_expired bit(1) not null,
    account_non_locked bit(1) not null,
    credentials_non_expired bit(1) not null,
    enabled bit(1) not null
);

drop table if exists readers;
create table readers (
    id_reader bigint primary key auto_increment,
    email varchar(200) unique not null,
    quantity_read int not null,
    birthdate date,
    user bigint
);

drop table if exists mangas_categories;
create table mangas_categories (
    id_manga_category bigint primary key auto_increment,
    manga bigint not null,
    category bigint not null
);

drop table if exists mangas_authors;
create table mangas_authors (
    id_manga_author bigint primary key auto_increment,
    manga bigint not null,
    author bigint not null
);

drop table if exists user_permission;
create table user_permission(
	id_user bigint,
    id_permission bigint,
    primary key ( id_user, id_permission)
);

drop table if exists readers_chapters;
create table readers_chapters (
    id_mangas_readers bigint primary key auto_increment,
    reader bigint not null,
    chapter bigint not null
);

alter table user_permission add constraint fk_user_permission_user foreign key (id_user) references users (id_user);
alter table user_permission add constraint fk_user_permission_permission foreign key (id_permission) references permission (id_permission);

alter table chapters add constraint fk_chapters_mangas foreign key (manga) references mangas (id_manga);

alter table mangas_categories add constraint fk_mangas_categories_mangas foreign key (manga) references mangas (id_manga);
alter table mangas_categories add constraint fk_mangas_categories_categories foreign key (category) references categories (id_category);

alter table mangas_authors add constraint fk_mangas_authors_manga foreign key (manga) references mangas (id_manga);
alter table mangas_authors add constraint fk_mangas_authors_author foreign key (author) references authors (id_author);

alter table readers add constraint fk_readers_user foreign key (user) references users (id_user);

alter table readers_chapters add constraint fk_readers_chapters_chapter foreign key (chapter) references chapters (id_chapter);
alter table readers_chapters add constraint fk_readers_chapters_reader foreign key (reader) references readers (id_reader);
