DROP DATABASE athena;
CREATE DATABASE athena;
USE athena;
CREATE TABLE member (
  `level` INT(11) NOT NULL,
  `discount` DOUBLE(3,2) DEFAULT 1,
  PRIMARY KEY (`level`)
);

CREATE TABLE game (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(50),
  `cost` DOUBLE(5,2) DEFAULT 0,
  `equipment` VARCHAR(50),
  `only` SMALLINT(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE account (
  `id` INT(11) NOT NULL,
  `balance` DOUBLE(10,2) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;