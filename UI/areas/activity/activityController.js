'use strict';
angular.module('banking')
.controller('ActivityController',function ($state,$rootScope,$scope,$http, $stateParams, AuthService) {
	var transactionId = $stateParams.transactionId;
    
	AuthService.getRequest("/api/transactions?id=" + transactionId, null, function (response) {
		$scope.Transactions = response.data;
	});

});
