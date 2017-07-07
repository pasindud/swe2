'use strict';
angular.module('banking')
.controller('ApprovalsController',function ($state,$rootScope,$scope,$http) {
  $http.get('SampleJSON/Approvals/approvalsList.json')
  .then(function(res) {
    $scope.AdminID = res.data[0].AdminID;
    $scope.ApprovalsList = res.data[0].ApprovalsList;
  })
});
