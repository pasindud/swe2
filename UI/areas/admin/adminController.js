/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
.controller('AdminController', function($state, $rootScope, $scope, AuthService) {
  //request to server
  AuthService.getRequest("/api/admin/get_suspicious_logs", null, function(response) {
    var responseData = response.data;
    $scope.Logs = "NOACTIVTY";
    //Handle error from server
    if (response.data.error) {
      $scope.Logs = "NOACTIVTY";
      $scope.noSuspicious = true;
    }
    else
    {
      if(response.data == null)
      {
        $scope.Logs = "NOACTIVTY";
        $scope.noSuspicious = true;
      }
      else
      {
        var len = responseData.length;
        console.log(len);
        if (len>3)
        {
          $scope.Logs = responseData.slice(-3);
        }
        else if(len == 0){
          $scope.noSuspicious = true;
        }
        else{
          $scope.Logs = responseData;
          $scope.noSuspicious = false;
        }

      }
      console.log($scope.noSuspicious);
    }
    console.log($scope.noSuspicious);
    //Keep Original Order for sorting purpose
    $scope.LogsOriginal = responseData; //View
  });

});
