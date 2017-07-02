'use strict';
angular.module('banking')
.controller('ViewAccountsController',function ($state,$rootScope,$scope,$http, AuthService) {

  AuthService.getRequest("/api/all_acounts", null, function (response) {
    $scope.all_acounts = response.data;
  });

  $http.get('SampleJSON/Accounts/UsersList.json')
  .then(function(res) {
    $scope.AdminID = res.data[0].AdminID;
    $scope.UsersList = res.data[0].UsersList;
    })
    
});
