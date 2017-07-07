'use strict';
angular.module('banking')
.controller('FlagsController',function ($state,$rootScope,$scope,$http,AuthService,PaginationService,SortingService,SearchService, toastr) {

  //request to server
  //
  $scope.runana = function () {
    AuthService.getRequest("/api/admin/get_freq_amount", null, function(response) {
      toastr.success("Running Suspicious Activity Algorithms", 'Sucessful');
    });
  }

  AuthService.getRequest("/api/admin/get_suspicious_logs", null, function(response) {
    var responseData = response.data;

    //Handle error from server
    if (response.data.error) {
      $('#ErrorModal').modal('open');
      var errorContent = {
        Title: "Error",
        Body: [response.data.error]
      }
      $rootScope.ErrorDialog = errorContent;
    } else {
      $scope.Logs = response.data;
      handlePagination($scope.Logs); //View
    }

    //Keep Original Order for sorting purpose
    $scope.LogsOriginal = responseData; //View
  });

  var paginationObj = "";
  var pageNumbers = "";
  var maxRecordsPerPage = 10;
  $scope.CurrentPage = 1;

  //Pagination
  function handlePagination(recordSet) {
    paginationObj = PaginationService.pagination(recordSet, maxRecordsPerPage);
    $scope.Logs = paginationObj.pages[1]; //View
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

    var recordSet = $scope.LogsOriginal; //View Ori
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
    $scope.Logs = paginationObj.pages[toPage]; //View
    $('#LoadingModal').modal('open');
    var a = setTimeout(function () {
      $('#LoadingModal').modal('close');
    },1000);
  }

  //Search
   var searchFields = [];
   searchFields.push({id : 'id', name : 'Violation ID'});
   searchFields.push({id : 'message', name : 'Message'});
   searchFields.push({id : 'createdDate', name : 'Created Date'});
   $scope.SearchFields = searchFields;

   //default
   var searchDataObj = {SearchField : 'message', SearchValue : ''}
   $scope.SearchData = searchDataObj;

   $scope.$watchCollection('SearchData', function() {
     if($scope.SearchData!== undefined){
       if(($scope.SearchData.SearchValue!=="" )&&($scope.SearchData.SearchValue!==undefined)){
         var searchField = $scope.SearchData.SearchField;
         var searchValue = $scope.SearchData.SearchValue;
         var searchResult = SearchService.search($scope.LogsOriginal,searchField,searchValue); //View
         handlePagination(searchResult);
       }
       else {
         handlePagination($scope.LogsOriginal); //view
       }
     }

   });

});
