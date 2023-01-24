--liquibase formatted sql

--changeset eyakauleva:addColumsLoginPassword

alter table users
    add column login varchar(45),
    add column password varchar(60);

--rollback alter table users
--  drop column login,
--  drop column password;