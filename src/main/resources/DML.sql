DROP SCHEMA law_office_schema CASCADE;

drop table affairs_has_lawyers;

drop table lawyers_has_services;

drop table affairs;

drop table consultations;

drop table reviews;

drop table lawyers;

drop table services;

drop table users;



SET search_path = law_office_schema, "$user", public; -- For current session only

  


CREATE OR REPLACE PROCEDURE check_consultation_on_unique_constraints(
	lawyer_id bigint,
	user_id bigint,
	visit_time timestamp,
	result_code OUT INT) 
LANGUAGE plpgsql AS
$$
DECLARE
   lawyer_id_visit_time_key TEXT := 'consultations_lawyer_id_visit_time_key';
   user_id_visit_time_key TEXT := 'consultations_user_id_visit_time_key';
   violation_name TEXT;
   should_rollback boolean := false;
BEGIN
	begin
		insert into consultations(lawyer_id, user_id, visit_time) 
			values(lawyer_id, user_id, visit_time);
		should_rollback := true;
		result_code := 0;
		exception
			when UNIQUE_VIOLATION then
				get STACKED DIAGNOSTICS violation_name := CONSTRAINT_NAME;
				if violation_name = lawyer_id_visit_time_key then
					result_code := 1;
				end if;
				if  violation_name= user_id_visit_time_key then
					result_code := 2;
				end if;
	end;
	if should_rollback then
		rollback;
	end if;
END;
$$

call check_consultation_on_unique_constraints(1, 4, '2023-01-20 16:00', null);



insert into users(role, name, surname, email, phone, status)
	values('LAWYER', 'ivan1', 'ivanov', 'ivanov@gmail.com', '+12345', 'ACTIVE');
insert into users(role, name, surname, email, phone, status)
	values('LAWYER', 'ivan2', 'ivanov', 'ivanov@gmail.com', '+12345', 'ACTIVE');
insert into users(role, name, surname, email, phone, status)
	values('LAWYER', 'ivan3', 'ivanov', 'ivanov@gmail.com', '+12345', 'ACTIVE');
insert into users(role, name, surname, email, phone, status)
	values('CLIENT', 'ivan4', 'ivanov', 'ivanov@gmail.com', '+12345', 'ACTIVE');
insert into users(role, name, surname, email, phone, status)
	values('CLIENT', 'ivan5', 'ivanov', 'ivanov@gmail.com', '+12345', 'ACTIVE');
	
	
insert into services(service_id, name, description)
	values(null, 'main1', 'this is main service #1');
insert into services(service_id, name, description)
	values(null, 'main2', 'this is main service #2');
insert into services(service_id, name, description)
	values(4, 'sub1', 'this is sub service qwe');
insert into services(service_id, name, description)
	values(4, 'sub2', 'ffvefwew');
insert into services(service_id, name, description)
	values(4, 'sub3', 'cccc');
insert into services(service_id, name, description)
	values(5, 'sub4', 'gdfdfsd');
insert into services(service_id, name, description)
	values(5, 'sub5', 'lalalalalal');
	
	


DO $$
DECLARE
   serviceId bigint; 
BEGIN
   insert into services(service_id, name, description)
		values(null, 'checkchekc5', 'this is main service #1') 
			returning id into serviceId;
   insert into services(service_id, name, description)
		values(serviceId, 'checkchekc6', 'this is main service #1');
END; $$

	
	
insert into lawyers(user_id, description, experience)
	values(1, 'best lawyer ever', 10.3);
insert into lawyers(user_id, description, experience)
	values(2, 'best lawyer ever', 10.3);
insert into lawyers(user_id, description, experience)
	values(3, 'best lawyer ever', 10.3);
	

insert into lawyers_has_services(lawyer_id, service_id) values(1, 4);
insert into lawyers_has_services values(1, 7);
insert into lawyers_has_services values(2, 4);
insert into lawyers_has_services values(2, 8);
insert into lawyers_has_services values(2, 9);
insert into lawyers_has_services values(3, 5);
insert into lawyers_has_services values(3, 10);


insert into reviews(user_id, description, grade, review_time) 
	values(4, 'everything is OK', 10, '2015-01-10 00:51:14');
insert into reviews(user_id, description, grade, review_time) 
	values(5, 'expected more...', 6, CURRENT_TIMESTAMP);
insert into reviews(user_id, description, grade) 
	values(5, 'new review', 7);
insert into reviews(user_id, description, grade, review_time) 
	values(5, 'expected more...', 6, null);
	
	
insert into consultations(lawyer_id, user_id, visit_time) 
	values(1, null, '2023-01-20 14:40');
insert into consultations(lawyer_id, user_id, visit_time) 
	values(2, 5, '2023-01-20 16:00');

-- continue-on-error: true
	
select * from users;

select * from services;

select * from lawyers;

select * from lawyers_has_services;

select * from reviews;

select * from consultations;


select * from affairs;
