--liquibase formatted sql

--changeset eyakauleva:updateUsersLoginPassword

update users
    set login = 'user1', password = '$2a$10$vs8oBxPz5g2EL676mhhcWON7955mS8.5dcU4820FZwRUvz6JEjUPe'
    where id = 1;
update users
    set login = 'user2', password = '$2a$10$vs8oBxPz5g2EL676mhhcWON7955mS8.5dcU4820FZwRUvz6JEjUPe'
    where id = 2;
update users
    set login = 'user3', password = '$2a$10$vs8oBxPz5g2EL676mhhcWON7955mS8.5dcU4820FZwRUvz6JEjUPe'
    where id = 3;
update users
    set login = 'user4', password = '$2a$10$vs8oBxPz5g2EL676mhhcWON7955mS8.5dcU4820FZwRUvz6JEjUPe'
    where id = 4;
update users
    set login = 'user5', password = '$2a$10$vs8oBxPz5g2EL676mhhcWON7955mS8.5dcU4820FZwRUvz6JEjUPe'
    where id = 5;