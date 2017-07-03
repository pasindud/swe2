'use strict';
angular.module('banking')
  .controller('AccountController', function($state, $rootScope, $scope, $http, $filter, AuthService) {

    AuthService.getRequest("/api/accounts?id=" + $rootScope.authData.userId, null, function(response) {
      $scope.user_accounts = response.data;
      console.log(response.data);
    });


    $http.get('SampleJSON/Accounts/Account1.json')
      .then(function(res) {
        $scope.UserID = res.data[0].UserID;
        $scope.PersonalInformation = res.data[0].PersonalInformation;
        $scope.Accounts = res.data[0].Accounts;
        var selectedAccountDetails = {
          branchId: {
            branchName: "N/A"
          },
          accTypeId: {
            accName: "N/A",
            accInterestRates: "N/A"
          },
          createdDate: "N/A",
          balance: "N/A",
          loan: "N/A",
          Interest: "N/A"
        }
        $scope.selectedAccountDetails = selectedAccountDetails;
        $scope.setAccountDetails = function() {
          var details = $filter('filter')($scope.user_accounts, {
            accountid: $scope.selectedAccount
          })[0];
          console.log(details);
          $scope.selectedAccountDetails = details;
        }
      })
  });