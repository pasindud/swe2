-- insert into city(id, country, name, state, map) values (1, 'Australia', 'Brisbane', 'Queensland', '-27.470933, 153.023502')

INSERT INTO `customer` (`customerid`, `first_name`) VALUES (1,"one name");
insert into role (id, name) values (1,'USER');
insert into role (id, name) values (2,'ADMIN');

insert into users(user_id, password, username, customer_id) values (1,'$2a$10$sLDnONOzHvBR04Xy5aiT2uAyZf3VUKDOKVFAQqNA2/bUsxC0Z0fOm','xyz',1);
insert into users(user_id, password, username) values (2,'$2a$10$kbgMM6IXQJcqAMUAUiOD8OII8pEwlrGuWhPyhGz0SFxC7V9kQuY0G','ADMINA');

insert into user_role(user_id, role_id) values(1,1)
insert into user_role(user_id, role_id) values(2,2)

insert into branch(branch_id, address_line1, address_line2, address_line3, branch_name, city, email, fax_no, telephone_no) values (1,'A','B','C',NULL,NULL,NULL,NULL,NULL);



insert into account_type (acc_type_id, acc_interest_rates, acc_name, max_months, max_over_draft_amount, min_avg_balance, min_init_balance, min_months) values (1,1,'asd',1,NULL,NULL,NULL,NULL);

INSERT INTO account (accountid, balance, created_date, currency, expire_date, acctypeid, branchid, userid) VALUES (1,1,'0001-01-01','11','0001-01-01',1,1,1);


INSERT INTO `merchant` (`merchantid`, `logourl`, `orgname`, `registrationno`, `taxno`) VALUES (1,'asd','asd','1232','12313');

INSERT INTO `merchant_services` (`serviceid`, `merchantid`, `servicename`) VALUES (1,1, "New Service");


