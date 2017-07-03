/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
  .controller('AddMerchantAccountController', function($state, $rootScope, $scope, $http, $stateParams, AuthService, ValidateService) {
    var isInvalidForm = false;
    var errors = [""];

    function inputFieldAnimate(id, status) {
      if (status) {
        $("#" + id).addClass('valid');
        $("#" + id).removeClass('invalid');
      } else {
        $("#" + id).addClass('invalid');
        $("#" + id).removeClass('valid');
      }
    }

    function formValidate(field) {
      if (!field.status) {
        isInvalidForm = true;
        errors.push(field.errorMsg);
      }
    }

    $scope.submitBtn = function(res) {
      $('#LoadingModal').modal('open');
      debugger;
      if ($scope.AddMerchantAccountForm !== undefined) {
        var FormData = $scope.AddMerchantAccountForm;
        errors = [""];
        isInvalidForm = false;

        var orgNameVal = ValidateService.ValidateOrgName(FormData.org_name);
        var regNoVal = ValidateService.ValidateRegNo(FormData.reg_no);
        var taxNoVal = ValidateService.ValidateTaxNo(FormData.tax_no);
        var serviceNameVal = ValidateService.ValidateServiceName(FormData.service_name);
        var descVal = ValidateService.ValidateDesc(FormData.desc);

        formValidate(orgNameVal);
        formValidate(regNoVal);
        formValidate(taxNoVal);
        formValidate(serviceNameVal);
        formValidate(descVal);

        inputFieldAnimate("org_name", orgNameVal.status);
        inputFieldAnimate("reg_no", regNoVal.status);
        inputFieldAnimate("tax_no", taxNoVal.status);
        inputFieldAnimate("service_name", serviceNameVal.status);
        inputFieldAnimate("desc", descVal.status);
        if (isInvalidForm) {
          var errorContent = {
            Title: "Validation Error",
            Body: errors
          }
          $('#ErrorModal').modal('open');
          $rootScope.ErrorDialog = errorContent;
        } else {
          //TODO Post or Get request
        }
      } else {
        $('#ErrorModal').modal('open');
        errors.push("Missing Values", " ");
        var errorContent = {
          Title: "Validation Error",
          Body: errors
        }
        $rootScope.ErrorDialog = errorContent;
      }
      $('#LoadingModal').modal('close');
    }

  });