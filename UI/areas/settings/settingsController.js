/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
  .controller('SettingsController', function($http, $state, $rootScope, $scope, AuthService, $location, nnyConst, ValidateService) {
    var UpdatePasswordData = "";
    var errors = [""];
    var isInvalidForm = false;
    $scope.languages = [{
      id: "en-us",
      name: "English"
    }, {
      id: "fr",
      name: "French"
    }, {
      id: "ie",
      name: "Irish"
    }];
    $scope.currencies = [{
      id: "USD",
      name: "US Dollar"
    }, {
      id: "EUR",
      name: "Pounds"
    }, {
      id: "LKR",
      name: "Rupees"
    }];

    function formValidate(field) {
      if (!field.status) {
        isInvalidForm = true;
        errors.push(field.errorMsg);
      }
    }

    function inputFieldAnimate(id, status) {
      if (status) {
        $("#" + id).addClass('valid');
        $("#" + id).removeClass('invalid');
      } else {
        $("#" + id).addClass('invalid');
        $("#" + id).removeClass('valid');
      }
    }
    $scope.savePasswordBtn = function() {
      errors = [""];
      isInvalidForm = false;
      if ($scope.UpdatePasswordForm !== undefined) {
        UpdatePasswordData = $scope.UpdatePasswordForm;
        var newPasswordVal = ValidateService.ValidatePassword(UpdatePasswordData.newPassword, UpdatePasswordData.confirmNewPassword);
        var verifyPassword = ValidateService.VerifyPassword(UpdatePasswordData.oldPassword);
        formValidate(newPasswordVal);
        formValidate(verifyPassword);
        inputFieldAnimate("new_password", newPasswordVal.status);
        inputFieldAnimate("confirm_new_password", newPasswordVal.status);
        inputFieldAnimate("old_password", verifyPassword.status);

        if (isInvalidForm) {
          var errorContent = {
            Title: "Validation Error",
            Body: errors
          }
          $('#ErrorModal').modal('open');
          $rootScope.ErrorDialog = errorContent;
        } else {
          //TODO : on validation success;
        }
        $scope.UpdatePasswordForm = UpdatePasswordData;
      } else {
        $('#ErrorModal').modal('open');
        errors.push("Missing Values", " ");
        var errorContent = {
          Title: "Validation Error",
          Body: errors
        }
        $rootScope.ErrorDialog = errorContent;
      }
    }

    $scope.setLanguage = function() {
      var lang = $scope.UpdatePreference.language;
      if (lang === undefined || lang === null) {
        lang = "en-us";
      }
      $http.get('areas/common/localization/' + lang + '.json')
        .then(function(res) {
          $rootScope.labels = res.data[0];
          $scope.selectedLanguage = lang.toUpperCase();
        })
    };
    $scope.setCurrency = function() {
      //TODO
    };

  });