CREATE TABLE IF NOT EXISTS car_route (
	id uuid PRIMARY KEY,
	car_id uuid NOT NULL,
	user_id uuid NOT NULL,
	created DATE NOT NULL,

	CONSTRAINT route_cars_fkey FOREIGN KEY (car_id)
    REFERENCES cars (id) MATCH SIMPLE
    ON UPDATE CASCADE ON DELETE CASCADE,

    CONSTRAINT route_users_fkey FOREIGN KEY (user_id)
    REFERENCES account (id) MATCH SIMPLE
    ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS route_mark (
	id uuid PRIMARY KEY,
	route_id uuid NOT NULL,
	time_mark timestamp NOT NULL,
	x VARCHAR(24) NOT NULL,
	y VARCHAR(24) NOT NULL,
	z VARCHAR(24) NOT NULL,
	speed FLOAT NOT NULL,
	oil FLOAT NOT NULL,

	CONSTRAINT mark_route_fkey FOREIGN KEY (route_id)
    REFERENCES car_route (id) MATCH SIMPLE
    ON UPDATE CASCADE ON DELETE CASCADE
);