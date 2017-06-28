'use strict';
angular.module('banking')
.controller('PaymentsController',function ($state,$rootScope,$scope,$http,$filter, AuthService) {

	AuthService.getRequest("/api/merchant_services", null, function (response) {
		$scope.merchant_services = response.data;
	});

	$scope.paybill = function () {

		var data = {
			amount : $scope.amount,
			billReferenceNumber : $scope.bill_reference_number,
			selectedServiceId : $scope.selectedService
		}

		return

		// TODO(pasindu): To be implmented.
		AuthService.getRequestPost("/api/merchant_services", data, function (response) {
			return response
		});

	}
});
