/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
  .controller('TransactionController', function($state, $rootScope, $scope, $http, $stateParams, AuthService) {
    var transactionId = $stateParams.transactionId;
    AuthService.getRequest("/api/transaction_by_id?id=" + transactionId, null, function(response) {
	    if (response.data.error) {
	      $('#ErrorModal').modal('open');
	      var errorContent = {
	        Title: "Transaction Error",
	        Body: ["No transaction found"]
	      }
	      $rootScope.ErrorDialog = errorContent;
	    } else {
	  	  $scope.TransactionDetails = response.data;  	
	    }
    });
  });