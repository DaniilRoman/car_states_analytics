CREATE TABLE IF NOT EXISTS parameters(
	id uuid PRIMARY KEY,
	name VARCHAR (32) NOT NULL,
	type INT NOT NULL
);

INSERT INTO parameters VALUES (uuid_generate_v4(), 'Max Speed', 1);
INSERT INTO parameters VALUES (uuid_generate_v4(), 'Max Oil Volume', 1);
INSERT INTO parameters VALUES (uuid_generate_v4(), 'Year', 2);
INSERT INTO parameters VALUES (uuid_generate_v4(), 'Color', 3);

CREATE TABLE IF NOT EXISTS car_parameters(
	car_id uuid,
	parameter_id uuid,
	val VARCHAR(32),

	CONSTRAINT cars_parameters_cars_fkey FOREIGN KEY (car_id)
    REFERENCES cars (id) MATCH SIMPLE
    ON UPDATE CASCADE ON DELETE CASCADE,

    CONSTRAINT cars_parameters_parameters_fkey FOREIGN KEY (parameter_id)
    REFERENCES parameters (id) MATCH SIMPLE
    ON UPDATE CASCADE ON DELETE CASCADE
);