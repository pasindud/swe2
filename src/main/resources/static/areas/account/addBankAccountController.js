/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
  .controller('AddBankAccountController', function($state, $rootScope, $scope, $http, $stateParams, AuthService, ValidateService,toastr) {
    var isInvalidForm = false;
    var errors = [""];

    $scope.masterData = $rootScope.MasterData;

    $scope.currencies = ["USD", "LKR", "SGD", "EUR", "GBP"];

    AuthService.getRequest("/api/ui_data_branch", null, function(response) {
      $scope.Branch = response.data;
      console.log(response.data);
    });
    AuthService.getRequest("/api/ui_data_acctype", null, function(response) {
      $scope.AccountType = response.data;
      console.log(response.data);
    });

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
          //
          var data = {
            currency: FormData.currency,
            accTypeid: FormData.account_type,
            branchid: FormData.branch           
          }
          
          AuthService.getRequest("/api/accounts_save", data, function(response) {
              if (response.data.errors != undefined && response.data.errors.length != 0) {
                 $('#ErrorModal').modal('open');
                 var errorContent = {
                   Title: "Error create account.",
                   Body: response.data.errors
                 }
                 $rootScope.ErrorDialog = errorContent;
              } else {
                toastr.success("Bank account created", 'Sucessful');
              }
          });

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
