CREATE TABLE parkingLot (
   id VARCHAR(3) NOT NULL PRIMARY KEY,
   capacity int(3) NOT NULL,
   availablePositionCount int(3),
   employeeId int(3)
);