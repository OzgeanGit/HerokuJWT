create table role_table(name varchar(50), id  NOT NULL, PRIMARY KEY(ID));
create table user_table(login varchar(50), password varchar(500), role_id integer, id  not null, primary key(id));
alter table USER_TABLE add foreign key (role_id) references role_table(id);

select * from role_table;
select * from USER_TABLE;

insert into role_table (NAME) VALUES ('ROLE_ADMIN');
insert into role_table (NAME) VALUES ('ROLE_PROFESSOR');
insert into role_table (NAME) VALUES ('ROLE_STUDENT');

UPDATE USER_TABLE SET ROLE_ID = 1 WHERE ID = 1;
UPDATE USER_TABLE SET ROLE_ID = 3 WHERE ID = 2;
UPDATE USER_TABLE SET ROLE_ID = 2 WHERE ID = 3;

UPDATE role_table SET name = 'ROLE_ADMIN' WHERE ID = 1;
UPDATE role_table SET name = 'ROLE_PROFESSOR'  WHERE ID = 2;
UPDATE role_table SET name = 'ROLE_STUDENT' WHERE ID = 3;

ALTER TABLE  USER_TABLE ALTER COLUMN password varchar(500) ;



delete from USER_TABLE where id=97;