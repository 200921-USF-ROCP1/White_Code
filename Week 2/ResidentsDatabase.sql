drop table if exists pets;
drop table if exists cars;
drop table if exists residents;
drop table if exists apartments;


create table apartments (
	id numeric(5) primary key,
	building_letter varchar(1),
	room_numer numeric(5),
	monthly_rent numeric(10,2) -- 2 decimal places, will round
);

create table residents (
	id numeric(5) primary key,
	first_name varchar(60),
	last_name varchar(60),
	apartment_id numeric(5) references apartments(id)
);

create table cars (
	id numeric(5) primary key,
	make varchar(60),
	model varchar(60),
	year numeric(4),
	license_plate varchar(10),
	owner_id numeric(5) references residents(id)
);


create table pets (
	id numeric(5) primary key,
	breed varchar(60),
	name varchar(60),
	apartment_id numeric(5) references apartments(id),
	is_service_animal numeric(1)
);


-- Insert values

INSERT INTO apartments VALUES
  (1, 'A', 1, 1250),
  (2, 'B', 1, 1300),
  (3, 'C', 1, 1500),
  (4, 'C', 2, 1400);

INSERT INTO residents VALUES
  (1, 'Jacob', 'Davis', 1),
  (2, 'Sally', 'Bobson', 2),
  (3, 'Ricky', 'Bobson', 2),
  (4, 'Martha', 'Stuart', 3),
  (5, 'Jackie', 'Samson', 4);

INSERT INTO cars VALUES
  (1, 'Toyota', 'Corolla', 1995, 'IGB18SS', 2),
  (2, 'Honda', 'Civic', 2001, 'FFGB91S', 3);

INSERT INTO pets VALUES
  (1, 'cat', 'Jimmy', 3, 0),
  (2, 'dog', 'Spot', 4, 1);
