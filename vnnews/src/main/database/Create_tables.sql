use vnnews;

create table role(
	id bigint not null primary key auto_increment,
    name varchar(255) not null,
    code varchar(255) not null,
    createddate timestamp null,
    modifieddate timestamp null,
    createdby varchar(255) null,
    modifiedby varchar(255) null
);

create table user(
	id bigint not null primary key auto_increment,
    username varchar(150) not null,
    password varchar(150) not null,
    fullname varchar(150) null,
    status int not null,
    roleid bigint not null,
    createddate timestamp null,
    modifieddate timestamp null,
    createdby varchar(255) null,
    modifiedby varchar(255) null
);

ALTER TABLE user ADD constraint fk_user_role foreign key (roleid) references role(id);

create table news(
	id bigint not null primary key auto_increment,
    title varchar(255) null,
    thumbnail varchar(255) null,
    shortdescription text null,
    content text null,
    categoryid bigint not null,
    createddate timestamp null,
    modifieddate timestamp null,
    createdby varchar(255) null,
    modifiedby varchar(255) null
);

create table category(
	id bigint not null primary key auto_increment,
    name varchar(255) not null,
    code varchar(255) not null,
    createddate timestamp null,
    modifieddate timestamp null,
    createdby varchar(255) null,
    modifiedby varchar(255) null
);

ALTER TABLE news ADD constraint fk_news_category foreign key (categoryid) references category(id);

create table comment(
	id bigint not null primary key auto_increment,
    content text not null,
    user_id bigint not null,
    new_id bigint not null,
    createddate timestamp null,
    modifieddate timestamp null,
    createdby varchar(255) null,
    modifiedby varchar(255) null
);

ALTER TABLE comment ADD constraint fk_comment_user foreign key (user_id) references user(id);
ALTER TABLE comment ADD constraint fk_comment_news foreign key (new_id) references news(id);


insert into role(code,name) values('ADMIN','ADMIN');
insert into role(code,name) values('USER','USER');
select * from user;

insert into user(username,password,fullname,status, roleid) values('admin','123456','admin',1,1);
insert into user(username,password,fullname,status, roleid) values('nguyenvana','123456','nguyen van a',1,2);
insert into user(username,password,fullname,status, roleid) values('nguyenvanb','123456','nguyen van b',1,2);











