'use strict';
angular.module('banking')
  .controller('ViewUsers', function($state, $rootScope, $scope, AuthService, NgTableParams) {
	  AuthService.getRequest("/api/admin/all_users", null, function (response) {
	    $scope.users_table = new NgTableParams({}, { dataset: response.data});
	  });
  });