/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
.controller('RecoverAccountController', function($state, $rootScope, $scope, $http, $stateParams, AuthService) {

	AuthService.getRequest("/api/ui_all_questions", null, function(response) {
		console.log(response.data);
		$scope.questionslist = response.data; 
	});

	$scope.recoverbtn = function () {

		var data = {
			username : $scope.username,
			answers : [
			{
				"id":$scope.FormData.question1,
				"answer":$scope.FormData.answer1
			},
			{
				"id":$scope.FormData.question2,
				"answer": $scope.FormData.answer2
			}
			]};

		}

	});