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
    url:'/admin/flagged',
    templateUrl : 'areas/admin/flagged/flags.html',
    controller: 'FlagsController'
  })
  .state('flaggedActivity',{
    url : '/admin/flaggedActivity',
    templateUrl: 'areas/admin/flagged/flaggedActivity.html',
    controller: 'FlaggedActivityController'
  })
  .state('DatePickerController',{
    controller:'DatePickerController'
  })
  .state('OverviewController',{
    controller:'OverviewController'
  });

})
.controller('OverviewController',function ($scope, $http,$rootScope,$location) {
      $scope.title = 'No Name Yet';
      $scope.setLanguage = function (provider) {
        $http.get('areas/common/localization/'+ provider +'.json')
         .then(function(res){
            $rootScope.labels = res.data[0];
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
.run(function ($rootScope,$state) {
    $rootScope.$on('',function (event, toState, toParams, fromState, fromParams, error) {
      event.preventDefault();
      if(error = 'AUTH_REQUIRED'){

      }
    });
});



/*.controller('LoginController',function ($scope, StateService) {
  $scope.title = 'Authentication';
  $scope.body = 'Testing';
})
.controller('HomeController',function ($scope, StateService) {
  $scope.title = 'Home';

})
.factory('StateService', function () {
        var message = 'Hello Message';
        var getMessage = function () {
            return message;
        };
        var setMessage = function (m) {
            message = m;
        };

        return {
            getMessage: getMessage,
            setMessage: setMessage
        }
    })
    .service('ExperimentsService', function () {
        var service = this,
            experiments = [
                {name: 'Experiment 1', description: 'This is an experiment', completed:0},
                {name: 'Experiment 2', description: 'This is an experiment', completed:0},
                {name: 'Experiment 3', description: 'This is an experiment', completed:0},
                {name: 'Experiment 4', description: 'This is an experiment', completed:0}
            ];

        service.getExperiments = function() {
            return experiments;
        };
    })
    .directive('experiment', function(){
        var linker = function (scope, element, attrs) {
            element.on('click', function(){
                scope.doExperiment();
            })
        };

        var controller =  function($scope){
            $scope.doExperiment = function() {
                $scope.$apply(function(){
                    $scope.experiment.completed++;
                });
            };
        };

        return {
            scope: true,
            restrict: 'E',
            template: '<div class="experiment">' +
                '<h3>{{experiment.name}}</h3>' +
                '<p>{{experiment.description}}</p>' +
                '<p><strong>{{experiment.completed}}</strong></p>' +
                '</div>',
            link: linker,
            controller: controller
        }
    });*/
