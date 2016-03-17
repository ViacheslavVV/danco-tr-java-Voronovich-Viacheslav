-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`lecturer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`lecturer` ;

CREATE TABLE IF NOT EXISTS `mydb`.`lecturer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`course` ;

CREATE TABLE IF NOT EXISTS `mydb`.`course` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  `name` VARCHAR(60) NOT NULL,
  `startDate` DATE NOT NULL,
  `finalDate` DATE NOT NULL,
  `maxStudents` INT NOT NULL,
  `maxLections` INT NOT NULL,
  `lecturerId` INT NOT NULL,
  
  INDEX `CourseLecturer_idx` (`lecturerId` ASC),
  CONSTRAINT `CourseLecturer`
    FOREIGN KEY (`lecturerId`)
    REFERENCES `mydb`.`lecturer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`lection`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`lection` ;

CREATE TABLE IF NOT EXISTS `mydb`.`lection` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  `name` VARCHAR(60) NOT NULL,
  `date` DATE NOT NULL,
  `courseId` INT NOT NULL,
  INDEX `CourseLections_idx` (`courseId` ASC),
  CONSTRAINT `CourseLections`
    FOREIGN KEY (`courseId`)
    REFERENCES `mydb`.`course` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`student`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`student` ;

CREATE TABLE IF NOT EXISTS `mydb`.`student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `courseId` INT NOT NULL,
  PRIMARY KEY (`id`),student
  CONSTRAINT `CourseStudents`
    FOREIGN KEY (`courseId`)
    REFERENCES `mydb`.`course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Data for table `mydb`.`lecturer`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`lecturer` (`id`, `name`, `age`) VALUES (1, 'Ivan Ivanovich', 44);
INSERT INTO `mydb`.`lecturer` (`id`, `name`, `age`) VALUES (2, 'Roman Romanovich', 47);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`course`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`course` (`id`, `name`, `startDate`, `finalDate`, `maxStudents`, `maxLections`, `lecturerId`) VALUES (1, 'Course1', '17.09.2017', '19.09.2017', 30, 30, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`lection`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`lection` (`id`, `name`, `date`, `courseId`) VALUES (1, 'Lection1', '18.09.2017', 1);
INSERT INTO `mydb`.`lection` (`id`, `name`, `date`, `courseId`) VALUES (2, 'Lection2', '18.09.2017', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`student`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`student` (`id`, `name`, `age`, `courseId`) VALUES (1, 'Nikolay Romanov', 19, 1);
INSERT INTO `mydb`.`student` (`id`, `name`, `age`, `courseId`) VALUES (2, 'Cheslav Rovba', 19, 1);

COMMIT;

