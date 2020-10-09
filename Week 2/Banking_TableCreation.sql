-- table creation script

drop table if exists users cascade;
drop table if exists roles cascade;
drop table if exists accounts cascade;
drop table if exists account_status cascade;
drop table if exists account_type cascade;
drop table if exists user_accounts cascade;

-- Users
create table roles (
	role_id serial primary key,
	role varchar(60) unique,
	
	check (role in ('Standard','Employee','Admin','Premium'))
);

create table users (
	user_id serial primary key,
	username varchar(60) unique,
	password varchar(60),
	first_name varchar(60),
	last_name varchar(60),
	email varchar(60),
	role_id serial references roles(role_id)
);

-- Accounts
create table account_status (
	status_id serial primary key,
	status varchar(60) unique,
	
	check ( status in ('Pending','Open','Closed','Denied'))
);

create table account_type (
	type_id serial primary key,
	type varchar(60) unique,
	
	check ( type in ('Checking','Savings'))
);

create table accounts (
	account_id serial primary key,
	balance numeric(10,2),
	status_id serial references account_status(status_id),
	type_id serial references account_type(type_id)
);
ALTER SEQUENCE accounts_account_id_seq RESTART WITH 100; --start accounts at 100


-- Joint users <-> accounts table

create table user_accounts(
	user_id serial references users(user_id) on delete cascade,
	account_id serial references accounts(account_id) on delete cascade,
	primary key(user_id,account_id)
);


-- Insert Roles, Statuses, and Types

insert into roles(role) values
	('Admin'),
	('Employee'),
	('Premium'),
	('Standard');

insert into account_status(status) values
	('Pending'),
	('Open'),
	('Closed'),
	('Denied');
	
insert into account_type(type) values
	('Checking'),
	('Savings');
	

-- create first admin 
insert into users(username, password, first_name, last_name, email, role_id) values
	('mwhite','1234','Matthew','White','mwhite@gmail.com',1);
	

SELECT * FROM users;
select * from accounts;
select * from user_accounts;
select * from account_status;

truncate accounts cascade;

select * from users
where user_id = 1

select a.*,s.status,t.type from user_accounts ua
	join accounts a on ua.account_id = a.account_id
	natural join account_type t 
	natural join account_status s 
	where a.status_id = 1
	order by t.type, s.status;
	
update users
	set username = 'mcoakley', password = '1234', first_name = 'Marina', last_name = 'Coakley', email = 'FinleysMom@gmail.com', role_id = 4
	where user_id = 3;