-- left (outer) join: get all data in left table + data 
-- matching join condition on right table. rows 
-- that dont have matches in the left table get 
-- nulls for the new columns

select * from apartments a 
	left join residents r on a.id = r.apartment_id;
	
-- right (outer) join: get all data in right table + data 
-- matching join condition on left table. rows 
-- that dont have matches in the right table get 
-- nulls for the new columns

select * from apartments a 
	right join residents r on a.id = r.apartment_id;
	
--inner join (default join): only gives values where all data matches join condition
select * from apartments a 
	inner join residents r on a.id = r.apartment_id;
	
--full outer join (full join): gives all rows from both tables, connecting matches
select * from apartments a 
	full outer join residents r on a.id = r.apartment_id;
	
--cross join: shows every join combination
select * from apartments a 
	cross join residents;
	
-- natural join: joins on same column name (in this case id)
-- note that this does not preserve the foreign key constraint
select * from apartments a
 natural join residents r;