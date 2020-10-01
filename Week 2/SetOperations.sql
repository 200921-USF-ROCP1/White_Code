--set operations

-- union: combines result sets. sets have to be the same number and type of columns
--		normally from same table
-- OR


select * from residents r 
	where id = 3
union 
select * from residents r2 
	where id = 5;


-- intersect: take only the rows where both selects apply 
-- AND
select * from apartments a
	where room_numer = 1
intersect
select * from apartments a2 
	where a2.building_letter = 'C';

	
--except: take all rows belonging to first result set that do not exist in second result set
-- NOT
select * from apartments a 
except
select * from apartments a2 
	where building_letter = 'C';


