'use strict';
angular.module('banking')
.controller('UserAccountController',function ($state,$rootScope,$scope,$http,$stateParams) {
  var userID = $stateParams.userID;
  Materialize.updateTextFields();
  $scope.mode = "edit";
  debugger;
  if ($stateParams.userID === ""){
      $state.transitionTo("login");
  }
  else {
    $http.get('SampleJSON/Accounts/'+ userID +'.json')
    .then(function(res) {
      $scope.user = res.data[0];
      var stringDate = res.data[0].PersonalInformation.DOB.split("/");
      $scope.user.PersonalInformation.DOB = new Date(stringDate[2],stringDate[1]-1,stringDate[0]);
      debugger;
    });
  }
});
