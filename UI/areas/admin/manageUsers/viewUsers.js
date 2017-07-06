'use strict';
angular.module('banking')
.controller('ViewUsers', function($state, $rootScope, $scope, AuthService, PaginationService, SortingService, SearchService, toastr)
    {

      AuthService.getRequest("/api/admin/all_users", null, function (response) {
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
          $scope.AllUsers = response.data; //view
          handlePagination($scope.AllUsers);
        }

        //Keep Original Order for sorting purpose
        $scope.AllUsersOriginal = responseData; //View

      });

    $scope.lockUser = function (lock, userId) {
        AuthService.getRequest("/api/admin/change_user_status?user_id="+ 
          userId + "&lock=" +lock, null, function (response) {
            toastr.success("User lock toggled", 'Sucessful');
            for (var i = $scope.AllUsers.length - 1; i >= 0; i--) {
              if ($scope.AllUsers[i].userId == userId) {
                $scope.AllUsers[i].locked = lock;
                break;
              };
            };
        });
    }

    var paginationObj = "";
    var pageNumbers = "";
    var maxRecordsPerPage = 10;
    $scope.CurrentPage = 1;

    //Pagination
    function handlePagination(recordSet) {
      paginationObj = PaginationService.pagination(recordSet, maxRecordsPerPage);
      $scope.AllUsers = paginationObj.pages[1]; //View
      pageNumbers = PaginationService.pageNumbers(paginationObj.pageCount);
      $scope.PageNumbers = pageNumbers;
    }

    //Pagination button
    $scope.pageBtn = function(toPage, fromPage) {
      if (typeof toPage == "undefined" || typeof fromPage == "undefined") {
        return;
      }
      debugger;

      //reset Pages
      for(i=1;i<pageNumbers.length;i++)
      { //skip 1
        pageNumbers[i].isCurrent = false;
      }

      pageNumbers[toPage].isCurrent = true;
      $scope.CurrentPage = toPage;
      $scope.PageNumbers = pageNumbers;
      $scope.AllUsers = paginationObj.pages[toPage]; //View
      $('#LoadingModal').modal('open');
      var a = setTimeout(function () {
        $('#LoadingModal').modal('close');
      },1000);
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

      var recordSet = $scope.AllUsersOriginal;
      var sortedRecordSet = SortingService.sortObjBy(recordSet, field, isDsc);
      $scope.sortedRecordSet = sortedRecordSet;
      handlePagination(sortedRecordSet);
    }

   //Search
    var searchFields = [];
    searchFields.push({id : 'username', name : 'Username'});
    searchFields.push({id : 'userId', name : 'User ID'});
    searchFields.push({id : 'customer.firstName', name : 'First Name'});
    searchFields.push({id : 'customer.lastName', name : 'Last Name'});
    searchFields.push({id : 'creationDate', name : 'Creation Date'});
    searchFields.push({id : 'customer.nic', name : 'NIC'});
    searchFields.push({id : 'customer.email', name : 'e-Mail'});
    $scope.SearchFields = searchFields;

    //default
    var searchDataObj = {SearchField : 'username', SearchValue : ''}
    $scope.SearchData = searchDataObj;

    $scope.$watchCollection('SearchData', function() {
      if($scope.SearchData!== undefined){
        if(($scope.SearchData.SearchValue!=="" )|| ($scope.SearchData.SearchValue!==undefined)){
          var searchField = $scope.SearchData.SearchField;
          var searchValue = $scope.SearchData.SearchValue;
          var searchResult = SearchService.search($scope.AllUsersOriginal,searchField,searchValue);
          handlePagination(searchResult);
        }
      }

    });

});

/*AuthService.getRequest("/api/admin/all_users", null, function (response) {
$scope.users_table = new NgTableParams({}, { dataset: response.data});
});*/
