-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 16, 2023 at 10:23 AM
-- Server version: 5.7.11
-- PHP Version: 7.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cinema`
--

-- --------------------------------------------------------

--
-- Table structure for table `acteur`
--

CREATE TABLE `acteur` (
  `id` int(11) NOT NULL,
  `date_naissance` date DEFAULT NULL,
  `id_imdb` varchar(255) DEFAULT NULL,
  `identite` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `ID_LIEU_NAISSANCE_ACTEUR` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `casting_principal`
--

CREATE TABLE `casting_principal` (
  `ID_FILM` int(11) NOT NULL,
  `ID_ACTEUR` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `film`
--

CREATE TABLE `film` (
  `id` int(11) NOT NULL,
  `annee` varchar(255) DEFAULT NULL,
  `id_imdb` varchar(255) DEFAULT NULL,
  `lieu_tournage` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `rating` varchar(255) DEFAULT NULL,
  `resume` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `ID_LANGUE` int(11) DEFAULT NULL,
  `ID_PAYS` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `film_genre`
--

CREATE TABLE `film_genre` (
  `ID_FILM` int(11) NOT NULL,
  `ID_GENRE` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `film_realisateur`
--

CREATE TABLE `film_realisateur` (
  `ID_FILM` int(11) NOT NULL,
  `ID_REALISATEUR` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `langue`
--

CREATE TABLE `langue` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lieu_naissance`
--

CREATE TABLE `lieu_naissance` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pays`
--

CREATE TABLE `pays` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `realisateur`
--

CREATE TABLE `realisateur` (
  `id` int(11) NOT NULL,
  `date_naissance` date DEFAULT NULL,
  `id_imdb` varchar(255) DEFAULT NULL,
  `identite` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `ID_LIEU_NAISSANCE_REALISATEUR` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `personnage` varchar(255) DEFAULT NULL,
  `ID_ACTEUR` int(11) DEFAULT NULL,
  `ID_FILM` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `acteur`
--
ALTER TABLE `acteur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_wmmrminmtx8c1fr7pyo7pvhd` (`id_imdb`),
  ADD UNIQUE KEY `UK_gw37iyltt2tog49l9gvpci02a` (`identite`),
  ADD KEY `FKqc9cm6ulq4y5vw386at8n971o` (`ID_LIEU_NAISSANCE_ACTEUR`);

--
-- Indexes for table `casting_principal`
--
ALTER TABLE `casting_principal`
  ADD KEY `FK3133fiokkeex4msq8yfy6evfj` (`ID_ACTEUR`),
  ADD KEY `FK32h58plaeckdp36v5pai8iymb` (`ID_FILM`);

--
-- Indexes for table `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_83mohtc9a816g7hs28n8fohme` (`id_imdb`),
  ADD UNIQUE KEY `UK_3u5kec4i6r11wbe6c7y5dwv2l` (`nom`),
  ADD KEY `FKe6okl19q5sblphrw3xeijtnvx` (`ID_LANGUE`),
  ADD KEY `FK2pkkyjg0mdnwcjjpm2ctgsn96` (`ID_PAYS`);

--
-- Indexes for table `film_genre`
--
ALTER TABLE `film_genre`
  ADD KEY `FK6xe0oc5fflr1jy8qi87jsc47k` (`ID_GENRE`),
  ADD KEY `FKdmh35tfgen26rbok32yilpvc4` (`ID_FILM`);

--
-- Indexes for table `film_realisateur`
--
ALTER TABLE `film_realisateur`
  ADD KEY `FK9yvq3eg4e576slgmw52marjnu` (`ID_REALISATEUR`),
  ADD KEY `FK507p8n67ele8d4kr1d7brw4g7` (`ID_FILM`);

--
-- Indexes for table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_n8awxco9nljil5fubf847bn6f` (`nom`);

--
-- Indexes for table `langue`
--
ALTER TABLE `langue`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_s34d43fg2ogu5royslruncb9g` (`nom`);

--
-- Indexes for table `lieu_naissance`
--
ALTER TABLE `lieu_naissance`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_dmvk1f3sg6dk809w0e58e3f0q` (`nom`);

--
-- Indexes for table `pays`
--
ALTER TABLE `pays`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_rn6diccwherlido3buwfxggh0` (`nom`);

--
-- Indexes for table `realisateur`
--
ALTER TABLE `realisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_5q7jgrx2n9brh89qeavchdrop` (`id_imdb`),
  ADD UNIQUE KEY `UK_ehor2bajty9vealn3qeotja6w` (`identite`),
  ADD KEY `FK4yajj5qgj01op5dxrsfjp3u6k` (`ID_LIEU_NAISSANCE_REALISATEUR`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf99t9xi03dy4rbf7scb73fy2l` (`ID_ACTEUR`),
  ADD KEY `FKgqpd6p9c80inj1buwsklk0f6d` (`ID_FILM`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `acteur`
--
ALTER TABLE `acteur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `film`
--
ALTER TABLE `film`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `genre`
--
ALTER TABLE `genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `langue`
--
ALTER TABLE `langue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `lieu_naissance`
--
ALTER TABLE `lieu_naissance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pays`
--
ALTER TABLE `pays`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `realisateur`
--
ALTER TABLE `realisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
