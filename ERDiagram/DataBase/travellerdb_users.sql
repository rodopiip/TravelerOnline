-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: travellerdb
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `profile_photo` varchar(255) DEFAULT NULL,
  `is_verified` tinyint DEFAULT NULL,
  `additional_info` varchar(255) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'John','Doe','password1','johndoe@example.com','555-1234','1980-01-01','profile1.jpg',1,'Some additional info 1','M'),(2,'Jane','Doe','password2','janedoe@example.com','555-5678','1985-02-14','profile2.jpg',1,'Some additional info 2','F'),(3,'Michael','Smith','password3','michaelsmith@example.com','555-9012','1990-03-15','profile3.jpg',0,'Some additional info 3','M'),(4,'Emily','Johnson','password4','emilyjohnson@example.com','555-3456','1982-04-22','profile4.jpg',1,'Some additional info 4','F'),(5,'David','Brown','password5','davidbrown@example.com','555-7890','1995-05-23','profile5.jpg',0,'Some additional info 5','M'),(6,'Sarah','Wilson','password6','sarahwilson@example.com','555-2345','1988-06-30','profile6.jpg',1,'Some additional info 6','F'),(7,'Christopher','Taylor','password7','christophertaylor@example.com','555-6789','1991-07-11','profile7.jpg',0,'Some additional info 7','M'),(8,'Maria','Garcia','password8','mariagarcia@example.com','555-0123','1984-08-08','profile8.jpg',1,'Some additional info 8','F'),(9,'William','Martin','password9','williammartin@example.com','555-4567','1993-09-18','profile9.jpg',0,'Some additional info 9','M'),(10,'Jessica','Lee','password10','jessicalee@example.com','555-8901','1987-10-29','profile10.jpg',1,'Some additional info 10','F');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-06  4:14:42
