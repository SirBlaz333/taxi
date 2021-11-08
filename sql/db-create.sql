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
  `car_id` INT NOT NULL,
  `price` INT NOT NULL,
  `length` INT NOT NULL,
  `destination` VARCHAR(45) NOT NULL,
  `departure` VARCHAR(45) NOT NULL,
  `date` VARCHAR(20) NOT NULL,
  `receipt_state_id` INT NOT NULL,
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
