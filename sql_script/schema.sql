create sequence main_seq start with 1000;

create table usr(
	id int primary key, 
	login varchar(20), 
	password varchar(20),
	firstname varchar(50),
	lastname varchar(50)
);

create table todolist (
	id int primary key,
	title varchar(100),
	color varchar(20),
	id_owner int, 
	due_date timestamp,
	constraint fk_todolist_owner foreign key (id_owner) references usr(id)
);
create table task (
	id int primary key,
	content varchar(3000),
	status varchar(20),
	id_list int,
	constraint fk_task_list foreign key (id_list) references todolist(id)
);