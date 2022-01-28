-- MySQL dump 10.13  Distrib 8.0.27, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: budget
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `account_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `type` varchar(45) NOT NULL,
  `acct_number` int NOT NULL,
  `bank_name` varchar(45) NOT NULL,
  `start_balance` double NOT NULL,
  `current_balance` double NOT NULL,
  PRIMARY KEY (`account_id`),
  KEY `userID_idx` (`user_id`),
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (7,65,'Checking',1234,'asdf',123,123),(8,65,'Savings',1,'1',1,1),(9,65,'College',1,'1',1,1),(10,65,'Retirement',1,'1',1,1),(11,66,'Checking',123456,'mtbank',700.8,700.8),(12,76,'Savings',123456789,'M&T Bank',50900,50900),(13,77,'Checking',1234,'bank one',5000,5000),(14,78,'Checking',123,'adsf',123,123),(15,80,'Checking',123,'asdf',123,123),(16,81,'Checking',123,'asdf',123,123),(17,81,'Savings',123,'asdf',123,123),(18,82,'Checking',1234,'mt bank',10000,10000),(19,82,'Savings',2345,'mt bank',20000,20000),(20,82,'Retirement',1234,'bank',100000,100000),(21,83,'Checking',1,'asdf',1,1),(22,84,'Checking',1234,'mt bank',1000,1000);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `budget`
--

DROP TABLE IF EXISTS `budget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `budget` (
  `budget_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `monthly_income` double NOT NULL,
  PRIMARY KEY (`budget_id`),
  KEY `budget.userID_idx` (`user_id`),
  CONSTRAINT `budget.userID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budget`
--

LOCK TABLES `budget` WRITE;
/*!40000 ALTER TABLE `budget` DISABLE KEYS */;
INSERT INTO `budget` VALUES (3,65,100000),(4,67,10000),(5,69,15000),(6,77,100000),(7,78,1000),(8,79,5000),(9,83,20000);
/*!40000 ALTER TABLE `budget` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `custom_goals`
--

DROP TABLE IF EXISTS `custom_goals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `custom_goals` (
  `custom_goal_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`custom_goal_id`),
  KEY `customgoal.userID_idx` (`user_id`),
  CONSTRAINT `customgoal.userID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom_goals`
--

LOCK TABLES `custom_goals` WRITE;
/*!40000 ALTER TABLE `custom_goals` DISABLE KEYS */;
INSERT INTO `custom_goals` VALUES (1,65,'name',100),(2,65,'vacation',5000);
/*!40000 ALTER TABLE `custom_goals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deposit`
--

DROP TABLE IF EXISTS `deposit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deposit` (
  `deposit_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `amount` double NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`deposit_id`),
  KEY `deposit.userID_idx` (`user_id`),
  CONSTRAINT `deposit.userID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deposit`
--

LOCK TABLES `deposit` WRITE;
/*!40000 ALTER TABLE `deposit` DISABLE KEYS */;
INSERT INTO `deposit` VALUES (2,65,234,'2022-01-16 13:51:53','Checking'),(3,65,23,'2022-01-16 18:16:43','Savings');
/*!40000 ALTER TABLE `deposit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expense` (
  `expense_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `type` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `amount` double NOT NULL,
  `date_added` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`expense_id`),
  KEY `userid_idx` (`user_id`),
  KEY `exepese.categoryid_idx` (`category`),
  KEY `expense.expensetypeid_idx` (`type`),
  CONSTRAINT `expense.userID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense`
--

LOCK TABLES `expense` WRITE;
/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
INSERT INTO `expense` VALUES (3,65,'Fixed Expense','Entertainment',234,'2022-01-16 13:50:52'),(4,67,'Fixed Expense','Pet Food',75,'2022-01-16 18:04:08'),(5,65,'Fixed Expense','Other Flexible Expense',34,'2022-01-16 18:09:09'),(6,83,'Fixed Expense','Gym Membership',0,'2022-01-18 18:33:29'),(7,83,'Fixed Expense','Alcohol',75,'2022-01-18 18:33:37'),(8,83,'Fixed Expense','Gym Membership',200,'2022-01-18 18:53:43');
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixed_exp`
--

DROP TABLE IF EXISTS `fixed_exp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fixed_exp` (
  `fixed_exp_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `category` varchar(50) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`fixed_exp_id`),
  KEY `fixedexp.userID_idx` (`user_id`),
  CONSTRAINT `fixedexp.userID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixed_exp`
--

LOCK TABLES `fixed_exp` WRITE;
/*!40000 ALTER TABLE `fixed_exp` DISABLE KEYS */;
INSERT INTO `fixed_exp` VALUES (183,65,'Gym Membership',100),(184,65,'Car Insurance',100),(185,65,'Life Insurance',100),(186,65,'Other Fixed Expense',100),(187,65,'Mortgage',100),(188,65,'TV / Internet',100),(189,65,'House Cleaning Service',100),(190,65,'Car Payment',100),(191,65,'Child Care',100),(192,65,'Health Insurance',100),(193,67,'Gym Membership',100),(194,67,'Car Insurance',250),(195,67,'Life Insurance',150),(196,67,'Other Fixed Expense',50),(197,67,'Mortgage',3000),(198,67,'TV / Internet',95),(199,67,'House Cleaning Service',400),(200,67,'Car Payment',500),(201,67,'Child Care',2000),(202,67,'Health Insurance',500),(203,69,'Gym Membership',100),(204,69,'Car Insurance',200),(205,69,'Life Insurance',200),(206,69,'Other Fixed Expense',50),(207,69,'Mortgage',5500),(208,69,'TV / Internet',100),(209,69,'House Cleaning Service',400),(210,69,'Car Payment',250),(211,69,'Child Care',4000),(212,69,'Health Insurance',1000),(213,77,'Gym Membership',100),(214,77,'Car Insurance',200),(215,77,'Life Insurance',150),(216,77,'Other Fixed Expense',50),(217,77,'Mortgage',4000),(218,77,'TV / Internet',100),(219,77,'House Cleaning Service',100),(220,77,'Car Payment',400),(221,77,'Child Care',2000),(222,77,'Health Insurance',100),(223,78,'Gym Membership',1),(224,78,'Car Insurance',1),(225,78,'Life Insurance',1),(226,78,'Other Fixed Expense',1),(227,78,'Mortgage',1),(228,78,'TV / Internet',1),(229,78,'House Cleaning Service',1),(230,78,'Car Payment',1),(231,78,'Child Care',1),(232,78,'Health Insurance',1),(233,79,'Gym Membership',1),(234,79,'Car Insurance',1),(235,79,'Life Insurance',1),(236,79,'Other Fixed Expense',1),(237,79,'Mortgage',1),(238,79,'TV / Internet',1),(239,79,'House Cleaning Service',1),(240,79,'Car Payment',1),(241,79,'Child Care',1),(242,79,'Health Insurance',1),(243,83,'Gym Membership',500),(244,83,'Car Insurance',400),(245,83,'Life Insurance',200),(246,83,'Other Fixed Expense',200),(247,83,'Mortgage',200),(248,83,'TV / Internet',200),(249,83,'House Cleaning Service',200),(250,83,'Car Payment',200),(251,83,'Child Care',200),(252,83,'Health Insurance',200);
/*!40000 ALTER TABLE `fixed_exp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flex_Exp`
--

DROP TABLE IF EXISTS `flex_Exp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flex_Exp` (
  `flex_exp_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `category` varchar(45) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`flex_exp_id`),
  KEY `flexexp.userID_idx` (`user_id`),
  CONSTRAINT `flexexp.userID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=313 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flex_Exp`
--

LOCK TABLES `flex_Exp` WRITE;
/*!40000 ALTER TABLE `flex_Exp` DISABLE KEYS */;
INSERT INTO `flex_Exp` VALUES (222,65,'Groceries',100),(223,65,'Vacation',100),(224,65,'Personal Care',100),(225,65,'Entertainment',100),(226,65,'Other Flexible Expense',100),(227,65,'Alcohol',100),(228,65,'Auto Maintenance',100),(229,65,'Utilities',100),(230,65,'House Supplies',100),(231,65,'Pet Food',100),(232,65,'Gas',100),(233,65,'Cell Phone',100),(234,65,'Veterinary',100),(235,67,'Groceries',1000),(236,67,'Vacation',400),(237,67,'Personal Care',300),(238,67,'Entertainment',200),(239,67,'Other Flexible Expense',50),(240,67,'Alcohol',100),(241,67,'Auto Maintenance',200),(242,67,'Utilities',350),(243,67,'House Supplies',500),(244,67,'Pet Food',100),(245,67,'Gas',200),(246,67,'Cell Phone',150),(247,67,'Veterinary',150),(248,69,'Groceries',1000),(249,69,'Vacation',500),(250,69,'Personal Care',200),(251,69,'Entertainment',500),(252,69,'Other Flexible Expense',100),(253,69,'Alcohol',100),(254,69,'Auto Maintenance',200),(255,69,'Utilities',350),(256,69,'House Supplies',200),(257,69,'Pet Food',50),(258,69,'Gas',200),(259,69,'Cell Phone',100),(260,69,'Veterinary',100),(261,77,'Groceries',800),(262,77,'Vacation',300),(263,77,'Personal Care',300),(264,77,'Entertainment',500),(265,77,'Other Flexible Expense',50),(266,77,'Alcohol',50),(267,77,'Auto Maintenance',200),(268,77,'Utilities',200),(269,77,'House Supplies',200),(270,77,'Pet Food',50),(271,77,'Gas',400),(272,77,'Cell Phone',100),(273,77,'Veterinary',100),(274,78,'Groceries',1),(275,78,'Vacation',1),(276,78,'Personal Care',1),(277,78,'Entertainment',1),(278,78,'Other Flexible Expense',1),(279,78,'Alcohol',1),(280,78,'Auto Maintenance',1),(281,78,'Utilities',1),(282,78,'House Supplies',1),(283,78,'Pet Food',1),(284,78,'Gas',1),(285,78,'Cell Phone',1),(286,78,'Veterinary',1),(287,79,'Groceries',1),(288,79,'Vacation',1),(289,79,'Personal Care',1),(290,79,'Entertainment',1),(291,79,'Other Flexible Expense',1),(292,79,'Alcohol',1),(293,79,'Auto Maintenance',1),(294,79,'Utilities',1),(295,79,'House Supplies',1),(296,79,'Pet Food',1),(297,79,'Gas',1),(298,79,'Cell Phone',1),(299,79,'Veterinary',1),(300,83,'Groceries',800),(301,83,'Vacation',400),(302,83,'Personal Care',200),(303,83,'Entertainment',500),(304,83,'Other Flexible Expense',50),(305,83,'Alcohol',50),(306,83,'Auto Maintenance',300),(307,83,'Utilities',300),(308,83,'House Supplies',300),(309,83,'Pet Food',200),(310,83,'Gas',43),(311,83,'Cell Phone',123),(312,83,'Veterinary',200);
/*!40000 ALTER TABLE `flex_Exp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loan` (
  `loan_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `type` varchar(45) NOT NULL,
  `lender_name` varchar(45) NOT NULL,
  `acct_number` int NOT NULL,
  `start_balance` double NOT NULL,
  `current_balance` double NOT NULL,
  `interest_rate` double NOT NULL,
  `monthly_payment` double NOT NULL,
  `monthly_due_date` int NOT NULL,
  PRIMARY KEY (`loan_id`),
  KEY `loan.userid_idx` (`user_id`),
  CONSTRAINT `loan.userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan`
--

LOCK TABLES `loan` WRITE;
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
INSERT INTO `loan` VALUES (2,65,'Mortgage','1',1,1,1,0.01,1,1),(3,65,'Auto','1',1,1,0,0.01,1,1),(4,65,'Student','1',1,1,0,0.01,1,1),(5,65,'Other','1',1,1,0,0.01,1,1),(6,67,'Auto','captialone',123456,234.87,234.87,0.07,350,1),(7,77,'Mortgage','bank2',1234,4000,4000,0.05,300,14),(8,78,'Mortgage','asd',12,123,123,0.02,2,2),(9,81,'Mortgage','asdf',1234,123,123,0.02,2,2),(10,83,'Mortgage','asdf',1234,123,123,0.04,124,2);
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savings_goals`
--

DROP TABLE IF EXISTS `savings_goals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `savings_goals` (
  `savings_goals_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `category` varchar(45) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`savings_goals_id`),
  KEY `savings.userID_idx` (`user_id`),
  CONSTRAINT `savings.userID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savings_goals`
--

LOCK TABLES `savings_goals` WRITE;
/*!40000 ALTER TABLE `savings_goals` DISABLE KEYS */;
INSERT INTO `savings_goals` VALUES (52,65,'College Savings',1),(53,65,'General Savings',1),(54,65,'Retirement Savings',1),(55,67,'College Savings',500),(56,67,'General Savings',500),(57,67,'Retirement Savings',2500),(58,69,'College Savings',500),(59,69,'General Savings',1500),(60,69,'Retirement Savings',2000),(61,77,'College Savings',200),(62,77,'General Savings',1000),(63,77,'Retirement Savings',2000),(64,78,'College Savings',1),(65,78,'General Savings',1),(66,78,'Retirement Savings',1),(67,79,'College Savings',1),(68,79,'General Savings',1),(69,79,'Retirement Savings',1),(70,83,'College Savings',500),(71,83,'General Savings',500),(72,83,'Retirement Savings',1000);
/*!40000 ALTER TABLE `savings_goals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `street_address` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `zip_code` int NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user.loginID_idx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (65,'1','1','chris','parker','1','1','chris@parkerdesignbuild.com',21117,'1'),(66,'cparker07','password','chris','parker','2506','owings','md',21117,'chris@parkerdesignbuild.com'),(67,'alinaparker','password','alina','parker','567','towson','md',21204,'alina@parker.com'),(68,'q','q','q','q','q','q','q',1,'q'),(69,'w','w','w','1','1','1','1',1,'1'),(70,'3','3','1','1','1','1','1',1,'1'),(71,'8','8','1','1','1','1','1',1,'1'),(72,'9','9','1','1','1','1','1',1,'1'),(73,'2','2','1','1','1','1','1',1,'1'),(74,'10','1','1','1','1','1','1',1,'1'),(75,'11','1','1','1','1','1','1',1,'1'),(76,'5','5','chris','parker smith','2506 main st','owings mills','new mexico',21234,'chris@parkerdesignbuild.com'),(77,'chrisparker','password','chris','parker','2506 caves rd','owings mills','md',21117,'chris@parkerdesignbuild.com'),(78,'o','o','1','1','','1','1',1,'1'),(79,'n','n','1','1','1','1','1',1,'1'),(80,'6','6','1','1','1','1','1',1,'1'),(81,'0','0','1','1','1','1','11',1,'1'),(82,'cparker17','password','chris','parker','2506 caves rd','owings mills','md',21117,'chris@parkerdesignbuild.com'),(83,'aparker','pass','alina','parker','2506 caves rd','owings mills','md',21117,'alina@mail.com'),(84,'cparker','password','chris','parker','2506 caves rd','owings mills','md',21117,'chris@parkerdesignbuild.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-27 11:33:57
