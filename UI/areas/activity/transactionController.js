/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
.controller('TransactionController',function ($state,$rootScope,$scope,$http, $stateParams, AuthService) {
  var transactionId = $stateParams.transactionId;

	AuthService.getRequest("/api/transactions?id=" + transactionId, null, function (response) {
		$scope.Transactions = response.data;
	});

});
