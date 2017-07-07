/* Author : Dushan Galappaththi */
var nnyApp = angular.module('banking');
nnyApp.factory('ObjectService', ['$http', 'nnyConst', '$rootScope', function($http, nnyConst, $rootScope) {
  var NA = "N/A";

  function getUsersObj() {
    var userObj = {
      username : "",
      password : "",
      userType : ""
    };
    return userObj;
  }

  function getCustomerObj() {
    var customerObj = {
      title : "",
      firstname : "",
      lastname : "",
      dob : "",
      nic : "",
      addressLine1 : "",
      addressLine2 : "",
      addressLine3 : "",
      city : "",
      mobileNo : "",
      email : "",
      faxNo : ""
    };
    return customerObj;
  }

  function getQandA() {
    var answer = {
      answer : "",
      securityQuestions : {
        id : ""
      }
    };
    return answer;
  }

  function getCustomerSignUpObj(){
    var signupObj = {
      users : "",
      customer : "",
      answers : []
    };
    return signupObj;
  }

  function getMappedSignUpDataC(data) {
    var userObj = getUsersObj();
    userObj.username = data.username;
    userObj.password = data.password;
    userObj.userType = "CUSTOMER";

    var customerObj = getCustomerObj();
    customerObj.title = data.title;
    customerObj.firstName = data.first_name;
    customerObj.lastName = data.last_name;
    customerObj.dob = data.dob;
    customerObj.nic = data.nic;
    customerObj.email = data.email;
    customerObj.faxNo = data.faxNo;
    customerObj.mobileNo = data.contact1;
    customerObj.city = data.city;
    customerObj.addressLine1 = data.addressL1;
    customerObj.addressLine2 = data.addressL2;
    customerObj.addressLine3 = data.addressL3;

    var answer1 = getQandA();
    var answer2 = getQandA();
    var answer3 = getQandA();

    answer1.answer = data.answer1;
    answer1.securityQuestions.id = data.question1;
    answer2.answer = data.answer2;
    answer2.securityQuestions.id = data.question2;
    answer3.answer = data.answer3;
    answer3.securityQuestions.id = data.question3;

    var signupObj = getCustomerSignUpObj();
    signupObj.users = userObj;
    signupObj.customer = customerObj;
    signupObj.answers = [answer1,answer2,answer3];

    return signupObj;
  }

  function getBranchObj()
  {
    var Branch = {
      branchId : NA,
      branchName : NA,
      city : NA,
      email : NA,
      faxNo : NA,
      telephoneNo : NA,
      addressLine1 : "",
      addressLine2 : "",
      addressLine3 : "",
      wholeAddress : NA
    }
    return Branch;
  }

  function getAccountType() {
    var AccountType = {
      accTypeId : NA,
      accName : NA,
      accInterestRates: NA,
      dailyWithdrawLimit : NA,
      maxMonths : NA,
      maxOverDraftAmount : NA,
      minAvgBalance : NA,
      minInitBalance : NA,
      minMonths : NA
    }

    return AccountType;
  }

  function getAccountObj() {
    var accountTypeObj = getAccountType();
    var branchObj = getBranchObj();

    var AccountObj = {
      accTypeId : accountTypeObj,
      accountid : NA,
      balance : NA,
      branchId : branchObj,
      createdDate : NA,
      currency : "",
      expireDate : NA,
      locked : false
    }

    return AccountObj;
  }

  return {
    getCustomerSignUpObj: function() {
      return getCustomerSignUpObj();
    },
    getCustomerObj: function() {
      return getCustomerObj();
    },
    getUsersObj :function () {
      return getUsersObj();
    },
    getMappedSignUpDataC : function (data) {
      return getMappedSignUpDataC(data);
    },
    getAccountObj: function () {
      return getAccountObj();
    }
  }

}]);
