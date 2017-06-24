-- insert into city(id, country, name, state, map) values (1, 'Australia', 'Brisbane', 'Queensland', '-27.470933, 153.023502')


insert into users(user_id, password, username) values (1,'$2a$10$sLDnONOzHvBR04Xy5aiT2uAyZf3VUKDOKVFAQqNA2/bUsxC0Z0fOm','xyz');


insert into branch(branch_id, address_line1, address_line2, address_line3, branch_name, city, email, fax_no, telephone_no) values (1,'A','B','C',NULL,NULL,NULL,NULL,NULL);


insert into role (id, name) values (1,'USER');

insert into account_type (acc_type_id, acc_interest_rates, acc_name, max_months, max_over_draft_amount, min_avg_balance, min_init_balance, min_months) values (1,1,'asd',1,NULL,NULL,NULL,NULL);


INSERT INTO account (accountid, balance, created_date, currency, expire_date, acctypeid, branchid, userid) VALUES (1,1,'0001-01-01','11','0001-01-01',1,1,1);


INSERT INTO `merchant` (`merchant_id`, `logo_url`, `org_name`, `registration_no`, `tax_no`) VALUES (1,'asd','asd','1232','12313');

INSERT INTO `merchant_services` (`service_id`, `merchant_userid`) VALUES (1,1);
