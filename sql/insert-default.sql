USE taxi;

INSERT INTO car_states VALUES(1, 'available to order');
INSERT INTO car_states VALUES(2, 'is on a trip');
INSERT INTO car_states VALUES(3, 'not active');

INSERT INTO roles VALUES(1, 'user');
INSERT INTO roles VALUES(2, 'admin');

-- Public hire --
INSERT INTO tariffs VALUES(1, 20, 1.5);
INSERT INTO tariffs VALUES(2, 17, 4);
INSERT INTO tariffs VALUES(3, 15, 5.5);
INSERT INTO tariffs VALUES(4, 14, 7);

-- Minicab --
INSERT INTO tariffs VALUES(5, 25, 1.5);
INSERT INTO tariffs VALUES(6, 22, 4);
INSERT INTO tariffs VALUES(7, 20, 5.5);
INSERT INTO tariffs VALUES(8, 19, 7);

-- Minibus --
INSERT INTO tariffs VALUES(9, 50, 1.5);
INSERT INTO tariffs VALUES(10, 47, 4);
INSERT INTO tariffs VALUES(11, 45, 5.5);
INSERT INTO tariffs VALUES(12, 42, 7);

INSERT INTO types_has_tariffs VALUES (1, 1);
INSERT INTO types_has_tariffs VALUES (1, 2);
INSERT INTO types_has_tariffs VALUES (1, 3);
INSERT INTO types_has_tariffs VALUES (1, 4);


INSERT INTO types_has_tariffs VALUES (2, 5);
INSERT INTO types_has_tariffs VALUES (2, 6);
INSERT INTO types_has_tariffs VALUES (2, 7);
INSERT INTO types_has_tariffs VALUES (2, 8);

INSERT INTO types_has_tariffs VALUES (3, 9);
INSERT INTO types_has_tariffs VALUES (3, 10);
INSERT INTO types_has_tariffs VALUES (3, 11);
INSERT INTO types_has_tariffs VALUES (3, 12);

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

INSERT receipts VALUES (DEFAULT, 1, 2, 100, 5, 'Kharkivskya, 22', 'Kurskya, 58', '2021-11-05 17:15:30', 3);