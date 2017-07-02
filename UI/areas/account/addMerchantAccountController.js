/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
.controller('AddMerchantAccountController',function ($state,$rootScope,$scope,$http, $stateParams, AuthService) {

  $scope.submitBtn = function (res) {
    $('#LoadingModal').modal('open');
    //TODO
    
    $('#LoadingModal').modal('close');
  }

});
