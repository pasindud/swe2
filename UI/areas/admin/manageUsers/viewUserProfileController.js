'use strict';
angular.module('banking')
.controller('ViewUserProfileController',function ($state,$rootScope,$scope,$http,$stateParams,$filter, AuthService) {
  var userId = $stateParams.userid;
  $scope.PersonalInformation;
  $scope.selectedAccountDetails;
  requestServerData();

  function requestServerData() {
    AuthService.getRequest("/api/admin/user_by_id?id=" + userId, null, function (response) {
      $scope.PersonalInformation = response.data.customer;
      $scope.PersonalInformation.userObj = response.data;
      var addressList = [$scope.PersonalInformation.addressLine1,$scope.PersonalInformation.addressLine2,$scope.PersonalInformation.addressLine3];
      $scope.PersonalInformation.wholeAddress = addressList.join(', ');
    });
    //request to server
    AuthService.getRequest("/api/admin/account__by_user_id?userid=" + userId, null, function(response) {
      $scope.user_accounts = response.data;
      console.log("Account" + userId);
      console.log(response.data);
    });
  }

  function lockRequest(userId,isLock) {
    //request to server
    AuthService.getRequest("/api/admin/change_user_status?user_id=" + userId + "&lock="+isLock, null, function(response) {
      console.log("lock/unlock tring");
      console.log(response.data);
    });
  }


  //when user selects their account from drop down
  $scope.setAccountDetails = function() {
    if($scope.selectedAccount!=="" && $scope.selectedAccount !== undefined && $scope.selectedAccount!= null){
      var details = $filter('filter')($scope.user_accounts, {
        accountid: $scope.selectedAccount
      })[0];
      $scope.selectedAccountDetails = details;
      var addressList = [details.branchId.addressLine1,details.branchId.addressLine2,details.branchId.addressLine3]
      $scope.selectedAccountDetails.branchId.wholeAddress = addressList.join(', ');
    }
  }

  $scope.btnLock = function () {
    lockRequest(userId,true);
  }

  $scope.btnUnlock = function () {
    lockRequest(userId,false);
  }


});
