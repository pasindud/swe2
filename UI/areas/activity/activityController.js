/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
.controller('ActivityController',function ($state,$rootScope,$scope,$http, $stateParams, AuthService, PaginationService, SortingService) {
	/*var transactionId = $stateParams.transactionId;

	AuthService.getRequest("/api/transactions?id=" + transactionId, null, function (response) {
	$scope.Transactions = response.data;
});*/
var paginationObj = "";
var pageNumbers = "";
$scope.CurrentPage = 1;

function handlePagination(recordSet)
{
	paginationObj = PaginationService.pagination(recordSet,10);
	$scope.Transactions = paginationObj.pages[1];
	pageNumbers = PaginationService.pageNumbers(paginationObj.pageCount);
	$scope.PageNumbers = pageNumbers;
}
//sample data
//TODO replace this
$http.get('SampleJSON/Activity/Activity1.json')
.then(function(res) {
	$scope.UserID = res.data[0].UserID;
	$scope.TransactionsOriginal = res.data[0].Transactions;
	handlePagination($scope.TransactionsOriginal);
});

//Sorting Button
$scope.sortBtn = function (field,ascending) {
	var recordSet = $scope.TransactionsOriginal;
	var sortedRecordSet = SortingService.sortObjBy(recordSet, field);
	handlePagination(sortedRecordSet);
}

//Pagination button
$scope.pageBtn = function (toPage, fromPage) {
	pageNumbers[fromPage].isCurrent = false;
	pageNumbers[toPage].isCurrent = true;
	$scope.CurrentPage = toPage;
	$scope.PageNumbers = pageNumbers;
	$scope.Transactions = paginationObj.pages[toPage];
}

//View more
$scope.viewDetailsBtn = function (transactionId) {
	$state.go('transaction',{transactionId : transactionId});
}


});
