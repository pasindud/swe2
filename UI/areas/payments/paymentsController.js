'use strict';
angular.module('banking')
.controller('PaymentsController',function ($state,$rootScope,$scope,$http,$filter, AuthService) {

	// Dedup the code here
	AuthService.getRequest("/api/accounts?id="+$rootScope.authData.userId, null, function (response) {
		$scope.user_accounts = response.data;
	});

	AuthService.getRequest("/api/merchant_services", null, function (response) {
		$scope.merchant_services = response.data;
	});

	$scope.paybill = function () {

		var data = {
			amount : $scope.amount,
			billReferenceNumber : $scope.bill_reference_number,
			selectedServiceId : $scope.selectedService,
			selectedAccountId : $scope.selectedAccount
		}

		// TODO(pasindu): To be implmented.
		AuthService.getRequest("/api/merchant_services_pay_bill", data, function (response) {
			return response
		});

	}


	$scope.transfer = function () {
		var data = {
			amount : $scope.transfere_amount,
			toaccountid : $scope.transfere_toaccountid,
			fromaccountid : $scope.selectedTransfereAccount,
			message : $scope.transfere_message
		}
		console.log(data);

		AuthService.getRequest("/api/do_transaction", data, function (response) {
			return response
		});

	}

});
