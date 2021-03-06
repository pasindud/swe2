#!/bin/bash
set -v

# $2a$10$Ar5Ibucf2eboPvstkLvUdeMP.S8/NaAZAVtevLJi7nHXDefelq6/2
# $2a$10$IgUlPsypOlhGO/HwNBcBJ.fwAeMKHbd43mg1pzrOKY7GCzFw1BAV6

curl -u testinguser1:testpassword "http://localhost:8080/api/auth"

USERNAME=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)

curl -u ${USERNAME}:testpassword "http://localhost:8080/api/auth"
curl -u ${USERNAME}:testpassword "http://localhost:8080/api/auth"
curl -u ${USERNAME}:testpassword "http://localhost:8080/api/auth"

curl -u sahan:xassyz "http://localhost:8080/api/auth"
curl -u sahan:xassyz "http://localhost:8080/api/auth"
curl -u sahan:xassyz "http://localhost:8080/api/auth"
curl -u sahan:xassyz "http://localhost:8080/api/auth"

curl -u ADMIN:xyz "http://localhost:8080/api/auth"

curl -u testinguser1:testpassword "http://localhost:8080/api/merchant_services"

curl -u testinguser1:testpassword "http://localhost:8080/api/merchant"

curl -u testinguser1:testpassword "http://localhost:8080/api/customer"

curl -u testinguser1:testpassword "http://localhost:8080/api/transactions?id=1"

curl -u testinguser1:testpassword "http://localhost:8080/api/accounts_user?userid=1"

curl -u testinguser1:testpassword "http://localhost:8080/api/accounts?id=1"

curl -u testinguser1:testpassword  -H "Content-Type: application/json" \
http://localhost:8080/api/accounts_save \
-d '{"accTypeid":1, "currency":"LKR", "branchid":1}'


curl -u testinguser1:testpassword -H "Content-Type: application/json" -X POST \
http://localhost:8080/api/merchant_services_pay_bill \
-d '{"amount":1, "selectedServiceId": 1, "selectedAccountId":1, "billReferenceNumber": 1}'

curl -u testinguser1:testpassword -H "Content-Type: application/json" -X POST \
http://localhost:8080/api/merchant_services_pay_bill \
-d '{"amount":1, "selectedServiceId": 1,  "billReferenceNumber": 1}'

curl -u testinguser1:testpassword  -H "Content-Type: application/json" -X POST \
http://localhost:8080/api/do_transaction \
-d '{"toaccountid":1, "fromaccountid": 1}'

curl -u testinguser1:testpassword  -H "Content-Type: application/json" -X POST \
http://localhost:8080/api/do_transaction \
-d '{"toaccountid":1, "fromaccountid": 1, "amount": 2}'


curl -H "Content-Type: application/json" -X POST \
-d '{"users": {"username":"username","password":"pw"}, "customer": {"first_name":"TESTING"}}' \
http://localhost:8080/api/registration

curl -H "Content-Type: application/json" -X POST \
-d '{"users":{"username":"testinguser2","password":"testpassword","userType":"CUSTOMER"},"customer":{"firstname":"","lastname":"","nic":"950483628v","addressLine1":"Lane 1","city":"Colombo","email":"testing@localhost.com","firstName":"Hellow","lastName":"HMM"},"answers":[{"answer":"1","securityQuestions":{"id":"1"}},{"answer":"1","securityQuestions":{"id":"2"}},{"answer":"1","securityQuestions":{"id":"1"}}]}' \
http://localhost:8080/api/registration



curl -u testinguser1:testpassword -H "Content-Type: application/json" -X POST http://localhost:8080/api/merchant_services_pay_bill -d '{"amount":1, "selectedServiceId": 1  }'

curl -u testinguser1:testpassword -H "Content-Type: application/json" -X POST http://localhost:8080/api/merchant_services_pay_bill -d '{"amount":-21, "selectedServiceId": 1  }'


curl -u testinguser1:testpassword  "http://localhost:8080/api/admin/change_user_status?user_id=1&lock=true"
curl -u ADMIN:xyz "http://localhost:8080/api/admin/change_user_status?user_id=1&lock=true"

curl -u ADMIN:xyz "http://localhost:8080/api/admin/lock_account?accountid=1&lock=true"
curl -u ADMIN:xyz "http://localhost:8080/api/admin/lock_account?accountid=1&lock=false"

curl -u testinguser1:testpassword "http://localhost:8080/api/admin/lock_account?accountid=1&lock=true"
curl -u testinguser1:testpassword "http://localhost:8080/api/admin/lock_account?accountid=1&lock=false"

# http://localhost:8080/api/admin/change_user_status?user_id=1&activate=true 

curl -u testinguser1:testpassword "http://localhost:8080/api/transaction_by_id?id=1"
curl -u testinguser1:testpassword "http://localhost:8080/api/transaction_by_id?id=4"
curl -u testinguser1:testpassword "http://localhost:8080/api/transaction_by_id?id=123123"


curl -u ADMIN:xyz "http://localhost:8080/api/admin/all_acounts" 
 
curl -u ADMIN:xyz "http://localhost:8080/api/admin/account_id?id=1" 
curl -u ADMIN:xyz "http://localhost:8080/api/admin/all_users" 
curl -u ADMIN:xyz "http://localhost:8080/api/admin/all_merchants" 

 curl -u testinguser1:testpassword -H "Content-Type: application/json" -X POST \
 http://localhost:8080/api/merchant_services_pay_bill -d \
 '{"amount":123123123121231231231231, "selectedServiceId": 1, "selectedAccountId":1, "billReferenceNumber": 1}asdasd' 


curl -H "Content-Type: application/json"  -X POST \
"http://localhost:8080/api/forgot_password" \
-d '{"username":"xyz","password":"xyz","answers":[{"securityQuestions":{"id":1},"answer":"1"},{"securityQuestions":{"id":2},"answer":"1"}]}'

 # '[{"answer":"Jayawardhana","securityQuestions":{"id":1}},{"answer":"Rex","securityQuestions":{"id":2}},{"answer":"colombo","securityQuestions":{"id":8}}]'

 curl -H "Content-Type: application/json" -X POST -u testinguser1:testpassword \
   -d '{ "current":"xyz", "newPassword":"xyzz"}' "http://localhost:8080/api/change_password"

curl -u ADMIN:xyz "http://localhost:8080//api/admin/get_suspicious_logs"

