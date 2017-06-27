'use strict';
angular.module('banking')
.controller('CreateNewAccountController',function ($state,$rootScope,$scope, AuthService) {
    var vm = this;
    $scope.firstname = "";

	$scope.register = function () {
		console.log("$scope.register");
		console.log($scope.username);
		console.log($scope.password);
		console.log($scope.first_name);
		console.log($scope.last_name);

		AuthService.getRequestPost("/api/registration", {
			users:{
				username : $scope.username,
				password : $scope.password
			}, customer:{
				first_name : $scope.first_name,
				last_name : $scope.last_name
			}}, function (response) {
			console.log(response);
		})
	}
});
