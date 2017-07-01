'use strict';
angular.module('banking')
.controller('UserProfileController',function ($state,$rootScope,$scope,$http,$filter, AuthService) {

  AuthService.getRequest("/api/customer", null, function (response) {
    $scope.PersonalInformation = response.data;
    console.log(response.data);
  });

});