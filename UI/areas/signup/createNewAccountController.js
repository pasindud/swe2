/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
.controller('CreateNewAccountController', function($state, $rootScope, $scope, ValidateService, AuthService,ObjectService) {
  var isInvalidForm = false;
  var errors = [""];

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

  $scope.submitBtn = function() {
    $('#LoadingModal').modal('open');
    if ($scope.FormData !== undefined) {
      var FormData = $scope.FormData;
      FormData.dob = $rootScope.ca_formData_DOB;
      FormData.aod = $rootScope.ca_formData_AOD;
      debugger;
      errors = [""];
      isInvalidForm = false;

      var usernameVal = ValidateService.ValidateUsername(FormData.username);
      var passwordVal = ValidateService.ValidatePassword(FormData.password, FormData.confirmPassword);
      var firstNameVal = ValidateService.ValidateFirstName(FormData.first_name);
      var lastNameVal = ValidateService.ValidateLastName(FormData.last_name);
      var genderVal = ValidateService.ValidateGender(FormData.gender);
      var nicVal = ValidateService.ValidateNIC(FormData.nic);
      var emailVal = ValidateService.ValidateEmail(FormData.email);
      var contact1Val = ValidateService.ValidateContact(FormData.contact1, false);
      var contact2Val = ValidateService.ValidateContact(FormData.contact2);
      var addressL1Val = ValidateService.ValidateAddress(FormData.addressL1);
      var cityVal = ValidateService.ValidateCity(FormData.city);

      formValidate(usernameVal);
      formValidate(passwordVal);
      formValidate(firstNameVal);
      formValidate(lastNameVal);
      formValidate(genderVal);
      formValidate(nicVal);
      formValidate(emailVal);
      formValidate(contact1Val);
      formValidate(contact2Val);
      formValidate(addressL1Val);
      formValidate(cityVal);

      inputFieldAnimate("password", passwordVal.status);
      inputFieldAnimate("confirm_password", passwordVal.status);
      inputFieldAnimate("username", usernameVal.status);
      inputFieldAnimate("first_name", firstNameVal.status);
      inputFieldAnimate("last_name", lastNameVal.status);
      inputFieldAnimate("gender", genderVal.status);
      inputFieldAnimate("NIC", nicVal.status);
      inputFieldAnimate("email_address", emailVal.status);
      inputFieldAnimate("contact1", contact1Val.status);
      inputFieldAnimate("contact2", contact2Val.status);
      inputFieldAnimate("addressL1", addressL1Val.status);
      inputFieldAnimate("city", cityVal.status);

      $('#LoadingModal').modal('close');
      if (isInvalidForm) {
        var errorContent = {
          Title: "Validation Error",
          Body: errors
        }
        $('#ErrorModal').modal('open');
        $rootScope.ErrorDialog = errorContent;
      } else {
        var mappedData = ObjectService.getMappedSignUpDataC(FormData);
        AuthService.getRequest("/api/registration", mappedData, function(response) {
          console.log(response);
        })
      }
      $scope.FormData = FormData;
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
});
