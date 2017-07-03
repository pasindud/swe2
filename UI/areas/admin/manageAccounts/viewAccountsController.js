'use strict';
angular.module('banking')
.controller('ViewAccountsController',function ($state,$rootScope,$scope,$http, AuthService) {
  AuthService.getRequest("/api/admin/all_acounts", null, function (response) {
    $scope.all_acounts = response.data;
  });    
});
