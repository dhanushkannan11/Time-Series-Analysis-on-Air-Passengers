-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 28, 2021 at 03:32 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sem`
--

-- --------------------------------------------------------

--
-- Table structure for table `si`
--

CREATE TABLE `si` (
  `year` int(4) NOT NULL,
  `quarter` int(2) NOT NULL,
  `data` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `si`
--

INSERT INTO `si` (`year`, `quarter`, `data`) VALUES
(2012, 1, 2302470),
(2012, 2, 2284638),
(2012, 3, 2045987),
(2012, 4, 2646618),
(2013, 1, 2619270),
(2013, 2, 2595997),
(2013, 3, 2521497),
(2013, 4, 2571932),
(2014, 1, 2665800),
(2014, 2, 2771176),
(2014, 3, 2673156),
(2014, 4, 3172227),
(2015, 1, 3110612),
(2015, 2, 2977389),
(2015, 3, 3082128),
(2015, 4, 3352624),
(2016, 1, 3330022),
(2016, 2, 3520422),
(2016, 3, 3430376),
(2016, 4, 3344321),
(2017, 1, 3439073),
(2017, 2, 3455446),
(2017, 3, 3519356),
(2017, 4, 3786207),
(2018, 1, 3994374),
(2018, 2, 3917574),
(2018, 3, 3693693),
(2018, 4, 3891934);

-- --------------------------------------------------------

--
-- Table structure for table `total`
--

CREATE TABLE `total` (
  `year` int(10) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `total`
--

INSERT INTO `total` (`year`, `total`) VALUES
(2012, 9279713),
(2013, 10308696),
(2014, 11282359),
(2015, 12522753),
(2016, 13625141),
(2017, 14200082),
(2018, 15497575);

-- --------------------------------------------------------

--
-- Table structure for table `y_2015`
--

CREATE TABLE `y_2015` (
  `month` varchar(10) NOT NULL,
  `no_of_passengers` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `y_2015`
--

INSERT INTO `y_2015` (`month`, `no_of_passengers`) VALUES
('jan', 1104361),
('feb', 1010005),
('mar', 996246),
('apr', 999526),
('may', 1043708),
('jun', 934155),
('jul', 1032276),
('aug', 1058866),
('sep', 990986),
('oct', 1015358),
('nov', 1119595),
('dec', 1217671);

-- --------------------------------------------------------

--
-- Table structure for table `y_2016`
--

CREATE TABLE `y_2016` (
  `month` varchar(10) NOT NULL,
  `no_of_passengers` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `y_2016`
--

INSERT INTO `y_2016` (`month`, `no_of_passengers`) VALUES
('jan', 1157645),
('feb', 1085634),
('mar', 1086743),
('apr', 1117795),
('may', 1246137),
('jun', 1156490),
('jul', 1177504),
('aug', 1134913),
('sep', 1117959),
('oct', 1030490),
('nov', 1068768),
('dec', 1245063);

-- --------------------------------------------------------

--
-- Table structure for table `y_2017`
--

CREATE TABLE `y_2017` (
  `month` varchar(10) NOT NULL,
  `no_of_passengers` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `y_2017`
--

INSERT INTO `y_2017` (`month`, `no_of_passengers`) VALUES
('jan', 1265046),
('feb', 1084609),
('mar', 1089418),
('apr', 1086116),
('may', 1219997),
('jun', 1149333),
('jul', 1179933),
('aug', 1176397),
('sep', 1163026),
('oct', 1216153),
('nov', 1271612),
('dec', 1298442);

-- --------------------------------------------------------

--
-- Table structure for table `y_2018`
--

CREATE TABLE `y_2018` (
  `month` varchar(10) NOT NULL,
  `no_of_passengers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `y_2018`
--

INSERT INTO `y_2018` (`month`, `no_of_passengers`) VALUES
('jan', 1265046),
('feb', 1084609),
('mar', 1089418),
('apr', 1086116),
('may', 1219997),
('jun', 1149333),
('jul', 1179933),
('aug', 1176397),
('sep', 1163026),
('oct', 1216153),
('nov', 1271612),
('dec', 1298442);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
