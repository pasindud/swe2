'use strict';
angular.module('banking')
  .controller('ViewUsers', function($state, $rootScope, $scope, AuthService) {
	  AuthService.getRequest("/api/admin/all_users", null, function (response) {
	    $scope.all_users = response.data;
	  });
  });