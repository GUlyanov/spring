delete from products;
delete from users;
insert into users(username) values ('Иванов Г.В.');
insert into products(userid, accnumber, accrest, prodtype)
	select us.id, '40702810312345678901', 2160.30, 'ACCOUNT' from users us where   us.username = 'Иванов Г.В.';
insert into products(userid, accnumber, accrest, prodtype)
    select us.id, '40702840027467738901', 53288.20, 'ACCOUNT' from users us where   us.username = 'Иванов Г.В.';
insert into products(userid, accnumber, accrest, prodtype)
    select us.id,  '123456789012', 50000.00, 'CARD' from users us where   us.username = 'Иванов Г.В.';
insert into users(username) values ('Сидоров П.Т.');
insert into products(userid, accnumber, accrest, prodtype)
    select us.id,  '40702810923456278192', 5672.11, 'ACCOUNT' from users us where   us.username = 'Сидоров П.Т.';
insert into products(userid, accnumber, accrest, prodtype)
    select us.id,  '40702810993333948192', 11340.57, 'ACCOUNT' from users us where   us.username = 'Сидоров П.Т.';
insert into products(userid, accnumber, accrest, prodtype)
    select us.id,  '102938475612', 10000.00, 'CARD' from users us where   us.username = 'Сидоров П.Т.';
insert into products(userid, accnumber, accrest, prodtype)
    select us.id,  '302963847563', 40000.00, 'CARD' from users us where   us.username = 'Сидоров П.Т.';
insert into users(username) values ('Теткин Ю.М.');
insert into products(userid, accnumber, accrest, prodtype)
    select us.id,  '407028107486723232310', 100000.00, 'ACCOUNT' from users us where   us.username = 'Теткин Ю.М.';
insert into products(userid, accnumber, accrest, prodtype)
    select us.id,  '67458793891', 46723.54, 'CARD' from users us where   us.username = 'Теткин Ю.М.';


