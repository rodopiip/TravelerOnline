-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema travellerdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema travellerdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `travellerdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `travellerdb`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`categories` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `keywords` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
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
  `date_created` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id`),
  INDEX `posts_ibfk_2` (`category_id` ASC) VISIBLE,
  CONSTRAINT `posts_ibfk_2`
    FOREIGN KEY (`category_id`)
    REFERENCES `travellerdb`.`categories` (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`users` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(20) NULL DEFAULT NULL,
  `date_of_birth` DATE NOT NULL,
  `profile_photo` VARCHAR(255) NULL DEFAULT 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png',
  `is_verified` TINYINT NULL DEFAULT NULL,
  `additional_info` VARCHAR(255) NULL DEFAULT NULL,
  `gender` VARCHAR(2) NULL DEFAULT NULL,
  `date_added` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`posts_tag_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`posts_tag_users` (
  `post_id` INT NOT NULL,
  `tagged_user_Id` INT NOT NULL,
  PRIMARY KEY (`post_id`, `tagged_user_Id`),
  INDEX `fk_posts_tag_users_users1_idx` (`tagged_user_Id` ASC) VISIBLE,
  CONSTRAINT `post_id`
    FOREIGN KEY (`post_id`)
    REFERENCES `travellerdb`.`posts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `tagged_user_id`
    FOREIGN KEY (`tagged_user_Id`)
    REFERENCES `travellerdb`.`users` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `travellerdb` ;

-- -----------------------------------------------------
-- Table `travellerdb`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `post_id` INT NULL,
  `super_comment_id` INT NULL DEFAULT NULL,
  `rating` INT NOT NULL DEFAULT 0,
  `date_added` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `post_id` (`post_id` ASC) VISIBLE,
  INDEX `comments_ibfk_3` (`super_comment_id` ASC) VISIBLE,
  CONSTRAINT `comments_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `travellerdb`.`users` (`Id`),
  CONSTRAINT `comments_ibfk_2`
    FOREIGN KEY (`post_id`)
    REFERENCES `travellerdb`.`posts` (`id`),
  CONSTRAINT `comments_ibfk_3`
    FOREIGN KEY (`super_comment_id`)
    REFERENCES `travellerdb`.`comments` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`images` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `post_Id` INT NOT NULL,
  `URL` VARCHAR(255) NULL,
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
  `status` TINYINT NOT NULL,
  `post_id` INT NULL,
  `comment_id` INT NULL,
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
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`users_save_posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`users_save_posts` (
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  INDEX `post_id` (`post_id` ASC) VISIBLE,
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`user_id`, `post_id`),
  CONSTRAINT `saved_posts_ibfk_1`
    FOREIGN KEY (`post_id`)
    REFERENCES `travellerdb`.`posts` (`id`),
  CONSTRAINT `saved_posts_ibfk_2`
    FOREIGN KEY (`user_id`)
    REFERENCES `travellerdb`.`users` (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travellerdb`.`users_subscribe_to_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travellerdb`.`users_subscribe_to_users` (
  `subscribed_to_id` INT NOT NULL,
  `subscriber_id` INT NOT NULL,
  INDEX `followed_id` (`subscribed_to_id` ASC) VISIBLE,
  INDEX `follower_id` (`subscriber_id` ASC) VISIBLE,
  PRIMARY KEY (`subscribed_to_id`, `subscriber_id`),
  CONSTRAINT `subscriber_ibfk_1`
    FOREIGN KEY (`subscribed_to_id`)
    REFERENCES `travellerdb`.`users` (`Id`),
  CONSTRAINT `subscriber_ibfk_2`
    FOREIGN KEY (`subscriber_id`)
    REFERENCES `travellerdb`.`users` (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
