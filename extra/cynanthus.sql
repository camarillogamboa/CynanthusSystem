-- -----------------------------------------------------
-- Schema cynanthus
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cynanthus` ;

-- -----------------------------------------------------
-- Schema cynanthus
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cynanthus` DEFAULT CHARACTER SET utf8 ;
USE `cynanthus` ;

-- -----------------------------------------------------
-- Table `server_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `server_info` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(60) NOT NULL DEFAULT '127.0.0.1',
  `port` INT UNSIGNED NOT NULL DEFAULT 9000,
  `server_type` ENUM('STREAM_DATA', "STORAGE", 'CONTROL') NOT NULL,
  `info` VARCHAR(300) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_user` INT UNSIGNED NOT NULL,
  `role_type` ENUM('ROLE_AGENT', 'ROLE_ADMIN', 'ROLE_USER') NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_rol_user_idx` (`id_user` ASC) VISIBLE,
  UNIQUE INDEX `id_user_role_typeUNIQUE` (`id_user` ASC, `role_type` ASC) VISIBLE,
  CONSTRAINT `id_rol_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `instruction_set`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `instruction_set` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `info` VARCHAR(300) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `set_name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `node_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `node_info` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `mac` VARCHAR(17) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `id_server_info` INT UNSIGNED NOT NULL,
  `id_set` INT UNSIGNED NULL,
  INDEX `node_id_server_info_idx` (`id_server_info` ASC) INVISIBLE,
  INDEX `node_set_name_fk_idx` (`id_set` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `mac_UNIQUE` (`mac` ASC) VISIBLE,
  CONSTRAINT `node_id_server_info_fk`
    FOREIGN KEY (`id_server_info`)
    REFERENCES `server_info` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `node_id_set_fk`
    FOREIGN KEY (`id_set`)
    REFERENCES `instruction_set` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `instruction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `instruction` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_set` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `vector` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_set_name_UNIQUE` (`id_set` ASC, `name` ASC) INVISIBLE,
  CONSTRAINT `instruction_id_set_fk`
    FOREIGN KEY (`id_set`)
    REFERENCES `instruction_set` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Data for table `server_info`
-- -----------------------------------------------------
START TRANSACTION;
USE `cynanthus`;
INSERT INTO `server_info` (`id`, `name`, `address`, `port`, `server_type`, `info`) VALUES (DEFAULT, 'SERVIDOR DE FLUJO DE DATOS', '127.0.0.1', 8001, 'STREAM_DATA', 'Servidor de flujo de datos');
INSERT INTO `server_info` (`id`, `name`, `address`, `port`, `server_type`, `info`) VALUES (DEFAULT, 'SERVIDOR DE ALMACENAMIENTO', '127.0.0.1', 8000, 'STORAGE', 'Servidor de almacenamiento de datos');
INSERT INTO `server_info` (`id`, `name`, `address`, `port`, `server_type`, `info`) VALUES (DEFAULT, 'SERVIDOR DE CONTROL', '127.0.0.1', 8002, 'CONTROL', 'Servidor de control');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `cynanthus`;
INSERT INTO `user` (`id`, `username`, `password`) VALUES (DEFAULT, 'admin', '$2a$10$G6D.wtNmZ7L4nKu4fQvF7eQo.3C0J84X.xXcFEOv3pX/qvTh0Kzue');
INSERT INTO `user` (`id`, `username`, `password`) VALUES (DEFAULT, 'user', '$2a$10$nLEDsP47u3lB1PoYlW7UKOVNdut/vh9.xOewLy0JACijC5Xjp6Gtq');

COMMIT;


-- -----------------------------------------------------
-- Data for table `role`
-- -----------------------------------------------------
START TRANSACTION;
USE `cynanthus`;
INSERT INTO `role` (`id`, `id_user`, `role_type`) VALUES (DEFAULT, 1, 'ROLE_ADMIN');
INSERT INTO `role` (`id`, `id_user`, `role_type`) VALUES (DEFAULT, 1, 'ROLE_USER');
INSERT INTO `role` (`id`, `id_user`, `role_type`) VALUES (DEFAULT, 2, 'ROLE_USER');

COMMIT;


-- -----------------------------------------------------
-- Data for table `instruction_set`
-- -----------------------------------------------------
START TRANSACTION;
USE `cynanthus`;
INSERT INTO `instruction_set` (`id`, `name`, `info`) VALUES (DEFAULT, 'Conjunto 1', 'Conjunto de instrucciones 1');
INSERT INTO `instruction_set` (`id`, `name`, `info`) VALUES (DEFAULT, 'Conjunto 2', 'Conjunto de instrucciones 2');
INSERT INTO `instruction_set` (`id`, `name`, `info`) VALUES (DEFAULT, 'Conjunto 3', 'Conjunto de instrucciones 3');
INSERT INTO `instruction_set` (`id`, `name`, `info`) VALUES (DEFAULT, 'Conjunto 4', 'Conjunto de instrucciones 4');

COMMIT;


-- -----------------------------------------------------
-- Data for table `node_info`
-- -----------------------------------------------------
START TRANSACTION;
USE `cynanthus`;
INSERT INTO `node_info` (`id`, `mac`, `name`, `id_server_info`, `id_set`) VALUES (DEFAULT, '00:00:00:00:00:00', 'node1', 1, NULL);
INSERT INTO `node_info` (`id`, `mac`, `name`, `id_server_info`, `id_set`) VALUES (DEFAULT, '00:00:00:00:00:01', 'node2', 2, NULL);
INSERT INTO `node_info` (`id`, `mac`, `name`, `id_server_info`, `id_set`) VALUES (DEFAULT, '00:00:00:00:00:02', 'node3', 3, 1);
INSERT INTO `node_info` (`id`, `mac`, `name`, `id_server_info`, `id_set`) VALUES (DEFAULT, '00:00:00:00:00:03', 'node4', 3, 1);
INSERT INTO `node_info` (`id`, `mac`, `name`, `id_server_info`, `id_set`) VALUES (DEFAULT, '00:00:00:00:00:04', 'node5', 3, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `instruction`
-- -----------------------------------------------------
START TRANSACTION;
USE `cynanthus`;
INSERT INTO `instruction` (`id`, `id_set`, `name`, `vector`) VALUES (DEFAULT, 1, 'ON', '01230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123');
INSERT INTO `instruction` (`id`, `id_set`, `name`, `vector`) VALUES (DEFAULT, 1, 'OFF', '01230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123');
INSERT INTO `instruction` (`id`, `id_set`, `name`, `vector`) VALUES (DEFAULT, 1, 'UP', '01230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123');
INSERT INTO `instruction` (`id`, `id_set`, `name`, `vector`) VALUES (DEFAULT, 1, 'DOWN', '01230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123');
INSERT INTO `instruction` (`id`, `id_set`, `name`, `vector`) VALUES (DEFAULT, 2, 'ON', '01230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123');
INSERT INTO `instruction` (`id`, `id_set`, `name`, `vector`) VALUES (DEFAULT, 2, 'OFF', '01230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123');
INSERT INTO `instruction` (`id`, `id_set`, `name`, `vector`) VALUES (DEFAULT, 2, 'UP', '01230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123');
INSERT INTO `instruction` (`id`, `id_set`, `name`, `vector`) VALUES (DEFAULT, 2, 'DOWN', '01230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123012301230123');

COMMIT;

