/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
  .controller('AddBankAccountController', function($state, $rootScope, $scope, $http, $stateParams, AuthService, ValidateService) {
    var isInvalidForm = false;
    var errors = [""];
    debugger;
    $scope.masterData = $rootScope.MasterData;

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
      if ($scope.AddBankAccountForm !== undefined) {
        var FormData = $scope.AddBankAccountForm;
        errors = [""];
        isInvalidForm = false;

        var accountNumberVal = ValidateService.ValidateAccountNumber(FormData.account_number);
        var branchVal = ValidateService.ValidateBranch(FormData.branch);
        var accountBarrierVal = ValidateService.ValidateAccBarrier(FormData.account_barrier);
        var accountTypeVal = ValidateService.ValidateAccountType(FormData.account_type);
        var openDateVal = ValidateService.ValidateDate(FormData.account_open_date);

        formValidate(accountNumberVal);
        formValidate(branchVal);
        formValidate(accountBarrierVal);
        formValidate(accountTypeVal);
        formValidate(openDateVal);

        inputFieldAnimate("account_number", accountNumberVal.status);
        inputFieldAnimate("branch", branchVal.status);
        inputFieldAnimate("account_barrier", accountBarrierVal.status);
        inputFieldAnimate("account_type", accountTypeVal.status);
        inputFieldAnimate("account_open_date", openDateVal.status);

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
