/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
  .controller('AdminController', function($state, $rootScope, $scope, AuthService,toastr) {
    //request to server
    //

   $scope.runana = function () {
      AuthService.getRequest("/api/admin/get_freq_amount", null, function(response) {
        toastr.success("Running Suspicious Activity Algorithms", 'Sucessful');
      });
    }

    AuthService.getRequest("/api/admin/get_suspicious_logs", null, function(response) {
      var responseData = response.data;
      $scope.Logs = "NOACTIVTY";
      //Handle error from server
      if (response.data.error) {
        $scope.Logs = "NOACTIVTY";
        $scope.noSuspicious = true;
      } else {
        if(response.data == null)
        {
          $scope.Logs = "NOACTIVTY";
          $scope.noSuspicious = true;
        }else {
          var len = responseData.length;
          if (len>3) {
            $scope.Logs = responseData.slice(-3);
          }else {
              $scope.Logs = responseData;
          }
          $scope.noSuspicious = false;
        }
      }
      //Keep Original Order for sorting purpose
      $scope.LogsOriginal = responseData; //View
    });

});
