-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.6.27-log

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
-- Table structure for table `purchaseorderline`
--

DROP TABLE IF EXISTS `purchaseorderline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchaseorderline` (
  `idpurchaseorder` int(11) NOT NULL,
  `idsupplier` int(11) NOT NULL,
  `iditem` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `damagedquantity` varchar(45) NOT NULL DEFAULT 'unchecked',
  PRIMARY KEY (`idpurchaseorder`,`iditem`),
  KEY `supplierID_idx` (`idsupplier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseorderline`
--

LOCK TABLES `purchaseorderline` WRITE;
/*!40000 ALTER TABLE `purchaseorderline` DISABLE KEYS */;
INSERT INTO `purchaseorderline` VALUES (1,1,1,100,'50'),(1,1,3,200,'40'),(2,1,1,50,'40'),(2,1,4,50,'40'),(2,1,5,50,'40'),(2,1,6,50,'40'),(3,1,4,60,'4'),(3,1,5,60,'8'),(4,1,1,10,'0'),(5,1,3,50,'40'),(6,1,4,80,'unchecked'),(6,1,7,75,'unchecked'),(7,1,3,432,'unchecked'),(7,1,5,231,'unchecked'),(8,1,3,40,'10'),(8,1,5,40,'40'),(8,1,6,40,'0'),(9,1,2,80,'10'),(9,1,5,80,'10'),(9,1,7,80,'10'),(10,1,4,84,'unchecked'),(10,1,6,78,'unchecked'),(10,1,7,7,'unchecked'),(11,1,6,100,'80'),(12,3,4,40,'unchecked'),(13,2,5,10,'unchecked'),(14,2,3,70,'unchecked'),(14,2,5,70,'unchecked'),(15,1,5,70,'unchecked'),(16,4,1,80,'unchecked'),(17,2,5,45,'unchecked'),(18,2,2,80,'unchecked'),(19,4,3,213,'unchecked'),(20,4,2,75,'unchecked'),(20,4,4,75,'unchecked');
/*!40000 ALTER TABLE `purchaseorderline` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-16 15:40:47
