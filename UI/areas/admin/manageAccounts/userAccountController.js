'use strict';
angular.module('banking')
.controller('UserAccountController',function ($state,$rootScope,$scope,$http,$stateParams,$filter, AuthService) {
  var userID = $stateParams.userID;
  Materialize.updateTextFields();
  $scope.mode = "edit";
  // debugger;
  if ($stateParams.userID === ""){
      $state.transitionTo("login");
  }

  AuthService.getRequest("/api/all_users", null, function (response) {
    $scope.all_users = response.data;
  });

  else {
    $http.get('SampleJSON/Accounts/'+ userID +'.json')
    .then(function(res) {
      $scope.user = res.data[0];
      var stringDate = res.data[0].PersonalInformation.DOB.split("/");
      $scope.user.PersonalInformation.DOB = new Date(stringDate[2],stringDate[1]-1,stringDate[0]);

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
        var details = $filter('filter')($scope.user.Accounts,{AccountNumber : $scope.selectedAccount},true)[0];
        $scope.selectedAccountDetails.branch = details.Branch;
        $scope.selectedAccountDetails.openDate = details.OpenDate;
        $scope.selectedAccountDetails.accountType = details.AcccountType;
        $scope.selectedAccountDetails.balance = details.Balance;
        $scope.selectedAccountDetails.loan = details.loan;
        $scope.selectedAccountDetails.Interest = details.Interest;

      }

      debugger;
    });
  }
});
