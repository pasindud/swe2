#!/bin/bash
set -v


curl -u xyz:xyz "http://localhost:8080/api/auth"

curl -u ADMIN:xyz "http://localhost:8080/api/auth"

curl -u xyz:xyz "http://localhost:8080/api/merchant_services"

curl -u xyz:xyz "http://localhost:8080/api/merchant"

curl -u xyz:xyz "http://localhost:8080/api/customer"

curl -u xyz:xyz "http://localhost:8080/api/transactions?id=1"

curl -u xyz:xyz "http://localhost:8080/api/accounts_user?userid=1"

curl -u xyz:xyz "http://localhost:8080/api/accounts?id=1"

curl -u xyz:xyz  -H "Content-Type: application/json" \
http://localhost:8080/api/accounts_save \
-d '{"accTypeid":1, "currency":"LKR", "branchid":1}'


curl -u xyz:xyz -H "Content-Type: application/json" -X POST \
http://localhost:8080/api/merchant_services_pay_bill \
-d '{"amount":1, "selectedServiceId": 1, "selectedAccountId":1, "billReferenceNumber": 1}'

curl -u xyz:xyz -H "Content-Type: application/json" -X POST \
http://localhost:8080/api/merchant_services_pay_bill \
-d '{"amount":1, "selectedServiceId": 1,  "billReferenceNumber": 1}'


curl -u xyz:xyz  -H "Content-Type: application/json" -X POST \
http://localhost:8080/api/do_transaction \
-d '{"toaccountid":1, "fromaccountid": 1}'

curl -u xyz:xyz  -H "Content-Type: application/json" -X POST \
http://localhost:8080/api/do_transaction \
-d '{"toaccountid":1, "fromaccountid": 1, "amount": 2}'


curl -H "Content-Type: application/json" -X POST \
-d '{"users": {"username":"username","password":"pw"}, "customer": {"first_name":"TESTING"}}' \
http://localhost:8080/api/registration

curl -u xyz:xyz -H "Content-Type: application/json" -X POST http://localhost:8080/api/merchant_services_pay_bill -d '{"amount":1, "selectedServiceId": 1  }'

curl -u xyz:xyz -H "Content-Type: application/json" -X POST http://localhost:8080/api/merchant_services_pay_bill -d '{"amount":-21, "selectedServiceId": 1  }'


curl -u xyz:xyz  "http://localhost:8080/api/admin/change_user_status?user_id=1&activate=true"
curl -u ADMIN:xyz "http://localhost:8080/api/admin/change_user_status?user_id=1&activate=true"

curl -u ADMIN:xyz "http://localhost:8080/api/admin/lock_account?accountid=1&lock=true"
curl -u ADMIN:xyz "http://localhost:8080/api/admin/lock_account?accountid=1&lock=false"

curl -u xyz:xyz "http://localhost:8080/api/admin/lock_account?accountid=1&lock=true"
curl -u xyz:xyz "http://localhost:8080/api/admin/lock_account?accountid=1&lock=false"


curl -u xyz:xyz "http://localhost:8080/api/transaction_by_id?id=1"
curl -u xyz:xyz "http://localhost:8080/api/transaction_by_id?id=4"
curl -u xyz:xyz "http://localhost:8080/api/transaction_by_id?id=123123"


curl -u ADMIN:xyz "http://localhost:8080/api/admin/all_acounts" 
curl -u ADMIN:xyz "http://localhost:8080/api/admin/all_users" 
curl -u ADMIN:xyz "http://localhost:8080/api/admin/all_merchants" 




