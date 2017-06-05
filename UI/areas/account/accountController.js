'use strict';
angular.module('banking')
.controller('AccountController',function ($state,$rootScope,$scope,$http) {
  $http.get('SampleJSON/Accounts/Account1.json')
  .then(function(res) {
    $scope.UserID = res.data[0].UserID;
    $scope.PersonalInformation = res.data[0].PersonalInformation;
    $scope.Accounts = res.data[0].Accounts;
  })
});
