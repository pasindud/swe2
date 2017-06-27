'use strict';
angular.module('banking')
.controller('LoginController',function ($state,$rootScope,$scope, AuthService, $location) {
   $scope.title = 'his';
   $rootScope.authData="";
   $scope.loginbtn = function () {
   		console.log($scope.upass);
      $('#LoadingModal').modal('open');
   		AuthService.getAuthToken($scope.username, $scope.upass).then(function () {
        if(AuthService.isLoggedin())
        {
            $state.go("account");
        }
        else {
            $('#username').addClass('invalid');
            $('#password').addClass('invalid');
            $('#ErrorModal').modal('open');
        }
        $scope.ErrorDialog = $rootScope.ErrorDialog;
        $('#LoadingModal').modal('close');
   		});
   }
});
