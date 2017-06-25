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
  })
  .state('home',{
    url:'/home',
    templateUrl:'areas/home/home.html',
    controller:'HomeController',


  })
  .state('account',{
    url:'/account',
    templateUrl:'areas/account/account.html',
    controller:'AccountController',

  })
  .state('payments',{
    url:'/payments',
    templateUrl:'areas/payments/payments.html',
    controller:'PaymentsController',

  })
  .state('activity',{
    url:'/activity',
    templateUrl:'areas/activity/activity.html',
    controller:'ActivityController',

  })
  .state('createnew',{
    url:'/createnew',
    templateUrl:'areas/account/createNewAccount.html',
    controller:'CreateNewAccountController'
  })
  .state('admin',{
    url:'/admin',
    templateUrl:'areas/admin/admin.html',
    controller:'AdminController'
  })
  .state('flags',{
    url:'/admin/flags',
    templateUrl : 'areas/admin/flagged/flags.html',
    controller: 'FlagsController'
  })
  .state('flag',{
    url : '/admin/flag/{flagID}',
    templateUrl: 'areas/admin/flagged/flaggedActivity.html',
    controller: 'FlaggedActivityController'
  })
  .state('approvals',{
    url : '/admin/approvals',
    templateUrl: 'areas/admin/approvals/approvals.html',
    controller: 'ApprovalsController'
  })
  .state('approval',{
    url : '/admin/approval/{approvalID}',
    templateUrl: 'areas/admin/approvals/approvalDetails.html',
    controller: 'ApprovalDetailsController'
  })
  .state('users',{
    url : '/admin/users',
    templateUrl: 'areas/admin/manageAccounts/ViewAccounts.html',
    controller: 'ViewAccountsController'
  })
  .state('user',{
      url : '/admin/user/{userID}',
      templateUrl: 'areas/admin/manageAccounts/userAccount.html',
      controller: 'UserAccountController'
    })
  .state('DatePickerController',{
    controller:'DatePickerController'
  })
  .state('OverviewController',{
    controller:'OverviewController'
  });

})
.controller('OverviewController',function ($scope, $http,$rootScope,$location,AuthService) {

  AuthService.authenticate().then(function (result) {
      var temp = result.data[0];
      var authData = {
        username : temp.Username,
        accessToken : temp.AccessToken,
        accessLevel : temp.AccessLevel
      };
      $scope.authData = authData;
  });
  debugger;
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
.run(function ($rootScope,$state, AuthService) {
    $rootScope.$on('',function (event, toState, toParams, fromState, fromParams, error) {
      event.preventDefault();
      if (!authRoute($location.url()) && !AuthService.isLoggedin()) {
        $location.path('/login');
      }
    });
  });
