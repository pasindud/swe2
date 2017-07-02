angular.module('banking', [
  'ui.router',
  'ui.materialize'
])
.constant('ENDPOINT_URI','localhost:8989/SampleJSON')
.config(function ($stateProvider,$urlRouterProvider,$locationProvider,nnyConst) {
  $urlRouterProvider.otherwise('/login');
  $stateProvider.state('login',{
    url:'/login',
    templateUrl:'areas/login/login.html',
    controller:'LoginController',
    data : {requireLogin : false },
  })
  .state('home',{
    url:'/home',
    templateUrl:'areas/home/home.html',
    controller:'HomeController',
    data : {requireLogin : true },
  })
  .state('userprofile',{
    url:'/userprofile',
    templateUrl:'areas/userprofile/userprofile.html',
    controller:'UserProfileController',
    data : {requireLogin : true ,
    authorizedRoles : [nnyConst.UserRoles.Admin,nnyConst.UserRoles.CustomerP]}
  })
  .state('account',{
    url:'/account',
    templateUrl:'areas/account/account.html',
    controller:'AccountController',
    data : {requireLogin : true,
    authorizedRoles : [nnyConst.UserRoles.CustomerP]}

  })
  .state('payments',{
    url:'/payments',
    templateUrl:'areas/payments/payments.html',
    controller:'PaymentsController',
    data : {requireLogin : true,
    authorizedRoles : [nnyConst.UserRoles.CustomerP]}

  })
  .state('activity',{
    url:'/activity',
    templateUrl:'areas/activity/activity.html',
    controller:'ActivityController',
    data : {requireLogin : true,
    authorizedRoles : [nnyConst.UserRoles.Admin,nnyConst.UserRoles.CustomerP]}

  })
  .state('transaction',{
    url:'/activity/{transactionId}',
    templateUrl:'areas/activity/transaction.html',
    controller:'TransactionController',
    data : {requireLogin : true,
    authorizedRoles : [nnyConst.UserRoles.Admin,nnyConst.UserRoles.CustomerP]}

  })
  .state('createnew',{
    url:'/createnew',
    templateUrl:'areas/account/createNewAccount.html',
    controller:'CreateNewAccountController',
    data : {requireLogin : false },

  })
  .state('admin',{
    url:'/admin',
    templateUrl:'areas/admin/admin.html',
    controller:'AdminController',
    data : {requireLogin : true,
    authorizedRoles : [nnyConst.UserRoles.Admin]}
  })
  .state('flags',{
    url:'/admin/flags',
    templateUrl : 'areas/admin/flagged/flags.html',
    controller: 'FlagsController',
    data : {requireLogin : true,
    authorizedRoles : [nnyConst.UserRoles.Admin]}
  })
  .state('flag',{
    url : '/admin/flag/{flagID}',
    templateUrl: 'areas/admin/flagged/flaggedActivity.html',
    controller: 'FlaggedActivityController',
    data : {requireLogin : true,
    authorizedRoles : [nnyConst.UserRoles.Admin]}
  })
  .state('approvals',{
    url : '/admin/approvals',
    templateUrl: 'areas/admin/approvals/approvals.html',
    controller: 'ApprovalsController',
    data : {requireLogin : true,
      authorizedRoles : [nnyConst.UserRoles.Admin]}
  })
  .state('approval',{
    url : '/admin/approval/{approvalID}',
    templateUrl: 'areas/admin/approvals/approvalDetails.html',
    controller: 'ApprovalDetailsController',
    data : {requireLogin : true,
      authorizedRoles : [nnyConst.UserRoles.Admin]}
  })
  .state('users',{
    url : '/admin/users',
    templateUrl: 'areas/admin/manageAccounts/ViewAccounts.html',
    controller: 'ViewAccountsController',
    data : {requireLogin : true,
      authorizedRoles : [nnyConst.UserRoles.Admin]}
  })
  .state('user',{
    url : '/admin/user/{userID}',
    templateUrl: 'areas/admin/manageAccounts/userAccount.html',
    controller: 'UserAccountController',
    data : {requireLogin : true,
      authorizedRoles : [nnyConst.UserRoles.Admin]}
  })
  .state('settings',{
    url : '/settings',
    controller:'SettingsController',
    templateUrl : 'areas/settings/settings.html',
    data : {
      requireLogin : true,
      authorizedRoles : [nnyConst.UserRoles.Admin,nnyConst.UserRoles.CustomerP]}
  })
  .state('addbankaccount',{
    url : '/addbankaccount',
    controller:'AddBankAccountController',
    templateUrl : 'areas/account/addBankAccount.html',
    data : {requireLogin : true,
      authorizedRoles : [nnyConst.UserRoles.Admin,nnyConst.UserRoles.CustomerP]}
  })
  .state('recover',{
    url : '/recover',
    controller:'RecoverAccountController',
    templateUrl : 'areas/login/recoverAccount.html',
    data : {requireLogin : false}
  })
  .state('DatePickerController',{
    controller:'DatePickerController',
    data : {requireLogin : false },
  })
  .state('OverviewController',{
    controller:'OverviewController',
    data : {requireLogin : false },
  });

})
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
  $scope.logout = function () {
    $rootScope.authData = undefined;
    localStorage.clear();
    $state.go("login");
  }

  $scope.title = 'No Name Yet';
  $scope.selectedLanguage = "EN";
  $scope.setLanguage = function (provider) {
    $http.get('areas/common/localization/'+ provider +'.json')
    .then(function(res){
      $rootScope.labels = res.data[0];
      $scope.selectedLanguage = provider.toUpperCase();
    });
  };
  $scope.$on('$viewContentLoaded', function(){
    $http.get('areas/common/localization/en-us.json')
    .then(function(res){
      $rootScope.labels = res.data[0];
    });
  });

  $scope.tabBtn = function (tabId) {
    $("#accountTab").removeClass("nny-tab-selected");
    $("#paymentsTab").removeClass("nny-tab-selected");
    $("#profileTab").removeClass("nny-tab-selected");

    $("#"+tabId).addClass("nny-tab-selected");
  }

  $scope.getLabels = function () {
    return $rootScope.labels;
  };
  $scope.setPage = function(view) {
    $location.path(view);
  };
})
.run(function ($rootScope,$state, AuthService,$location) {
  $rootScope.$on('$stateChangeStart',function (event, toState, toParams, fromState, fromParams, error) {
    console.log("asd");
    // if(toState !== undefined && toState.data && toState.data.requireLogin && !AuthService.isLoggedin())
    // {
    //   $state.go("login");
    //   event.preventDefault();
    //   return;
    // } else if (toState.data.authorizedRoles === undefined) {
    //   return;
    // }
    // else if(toState.data.authorizedRoles.indexOf($rootScope.authData.accessLevel) == -1) {
    //   console.log("1111");
    //   $state.go("login");
    //   event.preventDefault();
    //   return;
    // }
    // else {
    //   console.log("asds");
    //   return;
    // }
    if (!AuthService.isLoggedin()) {
      if (toState.name != "login") {
        $state.go("login");
        event.preventDefault();
      };
    };
     // if (!AuthService.isAuthRoute($location.url())&&!AuthService.isLoggedin()) {
    // $state.go("login");
    // event.preventDefault();
    // }
});
});
