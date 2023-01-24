--liquibase formatted sql

--changeset eyakauleva:addColumGrade

alter table reviews
    add column grade int not null;

--rollback alter table reviews
--  drop column grade;