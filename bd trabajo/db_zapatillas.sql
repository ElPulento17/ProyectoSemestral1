-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-05-2026 a las 16:50:14
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_zapatillas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca`
--

CREATE TABLE `marca` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `marca`
--

INSERT INTO `marca` (`id`, `nombre`) VALUES
(1, 'Nike');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `zapatilla`
--

CREATE TABLE `zapatilla` (
  `id` bigint(20) NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `precio` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `marca_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `zapatilla`
--

INSERT INTO `zapatilla` (`id`, `estado`, `modelo`, `precio`, `stock`, `marca_id`) VALUES
(1, 'NUEVA', 'Air Force 1', 120000, 10, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `marca`
--
ALTER TABLE `marca`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `zapatilla`
--
ALTER TABLE `zapatilla`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9w70g12jliqht2wk3aui0ayb5` (`marca_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `marca`
--
ALTER TABLE `marca`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `zapatilla`
--
ALTER TABLE `zapatilla`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `zapatilla`
--
ALTER TABLE `zapatilla`
  ADD CONSTRAINT `FK9w70g12jliqht2wk3aui0ayb5` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
