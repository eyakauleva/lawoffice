--liquibase formatted sql

--changeset eyakauleva:addLoginUniqueNotNullPasswordNotNull

alter table users
    add constraint users_login_key unique(login);
alter table users
    alter column login set not null,
    alter column password set not null;

--rollback alter table users
--  drop constraint users_login_key;
--  alter table users
--    alter column login drop not null,
--    alter column password drop not null;