'use strict';
angular.module('banking')
.controller('ActivityController',function ($state,$rootScope,$scope,$http) {
      $http.get('SampleJSON/Activity/Activity1.json')
      .then(function(res) {
        $scope.UserID = res.data[0].UserID;
        $scope.Transactions = res.data[0].Transactions;
      })
});
