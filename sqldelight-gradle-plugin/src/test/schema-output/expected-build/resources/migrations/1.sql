CREATE TABLE person (
  id INTEGER NOT NULL PRIMARY KEY,
  name TEXT NOT NULL,
  friends TEXT
);

CREATE TABLE dog (
  name VARCHAR(8) NOT NULL,
  breed VARCHAR(40) NOT NULL,
  owner INTEGER NOT NULL REFERENCES person(id)
);

CREATE VIEW bad_name_dogs AS
SELECT dog.name, dog.breed
FROM dog
WHERE name = breed;