-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : adminn.cam6mhx2dtqf.eu-west-3.rds.amazonaws.com:8080
-- Généré le : Dim 29 août 2021 à 20:46
-- Version du serveur :  8.0.23
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `pidev3a`
--

-- --------------------------------------------------------

--
-- Structure de la table `aliment`
--

DROP TABLE IF EXISTS `aliment`;
CREATE TABLE IF NOT EXISTS `aliment` (
  `id` int NOT NULL,
  `code_abarre` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `aliment`
--

INSERT INTO `aliment` (`id`, `code_abarre`) VALUES
(38, NULL),
(39, NULL),
(40, NULL),
(65, NULL),
(66, NULL),
(67, NULL),
(68, '6194043001095'),
(70, NULL),
(71, NULL),
(72, NULL),
(73, NULL),
(74, NULL),
(75, NULL),
(80, NULL),
(81, NULL),
(82, NULL),
(83, NULL),
(85, NULL),
(88, NULL),
(89, NULL),
(91, NULL),
(93, NULL),
(95, NULL),
(169, NULL),
(173, NULL),
(222, '6194021111426');

-- --------------------------------------------------------

--
-- Structure de la table `aliment_categorie_aliment`
--

DROP TABLE IF EXISTS `aliment_categorie_aliment`;
CREATE TABLE IF NOT EXISTS `aliment_categorie_aliment` (
  `aliment_id` int NOT NULL,
  `categorie_aliment_id` int NOT NULL,
  PRIMARY KEY (`aliment_id`,`categorie_aliment_id`),
  KEY `IDX_C52A8992415B9F11` (`aliment_id`),
  KEY `IDX_C52A8992DF9255BD` (`categorie_aliment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `aliment_categorie_aliment`
--

INSERT INTO `aliment_categorie_aliment` (`aliment_id`, `categorie_aliment_id`) VALUES
(38, 2),
(39, 5),
(40, 4),
(65, 6),
(66, 3),
(68, 3),
(68, 5),
(70, 1),
(71, 4),
(72, 3),
(73, 1),
(74, 7),
(75, 7),
(80, 1),
(81, 1),
(88, 8),
(89, 2),
(91, 1),
(93, 1),
(169, 2),
(173, 1),
(173, 2),
(222, 22);

-- --------------------------------------------------------

--
-- Structure de la table `blog_post`
--

DROP TABLE IF EXISTS `blog_post`;
CREATE TABLE IF NOT EXISTS `blog_post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `body` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date` date DEFAULT NULL,
  `nb_raiting` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `blog_post`
--

INSERT INTO `blog_post` (`id`, `title`, `body`, `date`, `nb_raiting`) VALUES
(5, 'How to survive PIDEV updating', 'YOU CANT', '2021-03-05', 0),
(7, 'test', 'bbijhg gygy u', '2021-03-05', 0),
(8, 'tesst', 'lihohhmouuuuuuuiiji', '2021-03-05', 0),
(9, 'jiugdyzgidzgd', 'zgfizgfizfygefgyeg', '2021-03-05', 0),
(10, 'test', 'igiygigiuyz', '2021-03-05', 0),
(11, 'sabrine', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Aenean euismod elementum nisi quis eleifend quam adipiscing. Pellentesque eu tincidunt tortor aliquam nulla facilisi. Penatibus et ', '2021-03-05', 0),
(12, 'test', 'hedyt yf', '2021-03-05', 0),
(13, 'tt', 'yy', '2021-04-02', 0),
(14, 'gsggsg', 'dgsgsg', '2021-04-02', 0),
(15, 'tet', 'etet', '2021-04-02', 0),
(16, 'tet', 'uckukuyk', '2021-04-05', 0),
(17, 'test', 'fffff', '2021-04-05', 0),
(18, 'fffef', 'efefff', '2021-04-05', 0),
(19, 'tet', 'tesst', '2021-04-07', 0),
(20, 'test hiuh', 'ggyttff', '2021-04-07', 0),
(21, 'last event ', 'last event body', '2021-04-06', 0),
(22, 'nouveau blog4', 'test', '2021-04-29', 0);

-- --------------------------------------------------------

--
-- Structure de la table `categorie_aliment`
--

DROP TABLE IF EXISTS `categorie_aliment`;
CREATE TABLE IF NOT EXISTS `categorie_aliment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom_categorie` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `categorie_aliment`
--

INSERT INTO `categorie_aliment` (`id`, `nom_categorie`) VALUES
(1, 'legume'),
(2, 'fruit'),
(3, 'produits laitierss'),
(4, 'volailles'),
(5, 'matières grasses'),
(6, 'farine'),
(7, 'epice'),
(8, 'pain'),
(10, 'sans alim'),
(12, 'qq45'),
(17, 'vvf'),
(19, 'categorie'),
(22, 'dessert emballé');

-- --------------------------------------------------------

--
-- Structure de la table `challenge`
--

DROP TABLE IF EXISTS `challenge`;
CREATE TABLE IF NOT EXISTS `challenge` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `categorie` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `challenge`
--

INSERT INTO `challenge` (`id`, `titre`, `description`, `categorie`, `date_debut`, `date_fin`) VALUES
(14, 'tttttttttttttttttt', 'ttt', 'tttttttttttt', '2021-05-19', '2024-02-16'),
(28, 'regime', 'desc', 'cat', '2028-06-07', '2029-07-08'),
(29, 'comment devenir comme bacem', 'comment gagner du poids', 'food', '2021-06-03', '2026-03-03');

-- --------------------------------------------------------

--
-- Structure de la table `challenge_tag`
--

DROP TABLE IF EXISTS `challenge_tag`;
CREATE TABLE IF NOT EXISTS `challenge_tag` (
  `id` int NOT NULL,
  `challenge_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_1AC21A8F98A21AC6` (`challenge_id`),
  KEY `IDX_1AC21A8FA76ED395` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `challenge_tag`
--

INSERT INTO `challenge_tag` (`id`, `challenge_id`, `user_id`) VALUES
(213, 14, 55),
(214, 14, 71),
(216, 14, 71),
(306, 28, 55),
(307, 29, 55);

-- --------------------------------------------------------

--
-- Structure de la table `comments`
--

DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `success_id` int NOT NULL,
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` tinyint(1) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nickname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_at` datetime NOT NULL,
  `rgpd` tinyint(1) NOT NULL,
  `parent_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_5F9E962AA63B36F1` (`success_id`),
  KEY `IDX_5F9E962A727ACA70` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `composition`
--

DROP TABLE IF EXISTS `composition`;
CREATE TABLE IF NOT EXISTS `composition` (
  `id` int NOT NULL AUTO_INCREMENT,
  `aliment_id` int DEFAULT NULL,
  `plat_id` int DEFAULT NULL,
  `poid` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_C7F4347D73DB560` (`plat_id`),
  KEY `IDX_C7F4347415B9F11` (`aliment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `composition`
--

INSERT INTO `composition` (`id`, `aliment_id`, `plat_id`, `poid`) VALUES
(50, 65, 69, 250),
(51, 40, 69, 4),
(52, 66, 69, 450),
(53, 67, 69, 80),
(54, 68, 69, 50),
(55, 70, 76, 300),
(56, 71, 76, 1000),
(57, 72, 76, 200),
(58, 73, 76, 10),
(59, 75, 76, 1),
(60, 74, 76, 5),
(61, 39, 76, 15),
(62, 66, 84, 300),
(63, 68, 84, 40),
(64, 65, 84, 125),
(65, 82, 84, 25),
(66, 40, 84, 3),
(67, 74, 84, 1),
(68, 67, 84, 20),
(69, 83, 84, 100),
(70, 73, 87, 10),
(71, 71, 87, 400),
(72, 38, 87, 100),
(73, 39, 87, 30),
(74, 89, 90, 150),
(75, 88, 90, 100),
(76, 38, 90, 100),
(77, 74, 90, 1),
(78, 75, 90, 1),
(79, 71, 92, 300),
(80, 38, 92, 100),
(81, 89, 92, 50),
(82, 91, 92, 100),
(83, 39, 92, 20),
(84, 85, 92, 10),
(85, 74, 92, 1),
(86, 75, 92, 1),
(87, 65, 94, 200),
(88, 68, 94, 100),
(89, 74, 94, 1),
(90, 70, 94, 500),
(91, 39, 94, 10),
(92, 73, 94, 5),
(93, 93, 94, 150),
(94, 72, 94, 200),
(95, 40, 96, 4),
(96, 93, 96, 50),
(97, 74, 96, 2),
(98, 75, 96, 1),
(99, 71, 96, 100),
(100, 38, 97, 100),
(101, 91, 97, 50);

-- --------------------------------------------------------

--
-- Structure de la table `contenu_multimedia`
--

DROP TABLE IF EXISTS `contenu_multimedia`;
CREATE TABLE IF NOT EXISTS `contenu_multimedia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `updated_at` datetime NOT NULL,
  `description` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nom_file` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dtype` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=305 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `contenu_multimedia`
--

INSERT INTO `contenu_multimedia` (`id`, `updated_at`, `description`, `nom_file`, `dtype`) VALUES
(33, '2021-04-04 23:37:30', NULL, '604ab4dd98c6e086288820.jpg', 'contenumultimedia'),
(34, '2021-04-04 23:37:30', NULL, '604add8e995b2353620808.jpg', 'contenumultimedia'),
(35, '2021-04-04 23:37:31', 'video herver', '606a4dbaf41e4502140639new.mp4', 'contenumultimedia'),
(36, '2021-04-04 23:31:34', NULL, '604a91ed9fb30336622720.JPG', 'contenumultimedia'),
(37, '2021-04-04 23:31:35', NULL, '606a4c57c14c9180038835new.mp4', 'contenumultimedia'),
(41, '2021-04-04 23:37:31', NULL, '606a4dbb40efa655817444new.mp4', 'contenumultimedia'),
(42, '2021-04-04 23:19:23', NULL, '604ae69c5f063692483898.JPG', 'contenumultimedia'),
(43, '2021-04-04 23:19:24', NULL, '606a497c1e52b210911431new.mp4', 'contenumultimedia'),
(45, '2021-04-04 23:56:02', NULL, '604b252d0cda9212641628.JPG', 'contenumultimedia'),
(46, '2021-04-05 00:02:12', NULL, '604b27cabe37d335792681.JPG', 'contenumultimedia'),
(47, '2021-04-05 00:02:32', NULL, '604b29843f1e4363465243.JPG', 'contenumultimedia'),
(48, '2021-04-05 00:06:11', NULL, '604b2b024248e533673096.JPG', 'contenumultimedia'),
(49, '2021-04-05 00:03:10', NULL, '604b2bf2a8987624327437.JPG', 'contenumultimedia'),
(50, '2021-04-05 00:03:39', NULL, '604b313f2288e834571551.jpg', 'contenumultimedia'),
(51, '2021-04-05 00:03:40', NULL, '606a53dc6ed56790206475new.mp4', 'contenumultimedia'),
(108, '2021-03-17 20:00:41', NULL, '60525a635d1fb601638607.jpg', 'contenumultimedia'),
(109, '2021-03-19 18:00:19', NULL, '6054e6b3c2dad972561331.jpg', 'contenumultimedia'),
(110, '2021-03-19 18:03:02', NULL, '6054e7567a813527762107.jpg', 'contenumultimedia'),
(121, '2021-03-19 18:04:23', NULL, '6054e7a7e0ce1024011523.jpg', 'contenumultimedia'),
(122, '2021-03-19 14:04:25', NULL, '6054af6989880356133138.jpg', 'contenumultimedia'),
(124, '2021-03-26 10:23:08', NULL, '605db60cb5b9e657327325.jpg', 'contenumultimedia'),
(125, '2021-04-07 12:44:26', NULL, '606da92a37fb6527096877.jpg', 'contenumultimedia'),
(126, '2021-03-26 10:16:58', NULL, '605db49a68ca2413991003.jpg', 'contenumultimedia'),
(127, '2021-03-26 10:21:52', NULL, '605db5c0e680a442602196.jpg', 'contenumultimedia'),
(140, '2021-03-26 10:23:54', NULL, '605db63a82987568056032.jpg', 'contenumultimedia'),
(141, '2021-03-26 10:25:34', NULL, '605db69e9e7ed911766130.jpg', 'contenumultimedia'),
(142, '2021-04-07 08:26:45', NULL, '605e6a224554e653335182.jpg', 'contenumultimedia'),
(143, '2021-04-07 08:26:45', NULL, '605e6a225aaf7758049329.jpg', 'contenumultimedia'),
(147, '2021-04-02 10:22:40', NULL, '6066f0707d7fe963906300.jpg', 'contenumultimedia'),
(166, '2021-04-06 10:48:55', NULL, '606acaad5bf3f225183165.jpg', 'contenumultimedia'),
(167, '2021-04-06 10:49:19', NULL, '606ac9f3b15ba387182338.jpg', 'contenumultimedia'),
(169, '2021-04-06 10:49:19', NULL, '606ad036137cd265748474.jpg', 'contenumultimedia'),
(171, '2021-04-05 11:01:44', NULL, '606aee18601c4057651162.jpg', 'contenumultimedia'),
(172, '2021-04-05 13:15:43', NULL, '606b0d7fb8017251757261.jpg', 'contenumultimedia'),
(173, '2021-04-05 13:16:05', NULL, '606b0d9587aff905306912.jpg', 'contenumultimedia'),
(174, '2021-04-07 08:26:45', NULL, '606c1e3127a2e129324351.jpg', 'contenumultimedia'),
(175, '2021-04-10 10:11:37', NULL, '606c8bef1607a194908741.JPG', 'contenumultimedia'),
(176, '2021-04-10 10:11:37', NULL, '606c9490cef73569980036.jpg', 'contenumultimedia'),
(177, '2021-04-07 01:20:34', NULL, '606d08cc80bf7449791643.jpg', 'contenumultimedia'),
(178, '2021-04-07 01:48:56', NULL, '606d0f7b08ca6855665940.jpg', 'contenumultimedia'),
(180, '2021-04-07 02:12:23', NULL, '606d1507b03e1879563342.jpg', 'contenumultimedia'),
(181, '2021-04-07 02:14:45', NULL, '606d159580afd349444777.jpg', 'contenumultimedia'),
(182, '2021-04-07 12:51:45', NULL, '606daae1a81ab946518893.jpg', 'contenumultimedia'),
(183, '2021-04-07 02:36:01', NULL, '606d1a91466e2315731500.jpg', 'contenumultimedia'),
(184, '2021-04-07 02:40:49', NULL, '606d1bb18bfc7430024325.jpg', 'contenumultimedia'),
(185, '2021-04-07 08:24:53', NULL, '606d6c4b94198486618200.jpg', 'contenumultimedia'),
(186, '2021-04-07 12:15:51', NULL, '606da277a6c87071581372.JPG', 'contenumultimedia'),
(187, '2021-04-07 12:15:51', NULL, '606da277acd47116985807.jpg', 'contenumultimedia'),
(188, '2021-04-07 12:15:51', NULL, '606da277ad8ac117812894.jpg', 'contenumultimedia'),
(189, '2021-04-07 12:45:11', NULL, '606da957c95a8299905834.jpg', 'contenumultimedia'),
(190, '2021-04-07 12:25:13', NULL, '606da4a903299152249885.jpg', 'contenumultimedia'),
(191, '2021-04-10 09:12:28', NULL, '606daabf7b4b7552593622.jpg', 'contenumultimedia'),
(198, '2021-04-07 15:52:54', NULL, '606dd556b0507853600298.JPG', 'contenumultimedia'),
(199, '2021-04-10 09:12:28', NULL, '60716a4e80bcf334519320.jpg', 'contenumultimedia'),
(200, '2021-04-10 09:12:28', NULL, '60716a598be6a435436274.jpg', 'contenumultimedia'),
(201, '2021-04-10 09:12:28', NULL, '60716a879067f490000708.jpg', 'contenumultimedia'),
(202, '2021-04-10 09:12:28', NULL, '60716ac86c82b989280622.jpg', 'contenumultimedia'),
(203, '2021-04-10 09:12:28', NULL, '60716ac87076a177179645.jpg', 'contenumultimedia'),
(204, '2021-04-10 09:12:28', NULL, '60716bfce988c706828953.jpg', 'contenumultimedia'),
(205, '2021-04-10 09:12:28', NULL, '60716bfcedd76791758864.jpg', 'contenumultimedia'),
(216, '2021-04-10 10:11:36', NULL, '6071798f6afa1703074296.jpg', 'contenumultimedia'),
(217, '2021-04-10 10:11:36', NULL, '607179c6be2fe224295525.jpg', 'contenumultimedia'),
(218, '2021-04-10 10:11:37', NULL, '607179d91ca82463420931.jpg', 'contenumultimedia'),
(219, '2021-04-10 21:10:56', NULL, '607214604540f412717620.jpg', 'contenumultimedia'),
(228, '2021-04-14 00:00:00', NULL, 'C:\\Users\\PC\\Pictures\\jzR5La.jpg', 'contenumultimedia'),
(229, '2021-04-14 00:00:00', NULL, 'C:\\Users\\PC\\Pictures\\jzR5La.jpg', 'contenumultimedia'),
(230, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture2.PNG', 'contenumultimedia'),
(231, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture1.PNG', 'contenumultimedia'),
(232, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture2.PNG', 'contenumultimedia'),
(233, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture2.PNG', 'contenumultimedia'),
(234, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture1.PNG', 'contenumultimedia'),
(235, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture2.PNG', 'contenumultimedia'),
(236, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture2.PNG', 'contenumultimedia'),
(237, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture2.PNG', 'contenumultimedia'),
(238, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture1.PNG', 'contenumultimedia'),
(239, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture2.PNG', 'contenumultimedia'),
(240, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture2.PNG', 'contenumultimedia'),
(241, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture1.PNG', 'contenumultimedia'),
(242, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture2.PNG', 'contenumultimedia'),
(243, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture1.PNG', 'contenumultimedia'),
(244, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\ph2.PNG', 'contenumultimedia'),
(245, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture2.PNG', 'contenumultimedia'),
(246, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\Capture1.PNG', 'contenumultimedia'),
(247, '2021-04-15 00:00:00', NULL, 'C:\\Users\\PC\\Desktop\\ph2.PNG', 'contenumultimedia'),
(249, '2021-04-27 08:39:04', NULL, '6087cda819b35976798744.jpg', 'contenumultimedia'),
(250, '2021-04-27 08:39:04', NULL, '6087cda83490b622687526.jpg', 'contenumultimedia'),
(251, '2021-04-27 08:39:04', NULL, '6087cda8351ea847275692.jpg', 'contenumultimedia'),
(252, '2021-04-27 14:31:54', NULL, '6088205abe70d132758873.jpg', 'contenumultimedia'),
(253, '2021-04-27 14:41:25', NULL, '6088229522de7319593627.jpg', 'contenumultimedia'),
(254, '2021-04-27 14:43:56', NULL, '6088232c1774f142558303.jpg', 'contenumultimedia'),
(255, '2021-04-27 14:45:20', NULL, '6088238011d1c330587865.jpg', 'contenumultimedia'),
(258, '2021-04-28 15:03:57', NULL, 'test', 'contenumultimedia'),
(260, '2021-04-28 20:23:43', NULL, '6089c44fbe9f3420367572new.mp4', 'contenumultimedia'),
(261, '2021-04-28 20:26:09', NULL, '6089c4e1985be895162875.jpg', 'contenumultimedia'),
(262, '2021-04-28 20:26:42', NULL, '6089c502c025e624903173.jpg', 'contenumultimedia'),
(263, '2021-04-28 20:30:36', NULL, '6089c5ec813ee688300815.jpg', 'contenumultimedia'),
(264, '2021-04-28 20:32:12', NULL, '6089c64ce76a6211571319.jpg', 'contenumultimedia'),
(265, '2021-04-28 20:39:46', NULL, '6089c8125eeb8757927461.jpg', 'contenumultimedia'),
(266, '2021-04-28 21:13:08', NULL, '6089cfe402187169898959.jpg', 'contenumultimedia'),
(267, '2021-04-28 21:13:11', NULL, '6089cfe757aec053304110.jpg', 'contenumultimedia'),
(268, '2021-04-28 21:13:28', NULL, '6089cff869f5b162760667new.mp4', 'contenumultimedia'),
(269, '2021-04-28 23:59:07', NULL, '6089f6cbebdeb758593911.PNG', 'contenumultimedia'),
(270, '2021-04-28 23:59:15', NULL, '6089f6d3505fd447921212.PNG', 'contenumultimedia'),
(271, '2021-04-28 23:59:21', NULL, '6089f6d9e7062179896721.PNG', 'contenumultimedia'),
(272, '2021-04-29 10:32:06', NULL, '608a8b262cabd388350892.jpg', 'contenumultimedia'),
(273, '2021-04-29 10:35:54', NULL, '608a8c0aebba6252497263new.mp4', 'contenumultimedia'),
(274, '2021-04-29 10:35:59', NULL, '608a8c0f33fee412181173.jpg', 'contenumultimedia'),
(275, '2021-04-29 10:36:03', NULL, '608a8c139c4d8566841435.jpg', 'contenumultimedia'),
(276, '2021-05-08 23:57:23', NULL, '609725635a435934827506.jpg', 'contenumultimedia'),
(280, '2021-05-09 00:26:24', NULL, '60972c30b49b4600956429.jpg', 'contenumultimedia'),
(281, '2021-05-09 00:31:00', NULL, '60972d441c69a373832436.jpg', 'contenumultimedia'),
(282, '2021-05-09 00:31:47', NULL, '60972d73b76e6038602564.jpg', 'contenumultimedia'),
(283, '2021-05-09 00:34:13', NULL, '60972e05368db793651879.JPG', 'contenumultimedia'),
(284, '2021-05-09 00:35:00', NULL, '60972e34205fc368277984.jpg', 'contenumultimedia'),
(285, '2021-05-09 00:35:56', NULL, '60972e6cee0d8901566527.jpg', 'contenumultimedia'),
(286, '2021-05-09 00:38:48', NULL, '60972f1880c7c650921946.jpg', 'contenumultimedia'),
(287, '2021-05-09 00:39:45', NULL, '60972f5142e7b058734243.jpg', 'contenumultimedia'),
(288, '2021-05-09 00:41:18', NULL, '60972fae4acb9927083238.JPG', 'contenumultimedia'),
(289, '2021-05-09 00:43:17', NULL, '609730259fbcc332512922.jpg', 'contenumultimedia'),
(290, '2021-05-09 00:50:27', NULL, '609731d3c65d9908539350.JPG', 'contenumultimedia'),
(291, '2021-05-09 00:50:51', NULL, '609731eb36590631874784.jpg', 'contenumultimedia'),
(292, '2021-05-09 00:51:24', NULL, '6097320c3ca36019951767.jpg', 'contenumultimedia'),
(293, '2021-05-09 00:51:54', NULL, '6097322a1ce5d831062978.jpg', 'contenumultimedia'),
(294, '2021-05-09 00:52:13', NULL, '6097323d6326f188180718.jpg', 'contenumultimedia'),
(295, '2021-05-09 00:52:30', NULL, '6097324edd0a1525286181.jpg', 'contenumultimedia'),
(296, '2021-05-09 00:52:56', NULL, '609732685b821054376240.jpg', 'contenumultimedia'),
(297, '2021-05-09 00:53:15', NULL, '6097327b811df065762239.jpg', 'contenumultimedia'),
(298, '2021-05-09 00:54:37', NULL, '609732cd9b90b056948703.jpg', 'contenumultimedia'),
(299, '2021-05-09 00:54:47', NULL, '609732d7a9c87714645775.jpg', 'contenumultimedia'),
(300, '2021-05-09 00:55:34', NULL, '6097330640e86105767774.jpg', 'contenumultimedia'),
(301, '2021-05-09 00:55:47', NULL, '609733137aef2990798316.jpg', 'contenumultimedia'),
(302, '2021-05-09 00:56:09', NULL, '60973329d0462245817689.jpg', 'contenumultimedia'),
(303, '2021-05-11 22:50:48', NULL, '609b0a4844f10740085990.jpg', 'contenumultimedia'),
(304, '2021-05-11 22:52:43', NULL, '609b0abb68db0815225864.jpg', 'contenumultimedia');

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

DROP TABLE IF EXISTS `doctrine_migration_versions`;
CREATE TABLE IF NOT EXISTS `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20210221093539', '2021-03-04 20:17:52', 488),
('DoctrineMigrations\\Version20210221122958', '2021-03-04 20:17:54', 81),
('DoctrineMigrations\\Version20210221134014', '2021-03-04 20:17:55', 88),
('DoctrineMigrations\\Version20210222213159', '2021-03-04 20:17:55', 73),
('DoctrineMigrations\\Version20210301224232', '2021-03-04 20:17:56', 437),
('DoctrineMigrations\\Version20210302132545', '2021-03-04 20:17:57', 240),
('DoctrineMigrations\\Version20210302134104', '2021-03-04 20:17:57', 517),
('DoctrineMigrations\\Version20210303174508', '2021-03-04 20:17:59', 97),
('DoctrineMigrations\\Version20210303215601', '2021-03-04 20:17:59', 602),
('DoctrineMigrations\\Version20210303215642', '2021-03-04 20:18:00', 696),
('DoctrineMigrations\\Version20210304112205', '2021-03-04 20:18:02', 83),
('DoctrineMigrations\\Version20210304113125', '2021-03-04 20:18:02', 304),
('DoctrineMigrations\\Version20210304113658', '2021-03-04 20:18:03', 252),
('DoctrineMigrations\\Version20210304115214', '2021-03-04 20:18:04', 526),
('DoctrineMigrations\\Version20210304154638', '2021-03-04 20:18:05', 96),
('DoctrineMigrations\\Version20210304201856', '2021-03-04 20:19:22', 94),
('DoctrineMigrations\\Version20210304221243', '2021-03-04 22:14:40', 224),
('DoctrineMigrations\\Version20210304221931', '2021-03-04 22:20:10', 176),
('DoctrineMigrations\\Version20210304231437', '2021-03-04 23:14:56', 256),
('DoctrineMigrations\\Version20210304232544', '2021-03-04 23:25:55', 249),
('DoctrineMigrations\\Version20210305012745', '2021-03-05 01:27:59', 634),
('DoctrineMigrations\\Version20210305083252', '2021-03-05 08:34:25', 452),
('DoctrineMigrations\\Version20210306162114', '2021-03-06 16:22:05', 640),
('DoctrineMigrations\\Version20210309140339', '2021-03-09 14:05:09', 404),
('DoctrineMigrations\\Version20210309143650', '2021-03-09 14:37:25', 314),
('DoctrineMigrations\\Version20210309155803', '2021-03-09 15:58:52', 894),
('DoctrineMigrations\\Version20210309185307', '2021-03-09 18:53:31', 99),
('DoctrineMigrations\\Version20210309185549', '2021-03-09 18:56:11', 113),
('DoctrineMigrations\\Version20210310164256', '2021-03-10 16:44:34', 2539),
('DoctrineMigrations\\Version20210311101958', '2021-03-11 10:21:07', 562),
('DoctrineMigrations\\Version20210317154306', '2021-03-17 15:45:19', 732),
('DoctrineMigrations\\Version20210317201555', '2021-03-17 20:17:54', 676),
('DoctrineMigrations\\Version20210319094320', '2021-03-19 09:44:34', 1609),
('DoctrineMigrations\\Version20210319095019', '2021-03-19 09:53:10', 242),
('DoctrineMigrations\\Version20210319153319', '2021-03-21 15:14:12', 1583),
('DoctrineMigrations\\Version20210321145159', '2021-03-21 15:02:34', 2450),
('DoctrineMigrations\\Version20210321160929', '2021-03-21 16:11:07', 609),
('DoctrineMigrations\\Version20210322213727', '2021-03-22 21:39:01', 906),
('DoctrineMigrations\\Version20210324130604', '2021-03-24 13:06:24', 1465),
('DoctrineMigrations\\Version20210324153515', '2021-03-24 15:37:27', 348),
('DoctrineMigrations\\Version20210325210550', '2021-03-25 21:13:00', 1446),
('DoctrineMigrations\\Version20210326222309', '2021-03-26 22:23:54', 513),
('DoctrineMigrations\\Version20210401170347', '2021-04-02 08:45:15', 60),
('DoctrineMigrations\\Version20210402084554', '2021-04-02 08:46:55', 428),
('DoctrineMigrations\\Version20210402170026', '2021-04-02 17:02:09', 922),
('DoctrineMigrations\\Version20210404171037', '2021-04-04 17:13:55', 373),
('DoctrineMigrations\\Version20210404220525', '2021-04-04 22:06:31', 149),
('DoctrineMigrations\\Version20210404221330', '2021-04-04 22:15:29', 552),
('DoctrineMigrations\\Version20210405034132', '2021-04-05 03:42:45', 140),
('DoctrineMigrations\\Version20210405034446', '2021-04-05 03:45:39', 469),
('DoctrineMigrations\\Version20210405075846', '2021-04-05 08:00:04', 429),
('DoctrineMigrations\\Version20210405084803', '2021-04-05 09:42:51', 116),
('DoctrineMigrations\\Version20210406084458', '2021-04-06 09:00:34', 478),
('DoctrineMigrations\\Version20210406103437', '2021-04-06 10:40:08', 1390),
('DoctrineMigrations\\Version20210407124741', '2021-04-07 12:50:14', 600),
('DoctrineMigrations\\Version20210407132420', '2021-04-07 13:25:56', 668),
('DoctrineMigrations\\Version20210408105417', '2021-04-08 10:55:33', 367),
('DoctrineMigrations\\Version20210410084147', '2021-04-10 08:44:27', 652),
('DoctrineMigrations\\Version20210410091450', '2021-04-10 09:15:31', 1225),
('DoctrineMigrations\\Version20210410092052', '2021-04-10 09:40:05', 702),
('DoctrineMigrations\\Version20210414103231', '2021-04-14 10:33:27', 1030),
('DoctrineMigrations\\Version20210505172144', '2021-05-05 17:26:17', 577);

-- --------------------------------------------------------

--
-- Structure de la table `etape_de_preparation`
--

DROP TABLE IF EXISTS `etape_de_preparation`;
CREATE TABLE IF NOT EXISTS `etape_de_preparation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ordre` int NOT NULL,
  `duree` int NOT NULL,
  `description` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `plat_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_35EC67BDD73DB560` (`plat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `etape_de_preparation`
--

INSERT INTO `etape_de_preparation` (`id`, `ordre`, `duree`, `description`, `plat_id`) VALUES
(44, 0, 2, 'Faites fondre le beurre au micro-ondes et faites légèrement chauffer le lait qui doit être à peine tiède (ça évite les grumeaux)', 69),
(45, 1, 1, 'Mélangez la farine tamisée, le sucre, le sel dans un grand bol.', 69),
(46, 2, 1, 'Ajoutez les oeufs, le beurre fondu, puis progressivement le lait, en battant avec un fouet bien pour éviter la formation des grumeaux', 69),
(47, 3, 10, 'Faîtes chauffer une noix de beurre dans la poêle et disposez une louche de pâte', 69),
(48, 4, 10, 'Faites cuire vos crêpes de chaque côté, qu\'elles soient bien dorées\r\nAu fur et à mesure, réservez dans une assiette en couvrant avec une feuille de papier aluminium pour les garder chaudes et moelleuse. Vous devriez obtenir entre 15 et 20 crêpes avec une poêle de 20 cm de diamètre.', 69),
(49, 0, 5, 'Commencez par laver et éplucher au besoin les champignons, faites les revenir avec l\'ail dans un peu d\'huile d\'olive, réservez', 76),
(50, 1, 10, 'Entaillez les blancs de poulet comme dans la vidéo ci-dessous, de façon à pouvoir les aplatir facilement, puis enduisez les d\'un mélange huile d\'olive, sel et poivre', 76),
(51, 2, 3, 'Sur une grande feuille de film alimentaire, étales les blancs de poulet en les faisant se chevaucher pour avoir un grand rectangle de blancs de poulet', 76),
(52, 3, 2, 'Disposez une couche de champignons dessus, puis le fromage râpé, et commencez à rouler délicatement en serrant bien pour obtenir un gros rouleau', 76),
(53, 4, 1, 'Utilisez de la ficelle alimentaire et ficelez votre roti de poulet pour qu\'il tienne bien pendant la cuisson', 76),
(54, 5, 50, 'Disposez dans un plat à rôtir avec 200 ml de fond de volaille et mettez au four à 180°C pendant 40 à 50 minutes', 76),
(55, 0, 5, 'Réalisez la pâte à crêpes : dans un saladier, mélangez la farine, le cacao, le sel et le sucre', 84),
(56, 1, 5, 'Ajoutez les oeufs 1 par un, puis le beurre fondu', 84),
(57, 2, 5, 'Ajoutez ensuite le lait tiède et mélangez vigoureusement pour dissoudre tous les grumeaux', 84),
(58, 3, 3, 'Faites chauffer la poêle avec une noix de beurre et répartissez une louche de pâte', 84),
(59, 4, 2, 'Retournez la crêpe quand elle est cuite et commence à dorer', 84),
(60, 5, 1, 'Etalez la pâte a tartiner et parsemez de pralin, rabattez 2 côtés de la crêpe et roulez comme un nem (voir la vidéo)', 84),
(61, 6, 20, 'continue jusqu\'au cuisson de tout les crêpe', 84),
(62, 0, 10, 'couper les legume', 97);

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE IF NOT EXISTS `evaluation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `commentaire` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `note` int DEFAULT NULL,
  `nutritionniste_id` int DEFAULT NULL,
  `patient_id` int DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_1323A575279DA68A` (`nutritionniste_id`),
  KEY `IDX_1323A5756B899279` (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `evaluation`
--

INSERT INTO `evaluation` (`id`, `commentaire`, `note`, `nutritionniste_id`, `patient_id`, `created_at`) VALUES
(1, 'testt', 4, 58, 67, '2021-03-26 16:01:01'),
(2, 'ya3tih saha', 4, 58, 67, '2021-03-26 16:37:15'),
(3, 'behya', 4, 58, 67, '2021-03-26 16:39:07'),
(4, 'mrigla', 4, 58, 67, '2021-03-26 17:47:35'),
(5, 'tt', 2, 58, 67, '2021-03-26 18:05:27'),
(6, 'mrigla', 2, 58, 67, '2021-03-26 19:48:50'),
(7, 'tres bien', 4, 58, 67, '2021-03-26 21:14:55'),
(8, 'bien', 4, 58, 67, '2021-03-26 21:18:12'),
(9, 'tres bien', 4, 58, 67, '2021-04-02 18:38:04'),
(10, 'sahit', 2, 58, 67, '2021-04-02 18:41:46'),
(11, 'bien', 1, 58, 67, '2021-04-02 18:51:30'),
(12, 'excelent medecin', 1, 58, 67, '2021-04-02 18:53:47'),
(13, 'ok', 3, 58, 67, '2021-04-02 20:08:11'),
(14, 'tres bien', 2, 58, 67, '2021-04-02 21:43:05'),
(15, 'Excelente nutritionniste !', 5, 68, 67, '2021-04-07 01:36:22'),
(16, 'excelent médecin!', 4, 64, 61, '2021-04-07 12:23:33'),
(17, 'tres bien', 3, 64, 61, '2021-04-07 15:31:14');

-- --------------------------------------------------------

--
-- Structure de la table `fiche_consultation`
--

DROP TABLE IF EXISTS `fiche_consultation`;
CREATE TABLE IF NOT EXISTS `fiche_consultation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `creation_date` date NOT NULL,
  `poids` double NOT NULL,
  `taille` double NOT NULL,
  `symptome` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `apetit` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `patient_id` int DEFAULT NULL,
  `nutritionniste_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_CAD698936B899279` (`patient_id`),
  KEY `IDX_CAD69893279DA68A` (`nutritionniste_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `fiche_consultation`
--

INSERT INTO `fiche_consultation` (`id`, `creation_date`, `poids`, `taille`, `symptome`, `apetit`, `description`, `patient_id`, `nutritionniste_id`) VALUES
(34, '2016-01-01', 2.5, 8.9, 'ss', 'ss', 'ss', 63, 68),
(35, '2022-06-06', 100, 120, 'diarré', 'modéré', 'surpoids', 63, 64),
(36, '2021-02-06', 80, 1.3, 'nossé', 'modéré', 'risque de nutrition', 67, 68),
(37, '2016-01-01', 75, 1.3, 'mal au dos', 'modéré', 'surpoids', 67, 64),
(38, '2021-01-01', 2.5, 8.9, 'ddd', 'dd', 'dd', 67, 64),
(39, '2016-01-01', 50, 9, 'hhhhh', 'modéré', 'kkkkkkk', 63, 68),
(40, '2019-07-09', 70, 1.3, 'diarré', 'modéré', 'patient à risque de dénutrition', 67, 68),
(42, '2016-05-01', 70, 1.5, 'affaiblissement', 'augmentation de l\'appétit', 'hypoglycémie survenue entre autre apres un effort physique', 67, 68),
(43, '2016-05-01', 70, 1.5, 'affaiblissement', 'augmentation de l\'appétit', 'hypoglycémie survenue entre autre apres un effort physique', 63, 68),
(44, '2016-01-01', 70, 120, 'hypoglycémie', 'augmentation de l\'appétit', 'Risque de dénutrition', 61, 68),
(45, '2016-01-01', 5, 8, 's', 's', 'ss', 61, 68),
(46, '2016-01-01', 70, 140, 'ed', 'dd', 'dd', 67, 64),
(47, '2018-01-01', 150, 140, 'vomissement', 'modéré', 'dénutrition', 61, 64),
(49, '2016-01-01', 190, 140, 'maladie', 'modéré', 'risque de dénutrition', 61, 64);

-- --------------------------------------------------------

--
-- Structure de la table `medicament`
--

DROP TABLE IF EXISTS `medicament`;
CREATE TABLE IF NOT EXISTS `medicament` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `quantite` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `duree` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fiche_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_9A9C723ADF522508` (`fiche_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `medicament`
--

INSERT INTO `medicament` (`id`, `nom`, `quantite`, `duree`, `fiche_id`) VALUES
(17, 'doliprane', '2', '1 mois', 34),
(18, 'gripex', '2', '1 mois', 35),
(19, 'doliprane', '8', '2 mois', 35),
(20, 'gripex', '2', '1 mois', 36),
(21, 'aaa', 'aa', 'aa', 36),
(22, 'doliprane', '2', '1 mois', 37),
(23, 'doliprane', '8', '1 mois', 38),
(25, 'gripex', '2 sachets', '2 mois', 40),
(28, 'OXOGLURATE D\'ORNITHINE', '5 grammes', '1 mois', 42),
(29, 'CETORNAN', '10 grammes', '1 mois', 42),
(30, 'doliprane', '8', '1 mois', 43),
(31, 'gripex', '10 grammes', '1 mois', 43),
(36, 'vitamine A', '2 sachets', '1 mois', 47),
(45, 'sirine', 'sirine', 'sirine', NULL),
(46, 'sirine', 'sirine', 'tawaa', NULL),
(47, 'doliprane', '3ff', 'fff', NULL),
(48, 'sirinne', 'sirine', 'siri,ee', NULL),
(49, 'hhhh', 'hhhhh', 'hhhhh', NULL),
(50, 'FAEFEA', 'feaefa', 'feafe', NULL),
(51, '', '', '', NULL),
(53, 'cetronan', '2 sachets', '1 mois', 44),
(54, 'OXOGLURATE D\'ORNITHINE', '1 comprimé/jour', '1 mois', 44),
(56, 'vitamine A', '2 sachets/jour', '2 mois', 35),
(57, 'nouveaumedicament', '1', '4 mois', 35);

-- --------------------------------------------------------

--
-- Structure de la table `messenger_messages`
--

DROP TABLE IF EXISTS `messenger_messages`;
CREATE TABLE IF NOT EXISTS `messenger_messages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `body` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `headers` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue_name` varchar(190) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `available_at` datetime NOT NULL,
  `delivered_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_75EA56E0FB7336F0` (`queue_name`),
  KEY `IDX_75EA56E0E3BD61CE` (`available_at`),
  KEY `IDX_75EA56E016BA31DB` (`delivered_at`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `mesure`
--

DROP TABLE IF EXISTS `mesure`;
CREATE TABLE IF NOT EXISTS `mesure` (
  `id` int NOT NULL AUTO_INCREMENT,
  `taille` double NOT NULL,
  `poids` double NOT NULL,
  `date_mesure` datetime NOT NULL,
  `patient_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `mesure`
--

INSERT INTO `mesure` (`id`, `taille`, `poids`, `date_mesure`, `patient_id`) VALUES
(8, 1.8, 878, '2021-04-04 00:00:00', 71),
(9, 1.9, 50, '2021-02-06 17:28:36', 71),
(10, 1.9, 50, '2021-08-06 17:29:04', 71),
(11, 1.9, 88, '2021-07-06 17:55:58', 71),
(12, 1.9, 70, '2021-06-06 20:05:39', 71),
(13, 1.9, 65, '2021-08-05 21:16:16', 71),
(14, 1.9, 61, '2021-04-09 21:42:13', 71),
(15, 1.9, 63, '2021-03-08 21:44:20', 71),
(29, 1.9, 20, '2021-04-07 12:00:21', 71),
(30, 1.9, 60, '2021-04-07 12:02:00', 71),
(31, 1.9, 85, '2021-04-07 15:50:26', 71),
(32, 1.8, 60, '2021-04-09 17:31:29', 67),
(33, 190, 80, '2021-04-06 00:00:00', 61),
(34, 190, 75, '2021-04-20 00:00:00', 61),
(35, 190, 75, '2021-04-20 00:00:00', 61),
(37, 190, 82, '2021-04-14 00:00:00', 61),
(38, 190, 85, '2021-04-13 00:00:00', 61),
(40, 4, 4, '2021-04-29 10:16:02', 67);

-- --------------------------------------------------------

--
-- Structure de la table `nourriture`
--

DROP TABLE IF EXISTS `nourriture`;
CREATE TABLE IF NOT EXISTS `nourriture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lipides` double NOT NULL,
  `glucides` double NOT NULL,
  `proteines` double NOT NULL,
  `poid` double NOT NULL,
  `dtype` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nutritionniste_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_7447E613279DA68A` (`nutritionniste_id`)
) ENGINE=InnoDB AUTO_INCREMENT=226 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `nourriture`
--

INSERT INTO `nourriture` (`id`, `nom`, `lipides`, `glucides`, `proteines`, `poid`, `dtype`, `nutritionniste_id`) VALUES
(38, 'tomate', 0.2, 3.9, 0.9, 100, 'aliment', NULL),
(39, 'huile olive', 100, 0, 0, 100, 'aliment', NULL),
(40, 'oeuf', 6.6, 0.66, 7.8, 1, 'aliment', NULL),
(65, 'farine de blé', 1, 76, 10, 100, 'aliment', NULL),
(66, 'lait', 1, 5, 3.4, 100, 'aliment', NULL),
(67, 'sucre', 0, 100, 0, 100, 'aliment', NULL),
(68, 'beurre', 81, 0.1, 0.9, 100, 'aliment', NULL),
(69, 'crepe', 73.9, 295.19, 71.95, 834, 'plat', 74),
(70, 'champignon', 0.3, 3.3, 3.1, 100, 'aliment', NULL),
(71, 'poulet', 14, 0, 27, 100, 'aliment', NULL),
(72, 'mozzarella', 17, 3.1, 28, 100, 'aliment', NULL),
(73, 'ail', 0.3, 21.1, 5.8, 100, 'aliment', NULL),
(74, 'sel', 0, 0, 0, 100, 'aliment', NULL),
(75, 'poivre noir', 3.26, 64.81, 10.95, 100, 'aliment', NULL),
(76, 'roule de poulet aux champignons et mozzarella', 189.9626, 18.8581, 335.9895, 1531, 'plat', 74),
(80, 'Laitue romaine', 0.3, 3.3, 1.2, 100, 'aliment', NULL),
(81, 'Laitue', 0.2, 2.9, 1.4, 100, 'aliment', NULL),
(82, 'cacao en poudre', 14, 58, 20, 100, 'aliment', NULL),
(83, 'nutella', 30.9, 57.5, 6.3, 100, 'aliment', NULL),
(84, 'crepe chocolate', 90.85, 204.02, 57.76, 614, 'plat', 74),
(85, 'vinaigre de pomme', 0, 0.9, 0, 100, 'aliment', NULL),
(87, 'curry aux légumes', 86.23, 6.01, 109.48, 540, 'plat', 76),
(88, 'pain de mie complet', 4.3, 42.1, 8.4, 100, 'aliment', NULL),
(89, 'avocat', 15, 9, 2, 100, 'aliment', NULL),
(90, 'Recette tartine avocat oeuf mollet', 27.0326, 60.1481, 12.4095, 352, 'plat', 76),
(91, 'Chou frisé', 0.9, 9, 4.3, 100, 'aliment', NULL),
(92, 'Salade de chou frisé au poulet', 70.6326, 18.1381, 87.3095, 582, 'plat', 76),
(93, 'persil', 0.8, 6, 3, 100, 'aliment', NULL),
(94, 'Recette de la tarte aux champignons et mozzarella', 129.715, 184.855, 97.19, 1166, 'plat', 76),
(95, 'feuille brick', 0.5, 7, 10, 100, 'aliment', NULL),
(96, 'Recette des Bricks ou Briouates au poulet', 40.8326, 6.2881, 59.8095, 157, 'plat', 76),
(97, 'salade', 0.65, 8.4, 3.05, 150, 'plat', 76),
(169, 'fraise', 0.3, 8, 0.7, 100, 'aliment', 74),
(173, 'pomme', 1, 1, 1, 100, 'aliment', 74),
(177, 'dd', 1, 1, 1, 100, 'aliment', 74),
(178, 'new', 1, 1, 1, 100, 'aliment', 74),
(179, 'aliment', 1, 1, 1, 100, 'aliment', 74),
(180, 'vvvvvv', 1, 1, 1, 100, 'aliment', 74),
(181, 'vv', 1, 1, 1, 100, 'aliment', 74),
(182, 'vvvx', 1, 1, 1, 100, 'aliment', 74),
(222, 'brownies', 23.64, 50.88, 5.76, 100, 'aliment', 55);

-- --------------------------------------------------------

--
-- Structure de la table `nutritionniste`
--

DROP TABLE IF EXISTS `nutritionniste`;
CREATE TABLE IF NOT EXISTS `nutritionniste` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `nutritionniste`
--

INSERT INTO `nutritionniste` (`id`) VALUES
(58),
(59),
(64),
(68),
(74),
(75),
(76),
(77),
(78),
(98),
(118),
(119),
(125);

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `id` int NOT NULL,
  `style_de_vie` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id`, `style_de_vie`) VALUES
(61, 'dbcslbdskbcdksj'),
(63, 'bytyvy'),
(67, 'jsp'),
(71, 'sscdsdq'),
(80, 'btbgb'),
(130, 'its my life'),
(131, 'tranquille		'),
(132, 'vfdvd'),
(133, 'njgljljn');

-- --------------------------------------------------------

--
-- Structure de la table `patient_challenge`
--

DROP TABLE IF EXISTS `patient_challenge`;
CREATE TABLE IF NOT EXISTS `patient_challenge` (
  `patient_id` int NOT NULL,
  `challenge_id` int NOT NULL,
  PRIMARY KEY (`patient_id`,`challenge_id`),
  KEY `IDX_71825B6E6B899279` (`patient_id`),
  KEY `IDX_71825B6E98A21AC6` (`challenge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `plat`
--

DROP TABLE IF EXISTS `plat`;
CREATE TABLE IF NOT EXISTS `plat` (
  `id` int NOT NULL,
  `description` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nbrportion` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `plat`
--

INSERT INTO `plat` (`id`, `description`, `nbrportion`) VALUES
(69, 'Ma recette facile et rapide de pâte à crêpes facile à réaliser pour un résultat excellent. Elles sont légères, parfumées, bien dorées et peu sucrées (ce qui permet de se rattraper sur la garniture :). Je vous donne ci-dessous mes astuces pour bien les réussir. J’essaie de beurrer ma poêle toutes les 3 crêpes en général, et je les réserve sous une feuille de papier aluminium avant de les déguster, elles restent chaudes et moelleuses', 4),
(76, 'A base de blancs de poulet ou de dinde, ce roulé est excellent comme plat familial à partager. Vous pouvez le garnir de champignons et mozzarella comme dans la vidéo ci-dessous, ou choisir d’autres ingrédients. Pour les fêtes, un peu de truffe pourrait être parfait, mais imaginez des versions plus exotiques avec du curry, des épices, ou pourquoi pas même des petits cubes d’ananas dans la farce', 8),
(84, 'Une nouvelle recette facile de crêpes au nutella ou votre pâte à tartinée préférée comme Nocciolata, Jardin bio, Alter eco, Ouf!, avec une pate à crêpes au chocolat facile et inratable. Elles sont déjà roulées et garnies, encore plus de plaisir à les déguster en famille ou entre amis.', 8),
(87, 'curry de légumes super facile et savoureux. La base : ail, gingembre et curry en poudre. Pour le reste, vous allez pouvoir utiliser les ingrédients à disposition chez vous. Les légumes : courgette, aubergine, petits pois, pois chiches, haricots, brocolis et choux, courge, potimaron, pomme de terre, carottes…. bref faites votre mix ! Mon conseil, faites des naans maison en plus pour déguster ce délicieux curry !', 4),
(90, 'Une recette de Tartine avocat et aux oeufs mollets (le blanc est cuit, le jaune est coulant), parfaite pour le brunch ou encore pour les sportifs ! Ces tartines font fureur, vous devez choisir un bon pain (pain de campagne, pain poilane, pain complet ou encore pain aux céréales par exemple), le faire dorer, et faites comme en Espagne, frottez le avec un peu d’huile d’olive, une tomate et de l’ail c’est topissime.', 4),
(92, 'Une salade Kale gourmande, goûteuse et fruitée pour se faire plaisir en cette fin d’été ! A base de chou Kale – LE légume minceur tendance aux Etats-Unis – de poulet mariné au citron pour l’apport de protéines, d’avocat dont les bienfaits ne sont plus à démontrer, de pêche et de graines pour apporter du croquant, elle vous régalera les papilles et vous fera du bien !', 4),
(94, 'C’est la saison des champignons, cèpes, girolles entre autres, alors tentez cette bonne tarte maison aux champignons fondants et mozzarella ! Dans la pâte brisée, j’ai mis 8 grammes d’encre de sèche (sous forme liquide, diluée dans l’eau utilisée pour réaliser ma pâte), que j’ai acheté chez le poissonnier. Le résultat ? Un fond de tarte de couleur noire, c’est très élégant et surprenant ! J’ai utilisé un mélange de champignons, mes préférés étant les cèpes.', 6),
(96, 'Mon amie Nadia du blog Paprikas est venue partager sa cuisine avec moi pour vous proposer des bricks ou briouates au poulet, bien dorées et croustillantes, parfaites pour une entrée ou un apéritif entre amis. Regardez la vidéo, et vous apprendrez à plier les feuilles de brick en triangle avec plein d’astuces !', 4),
(97, 'descripto', 4);

-- --------------------------------------------------------

--
-- Structure de la table `proportion`
--

DROP TABLE IF EXISTS `proportion`;
CREATE TABLE IF NOT EXISTS `proportion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int DEFAULT NULL,
  `aliment_id` int NOT NULL,
  `poid` double NOT NULL,
  `date` datetime NOT NULL,
  `lipides` double NOT NULL,
  `glucides` double NOT NULL,
  `proteines` double NOT NULL,
  `calorie` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_BFB196F26B899279` (`patient_id`),
  KEY `IDX_BFB196F2415B9F11` (`aliment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `proportion`
--

INSERT INTO `proportion` (`id`, `patient_id`, `aliment_id`, `poid`, `date`, `lipides`, `glucides`, `proteines`, `calorie`) VALUES
(1, 71, 65, 42, '2021-04-06 10:44:01', 0.42, 31.92, 4.2, 169.26),
(2, 71, 67, 100, '2021-04-06 10:44:50', 0, 100, 0, 400),
(4, 71, 66, 12, '2021-04-06 11:26:29', 0.12, 0.6, 0.408, 7.152),
(5, 71, 67, 42, '2021-04-06 13:35:16', 0, 42, 0, 168),
(6, 71, 39, 15, '2021-04-06 14:35:01', 15, 0, 0, 135),
(7, 71, 40, 17, '2021-04-04 14:35:04', 112.2, 11.22, 132.6, 2248.08),
(8, 71, 38, 19, '2021-04-06 14:35:08', 0.038, 0.741, 0.171, 4.845),
(9, 71, 66, 25, '2021-04-02 14:35:12', 0.25, 1.25, 0.85, 14.9),
(10, 71, 67, 100, '2021-04-03 14:35:15', 0, 100, 0, 400),
(11, 71, 39, 100, '2021-04-03 14:35:16', 100, 0, 0, 900),
(12, 71, 65, 100, '2021-04-03 14:35:19', 1, 76, 10, 403),
(14, 71, 67, 100, '2021-04-05 14:35:54', 0, 100, 0, 400),
(15, 71, 93, 74, '2021-03-10 14:36:24', 0.592, 4.44, 2.22, 43.068),
(17, 71, 67, 400, '2021-03-08 14:36:51', 0, 400, 0, 1600),
(18, 71, 67, 400, '2021-03-07 14:36:55', 0, 400, 0, 1600),
(19, 71, 67, 6000, '2021-03-01 14:40:51', 0, 6000, 0, 24000),
(20, 71, 67, 7000, '2021-02-06 14:41:04', 0, 7000, 0, 28000),
(21, 71, 67, 8000, '2021-01-06 14:41:13', 0, 8000, 0, 32000),
(23, 71, 67, 8554, '2020-12-06 14:50:00', 0, 8554, 0, 34216),
(24, 71, 67, 500, '2021-04-05 10:15:24', 0, 500, 0, 2000),
(25, 71, 65, 125, '2021-04-06 15:36:11', 1.25, 95, 12.5, 503.75),
(26, 71, 40, 2, '2021-04-06 15:36:11', 13.2, 1.32, 15.6, 264.48),
(27, 71, 66, 225, '2021-04-06 15:36:11', 2.25, 11.25, 7.65, 134.1),
(28, 71, 67, 40, '2021-04-06 15:36:11', 0, 40, 0, 160),
(29, 71, 68, 25, '2021-04-06 15:36:12', 20.25, 0.025, 0.225, 184.375),
(30, 67, 65, 15, '2021-04-06 18:02:23', 0.15, 11.4, 1.5, 60.45),
(31, 67, 38, 20, '2021-04-06 18:02:29', 0.04, 0.78, 0.18, 5.1),
(32, 67, 40, 12, '2021-04-06 18:07:12', 79.2, 7.92, 93.6, 1586.88),
(33, 67, 38, 45, '2021-04-06 18:07:56', 0.09, 1.755, 0.405, 11.475),
(34, 67, 66, 55, '2021-04-06 18:13:24', 0.55, 2.75, 1.87, 32.78),
(35, 67, 70, 75, '2021-04-06 18:25:20', 0.225, 2.475, 2.325, 32.85),
(36, 67, 71, 250, '2021-04-06 18:25:20', 35, 0, 67.5, 922.5),
(37, 67, 72, 50, '2021-04-06 18:25:20', 8.5, 1.55, 14, 208.7),
(38, 67, 73, 2.5, '2021-04-06 18:25:20', 0.0075, 0.5275, 0.145, 3.4825),
(39, 67, 75, 0.25, '2021-04-06 18:25:20', 0.00815, 0.162025, 0.027375, 0.967825),
(40, 67, 74, 1.25, '2021-04-06 18:25:20', 0, 0, 0, 0),
(41, 67, 39, 3.75, '2021-04-06 18:25:20', 3.75, 0, 0, 33.75),
(42, 67, 71, 20, '2021-04-06 18:26:57', 2.8, 0, 5.4, 73.8),
(43, 71, 89, 37.5, '2021-04-06 18:39:48', 5.625, 3.375, 0.75, 70.875),
(44, 71, 88, 25, '2021-04-06 18:39:48', 1.075, 10.525, 2.1, 70.675),
(45, 71, 38, 25, '2021-04-06 18:39:48', 0.05, 0.975, 0.225, 6.375),
(46, 71, 74, 0.25, '2021-04-06 18:39:48', 0, 0, 0, 0),
(47, 71, 75, 0.25, '2021-04-06 18:39:48', 0.00815, 0.162025, 0.027375, 0.967825),
(48, 71, 70, 37.5, '2021-04-07 14:19:13', 0.1125, 1.2375, 1.1625, 16.425),
(49, 71, 71, 125, '2021-04-07 14:19:13', 17.5, 0, 33.75, 461.25),
(50, 71, 72, 25, '2021-04-07 14:19:13', 4.25, 0.775, 7, 104.35),
(51, 71, 73, 1.25, '2021-04-07 14:19:13', 0.00375, 0.26375, 0.0725, 1.74125),
(52, 71, 75, 0.125, '2021-04-07 14:19:14', 0.004075, 0.0810125, 0.0136875, 0.4839125),
(53, 71, 74, 0.625, '2021-04-07 14:19:14', 0, 0, 0, 0),
(54, 71, 39, 1.875, '2021-04-07 08:19:14', 1.875, 0, 0, 16.875),
(55, 71, 67, 100, '2021-04-07 10:20:04', 0, 100, 0, 400),
(56, 71, 65, 50, '2021-04-07 12:20:31', 0.5, 38, 5, 201.5),
(57, 71, 71, 20, '2021-04-07 15:36:00', 2.8, 0, 5.4, 73.8),
(58, 71, 70, 37.5, '2021-04-07 15:39:20', 0.1125, 1.2375, 1.1625, 16.425),
(59, 71, 71, 125, '2021-04-07 15:39:21', 17.5, 0, 33.75, 461.25),
(60, 71, 72, 25, '2021-04-07 15:39:21', 4.25, 0.775, 7, 104.35),
(61, 71, 73, 1.25, '2021-04-07 15:39:21', 0.00375, 0.26375, 0.0725, 1.74125),
(62, 71, 75, 0.125, '2021-04-07 15:39:21', 0.004075, 0.0810125, 0.0136875, 0.4839125),
(63, 71, 74, 0.625, '2021-04-07 15:39:21', 0, 0, 0, 0),
(64, 71, 39, 1.875, '2021-04-07 15:39:21', 1.875, 0, 0, 16.875),
(65, 71, 65, 62.5, '2021-04-09 17:04:48', 0.625, 47.5, 6.25, 251.875),
(66, 71, 40, 1, '2021-04-09 17:04:48', 6.6, 0.66, 7.8, 132.24),
(67, 71, 66, 112.5, '2021-04-09 17:04:48', 1.125, 5.625, 3.825, 67.05),
(68, 71, 67, 20, '2021-04-09 17:04:49', 0, 20, 0, 80),
(69, 71, 68, 12.5, '2021-04-09 17:04:49', 10.125, 0.0125, 0.1125, 92.1875),
(70, 71, 85, 100, '2021-04-09 17:05:58', 0, 0.9, 0, 3.6),
(71, 71, 65, 62.5, '2021-05-14 16:06:12', 0.625, 47.5, 6.25, 251.875),
(72, 71, 40, 1, '2021-05-14 16:06:13', 6.6, 0.66, 7.8, 132.24),
(73, 71, 66, 112.5, '2021-05-14 16:06:13', 1.125, 5.625, 3.825, 67.05),
(74, 71, 67, 20, '2021-05-14 16:06:14', 0, 20, 0, 80),
(75, 71, 68, 12.5, '2021-05-14 16:06:14', 10.125, 0.0125, 0.1125, 92.1875),
(76, 71, 70, 37.5, '2021-05-15 06:14:59', 0.1125, 1.2375, 1.1625, 16.425),
(77, 71, 71, 125, '2021-05-15 06:14:59', 17.5, 0, 33.75, 461.25),
(78, 71, 72, 25, '2021-05-15 06:14:59', 4.25, 0.775, 7, 104.35),
(79, 71, 73, 1.25, '2021-05-15 06:15:00', 0.00375, 0.26375, 0.0725, 1.74125),
(80, 71, 75, 0.125, '2021-05-15 06:15:00', 0.004075, 0.0810125, 0.0136875, 0.4839125),
(81, 71, 74, 0.625, '2021-05-15 06:15:00', 0, 0, 0, 0),
(82, 71, 39, 1.875, '2021-05-15 06:15:01', 1.875, 0, 0, 16.875);

-- --------------------------------------------------------

--
-- Structure de la table `rendez_vous`
--

DROP TABLE IF EXISTS `rendez_vous`;
CREATE TABLE IF NOT EXISTS `rendez_vous` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `rendez_vous`
--

INSERT INTO `rendez_vous` (`id`, `date`, `description`) VALUES
(16, '2021-03-22', 'zjdfjqsdfkjsqhdjqhslkqsjdlkqjddlks'),
(17, '2021-04-03', 'icicicicicicic'),
(18, '2021-05-08', 'drtgsgsfgsf'),
(19, '2021-04-10', '44'),
(20, '2021-04-12', 'qsdfqsdqsd'),
(21, '2021-04-13', 'description du rendez-vous'),
(22, '2021-04-13', 'description du rendez-vous'),
(23, '2021-04-13', 'description du rendez-vous 23'),
(24, '2021-04-12', 'bonjour le monde !'),
(27, '2021-04-30', 'hello rendez vous'),
(28, '2021-04-16', 'rendezVous'),
(29, '2021-04-21', 'first rendezvous'),
(30, '2021-04-19', 'qkjedfkjqsdncljsdc'),
(31, '2021-04-30', 'fgfhfgfhfgfhfgfhfgh123456789'),
(32, '2021-04-22', 'rendez-vous'),
(35, '2021-05-05', 'description rendezVous'),
(36, '2021-05-06', 'description rendezVous'),
(37, '2021-05-06', 'description rendezVous'),
(38, '2021-05-06', 'description rendezVous'),
(39, '2021-05-06', 'description rendezVous'),
(40, '2021-05-06', 'description rendezVous'),
(41, '2021-05-06', 'description rendezVous'),
(42, '2021-05-06', 'description rendezVous'),
(43, '2021-05-06', 'description rendezVous');

-- --------------------------------------------------------

--
-- Structure de la table `secretaire`
--

DROP TABLE IF EXISTS `secretaire`;
CREATE TABLE IF NOT EXISTS `secretaire` (
  `id` int NOT NULL,
  `nutritionniste_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_7DB5C2D0279DA68A` (`nutritionniste_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `secretaire`
--

INSERT INTO `secretaire` (`id`, `nutritionniste_id`) VALUES
(119, NULL),
(62, 98),
(72, 118);

-- --------------------------------------------------------

--
-- Structure de la table `success_story`
--

DROP TABLE IF EXISTS `success_story`;
CREATE TABLE IF NOT EXISTS `success_story` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `text` varchar(10000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_creation` datetime DEFAULT NULL,
  `like_story` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `tag`
--

DROP TABLE IF EXISTS `tag`;
CREATE TABLE IF NOT EXISTS `tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `contenu_multimedia_id` int NOT NULL,
  `dtype` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_389B783C272E341` (`contenu_multimedia_id`)
) ENGINE=InnoDB AUTO_INCREMENT=308 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `tag`
--

INSERT INTO `tag` (`id`, `contenu_multimedia_id`, `dtype`) VALUES
(33, 33, 'tagnourriture'),
(34, 34, 'tagnourriture'),
(35, 35, 'tagnourriture'),
(36, 36, 'tagnourriture'),
(37, 37, 'tagnourriture'),
(41, 41, 'tagnourriture'),
(42, 42, 'tagnourriture'),
(43, 43, 'tagnourriture'),
(45, 45, 'tagnourriture'),
(46, 46, 'tagnourriture'),
(47, 47, 'tagnourriture'),
(48, 48, 'tagnourriture'),
(49, 49, 'tagnourriture'),
(50, 50, 'tagnourriture'),
(51, 51, 'tagnourriture'),
(104, 109, 'tagficheconsultation'),
(105, 110, 'tagficheconsultation'),
(112, 121, 'tagficheconsultation'),
(113, 122, 'tagficheconsultation'),
(115, 124, 'tagficheconsultation'),
(116, 125, 'tagficheconsultation'),
(117, 126, 'tagficheconsultation'),
(118, 127, 'tagficheconsultation'),
(131, 140, 'tagficheconsultation'),
(132, 141, 'tagficheconsultation'),
(133, 142, 'tagutilisateur'),
(134, 143, 'tagutilisateur'),
(138, 147, 'tagficheconsultation'),
(157, 166, 'tagutilisateur'),
(158, 167, 'tagutilisateur'),
(160, 169, 'tagutilisateur'),
(162, 171, 'tagblogpost'),
(163, 172, 'tagblogpost'),
(164, 173, 'tagblogpost'),
(165, 174, 'tagutilisateur'),
(166, 175, 'tagutilisateur'),
(167, 176, 'tagutilisateur'),
(168, 177, 'tagutilisateur'),
(170, 180, 'tagficheconsultation'),
(171, 181, 'tagficheconsultation'),
(172, 182, 'tagficheconsultation'),
(173, 183, 'tagficheconsultation'),
(174, 184, 'tagficheconsultation'),
(175, 185, 'tagutilisateur'),
(176, 186, 'tagsuccessstory'),
(177, 187, 'tagsuccessstory'),
(178, 188, 'tagsuccessstory'),
(179, 189, 'tagficheconsultation'),
(180, 190, 'tagblogpost'),
(181, 191, 'challengetag'),
(188, 198, 'tagblogpost'),
(189, 199, 'challengetag'),
(190, 200, 'challengetag'),
(191, 201, 'challengetag'),
(192, 202, 'challengetag'),
(193, 203, 'challengetag'),
(194, 204, 'challengetag'),
(195, 205, 'challengetag'),
(213, 216, 'challengetag'),
(214, 217, 'challengetag'),
(215, 217, 'tagutilisateur'),
(216, 218, 'challengetag'),
(217, 218, 'tagutilisateur'),
(218, 219, 'tagficheconsultation'),
(222, 228, 'contenumultimedia'),
(223, 229, 'contenumultimedia'),
(224, 230, 'contenumultimedia'),
(225, 231, 'contenumultimedia'),
(226, 232, 'contenumultimedia'),
(227, 233, 'contenumultimedia'),
(228, 234, 'contenumultimedia'),
(229, 235, 'contenumultimedia'),
(230, 236, 'contenumultimedia'),
(231, 237, 'contenumultimedia'),
(232, 238, 'contenumultimedia'),
(233, 239, 'contenumultimedia'),
(234, 240, 'contenumultimedia'),
(235, 241, 'contenumultimedia'),
(236, 242, 'contenumultimedia'),
(237, 243, 'contenumultimedia'),
(238, 244, 'contenumultimedia'),
(239, 245, 'contenumultimedia'),
(240, 246, 'contenumultimedia'),
(241, 247, 'contenumultimedia'),
(243, 249, 'tagsuccessstory'),
(244, 250, 'tagsuccessstory'),
(245, 251, 'tagsuccessstory'),
(276, 269, 'tagsuccessstory'),
(277, 270, 'tagsuccessstory'),
(278, 271, 'tagsuccessstory'),
(279, 276, 'tagnourriture'),
(283, 280, 'tagnourriture'),
(284, 281, 'tagnourriture'),
(285, 282, 'tagnourriture'),
(286, 283, 'tagnourriture'),
(287, 284, 'tagnourriture'),
(288, 285, 'tagnourriture'),
(289, 286, 'tagnourriture'),
(290, 287, 'tagnourriture'),
(291, 288, 'tagnourriture'),
(292, 289, 'tagnourriture'),
(293, 290, 'tagnourriture'),
(294, 291, 'tagnourriture'),
(295, 292, 'tagnourriture'),
(296, 293, 'tagnourriture'),
(297, 294, 'tagnourriture'),
(298, 295, 'tagnourriture'),
(299, 296, 'tagnourriture'),
(300, 297, 'tagnourriture'),
(301, 298, 'tagnourriture'),
(302, 299, 'tagnourriture'),
(303, 300, 'tagnourriture'),
(304, 301, 'tagnourriture'),
(305, 302, 'tagnourriture'),
(306, 303, 'challengetag'),
(307, 304, 'challengetag');

-- --------------------------------------------------------

--
-- Structure de la table `tags_challenge`
--

DROP TABLE IF EXISTS `tags_challenge`;
CREATE TABLE IF NOT EXISTS `tags_challenge` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `tags_user`
--

DROP TABLE IF EXISTS `tags_user`;
CREATE TABLE IF NOT EXISTS `tags_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `tag_blog_post`
--

DROP TABLE IF EXISTS `tag_blog_post`;
CREATE TABLE IF NOT EXISTS `tag_blog_post` (
  `id` int NOT NULL,
  `blog_post_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_AB6DDA3AA77FBEAF` (`blog_post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `tag_evaluation`
--

DROP TABLE IF EXISTS `tag_evaluation`;
CREATE TABLE IF NOT EXISTS `tag_evaluation` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `tag_fiche_consultation`
--

DROP TABLE IF EXISTS `tag_fiche_consultation`;
CREATE TABLE IF NOT EXISTS `tag_fiche_consultation` (
  `id` int NOT NULL,
  `fiche_consultation_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_9D0572AE4F3CB4A0` (`fiche_consultation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `tag_fiche_consultation`
--

INSERT INTO `tag_fiche_consultation` (`id`, `fiche_consultation_id`) VALUES
(115, 34),
(116, 35),
(117, 36),
(118, 37),
(131, 38),
(132, 39),
(138, 40),
(170, 42),
(171, 43),
(172, 44),
(173, 45),
(174, 46),
(179, 47),
(218, 49);

-- --------------------------------------------------------

--
-- Structure de la table `tag_mesure`
--

DROP TABLE IF EXISTS `tag_mesure`;
CREATE TABLE IF NOT EXISTS `tag_mesure` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `tag_nourriture`
--

DROP TABLE IF EXISTS `tag_nourriture`;
CREATE TABLE IF NOT EXISTS `tag_nourriture` (
  `id` int NOT NULL,
  `nourriture_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_D15C644298BD5834` (`nourriture_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `tag_nourriture`
--

INSERT INTO `tag_nourriture` (`id`, `nourriture_id`) VALUES
(283, 38),
(284, 39),
(285, 40),
(286, 65),
(287, 66),
(288, 67),
(289, 68),
(33, 69),
(34, 69),
(35, 69),
(41, 69),
(290, 70),
(291, 71),
(292, 72),
(293, 73),
(294, 74),
(295, 75),
(36, 76),
(37, 76),
(296, 80),
(297, 81),
(298, 82),
(299, 83),
(42, 84),
(43, 84),
(300, 85),
(45, 87),
(301, 89),
(46, 90),
(302, 91),
(47, 92),
(303, 93),
(48, 94),
(304, 95),
(49, 96),
(50, 97),
(51, 97),
(305, 173),
(279, 222);

-- --------------------------------------------------------

--
-- Structure de la table `tag_success_story`
--

DROP TABLE IF EXISTS `tag_success_story`;
CREATE TABLE IF NOT EXISTS `tag_success_story` (
  `id` int NOT NULL,
  `success_story_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_90EC4948C4903472` (`success_story_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `tag_utilisateur`
--

DROP TABLE IF EXISTS `tag_utilisateur`;
CREATE TABLE IF NOT EXISTS `tag_utilisateur` (
  `id` int NOT NULL,
  `utilisateur_id` int DEFAULT NULL,
  `is_photo_de_profile` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_1D51953FB88E14F` (`utilisateur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `tag_utilisateur`
--

INSERT INTO `tag_utilisateur` (`id`, `utilisateur_id`, `is_photo_de_profile`) VALUES
(133, 56, 0),
(134, 56, 0),
(157, 74, 1),
(158, 76, 1),
(160, 76, 0),
(165, 56, 1),
(166, 71, 1),
(167, 71, 0),
(168, 67, 1),
(175, 72, 1),
(215, 71, 0),
(217, 71, 0);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sexe` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_naiss` date NOT NULL,
  `email` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tel` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dtype` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ville` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `adresse` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_verified` tinyint(1) NOT NULL,
  `roles` json NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `sexe`, `date_naiss`, `email`, `tel`, `dtype`, `ville`, `adresse`, `password`, `is_verified`, `roles`) VALUES
(55, 'ama', 'assa', 'Femme', '1922-03-02', 'aaa@aaa.aaa', '25955486', 'utilisateur', 'nabeul', 'rue monji nahj salah', '$argon2i$v=19$m=65536,t=4,p=1$aG4xZjQ2VXhRUmxKWUU1Sw$aBJHNe6Ljt+6IjqtLKJKlR/9fRW0QD5PA11zqm/fGpQ', 0, '[\"ROLE_ADMIN\"]'),
(56, 'GANNAR', 'ALADIN', 'Homme', '1920-02-01', 'gannarala@gmail.com', '25955486', 'utilisateur', 'nabeul', 'rue monji nahj salah', '$argon2i$v=19$m=65536,t=4,p=1$aG4xZjQ2VXhRUmxKWUU1Sw$aBJHNe6Ljt+6IjqtLKJKlR/9fRW0QD5PA11zqm/fGpQ', 0, '[\"ROLE_SUPER_ADMIN\"]'),
(58, 'doctor', 'doc', 'homme', '2018-03-03', 'docteurdoc@gmail.com', '26548201', 'nutritionniste', 'ariana', 'rue salah bn med ali', '00000000', 0, '[]'),
(59, 'doctor', 'doc', 'Homme', '1921-03-02', 'docteurdocc@gmail.com', '26548201', 'nutritionniste', 'ariana', 'rue salah bn med ali', '$argon2i$v=19$m=65536,t=4,p=1$eGM4QTcyZ1dtcy5QUDZGOQ$j9CuTqIN3DDfdLC13028isuCiPlAZBnJqO+ZlOOmkPQ', 0, '[\"ROLE_ADMIN\"]'),
(61, 'gannar', 'ss', 'Femme', '1921-03-02', 'patient@patient.com', '25944019', 'patient', 'nabeul', 'rue xx num xx', '$argon2i$v=19$m=65536,t=4,p=1$V085Y0cyamlTTnFJVy5NcA$ZrOkdXE2iyPVLAzOLRpArusbZApm5bkglBJC9lbNUNs', 0, '[]'),
(62, 'sec', 'secccc', 'Femme', '1921-03-02', 'sec@sec.com', '23659541', 'secretaire', 'tunis', 'Rue b7ar 7oumt chaat', '$argon2i$v=19$m=65536,t=4,p=1$V29NenZWWW9EWFovNzl4dg$HxLhI9cn6TuI99qT7yC6bIyifz/kaRp+46qM0X8pbBE', 0, '[]'),
(63, 'gannar', 'ala eddine', 'Homme', '1922-03-01', 'patient1@patient.com', '54865210', 'patient', 'nabeul', 'rue xx num xx', '$argon2i$v=19$m=65536,t=4,p=1$VkJYY1F4UmdUTWcyR3RiQw$SiKMb+DhuvmGzaAC8IEryhsYuQqcwS12T8QSzcvNM50', 0, '[]'),
(64, 'doctor1', 'doc1', 'Homme', '1922-03-03', 'docteurdoc1@gmail.com', '26548201', 'nutritionniste', 'ariana', 'rue salah bn med ali', '$argon2i$v=19$m=65536,t=4,p=1$QVZyTnBkMzdkbVdYOEwuVw$vHDgcc77vwVI2j5T6yAjx01JyhzW9BvaRLVo6/KWc4c', 0, '[\"ROLE_DOCTOR\"]'),
(66, 'hammami', 'islem', 'Femme', '1999-02-02', 'islem.hammami@esprit.tn', '28429923', 'utilisateur', 'hammamet', 'xxxx', '$argon2i$v=19$m=65536,t=4,p=1$VHRGUFk5MzlkRFluM00wTQ$KGUtKRpCDcGBfAHtVhcdsx9fmhDKwdOJQAYvWtavODY', 0, '[]'),
(67, 'hammami', 'salah', 'Homme', '1998-02-02', 'salah.hammami@esprit.tn', '25485623', 'patient', 'hammaamet', 'rue nasr', '$argon2i$v=19$m=65536,t=4,p=1$a2pYQWE2T3k2dWZvNnZBUw$dcOflyOvF4F1YprGWZoQhphKP+JgQBNkW1T+zmvHrao', 0, '[]'),
(68, 'nour', 'emna', 'Femme', '1996-06-11', 'emna.nour@gmail.com', '25963457', 'nutritionniste', 'hammamet', 'rue tahrir', '$argon2i$v=19$m=65536,t=4,p=1$THB2Qjl3TlltNE1obnpKQw$D+B1JJQvKhf50OroZhRZQsjYal6xM3xHP/eILzQztDc', 0, '[\"ROLE_DOCTOR\"]'),
(71, 'sss', 'sss', 'Homme', '1921-03-02', 'salahsl@gmail.com', '25944019', 'patient', 'nabeul', 'rue xx num xx', '$argon2i$v=19$m=65536,t=4,p=1$b0sva2ZwdnB4NWRhTUtSdQ$IZxf0y6/2mhn1RizGReuPGisVUafOrczdJ3yVo7KZsQ', 0, '[\"ROLE_PATIENT\"]'),
(72, 'sec1', 'secccc1', 'Homme', '1921-03-01', 'sec1@sec.com', '23659541', 'secretaire', 'tunis', 'Rue b7ar 7oumt chaat', '$argon2i$v=19$m=65536,t=4,p=1$ZzBYc240NkVGRkUyN1plVQ$rFqPT4njFIpJfKBpdRpkFH4MYn4UZXqfnPmOUeebboE', 0, '[\"ROLE_SECRETAIRE\", \"ROLE_PATIENT\"]'),
(73, 'sssoo', 'ssoo', 'Homme', '1921-02-01', 'docteurdo11c1@gmail.com', '26548201', 'nutritionniste', 'ariana', 'rue salah bn med ali', '$argon2i$v=19$m=65536,t=4,p=1$aldBVnovMjd6amdDQ003Rw$XsoJtPNkSxU8vrc3irHIMJtoD60kL/3n0xAzN/XeoOE', 0, '[\"ROLE_DOCTOR\", \"ROLE_SECRETAIRE\", \"ROLE_PATIENT\"]'),
(74, 'zssssd', 'doc1s', 'Homme', '1920-01-01', 'docteurdocss1@gmail.com', '26548201', 'nutritionniste', 'ariana', 'rue salah bn med ali', '$argon2i$v=19$m=65536,t=4,p=1$L1JSTzIvdGVvNkJ5eHFNLw$NIpjrMx8FW4N5c5+898e+/qAHDPnkDt6BNFIB4IZV40', 0, '[\"ROLE_DOCTOR\", \"ROLE_SECRETAIRE\", \"ROLE_PATIENT\"]'),
(75, 'doctor1xd', 'docxxdsds', 'Homme', '1920-01-02', 'docteurdocsdds@gmail.com', '26548201', 'nutritionniste', 'ariana', 'rue salah bn med ali', '$argon2i$v=19$m=65536,t=4,p=1$ZlVLaDFRUm9lS3BaYlN4RQ$DHMirHnhOiidzNOAtsNbukWh7mBUE5wLcE/SshXAOaQ', 0, '[\"ROLE_DOCTOR\", \"ROLE_SECRETAIRE\", \"ROLE_PATIENT\"]'),
(76, 'doctor', 'doc', 'Homme', '1922-03-01', 'docteurdorfrec@gmail.com', '26548201', 'nutritionniste', 'ariana', 'rue salah bn med ali', '$argon2i$v=19$m=65536,t=4,p=1$dkMyY0hoUFJINzJUbXVxUQ$BK7GsXMGHhH5by5LU7OIYm+gSpP8ag0s38LhNiv6M18', 0, '[\"ROLE_DOCTOR\", \"ROLE_SECRETAIRE\", \"ROLE_PATIENT\"]'),
(77, 'hedi', 'jdid', 'Homme', '1921-02-01', 'hedih@gmail.com', '38885554', 'nutritionniste', 'manouba', 'test', '$argon2i$v=19$m=65536,t=4,p=1$NUZrWEJnUENkOWV6Umd0Lg$Hlg7D5f1irfF5YAKvlg1p/PeVQ76RjyTurSHHTAZHVs', 0, '[\"ROLE_DOCTOR\", \"ROLE_SECRETAIRE\", \"ROLE_PATIENT\"]'),
(78, 'doctor121', 'doc121', 'Homme', '1922-02-02', 'docteurdoc121@gmail.com', '26548201', 'nutritionniste', 'ariana', 'rue salah bn med ali', '$argon2i$v=19$m=65536,t=4,p=1$MnBBczN1bzdsaUc2NGFXOQ$BU/TDunWaH9noSCftNlzxwHct3kohT7J9pMDJtVUo+Y', 0, '[\"ROLE_DOCTOR\", \"ROLE_SECRETAIRE\", \"ROLE_PATIENT\"]'),
(80, 'gannar', 'ss', 'Homme', '1922-02-02', 'gannarrala111@gmail.com', '54865210', 'patient', 'ss', 'ss', '$argon2i$v=19$m=65536,t=4,p=1$WFRsa1ZtWkJxc1pQQVJyZA$v+x+Ze7iXxZgnNLM5KmPYpHLaZLz9ZeqHnlkR0+GL/c', 0, '[\"ROLE_PATIENT\"]'),
(98, 'ee', 'ee', 'Homme', '1922-02-02', 'docteurdoc1ee@gmail.com', '26548201', 'nutritionniste', 'ariana', 'rue salah bn med ali', '$argon2i$v=19$m=65536,t=4,p=1$cWZBcldDUDVnL0dNeVNZZQ$WJA786J7Xg2ejq0qukjcJxZFLo7l4xGlqnhSTaA81YY', 0, '[\"ROLE_DOCTOR\", \"ROLE_SECRETAIRE\", \"ROLE_PATIENT\"]'),
(116, 't', 't', 't', '2021-04-07', 't', '2651', 'secretaire', 't', 't', '00000000', 0, '[\"ROLE_USER\"]'),
(117, 't', 't', 't', '2021-04-07', 't', '2651', 'secretaire', 't', 't', '00000000', 0, '[\"ROLE_USER\"]'),
(118, 'k', 'k', 'Homme', '1922-01-02', 'docteurdockl@gmail.com', '26548201', 'nutritionniste', 'ariana', 'rue salah bn med ali', '$argon2i$v=19$m=65536,t=4,p=1$cGxmNDFqQUdwLnBwSm1iQw$teI6zcET8PvRlbhIo9A+j/Jw492XKGAdwIpjeLHYcss', 0, '[\"ROLE_DOCTOR\", \"ROLE_SECRETAIRE\", \"ROLE_PATIENT\"]'),
(119, 'sec2', 'secc2', 'Femme', '1922-01-01', 'sec12@sec.com', '23659541', 'secretaire', 'tunis', 'Rue b7ar 7oumt chaat', '$argon2i$v=19$m=65536,t=4,p=1$M2MudlB0OTdtRk5yNWhxYw$LG0B+yda0XG1o2I5GEHFL8K1a7l+mNJPCg6EKttwVkM', 0, '[\"ROLE_SECRETAIRE\", \"ROLE_PATIENT\"]'),
(123, 'gg', 'gg', 'Femme', '2021-04-01', 'gg@gg', '2361', 'nutritionniste', 'gg', 'gg', '00000000', 0, '[\"ROLE_USER\"]'),
(124, 'gfdg', 'gfd', 'Femme', '2021-03-31', 'gfsd', '161', 'nutritionniste', 'fdg', 'fdg', '00000000', 0, '[\"ROLE_USER\"]'),
(125, 'doctor', 'doc', 'Homme', '1922-01-02', 'docteurdoc00@gmail.com', '26548201', 'nutritionniste', 'ariana', 'rue salah bn med ali', '$argon2i$v=19$m=65536,t=4,p=1$VnFWMlYyemIxb0d0MU9BaA$O4z+h4xeMzDXDdie5Qf75eLtg+/KbAUqISNj3cNhYcM', 0, '[\"ROLE_DOCTOR\", \"ROLE_SECRETAIRE\", \"ROLE_PATIENT\"]'),
(130, 'ggg', 'ttt', 'Femme', '2021-04-07', 'ggg', '132', 'patient', 'gtg', 'tgt', '00000000', 0, '[\"ROLE_PATIENT\"]'),
(131, 'samir', 'sami', 'Femme', '2021-04-15', 'hhh', '236542', 'patient', 'ariana', 'rue salah', '00000000', 0, '[]'),
(132, 'sami', 'med', 'Femme', '2021-04-07', 'samimed@gmail.com', '321', 'patient', 'cdsvd', 'vfdv', '00000000', 0, '[\"[ROLE_PATIENT]\"]'),
(133, 'gannar', 'ss', 'Homme', '1920-01-01', 'gannarrala153@gmail.com', '25944019', 'patient', 'nabeul', 'rue xx num xx', '$2y$13$cI2HUBkidCrYSBGFPBoXpOhz9g3AnGweVSiH30y3ebkNCm9asf2tG', 0, '[\"ROLE_PATIENT\"]');

-- --------------------------------------------------------

--
-- Structure de la table `video`
--

DROP TABLE IF EXISTS `video`;
CREATE TABLE IF NOT EXISTS `video` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `aliment`
--
ALTER TABLE `aliment`
  ADD CONSTRAINT `FK_70FF972BBF396750` FOREIGN KEY (`id`) REFERENCES `nourriture` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `aliment_categorie_aliment`
--
ALTER TABLE `aliment_categorie_aliment`
  ADD CONSTRAINT `FK_C52A8992415B9F11` FOREIGN KEY (`aliment_id`) REFERENCES `aliment` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_C52A8992DF9255BD` FOREIGN KEY (`categorie_aliment_id`) REFERENCES `categorie_aliment` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `challenge_tag`
--
ALTER TABLE `challenge_tag`
  ADD CONSTRAINT `FK_1AC21A8F98A21AC6` FOREIGN KEY (`challenge_id`) REFERENCES `challenge` (`id`),
  ADD CONSTRAINT `FK_1AC21A8FA76ED395` FOREIGN KEY (`user_id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FK_1AC21A8FBF396750` FOREIGN KEY (`id`) REFERENCES `tag` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `fk_5f9e962a727aca70` FOREIGN KEY (`parent_id`) REFERENCES `comments` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_5f9e962aa63b36f1` FOREIGN KEY (`success_id`) REFERENCES `success_story` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `composition`
--
ALTER TABLE `composition`
  ADD CONSTRAINT `FK_C7F4347415B9F11` FOREIGN KEY (`aliment_id`) REFERENCES `aliment` (`id`),
  ADD CONSTRAINT `FK_C7F4347D73DB560` FOREIGN KEY (`plat_id`) REFERENCES `plat` (`id`);

--
-- Contraintes pour la table `etape_de_preparation`
--
ALTER TABLE `etape_de_preparation`
  ADD CONSTRAINT `FK_35EC67BDD73DB560` FOREIGN KEY (`plat_id`) REFERENCES `plat` (`id`);

--
-- Contraintes pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `FK_1323A575279DA68A` FOREIGN KEY (`nutritionniste_id`) REFERENCES `nutritionniste` (`id`),
  ADD CONSTRAINT `FK_1323A5756B899279` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);

--
-- Contraintes pour la table `fiche_consultation`
--
ALTER TABLE `fiche_consultation`
  ADD CONSTRAINT `FK_CAD69893279DA68A` FOREIGN KEY (`nutritionniste_id`) REFERENCES `nutritionniste` (`id`),
  ADD CONSTRAINT `FK_CAD698936B899279` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);

--
-- Contraintes pour la table `medicament`
--
ALTER TABLE `medicament`
  ADD CONSTRAINT `FK_9A9C723ADF522508` FOREIGN KEY (`fiche_id`) REFERENCES `fiche_consultation` (`id`);

--
-- Contraintes pour la table `nourriture`
--
ALTER TABLE `nourriture`
  ADD CONSTRAINT `FK_7447E613279DA68A` FOREIGN KEY (`nutritionniste_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `nutritionniste`
--
ALTER TABLE `nutritionniste`
  ADD CONSTRAINT `FK_F02DE71CBF396750` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `FK_1ADAD7EBBF396750` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `patient_challenge`
--
ALTER TABLE `patient_challenge`
  ADD CONSTRAINT `FK_71825B6E6B899279` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_71825B6E98A21AC6` FOREIGN KEY (`challenge_id`) REFERENCES `challenge` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `plat`
--
ALTER TABLE `plat`
  ADD CONSTRAINT `FK_2038A207BF396750` FOREIGN KEY (`id`) REFERENCES `nourriture` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `proportion`
--
ALTER TABLE `proportion`
  ADD CONSTRAINT `FK_BFB196F2415B9F11` FOREIGN KEY (`aliment_id`) REFERENCES `aliment` (`id`),
  ADD CONSTRAINT `FK_BFB196F26B899279` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);

--
-- Contraintes pour la table `secretaire`
--
ALTER TABLE `secretaire`
  ADD CONSTRAINT `FK_7DB5C2D0279DA68A` FOREIGN KEY (`nutritionniste_id`) REFERENCES `nutritionniste` (`id`),
  ADD CONSTRAINT `FK_7DB5C2D0BF396750` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `tag`
--
ALTER TABLE `tag`
  ADD CONSTRAINT `FK_389B783C272E341` FOREIGN KEY (`contenu_multimedia_id`) REFERENCES `contenu_multimedia` (`id`);

--
-- Contraintes pour la table `tag_blog_post`
--
ALTER TABLE `tag_blog_post`
  ADD CONSTRAINT `FK_AB6DDA3AA77FBEAF` FOREIGN KEY (`blog_post_id`) REFERENCES `blog_post` (`id`),
  ADD CONSTRAINT `FK_AB6DDA3ABF396750` FOREIGN KEY (`id`) REFERENCES `tag` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `tag_evaluation`
--
ALTER TABLE `tag_evaluation`
  ADD CONSTRAINT `FK_B6382724BF396750` FOREIGN KEY (`id`) REFERENCES `tag` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `tag_fiche_consultation`
--
ALTER TABLE `tag_fiche_consultation`
  ADD CONSTRAINT `FK_9D0572AE4F3CB4A0` FOREIGN KEY (`fiche_consultation_id`) REFERENCES `fiche_consultation` (`id`),
  ADD CONSTRAINT `FK_9D0572AEBF396750` FOREIGN KEY (`id`) REFERENCES `tag` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `tag_mesure`
--
ALTER TABLE `tag_mesure`
  ADD CONSTRAINT `FK_B65A275CBF396750` FOREIGN KEY (`id`) REFERENCES `tag` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `tag_nourriture`
--
ALTER TABLE `tag_nourriture`
  ADD CONSTRAINT `FK_D15C644298BD5834` FOREIGN KEY (`nourriture_id`) REFERENCES `nourriture` (`id`),
  ADD CONSTRAINT `FK_D15C6442BF396750` FOREIGN KEY (`id`) REFERENCES `tag` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `tag_success_story`
--
ALTER TABLE `tag_success_story`
  ADD CONSTRAINT `FK_90EC4948BF396750` FOREIGN KEY (`id`) REFERENCES `tag` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_90EC4948C4903472` FOREIGN KEY (`success_story_id`) REFERENCES `success_story` (`id`);

--
-- Contraintes pour la table `tag_utilisateur`
--
ALTER TABLE `tag_utilisateur`
  ADD CONSTRAINT `FK_1D51953BF396750` FOREIGN KEY (`id`) REFERENCES `tag` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_1D51953FB88E14F` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `video`
--
ALTER TABLE `video`
  ADD CONSTRAINT `FK_7CC7DA2CBF396750` FOREIGN KEY (`id`) REFERENCES `contenu_multimedia` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
