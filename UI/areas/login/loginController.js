'use strict';
angular.module('banking')
.controller('LoginController',function ($state,$rootScope,$scope, AuthService, $location) {
   $scope.title = 'his';

   $scope.loginbtn = function () {
   		console.log($scope.upass);
   		AuthService.getAuthToken($scope.username, $scope.upass);
        $location.path( "/account" );
   }
});
