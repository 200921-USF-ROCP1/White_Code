
-- all residents
select concat(first_name, ' ', last_name) as Residents from residents;

-- residents in building B
select concat(first_name, ' ', last_name) as Residents from residents r
	join apartments a on r.apartment_id = a.id
	where a.building_letter = 'B';

-- Average rent in building C
select AVG(monthly_rent) as Average_Rent from apartments a 
	where a.building_letter = 'C';
	group by a.building_letter

--total number of pets
select count(id) as total_pets from pets  ;

-- who owns spot? (could also join pets and residents based on the two foreign keys of the same type)
select concat(first_name, ' ', last_name) as Owner from residents
	join apartments on residents.apartment_id = apartments.id
	join pets on pets.apartment_id = apartments.id
	where pets."name" = 'Spot';

-- Make of Sally Bobson's Car
select make from cars c
	join residents r on c.owner_id = r.id 
	where concat(r.first_name, ' ', r.last_name) = 'Sally Bobson'; 




