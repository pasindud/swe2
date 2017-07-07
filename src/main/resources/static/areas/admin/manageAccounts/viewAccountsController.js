'use strict';
app.controller('ViewAccountsController',function ($state,$rootScope,$scope,$http, AuthService, PaginationService, SortingService, SearchService) {
  AuthService.getRequest("/api/admin/all_acounts", null, function (response) {
	   //$scope.accounts_table = new NgTableParams({}, { dataset: response.data});
     var responseData = response.data;

     //Handle error from server
     if (response.data.error) {
       $('#ErrorModal').modal('open');
       var errorContent = {
         Title: "View All Accounts Error",
         Body: [response.data.error]
       }
       $rootScope.ErrorDialog = errorContent;
     } else {
       $scope.Accounts = response.data;
       handlePagination($scope.Accounts); //View
     }

     //Keep Original Order for sorting purpose
     $scope.AccountsOriginal = responseData; //View
  });


  var paginationObj = "";
  var pageNumbers = "";
  var maxRecordsPerPage = 10;
  $scope.CurrentPage = 1;

  //Pagination
  function handlePagination(recordSet) {
    paginationObj = PaginationService.pagination(recordSet, maxRecordsPerPage);
    $scope.Accounts = paginationObj.pages[1]; //View
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

    var recordSet = $scope.AccountsOriginal; //View Ori
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
    $scope.Accounts = paginationObj.pages[toPage]; //View
    $('#LoadingModal').modal('open');
    var a = setTimeout(function () {
      $('#LoadingModal').modal('close');
    },1000);
  }

  //Search
  //TODO add proper search fields
   var searchFields = [];
   searchFields.push({id : 'accountid', name : 'Account ID'});
   searchFields.push({id : 'createdDate', name : 'Created Date'});
   searchFields.push({id : 'currency', name : 'Currency'});
   searchFields.push({id : 'expireDate', name : 'Expire Date'});
   $scope.SearchFields = searchFields;

   //default
   var searchDataObj = {SearchField : 'accountid', SearchValue : ''}
   $scope.SearchData = searchDataObj;

   $scope.$watchCollection('SearchData', function() {
     if($scope.SearchData!== undefined){
       if(($scope.SearchData.SearchValue!=="" )&&($scope.SearchData.SearchValue!==undefined)){
         var searchField = $scope.SearchData.SearchField;
         var searchValue = $scope.SearchData.SearchValue;
         var searchResult = SearchService.search($scope.AccountsOriginal,searchField,searchValue); //View
         handlePagination(searchResult);
       }
       else {
         handlePagination($scope.AccountsOriginal); //view
       }
     }

   });

   //View more
   /*
   $scope.viewDetailsBtn = function(transactionId) {
     $state.go('', {
       id: transactionId
     });
   }
   */

});
