/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
.controller('ExchangeRatesController', function($state, $rootScope, $scope,AuthService) {
  var baseCode = "USD";
  var rates = [];
  var cmbRates= []; //combo box
  debugger;
  $scope.BaseCode = baseCode;
  retrieveData(baseCode);

  function retrieveData(base)
  {
    $('#LoadingModal').modal('open');
    AuthService.getExternal("http://api.fixer.io/latest?base="+base, null, function(response) {
      console.log(response.data);
      angular.forEach(response.data.rates,function (value, key) {
        rates.push({currencyCode : key,rate : value});
        cmbRates.push(key);
      });
      $scope.Rates = rates;
      $scope.CMBRates = cmbRates;
      $scope.resultBase = response.data.base;
      $scope.resultDate = response.data.date;
      $('#LoadingModal').modal('close');
    });
  }


  $scope.$watch('BaseCode', function() {
    debugger;
    baseCode = $scope.BaseCode;
    if(baseCode !== "" && baseCode !== undefined && baseCode !== null ){
      rates = [];
      cmbRates= [];
      retrieveData(baseCode);
    }else {

    }
  });
});
