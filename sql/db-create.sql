-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema taxi
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema taxi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `taxi` DEFAULT CHARACTER SET utf8 ;
USE `taxi` ;

-- -----------------------------------------------------
-- Table `taxi`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi`.`roles` ;

CREATE TABLE IF NOT EXISTS `taxi`.`roles` (
  `id` INT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi`.`users` ;

CREATE TABLE IF NOT EXISTS `taxi`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(32) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `iduser_UNIQUE` ON `taxi`.`users` (`id` ASC) VISIBLE;

CREATE UNIQUE INDEX `login_UNIQUE` ON `taxi`.`users` (`login` ASC) VISIBLE;

CREATE UNIQUE INDEX `email_UNIQUE` ON `taxi`.`users` (`email` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `taxi`.`receipt_states`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi`.`receipt_states` ;

CREATE TABLE IF NOT EXISTS `taxi`.`receipt_states` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `state` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`receipts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi`.`receipts` ;

CREATE TABLE IF NOT EXISTS `taxi`.`receipts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `price` INT NOT NULL,
  `length` INT NOT NULL,
  `destination` VARCHAR(45) NOT NULL,
  `departure` VARCHAR(45) NOT NULL,
  `date` VARCHAR(20) NOT NULL,
  `receipt_state_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`car_states`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi`.`car_states` ;

CREATE TABLE IF NOT EXISTS `taxi`.`car_states` (
  `id` INT NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi`.`types` ;

CREATE TABLE IF NOT EXISTS `taxi`.`types` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`cars`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi`.`cars` ;

CREATE TABLE IF NOT EXISTS `taxi`.`cars` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `max_passengers` INT NOT NULL,
  `type_id` INT NOT NULL,
  `state_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`users_has_receipt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi`.`users_has_receipt` ;

CREATE TABLE IF NOT EXISTS `taxi`.`users_has_receipt` (
  `users_id` INT NOT NULL,
  `receipts_id` INT NOT NULL,
  PRIMARY KEY (`users_id`, `receipts_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`tariffs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi`.`tariffs` ;

CREATE TABLE IF NOT EXISTS `taxi`.`tariffs` (
  `id` INT NOT NULL,
  `types_id` INT NOT NULL,
  `price_for_km` INT NOT NULL,
  `length` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`receipts_has_cars`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi`.`receipts_has_cars` ;

CREATE TABLE IF NOT EXISTS `taxi`.`receipts_has_cars` (
  `receipts_id` INT NOT NULL,
  `cars_id` INT NOT NULL)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


USE taxi;

INSERT INTO car_states VALUES(1, 'available to order');
INSERT INTO car_states VALUES(2, 'is on a trip');
INSERT INTO car_states VALUES(3, 'not active');

INSERT INTO roles VALUES(1, 'user');
INSERT INTO roles VALUES(2, 'admin');

-- Public hire --
INSERT INTO tariffs VALUES(1, 1, 20, 150);
INSERT INTO tariffs VALUES(2, 1, 17, 400);
INSERT INTO tariffs VALUES(3, 1, 15, 550);
INSERT INTO tariffs VALUES(4, 1, 14, 700);

-- Minicab --
INSERT INTO tariffs VALUES(5, 2, 25, 150);
INSERT INTO tariffs VALUES(6, 2, 22, 400);
INSERT INTO tariffs VALUES(7, 2, 20, 550);
INSERT INTO tariffs VALUES(8, 2, 19, 700);

-- Minibus --
INSERT INTO tariffs VALUES(9, 3, 50, 150);
INSERT INTO tariffs VALUES(10, 3, 47, 400);
INSERT INTO tariffs VALUES(11, 3, 45, 550);
INSERT INTO tariffs VALUES(12, 3, 42, 700);

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

INSERT INTO users VALUES (DEFAULT, 'blaz333', '148151154', 'magicpechenka765@gmail.com', 2);
INSERT INTO users VALUES (DEFAULT, 'admin', '292301328316331', '123@gmail.com' , 2);
INSERT INTO users VALUES (DEFAULT, '123', '148151154', '1@gmail.com' , 1);

INSERT INTO receipt_states VALUES (1, 'created');
INSERT INTO receipt_states VALUES (2, 'confirmed');
INSERT INTO receipt_states VALUES (3, 'done');

INSERT INTO receipts VALUES (DEFAULT, 1, 1000, 5, 'Kharkivskya, 22', 'Kurskya, 58', '2021-11-05 13:17:10', 3);
INSERT INTO receipts VALUES (DEFAULT, 2, 12321, 5, 'Шевченко, 22', 'Лушпы 58', '2021-11-07 13:28:12', 3);
INSERT INTO receipts VALUES (DEFAULT, 2, 3123, 5, 'Лушпы 58', 'Шевченко, 23', '2021-11-07 17:15:30', 3);
INSERT INTO receipts VALUES (DEFAULT, 1, 1523, 5, 'Шевченко, 1', 'Лушпы 1', '2021-11-11 13:28:12', 3);
INSERT INTO receipts VALUES (DEFAULT, 1, 5456, 5, 'Шевченко, 3', 'Лушпы 17', '2021-11-11 17:25:13', 3);
INSERT INTO receipts VALUES (DEFAULT, 3, 18545, 5, 'Шевченко, 22', 'Лушпы 58', '2021-11-11 18:15:30', 3);
INSERT INTO receipts VALUES (DEFAULT, 1, 1554, 5, 'Шевченко, 21', 'Лушпы 35', '2021-11-11 18:00:10', 3);
INSERT INTO receipts VALUES (DEFAULT, 3, 33345, 5, 'Шевченко, 32', 'Лушпы 38', '2021-11-22 17:15:30', 2);
INSERT INTO receipts VALUES (DEFAULT, 1, 5435, 5, 'Шевченко, 22', 'Лушпы 42', '2021-11-22 17:25:30', 1);


INSERT INTO users_has_receipt VALUES (1, 1);
