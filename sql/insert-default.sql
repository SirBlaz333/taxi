USE taxi;

INSERT INTO car_states VALUES(1, 'available to order');
INSERT INTO car_states VALUES(2, 'is on a trip');
INSERT INTO car_states VALUES(3, 'not active');

INSERT INTO roles VALUES(1, 'user');
INSERT INTO roles VALUES(2, 'admin');

INSERT INTO tariffs VALUES(1, 1, 15);
INSERT INTO tariffs VALUES(2, 2, 23);
INSERT INTO tariffs VALUES(3, 3, 35);

INSERT INTO discounts VALUES (15000, 5);
INSERT INTO discounts VALUES (35000, 10);
INSERT INTO discounts VALUES (75000, 15);

INSERT INTO types VALUES (1, 'Public hire taxi');
INSERT INTO types VALUES (2, 'Minicab');
INSERT INTO types VALUES (3, 'Minibus');

INSERT INTO cars VALUES (DEFAULT, 4, 1, 1);
INSERT INTO cars VALUES (DEFAULT, 4, 1, 1);
INSERT INTO cars VALUES (DEFAULT, 5, 1, 1);
INSERT INTO cars VALUES (DEFAULT, 6, 1, 1);
INSERT INTO cars VALUES (DEFAULT, 30, 3, 1);
INSERT INTO cars VALUES (DEFAULT, 40, 3, 1);
INSERT INTO cars VALUES (DEFAULT, 60, 3, 1);
INSERT INTO cars VALUES (DEFAULT, 6, 2, 1);
INSERT INTO cars VALUES (DEFAULT, 8, 2, 1);
INSERT INTO cars VALUES (DEFAULT, 8, 2, 1);
INSERT INTO cars VALUES (DEFAULT, 8, 2, 1);

INSERT INTO users VALUES (DEFAULT, 'blaz333', '148151154', 'magicpechenka765@gmail.com', 2, 0, 'Admin');
INSERT INTO users VALUES (DEFAULT, 'admin', '292301328316331', '123@gmail.com' , 2, 0, 'admin');
INSERT INTO users VALUES (DEFAULT, '123', '148151154', '1@gmail.com' , 1, 0, '123');

INSERT INTO receipt_states VALUES (1, 'created');
INSERT INTO receipt_states VALUES (2, 'confirmed');
INSERT INTO receipt_states VALUES (3, 'done');

INSERT INTO receipts VALUES (DEFAULT, 1, 1000, 5, 'Kharkivskya, 22', 'Kurskya, 58', '2021-11-05 13:17:10', 3, 1);
INSERT INTO receipts VALUES (DEFAULT, 2, 12321, 5, 'Шевченко, 22', 'Лушпы 58', '2021-11-07 13:28:12', 3, 2);
INSERT INTO receipts VALUES (DEFAULT, 2, 3123, 5, 'Лушпы 58', 'Шевченко, 23', '2021-11-07 17:15:30', 3, 3);
INSERT INTO receipts VALUES (DEFAULT, 1, 1523, 5, 'Шевченко, 1', 'Лушпы 1', '2021-11-11 13:28:12', 3, 3);
INSERT INTO receipts VALUES (DEFAULT, 1, 5456, 5, 'Шевченко, 3', 'Лушпы 17', '2021-11-11 17:25:13', 3, 3);
INSERT INTO receipts VALUES (DEFAULT, 3, 18545, 5, 'Шевченко, 22', 'Лушпы 58', '2021-11-11 18:15:30', 3, 3);
INSERT INTO receipts VALUES (DEFAULT, 1, 1554, 5, 'Шевченко, 21', 'Лушпы 35', '2021-11-11 18:00:10', 3, 3);
INSERT INTO receipts VALUES (DEFAULT, 3, 33345, 5, 'Шевченко, 32', 'Лушпы 38', '2021-11-22 17:15:30', 2, 3);
INSERT INTO receipts VALUES (DEFAULT, 1, 5435, 5, 'Шевченко, 22', 'Лушпы 42', '2021-11-22 17:25:30', 1, 3);

INSERT INTO users_has_receipt VALUES (1, 1);
INSERT INTO users_has_receipt VALUES (2, 2);
INSERT INTO users_has_receipt VALUES (3, 2);
INSERT INTO users_has_receipt VALUES (4, 1);
INSERT INTO users_has_receipt VALUES (5, 1);
INSERT INTO users_has_receipt VALUES (6, 3);
INSERT INTO users_has_receipt VALUES (7, 1);
INSERT INTO users_has_receipt VALUES (8, 3);
INSERT INTO users_has_receipt VALUES (9, 1);