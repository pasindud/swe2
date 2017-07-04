'use strict';
app.controller('ViewAccountsController',function ($state,$rootScope,$scope,$http, AuthService, NgTableParams) {
  AuthService.getRequest("/api/admin/all_acounts", null, function (response) {
    // $scope.all_acounts = response.data;
    console.log("ads");
	$scope.accounts_table = new NgTableParams({}, { dataset: response.data});
  });    


//   var self = this;
// var data = [{name: "Moroni", age: 50} /*,*/];
// self.tableParams = new NgTableParams({}, { dataset: data});
	

});
