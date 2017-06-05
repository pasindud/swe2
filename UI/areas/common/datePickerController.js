'use strict';
angular.module('banking')
.controller('DatePickerController',function ($state,$rootScope,$scope) {
  var currentTime = new Date();
  $scope.currentTime = currentTime;
  $scope.month = $scope.labels.MonthLong,
  $scope.monthShort = $scope.labels.MonthShort;
  $scope.weekdaysFull = $scope.labels.WeekDaysFull;
  $scope.weekdaysLetter = ['S', 'M', 'T', 'W', 'T', 'F', 'S'];
  //$scope.disable = [false, 0, 8];
  $scope.today = $scope.labels.Today;
  $scope.clear = $scope.labels.Clear;
  $scope.close = $scope.labels.Close;
  var days = 365;
  $scope.minDate = (new Date($scope.currentTime.getTime() - ( 1000 * 60 * 60 *24 * days ))).toISOString();
  $scope.maxDate = (new Date($scope.currentTime.getTime() + ( 1000 * 60 * 60 *24 * days ))).toISOString();
  $scope.maxDateToday = (new Date($scope.currentTime.getTime())).toISOString();
  $scope.onStart = function () {
    console.log('onStart');
  };
  $scope.onRender = function () {
    console.log('onRender');
  };
  $scope.onOpen = function () {
    console.log('onOpen');
  };
  $scope.onClose = function () {
    console.log('onClose');
  };
  $scope.onSet = function () {
    console.log('onSet');
  };
  $scope.onStop = function () {
    console.log('onStop');
  };
});
