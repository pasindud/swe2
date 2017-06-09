-- MySQL Script generated by MySQL Workbench
-- Sat May 27 19:46:25 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `userId` INT NOT NULL,
  `userType` VARCHAR(45) NOT NULL,
  `creationDate` DATE NOT NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Customer` (
  `userId` INT NOT NULL,
  `title` VARCHAR(5) NULL,
  `firstName` VARCHAR(45) NULL,
  `lostName` VARCHAR(45) NULL,
  `dob` VARCHAR(45) NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `fk_Customer_User`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Merchant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Merchant` (
  `userId` INT NOT NULL,
  `OrgName` VARCHAR(45) NOT NULL,
  `registrationNo` VARCHAR(45) NOT NULL,
  `taxNo` VARCHAR(45) NOT NULL,
  `logoUrl` VARCHAR(255) NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `fk_Merchant_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Postion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Postion` (
  `postionId` INT NOT NULL,
  `position` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`postionId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Branch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Branch` (
  `branchId` INT NOT NULL,
  `branchName` VARCHAR(45) NULL,
  `addressLine1` VARCHAR(45) NULL,
  `addressLine2` VARCHAR(45) NULL,
  `addressLine3` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `telephoneNo` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `faxNo` VARCHAR(45) NULL,
  PRIMARY KEY (`branchId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Admin` (
  `userId` INT NOT NULL,
  `title` INT NULL,
  `postionId` INT NOT NULL,
  `branchId` INT NOT NULL,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  PRIMARY KEY (`userId`, `postionId`, `branchId`),
  INDEX `fk_Admin_User1_idx` (`userId` ASC),
  INDEX `fk_Admin_Postion1_idx` (`postionId` ASC),
  INDEX `fk_Admin_Branch1_idx` (`branchId` ASC),
  CONSTRAINT `fk_Admin_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Admin_Postion1`
    FOREIGN KEY (`postionId`)
    REFERENCES `mydb`.`Postion` (`postionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Admin_Branch1`
    FOREIGN KEY (`branchId`)
    REFERENCES `mydb`.`Branch` (`branchId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ContactInfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ContactInfo` (
  `contactId` INT NOT NULL,
  `userId` INT NOT NULL,
  `addressLine1` VARCHAR(45) NULL,
  `addressLine2` VARCHAR(45) NULL,
  `addressLine3` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `telephoneNo` VARCHAR(45) NULL,
  `mobileNo` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `faxNo` VARCHAR(45) NULL,
  PRIMARY KEY (`contactId`, `userId`),
  INDEX `fk_ContactInfo_User1_idx` (`userId` ASC),
  CONSTRAINT `fk_ContactInfo_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`AccountType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`AccountType` (
  `accTypeId` INT NOT NULL,
  `accName` VARCHAR(45) NULL,
  `accInterestRates` FLOAT NULL,
  `minInitBalance` FLOAT NULL,
  `minAvgBalance` FLOAT NULL,
  `minMonths` INT NULL,
  `maxMonths` INT NULL,
  `maxOverDraftAmount` FLOAT NULL,
  PRIMARY KEY (`accTypeId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`MerchantServices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`MerchantServices` (
  `serviceId` INT NOT NULL,
  `Merchant_userId` INT NOT NULL,
  `descrition` VARCHAR(45) NULL,
  `serviceLogpURL` VARCHAR(45) NULL,
  PRIMARY KEY (`serviceId`, `Merchant_userId`),
  INDEX `fk_MerchantServices_Merchant1_idx` (`Merchant_userId` ASC),
  CONSTRAINT `fk_MerchantServices_Merchant1`
    FOREIGN KEY (`Merchant_userId`)
    REFERENCES `mydb`.`Merchant` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Account` (
  `accountId` INT NOT NULL,
  `accTypeId` INT NOT NULL,
  `userId` INT NOT NULL,
  `branchId` INT NOT NULL,
  `expireDate` DATE NULL,
  `createdDate` DATE NULL,
  `balance` FLOAT NULL,
  `currency` VARCHAR(45) NULL,
  `MerchantServices_serviceId` INT NOT NULL,
  `MerchantServices_Merchant_userId` INT NOT NULL,
  PRIMARY KEY (`accountId`, `MerchantServices_serviceId`, `MerchantServices_Merchant_userId`),
  INDEX `fk_Account_AccountType1_idx` (`accTypeId` ASC),
  INDEX `fk_Account_User1_idx` (`userId` ASC),
  INDEX `fk_Account_Branch1_idx` (`branchId` ASC),
  INDEX `fk_Account_MerchantServices1_idx` (`MerchantServices_serviceId` ASC, `MerchantServices_Merchant_userId` ASC),
  CONSTRAINT `fk_Account_AccountType1`
    FOREIGN KEY (`accTypeId`)
    REFERENCES `mydb`.`AccountType` (`accTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Account_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Account_Branch1`
    FOREIGN KEY (`branchId`)
    REFERENCES `mydb`.`Branch` (`branchId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Account_MerchantServices1`
    FOREIGN KEY (`MerchantServices_serviceId` , `MerchantServices_Merchant_userId`)
    REFERENCES `mydb`.`MerchantServices` (`serviceId` , `Merchant_userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Transaction` (
  `transactionId` INT NOT NULL,
  `userId` INT NOT NULL,
  `accountId` INT NOT NULL,
  `transType` VARCHAR(45) NULL,
  `currency` VARCHAR(45) NULL,
  `amount` FLOAT NULL,
  PRIMARY KEY (`transactionId`),
  INDEX `fk_Transaction_User1_idx` (`userId` ASC),
  INDEX `fk_Transaction_Account1_idx` (`accountId` ASC),
  CONSTRAINT `fk_Transaction_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_Account1`
    FOREIGN KEY (`accountId`)
    REFERENCES `mydb`.`Account` (`accountId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`LoginInfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`LoginInfo` (
  `userId` INT NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `sequrityAns` VARCHAR(45) NULL,
  `defaultIP` VARCHAR(132) NULL,
  PRIMARY KEY (`userId`),
  INDEX `fk_LoginInfo_User1_idx` (`userId` ASC),
  CONSTRAINT `fk_LoginInfo_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`LoginHistory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`LoginHistory` (
  `id` INT NOT NULL,
  `userId` INT NOT NULL,
  `time` DATE NULL,
  `ipAddress` VARCHAR(132) NULL,
  PRIMARY KEY (`id`, `userId`),
  INDEX `fk_LoginHistory_User1_idx` (`userId` ASC),
  CONSTRAINT `fk_LoginHistory_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
