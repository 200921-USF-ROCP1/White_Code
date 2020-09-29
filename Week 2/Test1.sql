drop table test;

create table test (
	name varchar(60) primary key,
	number_of_pets numeric(5),
	yearly_income numeric(10,2),
	department varchar(60)
);

insert into test values
	('Matthew',1,0, 'Engineering'),
	('Jacob',0,50000, 'Engineering'),
	('Marina',1,60000, 'Human Resources');
	
insert into test(number_of_pets, yearly_income, name, department) values
	(4, 55000, 'Steve', 'Waste Management');




update test
set yearly_income = 30000
where name = 'Matthew';
	



select avg(yearly_income) from test; 

select * from test
where yearly_income < 60000
order by name desc;

select department, SUM(yearly_income) as expenditure
from test t
group by t.department
having SUM(yearly_income) >= 60000; --weirdly doesnt work with aliases

