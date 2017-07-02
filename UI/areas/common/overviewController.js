/* Author : Dushan Galappaththi */
'use strict';
angular.module('banking')
.controller('OverviewController',function ($scope, $http,$rootScope,$location,$state,AuthService, nnyConst) {
  $scope.AccessLevels = nnyConst.UserRoles;
  $rootScope.$watch('authDate',function (status) {
    if(status)
    {
      $scope.authData = $rootScope.authData;
    }
  });
  $rootScope.$watch('ErrorDialog',function (status) {
    if(status)
    {
      $scope.ErrorDialog = $rootScope.ErrorDialog;
    }
  });

  //Logout Button
  $scope.logout = function () {
    $rootScope.authData = undefined;
    localStorage.clear();
    $state.go("login");
  }

  $scope.title = 'No Name Yet';
  $scope.selectedLanguage = "EN";

  //Default Language
  $scope.setLanguage = function (provider) {
    $http.get('areas/common/localization/'+ provider +'.json')
    .then(function(res){
      $rootScope.labels = res.data[0];
      $scope.selectedLanguage = provider.toUpperCase();
    });
  };

  //look for language change
  $scope.$on('$viewContentLoaded', function(){
    $http.get('areas/common/localization/en-us.json')
    .then(function(res){
      $rootScope.labels = res.data[0];
    });
  });

  //Nav bar tab styling
  $scope.tabBtn = function (tabId) {
    switch(tabId)
    {
      case 1 :
          resetTabs(1);
          removeSelTabs(1);
          $("#accountTab").removeClass("nny-tab");
          $("#accountTab").addClass("nny-tab-selected");
        break;
      case 2 :
        resetTabs(1);
        removeSelTabs(1);
        $("#paymentsTab").removeClass("nny-tab");
        $("#paymentsTab").addClass("nny-tab-selected");
        break;
      case 3 :
        resetTabs(1);
        removeSelTabs(1);
        $("#profileTab").removeClass("nny-tab");
        $("#profileTab").addClass("nny-tab-selected");
        break;
    }
  }

  function resetTabs(a) {
    $("#accountTab").addClass("nny-tab");
    $("#paymentsTab").addClass("nny-tab");
    $("#profileTab").addClass("nny-tab");
  }

  function removeSelTabs(a){
    $("#accountTab").removeClass("nny-tab-selected");
    $("#paymentsTab").removeClass("nny-tab-selected");
    $("#profileTab").removeClass("nny-tab-selected");
  }

  //Localize
  $scope.getLabels = function () {
    return $rootScope.labels;
  };
  $scope.setPage = function(view) {
    $location.path(view);
  };
});