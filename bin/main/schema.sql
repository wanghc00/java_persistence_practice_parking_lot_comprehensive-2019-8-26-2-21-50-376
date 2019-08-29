CREATE TABLE employee (
  id INTEGER PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  age   int(4) NOT NULL
);

CREATE TABLE parkingLot (
   id VARCHAR(64) NOT NULL PRIMARY KEY,
   capacity int(3) NOT NULL
);