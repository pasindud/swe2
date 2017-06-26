'use strict';
angular.module('banking')
.controller('AccountController',function ($state,$rootScope,$scope,$http,$filter, AuthService) {

  AuthService.getRequest("/api/accounts?id="+$rootScope.authData.userId, null, function (response) {
    $scope.user_accounts = response.data;
  });

  $http.get('SampleJSON/Accounts/Account1.json')
  .then(function(res) {
    $scope.UserID = res.data[0].UserID;
    $scope.PersonalInformation = res.data[0].PersonalInformation;
    $scope.Accounts = res.data[0].Accounts;
    var selectedAccountDetails = {
      branch : "N/A",
      openDate : "N/A",
      accountType : "N/A",
      balance : "N/A",
      loan : "N/A",
      Interest : "N/A"
    }
    $scope.selectedAccountDetails = selectedAccountDetails;
    $scope.setAccountDetails = function () {
      var details = $filter('filter')($scope.Accounts,{AccountNumber : $scope.selectedAccount},true)[0];
      $scope.selectedAccountDetails.branch = details.Branch;
      $scope.selectedAccountDetails.openDate = details.OpenDate;
      $scope.selectedAccountDetails.accountType = details.AcccountType;
      $scope.selectedAccountDetails.balance = details.Balance;
      $scope.selectedAccountDetails.loan = details.loan;
      $scope.selectedAccountDetails.Interest = details.Interest;

    }
  })
});
