-- insert into city(id, country, name, state, map) values (1, 'Australia', 'Brisbane', 'Queensland', '-27.470933, 153.023502')

-- INSERT INTO customer (customerid, first_name) VALUES (1,"one name");
insert into role (id, name) values (1,'USER');
insert into role (id, name) values (2,'ADMIN');

INSERT INTO `customer` (`customerid`, `address_line1`, `address_line2`, `address_line3`, `city`, `dob`, `email`, `fax_no`, `first_name`, `gender`, `last_name`, `mobile_no`, `nic`, `telephone_no`, `title`) VALUES
(1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'one name', NULL, NULL, NULL, NULL, NULL, NULL),
(100, 'No. 100', 'Kolonnawa Road', NULL, 'Kolonnawa', '1993-05-01', 'sahan@gmail.com', NULL, 'Sahan', 'Male', 'Gunarathna', '0774532145', '934562845V', '0112587564', 'Mr'),
(101, 'No 20', 'Kalidasa Road', NULL, 'Matara', '1982-07-01', 'kamala@gmail.com', NULL, 'Kamala', 'Female', 'Jayarathne', '0715324785', '824532158V', NULL, 'Ms'),
(102, 'No. 52', 'Vijerama Road', NULL, 'Nugegoda', '1956-04-05', 'samantha@gmail.com', '0112452124', 'Samantha', 'Male', 'Gunawaradhana', '0778547851', '567862482V', '0114785245', 'Mr'),
(103, 'No. 35', 'Vihara Road', 'Isurupaya', 'Polonnaruwa', '1989-04-01', 'gihan@yahoo.com', NULL, 'Gihan', 'Male', 'Samarathunga', '0774528961', '891435826V', '0475682158', 'Mr'),
(104, 'No 26', 'Pengiriwattha Road', 'Delkanda', 'Nugegoda', '1995-06-08', 'sanathj@ymail.com', NULL, 'Sanath', 'Male', 'Jayasooriya', '0774582156', '957832145V', '0115789652', 'Mr');


INSERT INTO `users` (`user_id`, `activate`, `creation_date`, `password`, `time_locked`, `user_type`, `username`, `customer_id`, `merchant_merchantid`) VALUES
(1, 1, NULL, '$2a$10$sLDnONOzHvBR04Xy5aiT2uAyZf3VUKDOKVFAQqNA2/bUsxC0Z0fOm', 0, NULL, 'xyz', 1, NULL),
(2, 1, NULL, '$2a$10$sLDnONOzHvBR04Xy5aiT2uAyZf3VUKDOKVFAQqNA2/bUsxC0Z0fOm', 0, NULL, 'ADMIN', NULL, NULL),
(3, 1, '2017-07-02', '$2a$10$sLDnONOzHvBR04Xy5aiT2uAyZf3VUKDOKVFAQqNA2/bUsxC0Z0fOm', 0, NULL, 'sahan', 100, NULL),
(4, 1, '2017-07-02', '$2a$10$sLDnONOzHvBR04Xy5aiT2uAyZf3VUKDOKVFAQqNA2/bUsxC0Z0fOm', 0, NULL, 'kamala', 101, NULL),
(5, 1, '2017-07-02', '$2a$10$sLDnONOzHvBR04Xy5aiT2uAyZf3VUKDOKVFAQqNA2/bUsxC0Z0fOm', 0, NULL, 'samantha', 102, NULL),
(6, 1, '2017-07-02', '$2a$10$sLDnONOzHvBR04Xy5aiT2uAyZf3VUKDOKVFAQqNA2/bUsxC0Z0fOm', 0, NULL, 'gihan', 103, NULL),
(7, 1, '2017-07-02', '$2a$10$sLDnONOzHvBR04Xy5aiT2uAyZf3VUKDOKVFAQqNA2/bUsxC0Z0fOm', 0, NULL, 'sanath', 104, NULL);

insert into user_role(user_id, role_id) values(1,1);
insert into user_role(user_id, role_id) values(2,2);

-- insert into branch(branch_id, address_line1, address_line2, address_line3, branch_name, city, email, fax_no, telephone_no) values (1,'A','B','C',NULL,NULL,NULL,NULL,NULL);

INSERT INTO `account_type` (`acc_type_id`, `acc_interest_rates`, `acc_name`, `daily_withdraw_limit`, `max_months`, `max_over_draft_amount`, `min_avg_balance`, `min_init_balance`, `min_months`) VALUES
(1, 3.2, 'Savings', 50000, NULL, NULL, NULL, 500, NULL),
(2, 2, 'Current Account', NULL, NULL, 50000, 100000, 1000000, NULL),
(3, 3, 'Fixed Disposit', NULL, 24, NULL, 3000, 50000, 12);

-- insert into account_type (acc_type_id, acc_interest_rates, acc_name, max_months, max_over_draft_amount, min_avg_balance, min_init_balance, min_months) values (1,1,'asd',1,NULL,NULL,NULL,NULL);

INSERT INTO `branch` (`branch_id`, `address_line1`, `address_line2`, `address_line3`, `branch_name`, `city`, `email`, `fax_no`, `telephone_no`) VALUES
(1, '100', 'Anagaraika Dharmapala Road', '', 'Matara-2', 'Matara', 'matara2@abcbank.com', '0412235558', '0412235853'),
(2, 'No 45', 'Highlevel Road', NULL, 'Nugegoda-1', 'Nugegoda', 'nugegoda1@abcbank.com', '0112568432', '0112548558'),
(3, 'No 65', 'Isurupaya', NULL, 'Polonnaruwa-2', 'Polonnaruwa', 'polonnaruwa2@abcbank.com', '0474582357', '0475965826'),
(4, 'No 12', 'Highlevel Road', NULL, 'Nugegoda-2', 'Nugegoda', 'nugegoda1@abcbank.com', '0112568596', '0112548524'),
(5, 'no 23', 'Wellampitiya Road', NULL, 'Kolonnawa', 'Kolonnawa', 'kolonnawa@abcbank.com', '0114568478', '0114567539');


INSERT INTO merchant (merchantid, logourl, orgname, registrationno, taxno) VALUES (1,'asd','asd','1232','12313');
-- insert into city(id, country, name, state, map) values (1, 'Australia', 'Brisbane', 'Queensland', '-27.470933, 153.023502')


INSERT INTO `transaction` (`transactionid`, `amount`, `fromaccountid`, `fromcurrency`, `fromrate`, `message`, `toaccountid`, `tocurrency`, `torate`, `transactiontime`, `transtype`, `userid`) 
VALUES(1,1,1,'1',1,'1',1,'1',1,'0000-00-01 00:00:01','T',1);
INSERT INTO `transaction` (`transactionid`, `amount`, `fromaccountid`, `fromcurrency`, `fromrate`, `message`, `toaccountid`, `tocurrency`, `torate`, `transactiontime`, `transtype`, `userid`) 
VALUES(2,1,1,'1',1,'1',1,'1',1,'0000-00-02 00:00:02','T',1);
INSERT INTO `transaction` (`transactionid`, `amount`, `fromaccountid`, `fromcurrency`, `fromrate`, `message`, `toaccountid`, `tocurrency`, `torate`, `transactiontime`, `transtype`, `userid`) 
VALUES(3,1,1,'1',1,'1',1,'1',1,'0000-00-03 00:00:03','T',1);


INSERT INTO account (accountid, balance, created_date, currency, expire_date, acctypeid, branchid, userid) VALUES (1,10000,'0001-01-01','USD','0001-01-01',1,1,1);
INSERT INTO account (accountid, balance, created_date, currency, expire_date, acctypeid, branchid, userid) VALUES (2,10000,'0001-01-01','LKR','0001-01-01',1,1,1);

INSERT INTO `account` (`accountid`, `balance`, `created_date`, `currency`, `expire_date`, `acctypeid`, `branchid`, `userid`) VALUES
(110025, 4500, '2017-07-01', 'LKR', NULL, 1, 2, 7),
(553304, 500000, '2017-06-28', 'LKR', NULL, 2, 5, 3),
(753225, 3075, '2017-07-02', 'LKR', NULL, 1, 3, 6),
(853556, 600, '2017-06-14', 'USD', '2018-06-14', 3, 1, 4),
(884423, 8562, '2017-05-09', 'LKR', NULL, 1, 4, 5);

INSERT INTO merchant_services (serviceid, merchantid,accountid,  servicename) VALUES (1,1,2, "New Service");

INSERT INTO security_questions (id, question) values(1,"What is build");

