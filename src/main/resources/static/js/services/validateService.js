/* Author : Dushan Galappaththi */
var nnyApp = angular.module('banking');
nnyApp.factory('ValidateService', ['$http', 'nnyConst', '$rootScope', function($http, nnyConst, $rootScope) {

    //Common Functions region start ---------------------------------------
    function getValidateResult(validStatus, errorMessage) {
        var validationObj = {
            status: validStatus,
            errorMsg: errorMessage
        }
        return validationObj;
    }

    function isNull(value) {
        if (value === "" || value === undefined) {
            return true;
        } else {
            return false;
        }
    }

    function lengthCheck(value, maxLength, minLength = 0) {
        console.log(value.length, maxLength,minLength);
        if (value.length <= maxLength && value.length >= minLength) {
            return true;
        } else {
            return false;
        }
    }

    function isLettersOnly(str) {
        var validChars = /^[a-zA-Z\s]*$/;
        if (validChars.test(str)) {
            return true;
        } else {
            return false;
        }
    }

    function isNumbersOnly(str) {
        var validChars = /^[0-9]+$/;
        if (validChars.test(str)) {
            return true;
        } else {
            return false;
        }
    }

    function isDecimalOnly(str) {
        var validChars = /^[-+]?[0-9]+\.[0-9]+$/;
        if (validChars.test(str)) {
            return true;
        } else {
            return false;
        }
    }

    function isAlphanumeric(str) {
        var validChars = /^[0-9a-zA-Z]+$/;
        if (validChars.test(str)) {
            return true;
        } else {
            return false;
        }
    }

    function isEmail(str) {
        var validChars = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if (validChars.test(str)) {
            return true;
        } else {
            return false;
        }
    }

    function isUsernameTaken(username) {
        //TODO
        return false;
    }

    function isPasswordWrong(password) {
        //TODO
        return false;
    }
    function validateNIC(nic) {
      var nicPattern = /([0-9]){9}([vx]){1}/g;
      if (nicPattern.test(nic)) {
          return true;
      } else {
          return false;
      }
    }

    //Common Functions reigon end ---------------------------------------

    function checkUsername(username) {
        if (isNull(username)) {
            return getValidateResult(false, "Please Enter Username");
        } else if (!isAlphanumeric) {
            return getValidateResult(false, "Username can only contain 'A to Z','a to z' or '0 to 9'");
        } else if (isUsernameTaken(username)) {
            return getValidateResult(false, "Username is already taken");
        } else {
            return getValidateResult(true, "");
        }
    }

    function verifyPassword(password) {
        if (isNull(password)) {
            return getValidateResult(false, "Please Enter Old Password");
        } else if (!isAlphanumeric) {
            return getValidateResult(false, "Password can only contain 'A to Z','a to z' or '0 to 9'");
        } else if (isPasswordWrong(password)) {
            return getValidateResult(false, "Invalid Password");
        } else {
            return getValidateResult(true, "");
        }
    }


    function checkPassword(password, confirmPassword) {
        if (isNull(password)) {
            return getValidateResult(false, "Password Cannot Be Empty!");
        } else if (isNull(confirmPassword)) {
            return getValidateResult(false, "Confirm Password Cannot Be Empty!");
        } else if (password !== confirmPassword) {
            return getValidateResult(false, "Passwords do not match");
        } else if (!lengthCheck(password,16,8)) {
            return getValidateResult(false, "Password should be 8 to 16 characters long");
        }
        else {
            return getValidateResult(true, "");
        }
    }

    function checkFirstName(firstName) {
        if (isNull(firstName)) {
            return getValidateResult(false, "First Name Cannot be null.");
        } else if (!isLettersOnly(firstName)) {
            return getValidateResult(false, "First Name can only contain alphabetical characters");
        } else {
            return getValidateResult(true, "");
        }
    }

    function checkLastName(lastName) {
        if (isNull(lastName)) {
            return getValidateResult(false, "Last Name Cannot be null.");
        } else if (!isLettersOnly(lastName)) {
            return getValidateResult(false, "Last Name can only contain alphabetical characters");
        } else {
            return getValidateResult(true, "");
        }
    }

    function valGender(gender) {
        if (isNull(gender)) {
            return getValidateResult(false, "Please select gender");
        } else {
            return getValidateResult(true, "");
        }
    }

    function checkNIC(nic) {
        if (isNull(nic)) {
            return getValidateResult(false, "Please enter your NIC");
        } else if (!validateNIC(nic)) {
            return getValidateResult(false, "Please enter a valid NIC");
        } else {
            return getValidateResult(true, "");
        }
    }

    function checkEmail(email) {
        if (isNull(email)) {
            return getValidateResult(false, "Please enter your email");
        } else if (!isEmail(email)) {
            return getValidateResult(false, "Please enter a valid email");
        } else {
            return getValidateResult(true, "");
        }
    }

    function contactCheck(contact, nullable = true) {
        return getValidateResult(true, "");
        if (isNull(contact) && !nullable) {
            return getValidateResult(false, "Contact cannot be null.")
        } else if (isNumbersOnly(contact)) {
            return getValidateResult(false, "Contact can only contain numbers.")
        } else {
            return getValidateResult(true, "");
        }
    }

    function addressCheck(address) {
        if (isNull(address)) {
            return getValidateResult(false, "Address Cannot be empty");
        } else {
            return getValidateResult(true, "");
        }
    }

    function cityCheck(city) {
        if (isNull(city)) {
            return getValidateResult(false, "Please enter your city.")
        } else {
            return getValidateResult(true, "");
        }
    }

    function validateDate(date) {
        return getValidateResult(true, "");
    }

    function accountNumberCheck(accNo) {
        return getValidateResult(true, "");
    }

    function branchCheck(branch) {
        return getValidateResult(true, "");
    }

    function accountBarrierCheck(accBarrier) {
        return getValidateResult(true, "");
    }

    function accTypeCheck(accType) {
        return getValidateResult(true, "");
    }

    function orgNameCheck(orgName) {
        return getValidateResult(true, "");
    }

    function regNoCheck(regNo) {
        return getValidateResult(true, "");
    }

    function taxNoCheck(taxNo) {
        return getValidateResult(true, "");
    }

    function serviceNameCheck(serviceName) {
        return getValidateResult(true, "");
    }

    function descCheck(desc) {
        return getValidateResult(true, "");
    }


    return {
        ValidatePassword: function(password, confirmPassword) {
            return checkPassword(password, confirmPassword);
        },
        ValidateUsername: function(username) {
            return checkUsername(username);
        },
        ValidateFirstName: function(firstName) {
            return checkFirstName(firstName);
        },
        ValidateLastName: function(lastName) {
            return checkLastName(lastName);
        },
        ValidateGender: function(gender) {
            return valGender(gender);
        },
        ValidateNIC: function(nic) {
            return checkNIC(nic);
        },
        ValidateEmail: function(email) {
            return checkEmail(email);
        },
        ValidateContact: function(contact, nullable) {
            return contactCheck(contact, nullable);
        },
        ValidateAddress: function(address) {
            return addressCheck(address);
        },
        ValidateCity: function(city) {
            return cityCheck(city);
        },
        VerifyPassword: function(password) {
            return verifyPassword(password);
        },
        ValidateOrgName: function(orgName) {
            return orgNameCheck(orgName);
        },
        ValidateRegNo: function(regNo) {
            return regNoCheck(regNo);
        },
        ValidateTaxNo: function(taxNo) {
            return taxNoCheck(taxNo);
        },
        ValidateServiceName: function(serviceName) {
            return serviceNameCheck(serviceName);
        },
        ValidateDesc: function(desc) {
            return descCheck(desc);
        },
        ValidateAccountNumber: function(accNo) {
            return accountNumberCheck(accNo);
        },
        ValidateBranch: function(branch) {
            return branchCheck(branch);
        },
        ValidateAccBarrier: function(accBarrier) {
            return accountBarrierCheck(accBarrier);
        },
        ValidateAccountType: function(accType) {
            return accTypeCheck(accType);
        },
        ValidateDate: function(date) {
            return validateDate(date);
        }
    }
}]);
