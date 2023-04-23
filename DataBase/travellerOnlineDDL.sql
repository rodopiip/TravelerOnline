-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema travellerdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema travellerdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `travellerdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `travellerdb` ;

-- -----------------------------------------------------
-- Table `travellerdb`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`categories` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `post_id` INT NULL DEFAULT NULL,
  `rating` INT NOT NULL,
  `super_comment_id` INT NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  `date_added` DATETIME(6) NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`users` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(255) NULL DEFAULT NULL,
  `date_of_birth` DATE NOT NULL,
  `profile_photo` VARCHAR(255) NULL DEFAULT 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png',
  `is_verified` BIT(1) NULL DEFAULT NULL,
  `additional_info` VARCHAR(255) NULL DEFAULT NULL,
  `gender` VARCHAR(255) NULL DEFAULT NULL,
  `date_added` TIMESTAMP NOT NULL,
  `verification_code` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`posts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `title` VARCHAR(30) NOT NULL,
  `description` TEXT NOT NULL,
  `location` VARCHAR(255) NULL DEFAULT NULL,
  `category_id` INT NOT NULL,
  `video_url` VARCHAR(255) NULL DEFAULT NULL,
  `date_created` TIMESTAMP NOT NULL,
  `rating` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `posts_ibfk_2` (`category_id` ASC) VISIBLE,
  INDEX `FK5lidm6cqbc7u4xhqpxm898qme` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK5lidm6cqbc7u4xhqpxm898qme`
    FOREIGN KEY (`user_id`)
    REFERENCES `travellerdb`.`users` (`Id`),
  CONSTRAINT `FKijnwr3brs8vaosl80jg9rp7uc`
    FOREIGN KEY (`category_id`)
    REFERENCES `travellerdb`.`categories` (`Id`),
  CONSTRAINT `posts_ibfk_2`
    FOREIGN KEY (`category_id`)
    REFERENCES `travellerdb`.`categories` (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`images` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `post_id` INT NOT NULL,
  `url` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKcp0pycisii8ub3q4b7x5mfpn1` (`post_id` ASC) VISIBLE,
  CONSTRAINT `FKcp0pycisii8ub3q4b7x5mfpn1`
    FOREIGN KEY (`post_id`)
    REFERENCES `travellerdb`.`posts` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`reactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`reactions` (
  `comment_id` INT NULL DEFAULT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `post_id` INT NULL DEFAULT NULL,
  `status` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK49rifnmyo1sd243acaysemlbw` (`comment_id` ASC) VISIBLE,
  INDEX `FKh8b4h9wybhu8tc5w11e8t3krc` (`post_id` ASC) VISIBLE,
  INDEX `FKqmewaibcp5bxtlqxc2cawhuln` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK49rifnmyo1sd243acaysemlbw`
    FOREIGN KEY (`comment_id`)
    REFERENCES `travellerdb`.`comments` (`id`),
  CONSTRAINT `FKh8b4h9wybhu8tc5w11e8t3krc`
    FOREIGN KEY (`post_id`)
    REFERENCES `travellerdb`.`posts` (`id`),
  CONSTRAINT `FKqmewaibcp5bxtlqxc2cawhuln`
    FOREIGN KEY (`user_id`)
    REFERENCES `travellerdb`.`users` (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`users_save_posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`users_save_posts` (
  `post_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`post_id`, `user_id`),
  INDEX `FKt7p1g1dikbsmngtfd8xxaxqob` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKt7p1g1dikbsmngtfd8xxaxqob`
    FOREIGN KEY (`user_id`)
    REFERENCES `travellerdb`.`users` (`Id`),
  CONSTRAINT `FKtmcrb0sjs62auoorcsifhsvrp`
    FOREIGN KEY (`post_id`)
    REFERENCES `travellerdb`.`posts` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`users_subscribe_to_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`users_subscribe_to_users` (
  `subscribed_to_id` INT NOT NULL,
  `subscriber_id` INT NOT NULL,
  PRIMARY KEY (`subscribed_to_id`, `subscriber_id`),
  INDEX `FKhf0akplnsfyjv308d56s54pxv` (`subscriber_id` ASC) VISIBLE,
  CONSTRAINT `FKhf0akplnsfyjv308d56s54pxv`
    FOREIGN KEY (`subscriber_id`)
    REFERENCES `travellerdb`.`users` (`Id`),
  CONSTRAINT `FKr4km7vqrgap8pobyqvjcw3vbt`
    FOREIGN KEY (`subscribed_to_id`)
    REFERENCES `travellerdb`.`users` (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
