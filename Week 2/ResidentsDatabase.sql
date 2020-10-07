drop table if exists pets cascade;
drop table if exists cars cascade;
drop table if exists residents cascade;
drop table if exists apartments cascade; -- cascade removes order rules for dropping


create table apartments (
	id serial primary key,
	building_letter varchar(1),
	room_number numeric(5),
	monthly_rent numeric(10,2) -- 2 decimal places, will round
);

create table residents (
	id serial primary key,
	first_name varchar(60),
	last_name varchar(60),
	apartment_id serial references apartments(id)
);

create table cars (
	id serial primary key,
	make varchar(60),
	model varchar(60),
	year numeric(4),
	license_plate varchar(10),
	owner_id serial references residents(id) on delete cascade
);


create table pets (
	id serial primary key,
	breed varchar(60),
	name varchar(60),
	apartment_id serial references apartments(id) on delete cascade,
	is_service_animal numeric(1)
);


-- Insert values

INSERT INTO apartments(building_letter,room_number,monthly_rent) values
  ('A', 1, 1250),
  ('B', 1, 1300),
  ('C', 1, 1500),
  ('C', 2, 1400);

INSERT INTO residents(first_name,last_name,apartment_id) VALUES
  ('Jacob', 'Davis', 1),
  ('Sally', 'Bobson', 2),
  ('Ricky', 'Bobson', 2),
  ('Martha', 'Stuart', 3),
  ('Jackie', 'Samson', 4);

INSERT INTO cars(make,model,year,license_plate,owner_id) VALUES
  ('Toyota', 'Corolla', 1995, 'IGB18SS', 2),
  ('Honda', 'Civic', 2001, 'FFGB91S', 3);

INSERT INTO pets(breed,name,apartment_id,is_service_animal) VALUES
  ('cat', 'Jimmy', 3, 0),
  ('dog', 'Spot', 4, 1);
