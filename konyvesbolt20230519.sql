-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: konyvesbolt
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
-- Table structure for table `felhasznalo`
--

DROP TABLE IF EXISTS `felhasznalo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `felhasznalo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nev` varchar(45) NOT NULL,
  `felhasznalo_nev` varchar(45) NOT NULL,
  `jelszo` varchar(45) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `szerep` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `felhasznalo`
--

LOCK TABLES `felhasznalo` WRITE;
/*!40000 ALTER TABLE `felhasznalo` DISABLE KEYS */;
INSERT INTO `felhasznalo` VALUES (1,'Balogh József','Jozsika02','jelszo','bjozsef@gmail.com','USER'),(2,'Buzás Ádám','adamkaj','valami','adam2000buzas@gmail.com','ADMIN'),(3,'Czaffer Alexandra','czaffi','sdvsk','alexandraczaffer@gmail.com','USER'),(4,'Bekő Tóni','bTon23','12345','btoni@gmail.com','USER'),(5,'Cserepes Virág','csVir004','uwfbfoa124','utalomnevem@gmail.com','USER'),(6,'Lakatos Ferenc','navadjksv','titok','lferenc@gmail.com','USER'),(7,'Márk Botond','navadjksv','asvjkesvk','btond@gmail.com','USER'),(8,'Takács Sándor','Taki003','Analizis003','takiba@gmail.com','USER');
/*!40000 ALTER TABLE `felhasznalo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategoria`
--

DROP TABLE IF EXISTS `kategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kategoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nev` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategoria`
--

LOCK TABLES `kategoria` WRITE;
/*!40000 ALTER TABLE `kategoria` DISABLE KEYS */;
INSERT INTO `kategoria` VALUES (1,'Programozas'),(2,'Mesterséges intelligencia'),(3,'Adattudomány'),(4,'Számítógépes hálózatok'),(5,'Adatbázisok'),(6,'Operációs rendszerek'),(7,'Algoritmusok'),(8,'Számítógépes grafika');
/*!40000 ALTER TABLE `kategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `konyv`
--

DROP TABLE IF EXISTS `konyv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `konyv` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cim` varchar(45) NOT NULL,
  `szerzo` varchar(45) NOT NULL,
  `kiadas_eve` year NOT NULL,
  `ar` int NOT NULL,
  `kategoria_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kid_idx` (`kategoria_id`),
  CONSTRAINT `kid` FOREIGN KEY (`kategoria_id`) REFERENCES `kategoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `konyv`
--

LOCK TABLES `konyv` WRITE;
/*!40000 ALTER TABLE `konyv` DISABLE KEYS */;
INSERT INTO `konyv` VALUES (1,'Java konyv','Beko Toni',2005,2500,1),(2,'Clean Code','Robert C. Martin',2008,3400,1),(3,'The Pragmatic Programmer','Andrew Hunt és David Thomas',2007,3100,1),(4,'Artificial Intelligence: A Modern Approach','Stuart Russell és Peter Norvig',2017,4100,2),(5,'Deep Learning','Ian Goodfellow, Yoshua Bengio',2015,3800,2),(6,'Python Data Science Handbook','Jake VanderPlas ',2013,3200,3),(7,'Data Science for Business','Foster Provost és Tom Fawcett',2014,3600,3),(8,'Computer Networking: A Top-Down Approach','James Kurose és Keith Ross',2007,2200,4),(9,'TCP/IP Illustrated','Richard Stevens',2004,3000,4),(10,'Adatbázisok','Gajdos Sándor',2016,3500,5),(11,'Operating System Concepts','Abraham Silberschatz',2010,3000,6),(12,'Modern Operating Systems','Andrew S. Tanenbaum',2009,3300,6),(13,'Introduction to Algorithms','Thomas H. Cormen',2019,3300,7),(14,'Algorithms','Sanjoy Dasgupta',2011,2700,7),(15,'Grafika szépsége','Szirmay-Kalos László',2004,3500,8);
/*!40000 ALTER TABLE `konyv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rendeles`
--

DROP TABLE IF EXISTS `rendeles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rendeles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idopont` datetime(6) NOT NULL,
  `szallitasi_cim` varchar(45) NOT NULL,
  `felhasznalo_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fid_idx` (`felhasznalo_id`),
  CONSTRAINT `fid` FOREIGN KEY (`felhasznalo_id`) REFERENCES `felhasznalo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rendeles`
--

LOCK TABLES `rendeles` WRITE;
/*!40000 ALTER TABLE `rendeles` DISABLE KEYS */;
INSERT INTO `rendeles` VALUES (1,'2023-04-20 14:32:00.000000','Budapest',1),(2,'2023-04-22 14:00:00.000000','Székesfehérvár',3),(3,'2023-04-25 11:24:00.000000','Miskolc',2),(4,'2023-05-18 10:07:49.757280','7000, Sárbogárd, Kossuth u. 9',2),(5,'2023-05-18 10:09:45.748622','7000, Sárbogárd, Petőfi u. 3',8),(6,'2023-05-19 14:04:31.855687','7000, Sárbogárd, Tinódi utca, 9',8),(7,'2023-05-19 16:54:00.191270','8000, Székesfehérvár, Petőfi u. 3',8);
/*!40000 ALTER TABLE `rendeles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rendelt_konyv`
--

DROP TABLE IF EXISTS `rendelt_konyv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rendelt_konyv` (
  `konyv_id` int NOT NULL,
  `rendeles_id` int NOT NULL,
  `db` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `kid_idx` (`konyv_id`),
  KEY `rid_idx` (`rendeles_id`),
  CONSTRAINT `foreigid1` FOREIGN KEY (`konyv_id`) REFERENCES `konyv` (`id`),
  CONSTRAINT `foreigid2` FOREIGN KEY (`rendeles_id`) REFERENCES `rendeles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rendelt_konyv`
--

LOCK TABLES `rendelt_konyv` WRITE;
/*!40000 ALTER TABLE `rendelt_konyv` DISABLE KEYS */;
INSERT INTO `rendelt_konyv` VALUES (1,1,1,1),(5,1,2,2),(7,2,2,3),(1,4,1,4),(14,5,2,5),(15,5,1,6),(13,6,1,7),(15,6,1,8),(6,7,1,9);
/*!40000 ALTER TABLE `rendelt_konyv` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-19 20:58:27
