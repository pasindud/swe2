'use strict';
angular.module('banking')
.controller('UserAccountController',function ($state,$rootScope,$scope,$http,$stateParams,$filter, AuthService) {
  var userID = $stateParams.userID;
  Materialize.updateTextFields();
  $scope.mode = "edit";
  // debugger;
  if ($stateParams.userID === ""){
      $state.transitionTo("login");
  }

  AuthService.getRequest("/api/all_users", null, function (response) {
    $scope.all_users = response.data;
  });

});
