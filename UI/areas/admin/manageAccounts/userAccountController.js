'use strict';
angular.module('banking')
.controller('UserAccountController',function ($state,$rootScope,$scope,$http,$stateParams,$filter, AuthService) {
  var userID = $stateParams.accountid;
  console.log($stateParams.accountid);
});
