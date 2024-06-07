-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 07, 2024 at 08:42 PM
-- Server version: 8.2.0
-- PHP Version: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bazar_tp`
--

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id_cliente` bigint NOT NULL,
  `apellido` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `dni` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `apellido`, `dni`, `nombre`) VALUES
(1, 'Perez', '050118850045', 'Daniel'),
(2, 'Reyes', '0501199500298', 'David'),
(52, 'Reyes', '05011990442353', 'Josue'),
(102, 'Alfaro', '050119930049586', 'Edward'),
(152, 'Alfaro', '050119928328324', 'Ariel');

-- --------------------------------------------------------

--
-- Table structure for table `cliente_seq`
--

DROP TABLE IF EXISTS `cliente_seq`;
CREATE TABLE IF NOT EXISTS `cliente_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `cliente_seq`
--

INSERT INTO `cliente_seq` (`next_val`) VALUES
(251);

-- --------------------------------------------------------

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `id_producto` bigint NOT NULL,
  `cantidad_disponible` int NOT NULL,
  `marca` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `producto`
--

INSERT INTO `producto` (`id_producto`, `cantidad_disponible`, `marca`, `nombre`, `precio`) VALUES
(1, 16, 'Shampo 1lt', 'Head & Shoulder', 80),
(2, 8, 'China', 'Espejo 7 pulg', 30),
(52, 2, 'Comfort', 'Sofa Cama', 10000),
(53, 3, 'LG', 'Televisor 32inch Oled', 32000),
(54, 20, 'SmartLight', 'Lampara de noche 1ft', 230),
(102, 5, 'Logitech', 'Teclado rgb 60%', 1300),
(103, 3, 'Logitech', 'Mouse G703', 1100),
(104, 8, 'XTech', 'Speaker 3T', 600);

-- --------------------------------------------------------

--
-- Table structure for table `producto_seq`
--

DROP TABLE IF EXISTS `producto_seq`;
CREATE TABLE IF NOT EXISTS `producto_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `producto_seq`
--

INSERT INTO `producto_seq` (`next_val`) VALUES
(201);

-- --------------------------------------------------------

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
CREATE TABLE IF NOT EXISTS `venta` (
  `codigo_venta` bigint NOT NULL,
  `fecha_venta` date DEFAULT NULL,
  `total` double NOT NULL,
  `id_cliente` bigint DEFAULT NULL,
  PRIMARY KEY (`codigo_venta`),
  UNIQUE KEY `UKjywx1th6n2ytgn2glxi3ab56y` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `venta`
--

INSERT INTO `venta` (`codigo_venta`, `fecha_venta`, `total`, `id_cliente`) VALUES
(502, '2024-05-24', 410, 1),
(652, '2024-05-21', 74160, 52),
(702, '2024-05-30', 3000, 102),
(752, '2024-05-30', 3000, 2);

-- --------------------------------------------------------

--
-- Table structure for table `venta_producto`
--

DROP TABLE IF EXISTS `venta_producto`;
CREATE TABLE IF NOT EXISTS `venta_producto` (
  `cantidad` int NOT NULL,
  `total_pro` double NOT NULL,
  `id_producto` bigint NOT NULL,
  `codigo_venta` bigint NOT NULL,
  PRIMARY KEY (`id_producto`,`codigo_venta`),
  KEY `FKe5d2ajm79jhvoa9yxt8hg4r4u` (`codigo_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `venta_producto`
--

INSERT INTO `venta_producto` (`cantidad`, `total_pro`, `id_producto`, `codigo_venta`) VALUES
(4, 320, 1, 502),
(2, 160, 1, 652),
(3, 90, 2, 502),
(1, 10000, 52, 652),
(2, 64000, 53, 652),
(1, 1300, 102, 702),
(1, 1300, 102, 752),
(1, 1100, 103, 702),
(1, 1100, 103, 752),
(1, 600, 104, 702),
(1, 600, 104, 752);

-- --------------------------------------------------------

--
-- Table structure for table `venta_seq`
--

DROP TABLE IF EXISTS `venta_seq`;
CREATE TABLE IF NOT EXISTS `venta_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Dumping data for table `venta_seq`
--

INSERT INTO `venta_seq` (`next_val`) VALUES
(851);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `FKsor2qmi3thao7a8or49vlohp9` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`);

--
-- Constraints for table `venta_producto`
--
ALTER TABLE `venta_producto`
  ADD CONSTRAINT `FKe5d2ajm79jhvoa9yxt8hg4r4u` FOREIGN KEY (`codigo_venta`) REFERENCES `venta` (`codigo_venta`),
  ADD CONSTRAINT `FKrnj9u4edr8xyj6wladg622hpr` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
