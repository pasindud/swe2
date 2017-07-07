'use strict';
angular.module('banking')
.controller('UserAccountController',function ($state,$rootScope,$scope,$http,$stateParams,$filter, AuthService) {
  var userAccountid = $stateParams.accountid;
  var userId;
  $scope.mode = "read";
  $scope.PersonalInformation;
  $scope.selectedAccountDetails;

  AuthService.getRequest("/api/admin/account_id?id=" + userAccountid, null, function (response) {
    $scope.selectedAccountDetails = response.data.account;
    $scope.PersonalInformation = response.data.user.customer;
    $scope.PersonalInformation.userObj = response.data.user;
    var addressList = [$scope.PersonalInformation.addressLine1,$scope.PersonalInformation.addressLine2,$scope.PersonalInformation.addressLine3];
    $scope.PersonalInformation.wholeAddress = addressList.join(', ');
  });

  $scope.btnEdit=function () {
    $scope.mode = "edit";
  }
  $scope.saveUserInfo=function () {
    $scope.mode = "read";
  }


/*  AuthService.getRequest("/api/admin/user_by_id?id=" + userId, null, function (response) {
    $scope.PersonalInformation = response.data;
  });*/




});
