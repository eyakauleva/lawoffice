
CREATE TABLE iF NOT EXISTS users(
	id BIGSERIAL PRIMARY KEY,
	role VARCHAR(45) NOT NULL,
	name VARCHAR(45) NOT NULL,
	surname VARCHAR(45) NOT NULL,
	email VARCHAR(45) NOT NULL,
	phone VARCHAR(45) NOT NULL,
	status VARCHAR(45) NOT NULL
);

CREATE TABLE iF NOT EXISTS services(
	id BIGSERIAL PRIMARY KEY,
	service_id BIGINT,
	name VARCHAR(45) UNIQUE NOT NULL,
	description VARCHAR(1000),
	FOREIGN KEY (service_id)
      REFERENCES services (id)
);

CREATE TABLE iF NOT EXISTS lawyers(
	id BIGSERIAL PRIMARY KEY,
	user_id BIGINT NOT NULL,
	description VARCHAR(1000) NOT NULL,
	experience REAL,
	FOREIGN KEY (user_id)
      REFERENCES users (id)
);

CREATE TABLE iF NOT EXISTS consultations(
	id BIGSERIAL PRIMARY KEY,
	lawyer_id BIGINT NOT NULL,
	user_id BIGINT,
	visit_time TIMESTAMP NOT NULL,
	FOREIGN KEY (lawyer_id)
      REFERENCES lawyers (id),
	FOREIGN KEY (user_id)
      REFERENCES users (id)
);

CREATE TABLE iF NOT EXISTS reviews(
	id BIGSERIAL PRIMARY KEY,
	user_id BIGINT NOT NULL,
	description VARCHAR(1000) NOT NULL,
	grade INT NOT NULL,
	review_time TIMESTAMP(3) NOT NULL,
	FOREIGN KEY (user_id)
      REFERENCES users (id)
);

CREATE TABLE iF NOT EXISTS affairs(
	id BIGSERIAL PRIMARY KEY,
	user_id BIGINT,
	name VARCHAR(45) UNIQUE NOT NULL,
	status VARCHAR(45) UNIQUE NOT NULL,
	description VARCHAR(1000) UNIQUE NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE,
	price DECIMAL(10, 2),
	FOREIGN KEY (user_id)
       REFERENCES users (id)
);

CREATE TABLE iF NOT EXISTS lawyers_has_services (
	lawyer_id BIGINT REFERENCES lawyers (id),
	service_id BIGINT REFERENCES services (id),
	CONSTRAINT lawyers_has_services_pkey PRIMARY KEY (lawyer_id, service_id)
);

CREATE TABLE iF NOT EXISTS affairs_has_lawyers (
	affair_id BIGINT REFERENCES affairs (id),
	lawyer_id BIGINT REFERENCES lawyers (id),
	CONSTRAINT affairs_has_lawyers_pkey PRIMARY KEY (affair_id, lawyer_id)
);
