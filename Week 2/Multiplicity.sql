-- many-many relationships
drop table if exists users_accounts;
drop table if exists accounts;
drop table if exists users;

create table users (
	id serial primary key,
	username varchar(60),
	password varchar(60)
);

create table accounts (
	id serial primary key,
	account_balance numeric(10,2)
);

create table users_accounts (
	user_id serial references users(id),
	account_id serial references accounts(id)
);

insert into users (username, password) values
	('jacobd', 'password'),
	('otherguy', 'password');
	
insert into accounts (account_balance) values
	(1500),
	(1200),
	(30000);
	
insert into users_accounts values
	(1,1),
	(2,2),
	(1,3),
	(2,3);
	



select username,sum(account_balance) as total_balance,count(account_id) as Number_of_accounts from users u
	join users_accounts ua on u.id = ua.user_id
	join accounts a on ua.account_id = a.id
	group by username;