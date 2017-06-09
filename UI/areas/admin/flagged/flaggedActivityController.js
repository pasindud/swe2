'use strict';
angular.module('banking')
.controller('FlaggedActivityController',function ($state,$rootScope,$scope,$stateParams,$http) {
  var flagid = $stateParams.flagID;
  if ($stateParams.flagID === ""){
      $state.transitionTo("login");
  }
  else {
    $http.get('SampleJSON/Flagged/'+ flagid +'.json')
    .then(function(res) {
      $scope.flag = res.data[0];
    });
  }
});
