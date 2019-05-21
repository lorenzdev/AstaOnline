-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 17, 2019 at 01:24 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `asta`
--

-- --------------------------------------------------------

--
-- Table structure for table `oggetto`
--

CREATE TABLE `oggetto` (
  `id_oggetto` varchar(10) NOT NULL,
  `tipologia` varchar(20) NOT NULL,
  `prezzo` float NOT NULL,
  `nome` varchar(20) NOT NULL,
  `descrizione` varchar(25) NOT NULL,
  `data` date NOT NULL,
  `e-mail_autore` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `registrazione`
--

CREATE TABLE `registrazione` (
  `id_utente` varchar(10) NOT NULL,
  `id_oggetto` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `utente`
--

CREATE TABLE `utente` (
  `e-mail` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `id_utente` varchar(10) NOT NULL,
  `citta_residenza` varchar(10) NOT NULL,
  `indirizzo` varchar(20) NOT NULL,
  `data_nascita` date NOT NULL,
  `nr_cell` int(15) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `oggetto`
--
ALTER TABLE `oggetto`
  ADD PRIMARY KEY (`id_oggetto`);

--
-- Indexes for table `registrazione`
--
ALTER TABLE `registrazione`
  ADD PRIMARY KEY (`id_utente`,`id_oggetto`),
  ADD KEY `id_oggetto` (`id_oggetto`);

--
-- Indexes for table `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`e-mail`),
  ADD KEY `Id_utente` (`id_utente`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `registrazione`
--
ALTER TABLE `registrazione`
  ADD CONSTRAINT `registrazione_ibfk_1` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`Id_utente`),
  ADD CONSTRAINT `registrazione_ibfk_2` FOREIGN KEY (`id_oggetto`) REFERENCES `oggetto` (`Id_oggetto`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
