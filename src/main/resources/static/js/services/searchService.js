/* Author : Dushan Galappaththi */
var nnyApp = angular.module('banking');
nnyApp.factory('SearchService', ['$filter', 'nnyConst', function($filter, nnyConst) {

  //Recursively build object
  function buildExpression(key,val)
  {
    debugger;
    var keyList = key.split(".");
    if(keyList.length>1)
    {
      var expression = {};
      roundKey = keyList[0];
      keyList.shift();
      expression[roundKey] = buildExpression(keyList.join("."),val);
    }else {
      var expression = {};
      expression[key] = val;
    }
    return expression;
  }

  //Search function
  function search(recordSet, searchField, value)
  {
    var expression = buildExpression(searchField,value);
    return $filter('filter')(recordSet, expression);
  }

  return {
    search: function(recordSet, searchField, value) {
      if(value!==""){
        return search(recordSet, searchField, value);
      }
      else{
        return recordSet;
      }
    }
  }
}]);
