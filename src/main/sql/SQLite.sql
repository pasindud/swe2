
CREATE TABLE IF NOT EXISTS`User` (
  `userId` INT NOT NULL,
  `userType` VARCHAR(45) NOT NULL,
  `creationDate` DATE NOT NULL,
  PRIMARY KEY (`userId`))
;


-- -----------------------------------------------------
-- Table`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS`Customer` (
  `userId` INT NOT NULL,
  `title` VARCHAR(5) NULL,
  `firstName` VARCHAR(45) NULL,
  `lostName` VARCHAR(45) NULL,
  `dob` VARCHAR(45) NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `fk_Customer_User`
    FOREIGN KEY (`userId`)
    REFERENCES`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table`Merchant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS`Merchant` (
  `userId` INT NOT NULL,
  `OrgName` VARCHAR(45) NOT NULL,
  `registrationNo` VARCHAR(45) NOT NULL,
  `taxNo` VARCHAR(45) NOT NULL,
  `logoUrl` VARCHAR(255) NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `fk_Merchant_User1`
    FOREIGN KEY (`userId`)
    REFERENCES`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table`Postion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS`Postion` (
  `postionId` INT NOT NULL,
  `position` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`postionId`))
;


-- -----------------------------------------------------
-- Table`Branch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS`Branch` (
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
;


-- -----------------------------------------------------
-- Table`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS`Admin` (
  `userId` INT NOT NULL,
  `title` INT NULL,
  `postionId` INT NOT NULL,
  `branchId` INT NOT NULL,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  PRIMARY KEY (`userId`, `postionId`, `branchId`),
  CONSTRAINT `fk_Admin_User1`
    FOREIGN KEY (`userId`)
    REFERENCES`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Admin_Postion1`
    FOREIGN KEY (`postionId`)
    REFERENCES`Postion` (`postionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Admin_Branch1`
    FOREIGN KEY (`branchId`)
    REFERENCES`Branch` (`branchId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table`ContactInfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS`ContactInfo` (
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
  CONSTRAINT `fk_ContactInfo_User1`
    FOREIGN KEY (`userId`)
    REFERENCES`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table`AccountType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS`AccountType` (
  `accTypeId` INT NOT NULL,
  `accName` VARCHAR(45) NULL,
  `accInterestRates` FLOAT NULL,
  `minInitBalance` FLOAT NULL,
  `minAvgBalance` FLOAT NULL,
  `minMonths` INT NULL,
  `maxMonths` INT NULL,
  `maxOverDraftAmount` FLOAT NULL,
  PRIMARY KEY (`accTypeId`))
;


-- -----------------------------------------------------
-- Table`MerchantServices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS`MerchantServices` (
  `serviceId` INT NOT NULL,
  `Merchant_userId` INT NOT NULL,
  `descrition` VARCHAR(45) NULL,
  `serviceLogpURL` VARCHAR(45) NULL,
  PRIMARY KEY (`serviceId`, `Merchant_userId`),
  CONSTRAINT `fk_MerchantServices_Merchant1`
    FOREIGN KEY (`Merchant_userId`)
    REFERENCES`Merchant` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table`Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS`Account` (
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
  CONSTRAINT `fk_Account_AccountType1`
    FOREIGN KEY (`accTypeId`)
    REFERENCES`AccountType` (`accTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Account_User1`
    FOREIGN KEY (`userId`)
    REFERENCES`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Account_Branch1`
    FOREIGN KEY (`branchId`)
    REFERENCES`Branch` (`branchId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Account_MerchantServices1`
    FOREIGN KEY (`MerchantServices_serviceId` , `MerchantServices_Merchant_userId`)
    REFERENCES`MerchantServices` (`serviceId` , `Merchant_userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table`Transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS`Transaction` (
  `transactionId` INT NOT NULL,
  `userId` INT NOT NULL,
  `accountId` INT NOT NULL,
  `transType` VARCHAR(45) NULL,
  `currency` VARCHAR(45) NULL,
  `amount` FLOAT NULL,
  PRIMARY KEY (`transactionId`),
  CONSTRAINT `fk_Transaction_User1`
    FOREIGN KEY (`userId`)
    REFERENCES`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_Account1`
    FOREIGN KEY (`accountId`)
    REFERENCES`Account` (`accountId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table`LoginInfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS`LoginInfo` (
  `userId` INT NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `sequrityAns` VARCHAR(45) NULL,
  `defaultIP` VARCHAR(132) NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `fk_LoginInfo_User1`
    FOREIGN KEY (`userId`)
    REFERENCES`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table`LoginHistory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS`LoginHistory` (
  `id` INT NOT NULL,
  `userId` INT NOT NULL,
  `time` DATE NULL,
  `ipAddress` VARCHAR(132) NULL,
  PRIMARY KEY (`id`, `userId`),
  CONSTRAINT `fk_LoginHistory_User1`
    FOREIGN KEY (`userId`)
    REFERENCES`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

