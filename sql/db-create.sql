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
-- Table `taxi`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi`.`users` ;

CREATE TABLE IF NOT EXISTS `taxi`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(32) NOT NULL,
  `role` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `iduser_UNIQUE` ON `taxi`.`users` (`id` ASC) VISIBLE;

CREATE UNIQUE INDEX `login_UNIQUE` ON `taxi`.`users` (`login` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `taxi`.`cars`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi`.`cars` ;

CREATE TABLE IF NOT EXISTS `taxi`.`cars` (
  `id` INT NOT NULL,
  `max_passengers` INT NOT NULL,
  `type` VARCHAR(15) NOT NULL,
  `state` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`tariffs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi`.`tariffs` ;

CREATE TABLE IF NOT EXISTS `taxi`.`tariffs` (
  `id` INT NOT NULL,
  `coefficient` INT NOT NULL,
  `lenght` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`receipts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxi`.`receipts` ;

CREATE TABLE IF NOT EXISTS `taxi`.`receipts` (
  `id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `cars_id` INT NOT NULL,
  `price` INT NOT NULL,
  `length` INT NOT NULL,
  `destination` VARCHAR(45) NOT NULL,
  `departure` VARCHAR(45) NOT NULL,
  `tariffs_id` INT NOT NULL,
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
