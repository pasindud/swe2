'use strict';
angular.module('banking')
.controller('CreateNewAccountController',function ($state,$rootScope,$scope, ValidateService) {
  $scope.ctrl = "aa";
  var errors = "";
  debugger;
  $scope.submitBtn = function () {
    var isInvalidForm = false;
    var passwordVal = ValidateService.ValidatePassword($scope.FormData.password,$scope.FormData.confirmPassword);
    if(!passwordVal.status)
    {
      isInvalidForm = true;
      $('#confirm_password').addClass('invalid');
      $('#password').addClass('invalid');
      errors += " " + passwordVal.errorMsg;
    }

    if(isInvalidForm)
    {
      var errorContent = {
        Title : "Validation Error",
        Body : errors
      }
      $('#ErrorModal').modal('open');
      $rootScope.ErrorDialog = errorContent;
    }
  }
});
