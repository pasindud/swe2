/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
  .controller('MerchantProfileController', function($http, $state, $rootScope, $scope, nnyConst) {
    AuthService.getRequest("/api/merchant", null, function(response) {
      $scope.MerchantInformation = response.data;
      console.log(response.data);
    });
  });