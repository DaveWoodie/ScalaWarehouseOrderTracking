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
-- Table structure for table `purchaseorder`
--

DROP TABLE IF EXISTS `purchaseorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchaseorder` (
  `idpurchaseorder` int(11) NOT NULL,
  `dateplaced` varchar(45) DEFAULT NULL,
  `datedelivered` varchar(45) DEFAULT 'TBC',
  `idpurchaseorderstatus` int(11) DEFAULT NULL,
  `idEmployee` int(11) DEFAULT NULL,
  `idSupplier` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpurchaseorder`),
  KEY `supplierID_idx` (`idSupplier`),
  KEY `employeeID_idx` (`idEmployee`),
  CONSTRAINT `employeeID` FOREIGN KEY (`idEmployee`) REFERENCES `employee` (`idEmployee`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseorder`
--

LOCK TABLES `purchaseorder` WRITE;
/*!40000 ALTER TABLE `purchaseorder` DISABLE KEYS */;
INSERT INTO `purchaseorder` VALUES (1,'2015-11-05','Wed Nov 11 19:17:17 GMT 2015',3,1,1),(2,'2015-11-05','Sat Nov 14 15:21:15 GMT 2015',3,1,1),(3,'2015-11-05','Wed Nov 11 17:16:56 GMT 2015',3,1,1),(4,'2015-11-06','Thu Nov 12 14:49:57 GMT 2015',3,1,1),(5,'Thu Nov 12 11:14:12 GMT 2015','Sat Nov 14 15:17:35 GMT 2015',3,1,1),(6,'Thu Nov 12 11:14:34 GMT 2015','Thu Nov 12 15:24:05 GMT 2015',2,1,1),(7,'Thu Nov 12 11:36:33 GMT 2015','Sat Nov 14 15:11:56 GMT 2015',2,1,1),(8,'Thu Nov 12 11:46:07 GMT 2015','Thu Nov 12 15:24:45 GMT 2015',3,1,1),(9,'Thu Nov 12 11:48:11 GMT 2015','Fri Nov 13 17:13:49 GMT 2015',3,1,1),(10,'Thu Nov 12 14:49:57 GMT 2015','Sat Nov 14 15:12:03 GMT 2015',2,1,1),(11,'Thu Nov 12 15:25:41 GMT 2015','Sat Nov 14 15:11:50 GMT 2015',3,1,1),(12,'Fri Nov 13 09:39:02 GMT 2015','Sat Nov 14 15:17:22 GMT 2015',2,1,3),(13,'Fri Nov 13 09:39:21 GMT 2015','TBC',1,1,2),(14,'Fri Nov 13 09:39:35 GMT 2015','TBC',1,1,2),(15,'Fri Nov 13 09:39:46 GMT 2015','TBC',1,1,1),(16,'Fri Nov 13 09:39:56 GMT 2015','TBC',1,1,4),(17,'Fri Nov 13 13:08:35 GMT 2015','Fri Nov 13 14:24:33 GMT 2015',2,1,2),(18,'Sat Nov 14 12:35:57 GMT 2015','TBC',1,1,2),(19,'Sat Nov 14 16:19:30 GMT 2015','TBC',1,1,4),(20,'Sat Nov 14 16:19:48 GMT 2015','TBC',1,1,4);
/*!40000 ALTER TABLE `purchaseorder` ENABLE KEYS */;
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
