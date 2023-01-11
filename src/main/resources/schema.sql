CREATE SCHEMA IF NOT EXISTS law_office_schema;

SET search_path = law_office_schema, "$user", public;

CREATE TABLE iF NOT EXISTS users(
	id BIGSERIAL,
	role VARCHAR(45) NOT NULL,
	name VARCHAR(45) NOT NULL,
	surname VARCHAR(45) NOT NULL,
	email VARCHAR(45) NOT NULL,
	phone VARCHAR(45) NOT NULL,
	status VARCHAR(45) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE iF NOT EXISTS services(
	id BIGSERIAL,
	service_id BIGINT NULL,
	name VARCHAR(45) UNIQUE NOT NULL,
	description VARCHAR(1000) NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (service_id) REFERENCES services (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE iF NOT EXISTS lawyers(
	id BIGSERIAL,
	user_id BIGINT NOT NULL,
	description VARCHAR(1000) NOT NULL,
	experience REAL NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE iF NOT EXISTS consultations(
	id BIGSERIAL,
	lawyer_id BIGINT NOT NULL,
	user_id BIGINT NULL,
	visit_time TIMESTAMP NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (lawyer_id, visit_time),
	UNIQUE (user_id, visit_time),
	FOREIGN KEY (lawyer_id) REFERENCES lawyers (id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE iF NOT EXISTS reviews(
	id BIGSERIAL,
	user_id BIGINT NOT NULL,
	description VARCHAR(1000) NOT NULL,
	grade INT NOT NULL,
	review_time TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE iF NOT EXISTS affairs(
	id BIGSERIAL,
	user_id BIGINT NULL,
	name VARCHAR(45) UNIQUE NOT NULL,
	status VARCHAR(45) UNIQUE NOT NULL,
	description VARCHAR(1000) UNIQUE NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NULL,
	price DECIMAL(10, 2),
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE iF NOT EXISTS lawyers_has_services (
	lawyer_id BIGINT NOT NULL,
	service_id BIGINT NOT NULL,
	PRIMARY KEY (lawyer_id, service_id),
	FOREIGN KEY (lawyer_id) REFERENCES lawyers (id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (service_id) REFERENCES services (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE iF NOT EXISTS affairs_has_lawyers (
	affair_id BIGINT NOT NULL,
	lawyer_id BIGINT NOT NULL,
	PRIMARY KEY (affair_id, lawyer_id),
    FOREIGN KEY (affair_id) REFERENCES affairs (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (lawyer_id) REFERENCES lawyers (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE OR REPLACE PROCEDURE check_consultation_on_unique_constraints(
	lawyer_id bigint,
	user_id bigint,
	visit_time timestamp,
	result_code OUT INT)
LANGUAGE plpgsql AS
'
DECLARE
   lawyer_id_visit_time_key TEXT := ''consultations_lawyer_id_visit_time_key'';
   user_id_visit_time_key TEXT := ''consultations_user_id_visit_time_key'';
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
END; '