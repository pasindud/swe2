/* Author : Dushan Galappaththi */
var nnyApp = angular.module('banking');
nnyApp.factory('PaginationService',['$http','nnyConst','$rootScope',function ($http,nnyConst,$rootScope) {
  //initialize pages array
  var pages = [""];
  var page = [""];
  //initialize pagination object


  function getPageCount(recordCount,maxPerPage) {
    if(recordCount < maxPerPage){
      return 1;
    }
    else
    {
      var modulusT = recordCount % maxPerPage;
      if(modulusT > 0){
        return ((recordCount / maxPerPage) + 1);
      }
      return (recordCount / maxPerPage);
    }
  }

  function getPageNumbers(pageCount) {

    var pages = [""];
    for(i = 1; i <= pageCount; i++ )
    {
      var pageNumberObj ={
        isCurrent : true,
        number : 1,
        lastNumber : 1,
      }
      if(i !== 1)
      {
        pageNumberObj.isCurrent = false;
      }else {
        pageNumberObj.isCurrent = true;
      }
      pageNumberObj.number = i;
      pageNumberObj.lasNumber = pageCount;
      pages.push(pageNumberObj);
    }
    //pages.splice(0, 1);
    return pages;
  }

  function getPagination(recordSet, maxPerPage) {
    var paginationObj = { pageCount : 1, recordCount : 1, pages : ""}
    loopCount = 1;
    page = [];
    pages = [""];
    angular.forEach(recordSet, function (value, key) {
        if(page.length < maxPerPage ){
          page.push(value);
          if(loopCount==recordSet.length){
            pages.push(page);
          }
        }
        else {
          pages.push(page);
          page = [];
          page.push(value);
          //page.splice(0, 1);
        }
        loopCount++;
    });

    paginationObj.pageCount = getPageCount(recordSet.length,maxPerPage);
    paginationObj.recordCount = recordSet.length;
    paginationObj.pages = pages;
    return paginationObj;
  }



  return  {
    pagination : function (recordSet, maxPerPage = 10) {
      return getPagination(recordSet, maxPerPage);
    },
    recordCount : function (recordSet) {
      return recordSet.length;
    },
    pageCount : function (recordSet, maxPerPage = 10) {
      return getPageCount(recordSet,maxPerPage);
    },
    lastPage : function (recordSet, maxPerPage = 10) {
      return getPageCount(recordSet,maxPerPage);
    },
    pageNumbers : function (pageCount) {
      return getPageNumbers(pageCount);
    }
  };
}]);
