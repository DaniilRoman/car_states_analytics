CREATE TABLE IF NOT EXISTS cars(
	id uuid PRIMARY KEY,
	brand VARCHAR (24) NOT NULL,
	model VARCHAR (24) NOT NULL,
	owner_id uuid,

	CONSTRAINT cars_account_id_fkey FOREIGN KEY (owner_id)
    REFERENCES account (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE SET NULL
);
