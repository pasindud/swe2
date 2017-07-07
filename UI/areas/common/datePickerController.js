'use strict';
angular.module('banking')
  .controller('DatePickerController', function($scope, $rootScope) {
    var currentTime = new Date();
    $scope.onSet = function() {
      if ($scope.FormData !== undefined) {
        $rootScope.ca_formData_DOB = $scope.FormData.dob;
        $rootScope.ca_formData_AOD = $scope.FormData.account_open_date;
      }
    }

    $scope.currentTime = currentTime;
    $scope.month = $scope.labels.MonthLong,
    $scope.monthShort = $scope.labels.MonthShort;
    $scope.weekdaysFull = $scope.labels.WeekDaysFull;
    $scope.weekdaysLetter = ['S', 'M', 'T', 'W', 'T', 'F', 'S'];
    $scope.today = $scope.labels.Today;
    $scope.clear = $scope.labels.Clear;
    $scope.close = $scope.labels.Close;
    var days = 365 * 100;
    //$scope.minDate = (new Date($scope.currentTime.getTime() - (1000 * 60 * 60 * 24 * days))).toISOString();
    $scope.minDate = (new Date("01/01/1700")).toISOString();
    $scope.maxDate = (new Date($scope.currentTime.getTime() + (1000 * 60 * 60 * 24 * days))).toISOString();
    $scope.maxDateToday = (new Date($scope.currentTime.getTime())).toISOString();
  });
