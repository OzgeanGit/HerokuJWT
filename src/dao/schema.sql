






create table role_table
(
    id serial not null constraint role_table_pk  primary key,
    name varchar(20) not null
);

insert into role_table(id,name) values(1,'ADMIN');
insert into role_table(id,name) values(2,'STUDENT');
insert into role_table(id,name) values(3,'PROFESSOR');


create table user_table
(
    id serial not null constraint user_table_pk  primary key,
    login    varchar(50),
    password varchar(500),
    role_id  integer  constraint user_table_role_table_id_fk  references role_table
);

create unique index user_table_login_uindex
    on user_table (login);

update user_table set role_id=1 where id=1;
update user_table set role_id=2 where id=33;
update user_table set role_id=2 where id=34;
update user_table set role_id=3 where id=35;
update user_table set role_id=3 where id=36;

update user_table set role_id=2 where id=1;
select * from user_table;






drop table user;

CREATE TABLE user
(
  username character varying(50),
  password character varying(100),
  role character varying(20),
  user_id BIGINT UNSIGNED,
  CONSTRAINT users_id_pk PRIMARY KEY (user_id),
);

show columns from user;

insert into user(user_id,username,role) values(1,'ADMIN1','ADMIN');
insert into user(user_id,username,role) values(2,'STUDENT1','STUDENT');
insert into user(user_id,username,role) values(3,'STUDENT2','STUDENT');
insert into user(user_id,username,role) values(4,'PROF1','PROFESSOR');
insert into user(user_id,username,role) values(5,'PROF2','PROFESSOR');


select * from user;

update user set password='$2y$12$LKRca19TDN6YoQ4e/Yqq4.RhtbUTsHoAOr8Rrr8GTXdJ9oe7pP8WG' where user_id=1;

update user set password='$2y$12$N1ca7MrqkQsplpbCgiky0.yKTydANb5nV.PNz85nQZwv9MFLd3j9W' where user_id=2;
update user set password='$2y$12$N1ca7MrqkQsplpbCgiky0.yKTydANb5nV.PNz85nQZwv9MFLd3j9W' where user_id=3;

update user set password='$2y$12$qZ5T3nIgUeim7lYn93jsQO5YLdkKJuqGEyPHkIvSzr.dKMDZOeVV.' where user_id=4;
update user set password='$2y$12$qZ5T3nIgUeim7lYn93jsQO5YLdkKJuqGEyPHkIvSzr.dKMDZOeVV.' where user_id=5;