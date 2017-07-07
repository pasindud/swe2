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
