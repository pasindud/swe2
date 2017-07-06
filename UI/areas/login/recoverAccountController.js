/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
.controller('RecoverAccountController', function($state, $rootScope, $scope, $http, $stateParams, AuthService, toastr) {

	$scope.showusername = false;
	$scope.showsecurityquestions = true;
	$scope.showpassword = true;

	$scope.recoverbtn = function () {

		if (!$scope.showusername) {
			AuthService.getRequest("/api/user_questions?userName="+$scope.username, null, function(response) {
				console.log(response.data);
				$scope.questionslist = response.data; 

				$scope.showusername = true;
				$scope.showsecurityquestions = false;
			});
		} else if (!$scope.showsecurityquestions) {
			$scope.showsecurityquestions = true;
			$scope.showpassword = false;
		} else if (!$scope.showpassword) {

			var data = {
				username : $scope.username,
				password : $scope.password,
				answers : [
				{
					"securityQuestions" : {
						"id": $scope.FormData.question1
					},
					"answer":$scope.FormData.answer1
				},
				{
					"securityQuestions" : {
						"id": $scope.FormData.question2
					},
					"answer": $scope.FormData.answer2
				}
				]};

			$scope.showusername = true;
			$scope.showsecurityquestions = true;
			$scope.showpassword = true;

			AuthService.getRequest("/api/forgot_password", data, function(response) {
				if (response.data.errors != undefined && response.data.errors.length != 0) {
			         $('#ErrorModal').modal('open');
			         var errorContent = {
			           Title:  "Error recovering password.",
			           Body: response.data.errors
			         }
			         $rootScope.ErrorDialog = errorContent;
			      } else {
			        toastr.success("Account has been recoved.", 'Sucessful');
			      }
			});
		}
	}
});