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
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `description` text,
  `Location` varchar(255) DEFAULT NULL,
  `additional_info` text,
  `town_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `town_id` (`town_id`),
  KEY `posts_ibfk_2` (`category_id`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`town_id`) REFERENCES `towns` (`Id`),
  CONSTRAINT `posts_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,1,'My travel diary in Bali','In this post, I share my experience of traveling to Bali. I visited some of the best beaches and cultural landmarks in Bali, and I highly recommend visiting this beautiful island.','Bali, Indonesia','I stayed in a beachfront resort in Seminyak, which was absolutely amazing. The resort had great facilities and a stunning view of the ocean.',1,1,'https://www.youtube.com/watch?v=8WvvBTb_JvE'),(2,2,'Exploring the beauty of Santorini','Santorini is a stunning Greek island with blue waters, white buildings, and breathtaking views. In this post, I share my experience of exploring the island.','Santorini, Greece','I visited some of the best beaches on the island, including Perissa and Kamari. I also visited the famous Red Beach, which was definitely worth the hike.',2,1,'https://www.youtube.com/watch?v=H7VfKD6RVQU'),(3,3,'A foodie\'s guide to Tokyo','Tokyo is a food lover\'s paradise, and in this post, I share some of the best food experiences I had in the city. From sushi to ramen, Tokyo has it all!','Tokyo, Japan','I highly recommend trying the sushi at Tsukiji fish market and the ramen at Ichiran. I also visited a few izakayas, which are Japanese pubs, and tried some delicious yakitori.',3,2,'https://www.youtube.com/watch?v=zRrFyKT_BfI'),(4,4,'The best views of Paris','Paris is known for its stunning architecture and breathtaking views, and in this post, I share my experience of seeing some of the best views in the city.','Paris, France','I visited the Eiffel Tower, the Arc de Triomphe, and the Sacré-Cœur Basilica, which all have stunning views of the city. I also took a boat tour on the Seine River, which was a great way to see the city from a different perspective.',4,3,'https://www.youtube.com/watch?v=62k6Z84q9ZI'),(5,1,'My travel diary in Bali','In this post, I share my experience of traveling to Bali. I visited some of the best beaches and cultural landmarks in Bali, and I highly recommend visiting this beautiful island.','Bali, Indonesia','I stayed in a beachfront resort in Seminyak, which was absolutely amazing. The resort had great facilities and a stunning view of the ocean.',1,1,'https://www.youtube.com/watch?v=8WvvBTb_JvE'),(6,2,'Exploring the beauty of Santorini','Santorini is a stunning Greek island with blue waters, white buildings, and breathtaking views. In this post, I share my experience of exploring the island.','Santorini, Greece','I visited some of the best beaches on the island, including Perissa and Kamari. I also visited the famous Red Beach, which was definitely worth the hike.',2,1,'https://www.youtube.com/watch?v=H7VfKD6RVQU'),(7,3,'A foodie\'s guide to Tokyo','Tokyo is a food lover\'s paradise, and in this post, I share some of the best food experiences I had in the city. From sushi to ramen, Tokyo has it all!','Tokyo, Japan','I highly recommend trying the sushi at Tsukiji fish market and the ramen at Ichiran. I also visited a few izakayas, which are Japanese pubs, and tried some delicious yakitori.',3,2,'https://www.youtube.com/watch?v=zRrFyKT_BfI'),(8,4,'The best views of Paris','Paris is known for its stunning architecture and breathtaking views, and in this post, I share my experience of seeing some of the best views in the city.','Paris, France','I visited the Eiffel Tower, the Arc de Triomphe, and the Sacré-Cœur Basilica, which all have stunning views of the city. I also took a boat tour on the Seine River, which was a great way to see the city from a different perspective.',4,3,'https://www.youtube.com/watch?v=62k6Z84q9ZI');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
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
