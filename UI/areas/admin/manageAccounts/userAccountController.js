'use strict';
angular.module('banking')
.controller('UserAccountController',function ($state,$rootScope,$scope,$http,$stateParams,$filter, AuthService) {
  var userAccountid = $stateParams.accountid;

  AuthService.getRequest("/api/admin/account_id?id" + userAccountid, null, function (response) {
    $scope.select_account = response.data;
  }); 
});
