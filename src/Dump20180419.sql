-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: millers
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chain`
--

DROP TABLE IF EXISTS `chain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chain` (
  `idchain` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT 'chaine name',
  `description` varchar(45) NOT NULL COMMENT 'chain description',
  PRIMARY KEY (`idchain`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='commercial retail chain';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chain`
--

LOCK TABLES `chain` WRITE;
/*!40000 ALTER TABLE `chain` DISABLE KEYS */;
INSERT INTO `chain` VALUES (1,'koala bear','baby clothes and equipment'),(2,'work on it','office supplies stores'),(3,'Casual wear','family clothes'),(4,'Bakers','Footwear Group, Inc. Shoe Stores'),(5,'Genesco','Shoe Stores'),(6,'Wal-Mart','All in one'),(7,'Casual wear','family clothes');
/*!40000 ALTER TABLE `chain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `idcity` int(11) NOT NULL AUTO_INCREMENT COMMENT 'cirt id, auto incremental ',
  `name` varchar(45) NOT NULL COMMENT 'city name',
  PRIMARY KEY (`idcity`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='city names that will be implemented on others tables (employee, shop, malls)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Ramat Gan'),(2,'Holon'),(3,'Tel Aviv'),(4,'Bat Yam'),(5,'Shoham'),(6,'Ramat Gan'),(7,'Haifa'),(8,'Jerusalem');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `idemployee` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `city` int(11) NOT NULL DEFAULT '3',
  `street` varchar(45) DEFAULT NULL,
  `postal_code` varchar(45) DEFAULT NULL,
  `shop` int(11) DEFAULT NULL,
  `group_menagment` int(11) DEFAULT '0',
  `birthDay` date DEFAULT NULL,
  PRIMARY KEY (`idemployee`),
  KEY `city_idx` (`city`),
  KEY `shop_idx` (`shop`),
  KEY `EmpManagment_idx` (`group_menagment`),
  CONSTRAINT `City` FOREIGN KEY (`city`) REFERENCES `city` (`idcity`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `EmpManagment` FOREIGN KEY (`group_menagment`) REFERENCES `management` (`idmanagement`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Neta','Shezaf',1,'Shenkin 14','654323',1,NULL,'1970-08-03'),(2,'Orit','Miller',3,'Imber 3','567432',3,NULL,'1973-05-03'),(3,'Yehuda','Levy',4,'Kiryat Sefer 7','656789',2,NULL,'1980-08-15'),(4,'Yehiel','Elbaz',6,'Hamayan 61','765555',NULL,1,'1986-09-19'),(6,'Iris','Steiner',3,'Haavoda 12','556789',8,1,'1977-02-18');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall`
--

DROP TABLE IF EXISTS `mall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall` (
  `idmall` int(11) NOT NULL AUTO_INCREMENT,
  `group` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `city` int(11) NOT NULL,
  `street` varchar(45) NOT NULL,
  PRIMARY KEY (`idmall`),
  KEY `mallCityId_idx` (`city`),
  KEY `mallGroupId_idx` (`group`),
  KEY `cityId_idx` (`city`),
  CONSTRAINT `mallCityId` FOREIGN KEY (`city`) REFERENCES `city` (`idcity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `mallGroupId` FOREIGN KEY (`group`) REFERENCES `mall_group` (`idmall_group`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='mall in the miller''s retail chain';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall`
--

LOCK TABLES `mall` WRITE;
/*!40000 ALTER TABLE `mall` DISABLE KEYS */;
INSERT INTO `mall` VALUES (1,2,'G Mall',2,'Ben Yehuda 210'),(2,1,'Arlov Mamilla',8,'Old City'),(3,3,'Malcha',8,'HolyLand'),(4,4,'Ice Park',7,'Lev Amifratz');
/*!40000 ALTER TABLE `mall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mall_group`
--

DROP TABLE IF EXISTS `mall_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_group` (
  `idmall_group` int(11) NOT NULL AUTO_INCREMENT COMMENT 'group id - auto increment',
  `name` varchar(45) NOT NULL COMMENT 'group name',
  `Description` varchar(45) NOT NULL,
  PRIMARY KEY (`idmall_group`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='mall groups in the miller''s retail chain';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_group`
--

LOCK TABLES `mall_group` WRITE;
/*!40000 ALTER TABLE `mall_group` DISABLE KEYS */;
INSERT INTO `mall_group` VALUES (1,'Azrieli','home goods, gifts, books, music, toys, cafes'),(2,'La Plaz','A prestigious, personal, pampering '),(3,'Hadar','shops, cinemas, cafes'),(4,'Gindi','fashion, pharmacies, shoes');
/*!40000 ALTER TABLE `mall_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `management`
--

DROP TABLE IF EXISTS `management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `management` (
  `idmanagement` int(11) NOT NULL AUTO_INCREMENT COMMENT 'management id - auto increment',
  `employee` int(11) NOT NULL COMMENT 'employee ID',
  `role` varchar(45) NOT NULL COMMENT 'role description',
  PRIMARY KEY (`idmanagement`),
  KEY `employeeId_idx` (`employee`),
  CONSTRAINT `employeeId` FOREIGN KEY (`employee`) REFERENCES `employee` (`idemployee`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='management roles';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `management`
--

LOCK TABLES `management` WRITE;
/*!40000 ALTER TABLE `management` DISABLE KEYS */;
INSERT INTO `management` VALUES (1,3,'Shop Manager');
/*!40000 ALTER TABLE `management` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop` (
  `idshop` int(11) NOT NULL AUTO_INCREMENT COMMENT 'shop id',
  `name` varchar(45) NOT NULL COMMENT 'shop name',
  `city` int(11) NOT NULL COMMENT 'city id',
  `street` varchar(45) NOT NULL COMMENT 'streen and number',
  `employee` int(11) NOT NULL COMMENT 'employee id',
  `chain` int(11) NOT NULL DEFAULT '0' COMMENT 'chain id',
  PRIMARY KEY (`idshop`),
  KEY `cityId_idx` (`city`),
  KEY `empId_idx` (`employee`),
  KEY `chainId_idx` (`chain`),
  CONSTRAINT `chainId` FOREIGN KEY (`chain`) REFERENCES `chain` (`idchain`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cityId` FOREIGN KEY (`city`) REFERENCES `city` (`idcity`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `empId` FOREIGN KEY (`employee`) REFERENCES `employee` (`idemployee`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='shop in the miller’s retail chain';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (4,'Abercrombie & Fitch',2,'Dizengoff 7',1,2),(5,'Asics',5,'Rothschild 56',2,3),(6,'MAC Cosmetics',3,'Shenkin 19',3,4),(8,'Mini Garden',5,'Herzel 65',3,1),(9,'Starbucks Coffee',6,'Kaplan 210',4,6);
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shops_in_mall`
--

DROP TABLE IF EXISTS `shops_in_mall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shops_in_mall` (
  `mall` int(11) NOT NULL,
  `shop` int(11) NOT NULL,
  `shop_number` varchar(45) NOT NULL COMMENT 'shop number in the mall',
  PRIMARY KEY (`shop`,`mall`),
  KEY `shopId_idx` (`shop`),
  KEY `mallId_idx` (`mall`),
  CONSTRAINT `mallId` FOREIGN KEY (`mall`) REFERENCES `mall` (`idmall`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `shopId` FOREIGN KEY (`shop`) REFERENCES `shop` (`idshop`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='shops in mall - represent many to many constraint ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shops_in_mall`
--

LOCK TABLES `shops_in_mall` WRITE;
/*!40000 ALTER TABLE `shops_in_mall` DISABLE KEYS */;
INSERT INTO `shops_in_mall` VALUES (1,4,'3'),(1,5,'7'),(3,5,'12'),(1,8,'109'),(1,9,'31');
/*!40000 ALTER TABLE `shops_in_mall` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-19 13:55:57
