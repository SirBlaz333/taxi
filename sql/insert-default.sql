USE taxi;

INSERT INTO car_states VALUES(1, 'available to order');
INSERT INTO car_states VALUES(2, 'is on a trip');
INSERT INTO car_states VALUES(3, 'not active');

INSERT INTO roles VALUES(1, 'user');
INSERT INTO roles VALUES(2, 'admin');

INSERT INTO tariffs VALUES(1, 1, 1.5);
INSERT INTO tariffs VALUES(2, 0.9, 4);
INSERT INTO tariffs VALUES(3, 0.8, 5.5);
INSERT INTO tariffs VALUES(4, 0.7, 7);

INSERT INTO types VALUES (1, 'Public hire taxi');
INSERT INTO types VALUES (2, 'Minicab');
INSERT INTO types VALUES (3, 'Minibus');

INSERT INTO cars VALUES (DEFAULT, 4, 1, 1);
INSERT INTO cars VALUES (DEFAULT, 30, 3, 2);
INSERT INTO cars VALUES (DEFAULT, 6, 2, 1);

INSERT INTO users VALUES (DEFAULT, 'ivanov', 'ivanov', 'ivanov@gmail.com', 1);
INSERT INTO users VALUES (DEFAULT, 'petrov', 'petrov', 'petrov@gmail.com', 1);
INSERT INTO users VALUES (DEFAULT, 'destroyer2007', 'asdf', 'asdf@mail.ru',  1);
INSERT INTO users VALUES (DEFAULT, 'blaz333', '123', 'magicpechenka765@gmail.com', 2);
INSERT INTO users VALUES (DEFAULT, 'admin', 'admin', '123@gmail.com' , 2);

INSERT INTO receipt_states VALUES (1, 'created');
INSERT INTO receipt_states VALUES (2, 'confirmed');
INSERT INTO receipt_states VALUES (3, 'done');

INSERT receipts VALUES (DEFAULT, 1, 2, 100, 5, 2, 'Kharkivskya, 22', 'Kurskya, 58', '2021-11-05 17:15:30', 3);