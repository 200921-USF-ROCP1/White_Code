select * from residents r ;

-- DML (manipulate rows)

--updating
update residents --table
	set last_name = null -- new values
	where last_name = 'Bobson'; -- condition

--delete
delete from residents --delete rows
	where id = 4; -- condition

--truncate
truncate residents; -- delete all values (rows) but not table
	
-- DDL (mainpulate columns)
	
-- drop
drop residents; -- deletes table

--alter
alter table residents drop column apartment_id;
alter table residents add column last_name varchar(60);
alter table residents alter column last_name set not null; -- adds constraint to not be null value
alter table residents alter column last_name drop not null; -- remove above constraint

alter table residents drop constraint residents_apartment_id_fkey; -- remove foreign key constraints

