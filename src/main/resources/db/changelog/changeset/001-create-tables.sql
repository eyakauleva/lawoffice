--liquibase formatted sql

--changeset eyakauleva:create_tables

create table users
(
    id bigserial,
	role varchar(45) not null,
	name varchar(45) not null,
	surname varchar(45) not null,
	email varchar(45) not null,
	phone varchar(45) not null,
	status varchar(45) not null,
	primary key (id)
);

create table services(
	id bigserial,
	service_id bigint null,
	name varchar(45) unique not null,
	description varchar(1000) null,
	primary key (id),
	foreign key (service_id) references services (id) on update cascade on delete cascade
);

create table lawyers(
	id bigserial,
	user_id bigint not null,
	description varchar(1000) not null,
	experience REAL null,
	start_date date not null default current_timestamp,
	primary key (id),
	foreign key (user_id) references users (id) on update cascade on delete cascade
);

create table consultations(
	id bigserial,
	lawyer_id bigint not null,
	user_id bigint null,
	visit_time timestamp not null,
	primary key (id),
	unique (lawyer_id, visit_time),
	unique (user_id, visit_time),
	foreign key (lawyer_id) references lawyers (id) on update cascade on delete cascade,
	foreign key (user_id) references users (id) on update cascade on delete set null
);

create table reviews(
	id bigserial,
	user_id bigint not null,
	description varchar(1000) not null,
	review_time timestamp not null default current_timestamp,
	primary key (id),
	foreign key (user_id) references users (id) on update cascade on delete set null
);

create table affairs(
	id bigserial,
	user_id bigint null,
	name varchar(45) unique not null,
	status varchar(45) not null,
	description varchar(1000) not null,
	start_date date not null,
	end_date date null,
	price decimal(10, 2),
	primary key (id),
	foreign key (user_id) references users (id) on update cascade on delete set null
);

create table lawyers_has_services (
	lawyer_id bigint not null,
	service_id bigint not null,
	primary key (lawyer_id, service_id),
	foreign key (lawyer_id) references lawyers (id) on update cascade on delete cascade,
	foreign key (service_id) references services (id) on update cascade on delete cascade
);

create table affairs_has_lawyers (
	affair_id bigint not null,
	lawyer_id bigint not null,
	primary key (affair_id, lawyer_id),
    foreign key (affair_id) references affairs (id) on update cascade on delete cascade,
    foreign key (lawyer_id) references lawyers (id) on update cascade on delete cascade
);

--rollback drop table affairs_has_lawyers;
--  drop table lawyers_has_services;
--  drop table affairs;
--  drop table consultations;
--  drop table reviews;
--  drop table lawyers;
--  drop table services;
--  drop table users;
