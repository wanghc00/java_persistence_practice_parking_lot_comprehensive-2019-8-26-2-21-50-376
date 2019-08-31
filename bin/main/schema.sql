CREATE TABLE employee (
  id int(3) PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  age   int(3) NOT NULL
);

CREATE TABLE parkingLot (
   id VARCHAR(3) NOT NULL PRIMARY KEY,
   capacity int(3) NOT NULL,
   availablePositionCount int(3),
   employeeId int(3)
);

insert into employee values (1, 'zhangsan', 18);
insert into employee values (2, 'zhangsan', 18);
insert into employee values (3, 'zhangsan', 18);

insert into parkingLot (id, capacity, employeeId) values ('1', 10, 1);
insert into parkingLot (id, capacity, employeeId) values ('2', 20, 2);
insert into parkingLot (id, capacity, employeeId) values ('3', 30, 3);
insert into parkingLot (id, capacity, employeeId) values ('4', 40, 1);
