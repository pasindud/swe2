'use strict';
angular.module('banking')
.controller('FlagsController',function ($state,$rootScope,$scope,$http) {
  $http.get('SampleJSON/Flagged/flaggedList.json')
  .then(function(res) {
    $scope.AdminID = res.data[0].AdminID;
    $scope.FlaggedList = res.data[0].FlaggedList;
  })
});
