DROP DATABASE athena;
CREATE DATABASE athena;
USE athena;
CREATE TABLE member (
  `level` INT(11) NOT NULL,
  `discount` DOUBLE(3,2) DEFAULT 1,
  PRIMARY KEY (`level`)
)
