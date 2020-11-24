CREATE TABLE IF NOT EXISTS role(
  id uuid PRIMARY KEY,
  name VARCHAR (255) UNIQUE NOT NULL,
  created DATE NOT NULL,
  updated DATE NOT NULL
);


ALTER TABLE account
  ADD COLUMN created DATE NOT NULL,
  ADD COLUMN updated DATE NOT NULL;


CREATE TABLE IF NOT EXISTS account_role
(
  account_id uuid NOT NULL,
  role_id uuid NOT NULL,

  PRIMARY KEY (account_id, role_id),

  CONSTRAINT account_role_role_id_fkey FOREIGN KEY (role_id)
  REFERENCES role (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,

  CONSTRAINT account_role_user_id_fkey FOREIGN KEY (account_id)
  REFERENCES account (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);