'use strict';
angular.module('banking')
.controller('LoginController',function ($state,$rootScope,$scope, AuthService, $location,nnyConst) {
   $scope.title = 'his';
   $rootScope.authData="";
   
  if(AuthService.isLoggedin()) {
      console.log($rootScope.authData);
      if($rootScope.authData.accessLevel == nnyConst.UserRoles.Admin) {
        $state.go("admin");
      } else {
        $state.go("account");
        console.log("reide");
      }
  }

   $scope.loginbtn = function () {
   		console.log($scope.upass);
      $('#LoadingModal').modal('open');
   		AuthService.getAuthToken($scope.username, $scope.upass, function  (response) {
        console.log("Check logging");
        if(AuthService.isLoggedin()) {
          if($rootScope.authData.accessLevel == nnyConst.UserRoles.Admin) {
            $state.go("admin");
          } else {
            $state.go("account");
          }
        } else {
            $('#username').addClass('invalid');
            $('#password').addClass('invalid');
            $('#ErrorModal').modal('open');
        }
        $scope.ErrorDialog = $rootScope.ErrorDialog;
        $('#LoadingModal').modal('close');
      })
   }
});
