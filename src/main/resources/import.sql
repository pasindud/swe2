-- insert into city(id, country, name, state, map) values (1, 'Australia', 'Brisbane', 'Queensland', '-27.470933, 153.023502')

-- INSERT INTO customer (customerid, first_name) VALUES (1,"one name");
insert into role (id, name) values (1,'USER');
insert into role (id, name) values (2,'ADMIN');

INSERT INTO security_questions (id, question) values(1,"What is build");
INSERT INTO security_questions (id, question) values(2,"What is your dogs name");
INSERT INTO security_questions (id, question) values(3,"What is your name");



INSERT INTO `branch` (`branch_id`, `address_line1`, `address_line2`, `address_line3`, `branch_name`, `city`, `email`, `fax_no`, `telephone_no`) VALUES
(1, '100', 'Anagaraika Dharmapala Road', '', 'Matara-2', 'Matara', 'matara2@abcbank.com', '0412235558', '0412235853'),
(2, 'No 45', 'Highlevel Road', NULL, 'Nugegoda-1', 'Nugegoda', 'nugegoda1@abcbank.com', '0112568432', '0112548558'),
(3, 'No 65', 'Isurupaya', NULL, 'Polonnaruwa-2', 'Polonnaruwa', 'polonnaruwa2@abcbank.com', '0474582357', '0475965826'),
(4, 'No 12', 'Highlevel Road', NULL, 'Nugegoda-2', 'Nugegoda', 'nugegoda1@abcbank.com', '0112568596', '0112548524'),
(5, 'no 23', 'Wellampitiya Road', NULL, 'Kolonnawa', 'Kolonnawa', 'kolonnawa@abcbank.com', '0114568478', '0114567539');


INSERT INTO `account_type` (`acc_type_id`, `acc_interest_rates`, `acc_name`, `daily_withdraw_limit`, `max_months`, `max_over_draft_amount`, `min_avg_balance`, `min_init_balance`, `min_months`) VALUES
(1, 3.2, 'Savings', 50000, NULL, NULL, NULL, 500, NULL),
(2, 2, 'Current Account', NULL, NULL, 50000, 100000, 1000000, NULL),
(3, 3, 'Fixed Disposit', NULL, 24, NULL, 3000, 50000, 12);


-- 


INSERT INTO `customer` (`customerid`, `address_line1`, `address_line2`, `address_line3`, `city`, `dob`, `email`, `fax_no`, `first_name`, `gender`, `last_name`, `mobile_no`, `nic`, `telephone_no`, `title`)
VALUES
    (1, 'rUKVrIm0hVXBAmn6MoU7zg==', NULL, NULL, 'wb5ELIgAjUET9BLTxLxjIQ==', NULL, 'kJScSvtzM0tB36PSse8GdAqlammTmdqyt4YF9wbvv/s=', NULL, 'OrL5aKAP2Kan5BQoXyGoHA==', NULL, '2fCrdZwn3DPDrPRVXOdauw==', NULL, '5ugCjoAV1CZlF6PshvnrBA==', NULL, NULL);


-- testinguser1 testpassword
INSERT INTO `users` (`user_id`, `creation_date`, `locked`, `password`, `user_type`, `username`, `customer_id`, `merchant_merchantid`)
VALUES
    (1, '2017-07-08 05:29:32', 1, '$2a$10$7p.ELkhd6nROL1L12MVsRu8kSEj2mJTxe8aCNxryNamimqKHMR932', 'CUSTOMER', 'testinguser1', 1, NULL);

INSERT INTO `users` (`user_id`, `creation_date`, `locked`, `password`, `user_type`, `username`, `customer_id`, `merchant_merchantid`)
VALUES
    (2, '2017-07-08 05:29:32', 1, '$2a$10$7p.ELkhd6nROL1L12MVsRu8kSEj2mJTxe8aCNxryNamimqKHMR932', 'CUSTOMER', 'ADMIN', NULL, NULL);

INSERT INTO `users` (`user_id`, `creation_date`, `locked`, `password`, `user_type`, `username`, `customer_id`, `merchant_merchantid`)
VALUES
    (3, '2017-07-08 05:49:39', 1, '$2a$10$VnHvizPAek3IOeHqgb4i.OBfUWtEMJrjWIue/SBVsm4zqWx5TFqSi', 'CUSTOMER', 'testinguser2', 2, NULL);


INSERT INTO `user_role` (`users_user_id`, `role_id`)
VALUES
    (1, 1),
    (2, 2);

INSERT INTO `account` (`accountid`, `balance`, `creation_date`, `currency`, `expire_date`, `locked`, `acctypeid`, `branchid`, `userid`)
VALUES
    (1, 9945, '2017-07-08 05:39:12', 'EUR', NULL, 0, 1, 1, 1),
    (2, 12489.8, '2017-07-08 05:47:04', 'LKR', NULL, 0, 1, 1, 1),
    (3, 18597, '2017-07-08 05:47:04', 'LKR', NULL, 0, 1, 1, 2);


INSERT INTO merchant (merchantid, logourl, orgname, registrationno, taxno) VALUES (1,'non','Dialog','1232','12313');
INSERT INTO merchant_services (serviceid, merchantid,accountid,  servicename) VALUES (1,1,3, "Phone Bill");




