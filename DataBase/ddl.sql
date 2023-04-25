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
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`users` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
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
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`posts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `location` VARCHAR(255) NULL DEFAULT NULL,
  `additional_info` TEXT NULL DEFAULT NULL,
  `category_id` INT NOT NULL,
  `video_url` VARCHAR(255) NULL DEFAULT NULL,
  `date_created` TIMESTAMP NOT NULL,
  `rating` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `posts_ibfk_2` (`category_id` ASC) VISIBLE,
  INDEX `FK5lidm6cqbc7u4xhqpxm898qme` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK5lidm6cqbc7u4xhqpxm898qme`
    FOREIGN KEY (`user_id`)
    REFERENCES `travellerdb`.`users` (`Id`),
  CONSTRAINT `posts_ibfk_2`
    FOREIGN KEY (`category_id`)
    REFERENCES `travellerdb`.`categories` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `post_id` INT NULL DEFAULT NULL,
  `super_comment_id` INT NULL DEFAULT NULL,
  `rating` INT NOT NULL DEFAULT '0',
  `date_added` DATETIME(6) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `post_id` (`post_id` ASC) VISIBLE,
  INDEX `comments_ibfk_3` (`super_comment_id` ASC) VISIBLE,
  CONSTRAINT `comments_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `travellerdb`.`users` (`Id`),
  CONSTRAINT `comments_ibfk_2`
    FOREIGN KEY (`post_id`)
    REFERENCES `travellerdb`.`posts` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `comments_ibfk_3`
    FOREIGN KEY (`super_comment_id`)
    REFERENCES `travellerdb`.`comments` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`images` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `post_Id` INT NOT NULL,
  `URL` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `post_Id` (`post_Id` ASC) VISIBLE,
  CONSTRAINT `images_ibfk_1`
    FOREIGN KEY (`post_Id`)
    REFERENCES `travellerdb`.`posts` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`reactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`reactions` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `status` INT NOT NULL,
  `post_id` INT NULL DEFAULT NULL,
  `comment_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `post_id` (`post_id` ASC) VISIBLE,
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `reactions_ibfk_2` (`comment_id` ASC) VISIBLE,
  CONSTRAINT `reactions_ibfk_1`
    FOREIGN KEY (`post_id`)
    REFERENCES `travellerdb`.`posts` (`id`),
  CONSTRAINT `reactions_ibfk_2`
    FOREIGN KEY (`comment_id`)
    REFERENCES `travellerdb`.`comments` (`id`),
  CONSTRAINT `reactions_ibfk_3`
    FOREIGN KEY (`user_id`)
    REFERENCES `travellerdb`.`users` (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`users_save_posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`users_save_posts` (
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `post_id`),
  INDEX `post_id` (`post_id` ASC) VISIBLE,
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `saved_posts_ibfk_1`
    FOREIGN KEY (`post_id`)
    REFERENCES `travellerdb`.`posts` (`id`),
  CONSTRAINT `saved_posts_ibfk_2`
    FOREIGN KEY (`user_id`)
    REFERENCES `travellerdb`.`users` (`Id`))
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
  INDEX `followed_id` (`subscribed_to_id` ASC) VISIBLE,
  INDEX `follower_id` (`subscriber_id` ASC) VISIBLE,
  CONSTRAINT `subscriber_ibfk_1`
    FOREIGN KEY (`subscribed_to_id`)
    REFERENCES `travellerdb`.`users` (`Id`),
  CONSTRAINT `subscriber_ibfk_2`
    FOREIGN KEY (`subscriber_id`)
    REFERENCES `travellerdb`.`users` (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
