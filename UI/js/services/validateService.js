var nnyApp = angular.module('banking');
nnyApp.factory('ValidateService',['$http','nnyConst','$rootScope',function ($http,nnyConst,$rootScope) {

  function getValidateResult(validStatus,errorMessage)
  {
    var validationObj = {
      status : validStatus,
      errorMsg : errorMessage
    }
    return validationObj;
  }

  function checkPassword(password, confirmPassword)
  {
    if(password == ""){
      return getValidateResult(false,"Password Cannot Be Empty!");
    }else if (confirmPassword == "") {
      return getValidateResult(false,"Confirm Password Cannot Be Empty!");
    }else if (password !== confirmPassword) {
      return getValidateResult(false,"Passwords do not match");
    }else {
      return getValidateResult(true,"");
    }
  }
  return {
    ValidatePassword : function (password, confirmPassword) {
      return checkPassword(password, confirmPassword);
    }
  }
}]);
