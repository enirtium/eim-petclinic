
DROP TABLE IF EXISTS vets;
DROP SEQUENCE IF EXISTS all_seq;

CREATE SEQUENCE all_seq;

CREATE TABLE vets (
  id         INTEGER  PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30)
);
CREATE INDEX vets_last_name ON vets (last_name);

