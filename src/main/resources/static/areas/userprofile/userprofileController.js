'use strict';
angular.module('banking')
  .controller('UserProfileController', function($state, $rootScope, $scope, $http, $filter, AuthService) {

    AuthService.getRequest("/api/customer", null, function(response) {
      var responseData = response.data;
      $scope.PersonalInformation = responseData;
      var addressList = [responseData.addressLine1,responseData.addressLine2,responseData.addressLine3];
      $scope.PersonalInformation.wholeAddress = addressList.join(', ');
      console.log(response.data);
    });

  });
