'use strict';
angular.module('banking')
.controller('ApprovalDetailsController',function ($state,$rootScope,$scope,$stateParams,$http) {
  var approvalID = $stateParams.approvalID;
  debugger;
  if ($stateParams.approvalID === ""){
      $state.transitionTo("login");
  }
  else {
    $http.get('SampleJSON/Approvals/'+ approvalID +'.json')
    .then(function(res) {
      $scope.approval = res.data[0];
    });
  }
});
