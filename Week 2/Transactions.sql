-- transaction keywords

-- starting
begin;
--or 
begin transaction;

-- to commit
commit;

-- rollback (only before commit)
rollback;

-- save transaction progress (checkpoint)
savepoint my_savepoint;
rollback to my_savepoint;

-- example
begin;

update residents set first_name = 'Harry'
	where first_name = 'Jacob';

savepoint harry;

update residents set last_name = 'Davises'
	where last_name  = 'Davis';

commit;

select * from residents r ;
rollback to harry;