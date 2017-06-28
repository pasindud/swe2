'use strict';
angular.module('banking')
.controller('CreateNewAccountController',function ($state,$rootScope,$scope, ValidateService, AuthService) {
  function inputFieldAnimate(id,status) {
    if(status){
      $("#"+id).addClass('valid');
      $("#"+id).removeClass('invalid');
    }else {
      $("#"+id).addClass('invalid');
      $("#"+id).removeClass('valid');
    }
  }

  $scope.submitBtn = function () {
    console.log("$scope.register");
    console.log($scope.FormData.username);
    console.log($scope.FormData.password);
    console.log($scope.FormData.first_name);
    console.log($scope.FormData.last_name);

    var errors = "";
    var isInvalidForm = false;
    var passwordVal = ValidateService.ValidatePassword($scope.FormData.password,$scope.FormData.confirmPassword);
    if(!passwordVal.status)
    {
      isInvalidForm = true;
      errors += " " + passwordVal.errorMsg;
    }
    inputFieldAnimate("password",passwordVal.status);
    inputFieldAnimate("confirm_password",passwordVal.status);
    
    if(isInvalidForm)
    {
      var errorContent = {
        Title : "Validation Error",
        Body : errors
      }
      $('#ErrorModal').modal('open');
      $rootScope.ErrorDialog = errorContent;
    }
    else
    {
      AuthService.getRequestPost("/api/registration", {
        users:{
          username : $scope.username,
          password : $scope.password
        }, customer:{
          first_name : $scope.first_name,
          last_name : $scope.last_name
        }}, function (response) {
          console.log(response);
        })
      }
    }
  });
