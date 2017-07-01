angular.module('banking', [
  'ui.router',
  'ui.materialize'
])
.constant('ENDPOINT_URI','localhost:8989/SampleJSON')
.config(function ($stateProvider,$urlRouterProvider,$locationProvider) {
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
    data : {requireLogin : true },
  })
  .state('account',{
    url:'/account',
    templateUrl:'areas/account/account.html',
    controller:'AccountController',
    data : {requireLogin : true },

  })
  .state('payments',{
    url:'/payments',
    templateUrl:'areas/payments/payments.html',
    controller:'PaymentsController',
    data : {requireLogin : true },

  })
  .state('activity',{
    url:'/activity/{transactionId}',
    templateUrl:'areas/activity/activity.html',
    controller:'ActivityController',
    data : {requireLogin : true },

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
    data : {requireLogin : true },
  })
  .state('flags',{
    url:'/admin/flags',
    templateUrl : 'areas/admin/flagged/flags.html',
    controller: 'FlagsController',
    data : {requireLogin : true },
  })
  .state('flag',{
    url : '/admin/flag/{flagID}',
    templateUrl: 'areas/admin/flagged/flaggedActivity.html',
    controller: 'FlaggedActivityController',
    data : {requireLogin : true },
  })
  .state('approvals',{
    url : '/admin/approvals',
    templateUrl: 'areas/admin/approvals/approvals.html',
    controller: 'ApprovalsController',
    data : {requireLogin : true },
  })
  .state('approval',{
    url : '/admin/approval/{approvalID}',
    templateUrl: 'areas/admin/approvals/approvalDetails.html',
    controller: 'ApprovalDetailsController',
    data : {requireLogin : true },
  })
  .state('users',{
    url : '/admin/users',
    templateUrl: 'areas/admin/manageAccounts/ViewAccounts.html',
    controller: 'ViewAccountsController',
    data : {requireLogin : true },
  })
  .state('user',{
    url : '/admin/user/{userID}',
    templateUrl: 'areas/admin/manageAccounts/userAccount.html',
    controller: 'UserAccountController',
    data : {requireLogin : true },
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

  $scope.getLabels = function () {
    return $rootScope.labels;
  };
  $scope.setPage = function(view) {
    $location.path(view);
  };
})
.run(function ($rootScope,$state, AuthService,$location) {
  $rootScope.$on('$stateChangeStart',function (event, toState, toParams, fromState, fromParams, error) {
    if(toState !== undefined && toState.data && toState.data.requireLogin && !AuthService.isLoggedin())
    {
      $state.go("login");
      event.preventDefault();
      return;
    }

    /* if (!AuthService.isAuthRoute($location.url())&&!AuthService.isLoggedin()) {
    $state.go("login");
    event.preventDefault();
  }*/
});
});
