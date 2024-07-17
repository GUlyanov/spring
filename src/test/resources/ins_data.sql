DO $$
DECLARE userId bigint;
BEGIN
DELETE FROM products;
DELETE FROM users;
INSERT INTO users(username) VALUES ("Иванов Г.В.") RETURNING id INTO userId;
INSERT INTO products(userid, accnumber, accrest, prodtype) VALUES (userId, "40702810312345678901", 2160.30, "ACCOUNT");
INSERT INTO products(userid, accnumber, accrest, prodtype) VALUES (userId, "40702840027467738901", 53288.20, "ACCOUNT");
INSERT INTO products(userid, accnumber, accrest, prodtype) VALUES (userId, "123456789012", 50000.00, "CARD");
INSERT INTO users(username) VALUES ("Сидоров П.Т.") RETURNING id INTO userId;
INSERT INTO products(userid, accnumber, accrest, prodtype) VALUES (userId, "40702810923456278192", 5672.11, "ACCOUNT");
INSERT INTO products(userid, accnumber, accrest, prodtype) VALUES (userId, "40702810993333948192", 11340.57, "ACCOUNT");
INSERT INTO products(userid, accnumber, accrest, prodtype) VALUES (userId, "102938475612", 10000.00, "CARD");
INSERT INTO products(userid, accnumber, accrest, prodtype) VALUES (userId, "302963847563", 40000.00, "CARD");
INSERT INTO users(username) VALUES ("Теткин Ю.М.") RETURNING id INTO userId;
INSERT INTO products(userid, accnumber, accrest, prodtype) VALUES (userId, "407028107486723232310", 100000.00, "ACCOUNT");
INSERT INTO products(userid, accnumber, accrest, prodtype) VALUES (userId, "67458793891", 46723.54, "CARD");
COMMIT;
END $$;
