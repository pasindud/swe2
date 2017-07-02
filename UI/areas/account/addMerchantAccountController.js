/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
.controller('AddMerchantAccountController',function ($state,$rootScope,$scope,$http, $stateParams, AuthService, validateService) {
  var isInvalidForm = false;
  var errors = [""];
  function inputFieldAnimate(id,status) {
    if(status){
      $("#"+id).addClass('valid');
      $("#"+id).removeClass('invalid');
    }else {
      $("#"+id).addClass('invalid');
      $("#"+id).removeClass('valid');
    }
  }

  function formValidate(field) {
    if(!field.status)
    {
      isInvalidForm = true;
      errors.push(field.errorMsg);
    }
  }

  $scope.submitBtn = function (res) {
    $('#LoadingModal').modal('open');

    if($scope.AddMerchantAccountForm !== undefined){
      var FormData =$scope.AddMerchantAccountForm;
      errors = [""];
      isInvalidForm = false;

      //var orgNameVal = ValidateService.


    }else{
          $('#ErrorModal').modal('open');
          errors.push("Missing Values"," ");
          var errorContent = {
            Title : "Validation Error",
            Body : errors
          }
          $rootScope.ErrorDialog= errorContent;
    }
    $('#LoadingModal').modal('close');
  }

});
