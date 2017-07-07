'use strict';
angular.module('banking')
.controller('AccountController', function($state, $rootScope, $scope, $http, $filter, AuthService, ObjectService) {

  //obj init
  $scope.selectedAccountDetails = ObjectService.getAccountObj();

  //request to server
  AuthService.getRequest("/api/accounts?id=" + $rootScope.authData.userId, null, function(response) {
    $scope.user_accounts = response.data;
    console.log("Account");
    console.log(response.data);
  });

  //when user selects their account from drop down
  $scope.setAccountDetails = function() {
    if($scope.selectedAccount!=="" && $scope.selectedAccount !== undefined && $scope.selectedAccount!= null){
      var details = $filter('filter')($scope.user_accounts, {
        accountid: $scope.selectedAccount
      })[0];
      $scope.selectedAccountDetails = details;
      var addressList = [details.branchId.addressLine1,details.branchId.addressLine2,details.branchId.addressLine3]
      $scope.selectedAccountDetails.branchId.wholeAddress = addressList.join(', ');
    }
  }



});
