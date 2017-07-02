'use strict';
angular.module('banking')
.controller('UserAccountController',function ($state,$rootScope,$scope,$http,$stateParams,$filter, AuthService) {
  var userID = $stateParams.account_list;
  console.log($stateParams.account_list);
});
