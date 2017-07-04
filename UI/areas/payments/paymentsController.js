'use strict';
angular.module('banking')
  .controller('PaymentsController', function($state, $rootScope, $scope, $http, $filter, AuthService) {

    // Dedup the code here
    AuthService.getRequest("/api/accounts?id=" + $rootScope.authData.userId, null, function(response) {
      $scope.user_accounts = response.data;
    });

    AuthService.getRequest("/api/merchant_services", null, function(response) {
      $scope.merchant_services = response.data;
    });

    function handErrors(type, response) {
      if (response.data.errors != undefined && response.data.errors.length != 0) {
         $('#ErrorModal').modal('open');
         var errorContent = {
           Title: type + " Error",
           Body: response.data.errors
         }
         $rootScope.ErrorDialog = errorContent;
      } else {
       // TODO show sucessfull message;
      }
    }

    $scope.paybill = function() {
      var data = {
        amount: $scope.amount,
        billReferenceNumber: $scope.bill_reference_number,
        selectedServiceId: $scope.selectedService,
        selectedAccountId: $scope.selectedAccount
      }
      console.log(data);

      AuthService.getRequest("/api/merchant_services_pay_bill", data, function(response) {
        handErrors("Pay Bills", response);
      });
    };

    $scope.transfer = function() {
      var data = {
        amount: $scope.transfere_amount,
        toaccountid: $scope.transfere_toaccountid,
        fromaccountid: $scope.selectedTransfereAccount,
        message: $scope.transfere_message
      }
      console.log(data);

      AuthService.getRequest("/api/do_transaction", data, function(response) {
        handErrors("Transfer", response);
      });
    }
  });
