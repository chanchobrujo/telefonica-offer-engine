-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-09-2021 a las 23:03:23
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 7.3.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `telefonicabd`
--
CREATE DATABASE IF NOT EXISTS `telefonicabd` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `telefonicabd`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `idcustomer` int(11) NOT NULL AUTO_INCREMENT,
  `dateborn` datetime DEFAULT NULL,
  `fullname` varchar(100) DEFAULT NULL,
  `numberdocument` varchar(15) DEFAULT NULL,
  `typedocument` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idcustomer`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customer_line`
--

DROP TABLE IF EXISTS `customer_line`;
CREATE TABLE IF NOT EXISTS `customer_line` (
  `idcustomer` int(11) NOT NULL,
  `idlinemobile` int(11) NOT NULL,
  PRIMARY KEY (`idcustomer`,`idlinemobile`),
  KEY `FKabr1ldsbn4fb0hwtjrstbeaa6` (`idlinemobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `line_mobile`
--

DROP TABLE IF EXISTS `line_mobile`;
CREATE TABLE IF NOT EXISTS `line_mobile` (
  `idlinemobile` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` datetime DEFAULT NULL,
  `nameplane` varchar(100) DEFAULT NULL,
  `numberphone` varchar(100) DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  `typeplane` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idlinemobile`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `line_offer`
--

DROP TABLE IF EXISTS `line_offer`;
CREATE TABLE IF NOT EXISTS `line_offer` (
  `idlinemobile` int(11) NOT NULL,
  `idoffer` int(11) NOT NULL,
  PRIMARY KEY (`idlinemobile`,`idoffer`),
  KEY `FKd71hndmdb2yifjuqxj04i0taw` (`idoffer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `offer`
--

DROP TABLE IF EXISTS `offer`;
CREATE TABLE IF NOT EXISTS `offer` (
  `idoffer` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `dateend` datetime DEFAULT NULL,
  `datestart` datetime DEFAULT NULL,
  PRIMARY KEY (`idoffer`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `customer_line`
--
ALTER TABLE `customer_line`
  ADD CONSTRAINT `FKabr1ldsbn4fb0hwtjrstbeaa6` FOREIGN KEY (`idlinemobile`) REFERENCES `line_mobile` (`idlinemobile`),
  ADD CONSTRAINT `FKr9ok7nfo9uyxwb86i68f5uarf` FOREIGN KEY (`idcustomer`) REFERENCES `customer` (`idcustomer`);

--
-- Filtros para la tabla `line_offer`
--
ALTER TABLE `line_offer`
  ADD CONSTRAINT `FKd71hndmdb2yifjuqxj04i0taw` FOREIGN KEY (`idoffer`) REFERENCES `offer` (`idoffer`),
  ADD CONSTRAINT `FKrdt1dqmtlbkd9hkep2is23s7i` FOREIGN KEY (`idlinemobile`) REFERENCES `line_mobile` (`idlinemobile`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
