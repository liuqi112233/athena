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


CREATE TABLE consume (
  `id` INT(11) NOT NULL,
  `info` VARCHAR(50) DEFAULT "",
  `time` DATETIME ,
  `cost` DOUBLE(10,2) DEFAULT 0,
  `balance` DOUBLE(10,2) DEFAULT 0
) ENGINE=INNODB DEFAULT CHARSET=utf8;


DELIMITER $$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    FUNCTION `athena`.`recharge`(userid INT,userbalance DOUBLE)
    RETURNS DOUBLE
    /*LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    | COMMENT 'string'*/
    BEGIN
	DECLARE ret_val DOUBLE DEFAULT 0;
	DECLARE have INT DEFAULT 0;
	INSERT INTO `athena`.`consume` (`id`, `info`, `time`, `balance`) VALUES (userid, '充值', NOW(), userbalance);
	
	SELECT  balance INTO ret_val FROM account WHERE id=userid;
	SET ret_val = ret_val+userbalance;
	SELECT COUNT(*) INTO have FROM account WHERE id = userid;
	IF (have>0) THEN
		UPDATE account SET `balance`=ret_val WHERE id=userid;
	ELSE
		INSERT INTO account (`id`,`balance`) VALUES(userid,ret_val);
	END IF;
	RETURN ret_val;
    END$$

DELIMITER ;