-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema finalprojyp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema finalprojyp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `finalprojyp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `finalprojyp` ;

-- -----------------------------------------------------
-- Table `finalprojyp`.`type_literature`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalprojyp`.`type_literature` (
  `id_book_type_literature` BIGINT NOT NULL AUTO_INCREMENT,
  `name_type_literature` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_book_type_literature`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `finalprojyp`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalprojyp`.`genre` (
  `id_book_genre` BIGINT NOT NULL AUTO_INCREMENT,
  `name_genre` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_book_genre`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `finalprojyp`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalprojyp`.`book` (
  `id_book` BIGINT NOT NULL AUTO_INCREMENT,
  `author` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `genre_id` BIGINT NOT NULL,
  `type_literature_id` BIGINT NOT NULL,
  PRIMARY KEY (`id_book`),
  INDEX `FKm1t3yvw5i7olwdf32cwuul7ta` (`genre_id` ASC) VISIBLE,
  INDEX `FK4jcvs43hc8o7xb8vmh79b7s0q` (`type_literature_id` ASC) VISIBLE,
  CONSTRAINT `FK4jcvs43hc8o7xb8vmh79b7s0q`
    FOREIGN KEY (`type_literature_id`)
    REFERENCES `finalprojyp`.`type_literature` (`id_book_type_literature`),
  CONSTRAINT `FKm1t3yvw5i7olwdf32cwuul7ta`
    FOREIGN KEY (`genre_id`)
    REFERENCES `finalprojyp`.`genre` (`id_book_genre`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `finalprojyp`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalprojyp`.`user` (
  `id_user` BIGINT NOT NULL AUTO_INCREMENT,
  `active` BIT(1) NOT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `finalprojyp`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalprojyp`.`role` (
  `user_id` BIGINT NOT NULL,
  `roles` ENUM('ADMIN', 'EMPLOYEE', 'USER') NULL DEFAULT NULL,
  INDEX `FK61g3ambult7v7nh59xirgd9nf` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK61g3ambult7v7nh59xirgd9nf`
    FOREIGN KEY (`user_id`)
    REFERENCES `finalprojyp`.`user` (`id_user`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `finalprojyp`.`schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalprojyp`.`schedule` (
  `id_schedule` BIGINT NOT NULL AUTO_INCREMENT,
  `schedule` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_schedule`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `finalprojyp`.`shop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalprojyp`.`shop` (
  `id_shop` BIGINT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `book_id` BIGINT NOT NULL,
  `schedule_id` BIGINT NOT NULL,
  PRIMARY KEY (`id_shop`),
  INDEX `FK3ystvlh74rsr5ra89h63ohpvj` (`book_id` ASC) VISIBLE,
  INDEX `FKgsknmlvdvs204voeudc3nlt3x` (`schedule_id` ASC) VISIBLE,
  CONSTRAINT `FK3ystvlh74rsr5ra89h63ohpvj`
    FOREIGN KEY (`book_id`)
    REFERENCES `finalprojyp`.`book` (`id_book`),
  CONSTRAINT `FKgsknmlvdvs204voeudc3nlt3x`
    FOREIGN KEY (`schedule_id`)
    REFERENCES `finalprojyp`.`schedule` (`id_schedule`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `finalprojyp`.`user_buy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalprojyp`.`user_buy` (
  `id_user_buy` BIGINT NOT NULL AUTO_INCREMENT,
  `book_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id_user_buy`),
  INDEX `FKjset7eg8sccc9qvc5x4yoiqb3` (`book_id` ASC) VISIBLE,
  INDEX `FK7n2v1w7fs98psoe5t7nm1cfwb` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK7n2v1w7fs98psoe5t7nm1cfwb`
    FOREIGN KEY (`user_id`)
    REFERENCES `finalprojyp`.`user` (`id_user`),
  CONSTRAINT `FKjset7eg8sccc9qvc5x4yoiqb3`
    FOREIGN KEY (`book_id`)
    REFERENCES `finalprojyp`.`book` (`id_book`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
