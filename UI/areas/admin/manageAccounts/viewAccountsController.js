'use strict';
app.controller('ViewAccountsController',function ($state,$rootScope,$scope,$http, AuthService, NgTableParams) {
  AuthService.getRequest("/api/admin/all_acounts", null, function (response) {
	$scope.accounts_table = new NgTableParams({}, { dataset: response.data});
  });
});
