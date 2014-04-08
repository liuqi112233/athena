DROP DATABASE athena;
CREATE DATABASE athena;
USE athena;
CREATE TABLE member (
  `level` INT(11) NOT NULL,
  `discount` FLOAT(6) DEFAULT 1,
  PRIMARY KEY (`level`)
)
