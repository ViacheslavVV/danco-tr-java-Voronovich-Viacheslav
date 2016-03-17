-- -----------------------------------------------------
-- Schema products_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `products_schema` DEFAULT CHARACTER SET utf8 ;
USE `products_schema` ;

-- -----------------------------------------------------
-- Table `products_schema`.`Product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `products_schema`.`Product` ;

CREATE TABLE IF NOT EXISTS `products_schema`.`Product` (
  `Maker` VARCHAR(10) NOT NULL,
  `Model` VARCHAR(50) NOT NULL,
  `Type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Model`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `products_schema`.`PC`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `products_schema`.`PC` ;

CREATE TABLE IF NOT EXISTS `products_schema`.`PC` (
  `Code` INT NOT NULL AUTO_INCREMENT,
  `Model` VARCHAR(50) NOT NULL,
  `Speed` SMALLINT NOT NULL,
  `Ram` SMALLINT NOT NULL,
  `HD` REAL NOT NULL,
  `CD` VARCHAR(10) NOT NULL,
  `Price` INT NULL,
  PRIMARY KEY (`Code`),
  INDEX `Model_idx` (`Model` ASC),
  CONSTRAINT `PC_FK`
    FOREIGN KEY (`Model`)
    REFERENCES `products_schema`.`Product` (`Model`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `products_schema`.`Laptop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `products_schema`.`Laptop` ;

CREATE TABLE IF NOT EXISTS `products_schema`.`Laptop` (
  `Code` INT NOT NULL AUTO_INCREMENT,
  `Model` VARCHAR(50) NOT NULL,
  `Speed` SMALLINT NOT NULL,
  `Ram` SMALLINT NOT NULL,
  `HD` REAL NOT NULL,
  `Price` INT NULL,
  `Screen` TINYINT NOT NULL,
  PRIMARY KEY (`Code`),
  INDEX `Model_idx` (`Model` ASC),
  CONSTRAINT `LT_FK`
    FOREIGN KEY (`Model`)
    REFERENCES `products_schema`.`Product` (`Model`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `products_schema`.`Printer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `products_schema`.`Printer` ;

CREATE TABLE IF NOT EXISTS `products_schema`.`Printer` (
  `Code` INT NOT NULL AUTO_INCREMENT,
  `Model` VARCHAR(50) NOT NULL,
  `Color` CHAR(1) NOT NULL,
  `Type` VARCHAR(10) NOT NULL,
  `Price` INT NULL,
  PRIMARY KEY (`Code`),
  INDEX `Model_idx` (`Model` ASC),
  CONSTRAINT `Printer_FK`
    FOREIGN KEY (`Model`)
    REFERENCES `products_schema`.`Product` (`Model`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `products_schema`.`Product`
-- -----------------------------------------------------
START TRANSACTION;
USE `products_schema`;
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker1', 'PC1', 'PC');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker2', 'PC2', 'PC');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker3', 'PC3', 'PC');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker4', 'PC4', 'PC');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker5', 'PC5', 'PC');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker6', 'PC6', 'PC');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker1', 'LT1', 'LT');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker2', 'LT2', 'LT');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker3', 'LT3', 'LT');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker4', 'LT4', 'LT');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker5', 'LT5', 'LT');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker6', 'LT6', 'LT');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker1', 'PR1', 'PR');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker2', 'PR2', 'PR');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker3', 'PR3', 'PR');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker4', 'PR4', 'PR');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker5', 'PR5', 'PR');
INSERT INTO `products_schema`.`Product` (`Maker`, `Model`, `Type`) VALUES ('Maker6', 'PR6', 'PR');

COMMIT;


-- -----------------------------------------------------
-- Data for table `products_schema`.`PC`
-- -----------------------------------------------------
START TRANSACTION;
USE `products_schema`;
INSERT INTO `products_schema`.`PC` (`Code`, `Model`, `Speed`, `Ram`, `HD`, `CD`, `Price`) VALUES (1, 'PC1', 2100, 4000, 500, '8x', 400);
INSERT INTO `products_schema`.`PC` (`Code`, `Model`, `Speed`, `Ram`, `HD`, `CD`, `Price`) VALUES (2, 'PC2', 4200, 4000, 750, '8x', 600);
INSERT INTO `products_schema`.`PC` (`Code`, `Model`, `Speed`, `Ram`, `HD`, `CD`, `Price`) VALUES (3, 'PC3', 2100, 2000, 250, '4x', 300);
INSERT INTO `products_schema`.`PC` (`Code`, `Model`, `Speed`, `Ram`, `HD`, `CD`, `Price`) VALUES (4, 'PC4', 8400, 8000, 500, '8x', 1000);
INSERT INTO `products_schema`.`PC` (`Code`, `Model`, `Speed`, `Ram`, `HD`, `CD`, `Price`) VALUES (5, 'PC5', 4200, 4000, 1000, '16x', 700);
INSERT INTO `products_schema`.`PC` (`Code`, `Model`, `Speed`, `Ram`, `HD`, `CD`, `Price`) VALUES (6, 'PC6', 2100, 2000, 250, '8x', 350);

COMMIT;


-- -----------------------------------------------------
-- Data for table `products_schema`.`Laptop`
-- -----------------------------------------------------
START TRANSACTION;
USE `products_schema`;
INSERT INTO `products_schema`.`Laptop` (`Code`, `Model`, `Speed`, `Ram`, `HD`, `Price`, `Screen`) VALUES (1, 'LT1', 4200, 4000, 500, 500, 16);
INSERT INTO `products_schema`.`Laptop` (`Code`, `Model`, `Speed`, `Ram`, `HD`, `Price`, `Screen`) VALUES (2, 'LT2', 2100, 2000, 200, 300, 15);
INSERT INTO `products_schema`.`Laptop` (`Code`, `Model`, `Speed`, `Ram`, `HD`, `Price`, `Screen`) VALUES (3, 'LT3', 8400, 8000, 750, 800, 19);
INSERT INTO `products_schema`.`Laptop` (`Code`, `Model`, `Speed`, `Ram`, `HD`, `Price`, `Screen`) VALUES (4, 'LT4', 2100, 2000, 200, 330, 15);
INSERT INTO `products_schema`.`Laptop` (`Code`, `Model`, `Speed`, `Ram`, `HD`, `Price`, `Screen`) VALUES (5, 'LT5', 1600, 2000, 150, 250, 14);
INSERT INTO `products_schema`.`Laptop` (`Code`, `Model`, `Speed`, `Ram`, `HD`, `Price`, `Screen`) VALUES (6, 'LT6', 2100, 2000, 200, 300, 16);

COMMIT;


-- -----------------------------------------------------
-- Data for table `products_schema`.`Printer`
-- -----------------------------------------------------
START TRANSACTION;
USE `products_schema`;
INSERT INTO `products_schema`.`Printer` (`Code`, `Model`, `Color`, `Type`, `Price`) VALUES (1, 'PR1', 'y', 'Laser', 500);
INSERT INTO `products_schema`.`Printer` (`Code`, `Model`, `Color`, `Type`, `Price`) VALUES (2, 'PR2', 'n', 'Matrix', 200);
INSERT INTO `products_schema`.`Printer` (`Code`, `Model`, `Color`, `Type`, `Price`) VALUES (3, 'PR3', 'y', 'Jet', 600);
INSERT INTO `products_schema`.`Printer` (`Code`, `Model`, `Color`, `Type`, `Price`) VALUES (4, 'PR4', 'n', 'Matrix', 300);
INSERT INTO `products_schema`.`Printer` (`Code`, `Model`, `Color`, `Type`, `Price`) VALUES (5, 'PR5', 'y', 'Laser', 700);
INSERT INTO `products_schema`.`Printer` (`Code`, `Model`, `Color`, `Type`, `Price`) VALUES (6, 'PR6', 'y', 'Jet', 800);

COMMIT;

