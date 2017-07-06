/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
  .controller('ActivityController', function($state, $rootScope, $scope, $http, $stateParams, AuthService, PaginationService, SortingService, SearchService) {

    var transactionId = $stateParams.transactionId;
    if (!transactionId) {
      $state.go("account");
      return;
    };
    AuthService.getRequest("/api/transactions?id=" + transactionId, null, function(response) {
      var responseData = response.data;
      console.log(responseData);

      //Handle error from server
      if (response.data.error) {
        $('#ErrorModal').modal('open');
        var errorContent = {
          Title: "Transactions Error",
          Body: [response.data.error]
        }
        $rootScope.ErrorDialog = errorContent;
      } else {
        $scope.Transactions = response.data;
        handlePagination($scope.Transactions);
      }

      //Keep Original Order for sorting purpose
      $scope.TransactionsOriginal = responseData; //View

    });

    var paginationObj = "";
    var pageNumbers = "";
    var maxRecordsPerPage = 10;
    $scope.CurrentPage = 1;

    //Pagination
    function handlePagination(recordSet) {
      paginationObj = PaginationService.pagination(recordSet, maxRecordsPerPage);
      $scope.Transactions = paginationObj.pages[1]; //View
      pageNumbers = PaginationService.pageNumbers(paginationObj.pageCount);
      $scope.PageNumbers = pageNumbers;
    }

    //Sorting Button
    var fromField ="";
    var isDsc= false;
    $scope.sortBtn = function(field, ascending) {
      debugger;
      //Determine whether its descending or ascending
      if(fromField === field)
      {
        if(isDsc){
          isDsc = false;
        }else {
          isDsc = true;
        }
      }else {
        fromField = field;
        isDsc = false;
      }

      var recordSet = $scope.TransactionsOriginal; //View Ori
      var sortedRecordSet = SortingService.sortObjBy(recordSet, field, isDsc);
      $scope.sortedRecordSet = sortedRecordSet;
      handlePagination(sortedRecordSet);
    }

    //Pagination button
    $scope.pageBtn = function(toPage, fromPage) {
      if (typeof toPage == "undefined" || typeof fromPage == "undefined") {
        return;
      }
      //reset Pages
      for(i=1;i<pageNumbers.length;i++)
      { //skip 1
        pageNumbers[i].isCurrent = false;
      }

      pageNumbers[toPage].isCurrent = true;
      $scope.CurrentPage = toPage;
      $scope.PageNumbers = pageNumbers;
      $scope.Transactions = paginationObj.pages[toPage]; //View
      $('#LoadingModal').modal('open');
      var a = setTimeout(function () {
        $('#LoadingModal').modal('close');
      },1000);
    }

    //View more
    $scope.viewDetailsBtn = function(transactionId) {
      $state.go('transaction', {
        transactionId: transactionId
      });
    }

    //Search
    //TODO add proper search fields
     var searchFields = [];
     searchFields.push({id : 'username', name : 'Username'});
     searchFields.push({id : 'userId', name : 'User ID'});
     $scope.SearchFields = searchFields;

     //default
     var searchDataObj = {SearchField : 'username', SearchValue : ''}
     $scope.SearchData = searchDataObj;

     $scope.$watchCollection('SearchData', function() {
       if($scope.SearchData!== undefined){
         if(($scope.SearchData.SearchValue!=="" )&&($scope.SearchData.SearchValue!==undefined)){
           var searchField = $scope.SearchData.SearchField;
           var searchValue = $scope.SearchData.SearchValue;
           var searchResult = SearchService.search($scope.TransactionsOriginal,searchField,searchValue);
           handlePagination(searchResult);
         }
       }

     });


  });
