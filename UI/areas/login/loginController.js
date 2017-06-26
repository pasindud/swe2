'use strict';
angular.module('banking')
.controller('LoginController',function ($state,$rootScope,$scope, AuthService, $location) {
   $scope.title = 'his';
   $rootScope.authData="";
   $scope.loginbtn = function () {
   		console.log($scope.upass);
   		AuthService.getAuthToken($scope.username, $scope.upass);
   		  $state.go("account");


   }
});
