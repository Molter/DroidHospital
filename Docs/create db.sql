CREATE DATABASE  IF NOT EXISTS `droid_hospital` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `droid_hospital`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: droid_hospital
-- ------------------------------------------------------
-- Server version	5.5.24-log

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
-- Table structure for table `aplicacoes`
--

DROP TABLE IF EXISTS `aplicacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aplicacoes` (
  `idaplicacao` int(11) NOT NULL AUTO_INCREMENT,
  `idEnfermeiro` int(11) DEFAULT NULL,
  `idprescricao` int(11) NOT NULL,
  `hora_previsto` datetime NOT NULL,
  `hora_aplicado` datetime DEFAULT NULL,
  PRIMARY KEY (`idaplicacao`),
  KEY `idEnfermeiro` (`idEnfermeiro`),
  KEY `idprescricao` (`idprescricao`),
  CONSTRAINT `aplicacoes_ibfk_1` FOREIGN KEY (`idEnfermeiro`) REFERENCES `pessoas` (`idpessoa`),
  CONSTRAINT `aplicacoes_ibfk_2` FOREIGN KEY (`idprescricao`) REFERENCES `prescricoes` (`idprescricao`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aplicacoes`
--

LOCK TABLES `aplicacoes` WRITE;
/*!40000 ALTER TABLE `aplicacoes` DISABLE KEYS */;
INSERT INTO `aplicacoes` VALUES (1,2,1,'2013-02-12 20:00:00',NULL);
/*!40000 ALTER TABLE `aplicacoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atendimentos`
--

DROP TABLE IF EXISTS `atendimentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atendimentos` (
  `idatendimento` int(11) NOT NULL AUTO_INCREMENT,
  `idleito` int(11) NOT NULL,
  `idpaciente` int(11) NOT NULL,
  `fuma` char(1) NOT NULL DEFAULT 'N',
  `peso` decimal(10,0) NOT NULL DEFAULT '0',
  `data_entrada` datetime NOT NULL,
  `data_saida` datetime DEFAULT NULL,
  PRIMARY KEY (`idatendimento`),
  KEY `idleito` (`idleito`),
  KEY `idpaciente` (`idpaciente`),
  CONSTRAINT `atendimentos_ibfk_1` FOREIGN KEY (`idleito`) REFERENCES `leitos` (`idleito`),
  CONSTRAINT `atendimentos_ibfk_2` FOREIGN KEY (`idpaciente`) REFERENCES `pessoas` (`idpessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atendimentos`
--

LOCK TABLES `atendimentos` WRITE;
/*!40000 ALTER TABLE `atendimentos` DISABLE KEYS */;
INSERT INTO `atendimentos` VALUES (1,1,3,'S',100,'2013-05-01 09:20:58',NULL),(2,2,2,'S',90,'2013-05-01 09:20:58',NULL),(3,3,3,'N',87,'2013-05-01 09:20:58',NULL),(4,4,4,'S',42,'2013-05-01 09:20:58',NULL),(5,5,5,'N',88,'2013-05-01 09:20:58',NULL),(6,6,6,'N',69,'2013-05-01 09:20:58',NULL),(7,7,7,'N',37,'2013-05-01 09:20:58',NULL);
/*!40000 ALTER TABLE `atendimentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leitos`
--

DROP TABLE IF EXISTS `leitos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leitos` (
  `idleito` int(11) NOT NULL AUTO_INCREMENT,
  `quarto` tinyint(10) NOT NULL,
  `leito` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idleito`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leitos`
--

LOCK TABLES `leitos` WRITE;
/*!40000 ALTER TABLE `leitos` DISABLE KEYS */;
INSERT INTO `leitos` VALUES (1,10,'A'),(2,10,'B'),(3,10,'C'),(4,11,'A'),(5,12,'A'),(6,12,'B'),(7,12,'C');
/*!40000 ALTER TABLE `leitos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicamentos`
--

DROP TABLE IF EXISTS `medicamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicamentos` (
  `idmedicamento` int(11) NOT NULL AUTO_INCREMENT,
  `farmaco` varchar(200) DEFAULT NULL,
  `detentor` varchar(200) DEFAULT NULL,
  `medicamento_referencia` varchar(150) DEFAULT NULL,
  `concentracao` varchar(15) DEFAULT NULL,
  `forma_farmaceutica` char(2) NOT NULL,
  PRIMARY KEY (`idmedicamento`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COMMENT='Referência para a tabela: http://www.anvisa.gov.br/medicamen';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicamentos`
--

LOCK TABLES `medicamentos` WRITE;
/*!40000 ALTER TABLE `medicamentos` DISABLE KEYS */;
INSERT INTO `medicamentos` VALUES (1,'dipirona','house of death','no one','500mg','cm'),(2,'aspirina','house of death','no one','250mg','dg'),(3,'coristina','house of death','no one','500mg','su'),(4,'charrua','house of death','no one','500mg','cp');
/*!40000 ALTER TABLE `medicamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoas`
--

DROP TABLE IF EXISTS `pessoas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoas` (
  `idpessoa` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `endereco` varchar(200) DEFAULT NULL,
  `numero` varchar(200) DEFAULT NULL,
  `cidade` varchar(50) NOT NULL,
  `estado` char(2) NOT NULL,
  `pais` varchar(20) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `tipo_pessoa` char(1) DEFAULT NULL COMMENT 'M - Médico, E - Enfermeiro, P - Paciente',
  `especialidade` varchar(40) DEFAULT NULL,
  `data_nascimento` date NOT NULL,
  `tipo_sanguineo` char(2) NOT NULL,
  `usuario` varchar(200) NOT NULL,adapter
  `senha` varchar(32) NOT NULL,
  `alergias` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`idpessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoas`
--

LOCK TABLES `pessoas` WRITE;
/*!40000 ALTER TABLE `pessoas` DISABLE KEYS */;
INSERT INTO `pessoas` VALUES (1,'Gabriel','01243444029','aff','12','Sapiranga','Rs','Brasil','0','M',NULL,'1990-03-12','a','edvar','edvar','Arroz, Leite, Lactose, Mosquito, Paracetalmol'),(2,'Jose','12345679','aff','12','Sapiranga','Rs','Brasil','0','E',NULL,'1990-03-12','a','molter','edvar','Arroz, Leite, Lactose, Mosquito, Paracetalmol'),(3,'Pedro','12345679','aff','12','Sapiranga','Rs','Brasil','0','P',NULL,'1990-03-12','a','edvar','edvar','Arroz, Leite, Lactose, Mosquito, Paracetalmol'),(4,'Carlos','12345679','aff','12','Sapiranga','Rs','Brasil','0','P',NULL,'1990-03-12','a','edvar','edvar','Arroz, Leite, Lactose, Mosquito, Paracetalmol'),(5,'Thomas','12345679','aff','12','Sapiranga','Rs','Brasil','0','P',NULL,'1990-03-12','a','edvar','edvar','Arroz, Leite, Lactose, Mosquito, Paracetalmol'),(6,'Pafuncio','12345679','aff','12','Sapiranga','Rs','Brasil','0','P',NULL,'1990-03-12','a','edvar','edvar','Arroz, Leite, Lactose, Mosquito, Paracetalmol'),(7,'Beltrano','12345679','aff','12','Sapiranga','Rs','Brasil','0','P',NULL,'1990-03-12','a','edvar','edvar','Arroz, Leite, Lactose, Mosquito, Paracetalmol'),(8,'Ciclano','12345679','aff','12','Sapiranga','Rs','Brasil','0','P',NULL,'1990-03-12','a','edvar','edvar','Arroz, Leite, Lactose, Mosquito, Paracetalmol'),(9,'Jose joao','12345679','aff','12','Sapiranga','Rs','Brasil','0','P',NULL,'1990-03-12','a','edvar','edvar','Arroz, Leite, Lactose, Mosquito, Paracetalmol');
/*!40000 ALTER TABLE `pessoas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescricoes`
--

DROP TABLE IF EXISTS `prescricoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescricoes` (
  `idprescricao` int(11) NOT NULL AUTO_INCREMENT,
  `idatendimento` int(11) NOT NULL,
  `idmedicamento` int(11) NOT NULL,
  `intervalo_aplicacoes` time NOT NULL,
  `hora_inicio` time DEFAULT NULL,
  `quantidade_aplicacoes` int(11) NOT NULL DEFAULT '1',
  `idMedico` int(11) DEFAULT NULL,
  PRIMARY KEY (`idprescricao`),
  KEY `idatendimento` (`idatendimento`),
  KEY `idmedicamento` (`idmedicamento`),
  KEY `idMedico` (`idMedico`),
  CONSTRAINT `prescricoes_ibfk_1` FOREIGN KEY (`idatendimento`) REFERENCES `atendimentos` (`idatendimento`),
  CONSTRAINT `prescricoes_ibfk_2` FOREIGN KEY (`idmedicamento`) REFERENCES `medicamentos` (`idmedicamento`),
  CONSTRAINT `prescricoes_ibfk_3` FOREIGN KEY (`idMedico`) REFERENCES `pessoas` (`idpessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescricoes`
--

LOCK TABLES `prescricoes` WRITE;
/*!40000 ALTER TABLE `prescricoes` DISABLE KEYS */;
INSERT INTO `prescricoes` VALUES (1,1,1,'02:00:00','20:00:00',10,1);
/*!40000 ALTER TABLE `prescricoes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-09 12:32:17
