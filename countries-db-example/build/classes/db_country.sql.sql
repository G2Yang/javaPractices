-- Create user for local access.
CREATE USER 'usrcountry'@'localhost' IDENTIFIED BY 'pswcountry';
-- Create database.
CREATE DATABASE dbcountry
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
-- Grant permissions.
GRANT SELECT, INSERT, UPDATE, DELETE ON dbcountry.* TO 'usrcountry'@'localhost';
-- Use database.
USE dbcountry;
-- Create table 'countries'
CREATE TABLE `countries` (
`id` int(4) NOT NULL auto_increment,
`name` varchar(40) NOT NULL,
`capital` varchar(40) default NULL,
`surface` double default 0.0,
`inhabitants` int default 0,
`pib` double default 0.0,
`lifeexpectancy` int default 0,
PRIMARY KEY (`id`)
) ENGINE=InnoDB;
-- Data insertion.
INSERT INTO `countries` (`id`, `name`, `capital`, `surface`, `inhabitants`, `pib`, `lifeexpectancy`) 
VALUES
(1, 'Albania', 'Tirana', 28748, 3100000, 3189, 73),
(2, 'Alemania Federal', 'Berlín', 357021, 82000000, 23742, 77),
(3, 'Andorra', 'AndorraLaVieja', 453, 75000, 17420, 78),
(4, 'Austria', 'Viena', 83859, 8100000, 25089, 77),
(5, 'Belarús', 'Minsk', 207600, 10100000, 6876, 68),
(6, 'Bélgica', 'Bruselas', 30528, 10300000, 25443, 78),
(7, 'BosniayHerzegovina', 'Sarajevo', 51129, 4100000, 1086, 56),
(8, 'Bulgaria', 'Sofia', 110994, 7900000, 5071, 70),
(9, 'Croacia', 'Zagreb', 56610, 4700000, 7387, 73),
(10, 'Dinamarca', 'Copenhague', 44493, 5300000, 25869, 76),
(11, 'Eslovaquia', 'Bratislava', 49035, 5400000, 10591, 73),
(12, 'Eslovenia', 'Lubiana', 20258, 2000000, 15977, 75),
(13, 'España', 'Madrid', 505989, 39900000, 18079, 78),
(14, 'Estonia', 'Tallin', 45227, 1400000, 8355, 70),
(15, 'FederacióndeRusia', 'Moscú', 17075400, 144700000, 7473, 66),
(16, 'Finlandia', 'Helsinki', 338145, 5200000, 23, 77),
(17, 'Francia', 'París', 543965, 59500000, 22897, 78),
(18, 'Grecia', 'Atenas', 131957, 10600000, 15414, 78),
(19, 'Hungría', 'Budapest', 93030, 9900000, 11430, 71),
(20, 'Irlanda', 'Dublin', 70285, 3800000, 25918, 76),
(21, 'Islandia', 'Reykjavik', 102819, 281000, 27835, 79),
(22, 'Italia', 'Roma', 301308, 57500000, 22172, 78),
(23, 'Letonia', 'Riga', 64610, 2400000, 6264, 70),
(24, 'Liechtenstein', 'Vaduz', 160, 32015, 37000, 72),
(25, 'Lituania', 'Vilnius', 65300, 3700000, 6656, 71),
(26, 'Luxemburgo', 'Luxemburgo', 2586, 442000, 42769, 77),
(27, 'Macedonia', 'Skopje', 25713, 2000000, 4651, 73),
(28, 'Malta', 'La Valletta', 316, 392000, 15189, 77),
(29, 'Moldova, Rep. de', 'Kisinev', 33700, 4300000, 2037, 66),
(30, 'Mónaco', 'Mónaco', 2, 33000, 26470, 78),
(31, 'Noruega', 'Oslo', 323758, 4500000, 28433, 78),
(32, 'Países Bajos', 'Amsterdam', 41526, 15900000, 24215, 78),
(33, 'Polonia', 'Varsovia', 312685, 38600000, 8450, 73),
(34, 'Portugal', 'Lisboa', 91831, 10000000, 16064, 75),
(35, 'Reino Unido de Gran Bretaña', 'Londres', 244110, 59500000, 22093, 77),
(36, 'República Checa', 'Praga', 78866, 10300000, 13018, 74),
(37, 'Rumania', 'Bucarest', 238391, 22400000, 6041, 69),
(38, 'Suecia', 'Estocolmo', 449964, 8800000, 22636, 79),
(39, 'Suiza', 'Berna', 41285, 7200000, 27171, 78),
(40, 'Ucrania', 'Kiev', 603700, 49100000, 3458, 68);