var nnyApp = angular.module('banking');
nnyApp.factory('ValidateService',['$http','nnyConst','$rootScope',function ($http,nnyConst,$rootScope) {
  var validationObj = {
    status : "",
    errorMsg : ""
  }
  function checkPassword(password, confirmPassword)
  {
    if(password == "")
    {
      validationObj.status =false;
      validationObj.errorMsg = "Password Cannot Be Empty!";
      return validationObj;
    }else if (confirmPassword == "") {
      validationObj.status =false;
      validationObj.errorMsg = "Confirm Password Cannot Be Empty!";
      return validationObj;
    }else if (password !== confirmPassword) {
      validationObj.status =false;
      validationObj.errorMsg = "Passwords do not match";
      return validationObj;
    }else {
      validationObj.status =true;
      validationObj.errorMsg = "";
      return validationObj;
    }
  }

  return {
    ValidatePassword : function (password, confirmPassword) {
      return checkPassword(password, confirmPassword);
    }
  }
}]);
