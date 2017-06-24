/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table account
# ------------------------------------------------------------

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;

INSERT INTO `account` (`accountid`, `balance`, `created_date`, `currency`, `expire_date`, `acctypeid`, `branchid`, `userid`)
VALUES
	(1,111,'0001-01-01','11','0001-01-01',1,1,1);

/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table account_type
# ------------------------------------------------------------

LOCK TABLES `account_type` WRITE;
/*!40000 ALTER TABLE `account_type` DISABLE KEYS */;

INSERT INTO `account_type` (`acc_type_id`, `acc_interest_rates`, `acc_name`, `max_months`, `max_over_draft_amount`, `min_avg_balance`, `min_init_balance`, `min_months`)
VALUES
	(1,1,'asd',1,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `account_type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table admin
# ------------------------------------------------------------



# Dump of table branch
# ------------------------------------------------------------

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;

INSERT INTO `branch` (`branch_id`, `address_line1`, `address_line2`, `address_line3`, `branch_name`, `city`, `email`, `fax_no`, `telephone_no`)
VALUES
	(1,'A','B','C',NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table contact_info
# ------------------------------------------------------------



# Dump of table customer
# ------------------------------------------------------------



# Dump of table login_history
# ------------------------------------------------------------



# Dump of table login_info
# ------------------------------------------------------------



# Dump of table merchant
# ------------------------------------------------------------

LOCK TABLES `merchant` WRITE;
/*!40000 ALTER TABLE `merchant` DISABLE KEYS */;

INSERT INTO `merchant` (`user_id`, `logo_url`, `org_name`, `registration_no`, `tax_no`)
VALUES
	(1,'asd','asd','1232','12313');

/*!40000 ALTER TABLE `merchant` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table merchant_services
# ------------------------------------------------------------



# Dump of table postion
# ------------------------------------------------------------



# Dump of table role
# ------------------------------------------------------------

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;

INSERT INTO `role` (`id`, `name`)
VALUES
	(1,'USER');

/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table transaction
# ------------------------------------------------------------



# Dump of table user
# ------------------------------------------------------------

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`user_id`, `password`, `username`)
VALUES
	(1,NULL,NULL),
	(9,'$2a$10$sLDnONOzHvBR04Xy5aiT2uAyZf3VUKDOKVFAQqNA2/bUsxC0Z0fOm','xyz'),

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_role
# ------------------------------------------------------------

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;

INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES
	(1,1);

/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table users
# ------------------------------------------------------------

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`user_id`, `creation_date`, `user_type`, `merchant_user_id`)
VALUES
	(1,'0001-01-01','',NULL);

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
