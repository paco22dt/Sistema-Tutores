-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2016 at 02:33 PM
-- Server version: 5.7.9
-- PHP Version: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bdd_tutores`
--

-- --------------------------------------------------------

--
-- Table structure for table `curso_lab`
--

DROP TABLE IF EXISTS `curso_lab`;
CREATE TABLE IF NOT EXISTS `curso_lab` (
  `CURSO_TE_id_curso` int(11) NOT NULL,
  `seccion` int(11) NOT NULL,
  PRIMARY KEY (`seccion`),
  KEY `CURSO_TE_id_curso` (`CURSO_TE_id_curso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `curso_te`
--

DROP TABLE IF EXISTS `curso_te`;
CREATE TABLE IF NOT EXISTS `curso_te` (
  `id_curso` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `seccion` int(11) NOT NULL,
  PRIMARY KEY (`id_curso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `curso_tutor`
--

DROP TABLE IF EXISTS `curso_tutor`;
CREATE TABLE IF NOT EXISTS `curso_tutor` (
  `id_curso_tutor` int(11) NOT NULL,
  `TUTOR_carnet` int(11) NOT NULL,
  `DOCENTE_id_docente` int(11) NOT NULL,
  `tipo_curso` varchar(10) NOT NULL,
  `CURSO_LAB_seccion` int(11) NOT NULL,
  `año` int(11) NOT NULL,
  `ciclo` int(11) NOT NULL,
  PRIMARY KEY (`id_curso_tutor`),
  KEY `TUTOR_carnet` (`TUTOR_carnet`),
  KEY `DOCENTE_id_docente` (`DOCENTE_id_docente`),
  KEY `CURSO_LAB_seccion` (`CURSO_LAB_seccion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `docente`
--

DROP TABLE IF EXISTS `docente`;
CREATE TABLE IF NOT EXISTS `docente` (
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `id_docente` int(11) NOT NULL,
  PRIMARY KEY (`id_docente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `historial_tutor`
--

DROP TABLE IF EXISTS `historial_tutor`;
CREATE TABLE IF NOT EXISTS `historial_tutor` (
  `id_historial_tutor` int(11) NOT NULL AUTO_INCREMENT,
  `curso` varchar(30) NOT NULL,
  `seccion` int(11) NOT NULL,
  `nombre_docente` varchar(30) NOT NULL,
  `apellido_docente` varchar(30) NOT NULL,
  `nota_evaluacion` int(11) NOT NULL,
  `TUTOR_carnet` int(11) NOT NULL,
  PRIMARY KEY (`id_historial_tutor`),
  KEY `TUTOR_carnet` (`TUTOR_carnet`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `nota_curso`
--

DROP TABLE IF EXISTS `nota_curso`;
CREATE TABLE IF NOT EXISTS `nota_curso` (
  `nota_curso` int(11) NOT NULL,
  `CURSO_TE_id_curso` int(11) NOT NULL,
  `TUTOR_carnet` int(11) NOT NULL,
  PRIMARY KEY (`CURSO_TE_id_curso`,`TUTOR_carnet`),
  KEY `TUTOR_carnet` (`TUTOR_carnet`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `periodo`
--

DROP TABLE IF EXISTS `periodo`;
CREATE TABLE IF NOT EXISTS `periodo` (
  `id_periodo` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_inicio` date NOT NULL,
  `CURSO_TUTOR_id_curso_tutor` int(11) NOT NULL,
  `fecha_fin` date NOT NULL,
  PRIMARY KEY (`id_periodo`),
  KEY `periodo_ibfk_1` (`CURSO_TUTOR_id_curso_tutor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tutor`
--

DROP TABLE IF EXISTS `tutor`;
CREATE TABLE IF NOT EXISTS `tutor` (
  `carnet` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `carrera` int(11) NOT NULL,
  `porcentaje` int(11) DEFAULT NULL,
  `tipo_ayuda_economica` varchar(30) DEFAULT NULL,
  `correo` varchar(50) NOT NULL,
  `ciclo_actual` varchar(30) NOT NULL,
  `promedio` int(11) NOT NULL,
  `telefono` int(11) NOT NULL,
  PRIMARY KEY (`carnet`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id_user` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `password`) VALUES
('admin', 'admin');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `curso_lab`
--
ALTER TABLE `curso_lab`
  ADD CONSTRAINT `curso_lab_ibfk_1` FOREIGN KEY (`CURSO_TE_id_curso`) REFERENCES `curso_tutor` (`id_curso_tutor`);

--
-- Constraints for table `curso_tutor`
--
ALTER TABLE `curso_tutor`
  ADD CONSTRAINT `curso_tutor_ibfk_1` FOREIGN KEY (`TUTOR_carnet`) REFERENCES `tutor` (`carnet`),
  ADD CONSTRAINT `curso_tutor_ibfk_2` FOREIGN KEY (`DOCENTE_id_docente`) REFERENCES `docente` (`id_docente`),
  ADD CONSTRAINT `curso_tutor_ibfk_3` FOREIGN KEY (`CURSO_LAB_seccion`) REFERENCES `curso_lab` (`seccion`);

--
-- Constraints for table `historial_tutor`
--
ALTER TABLE `historial_tutor`
  ADD CONSTRAINT `historial_tutor_ibfk_1` FOREIGN KEY (`TUTOR_carnet`) REFERENCES `tutor` (`carnet`);

--
-- Constraints for table `nota_curso`
--
ALTER TABLE `nota_curso`
  ADD CONSTRAINT `nota_curso_ibfk_1` FOREIGN KEY (`TUTOR_carnet`) REFERENCES `tutor` (`carnet`),
  ADD CONSTRAINT `nota_curso_ibfk_2` FOREIGN KEY (`CURSO_TE_id_curso`) REFERENCES `curso_te` (`id_curso`);

--
-- Constraints for table `periodo`
--
ALTER TABLE `periodo`
  ADD CONSTRAINT `periodo_ibfk_1` FOREIGN KEY (`CURSO_TUTOR_id_curso_tutor`) REFERENCES `curso_tutor` (`id_curso_tutor`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
