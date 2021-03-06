var app = angular.module('banking', [
    'ui.router',
    'ui.materialize',
    'ngTable',
    'ngAnimate',
    'toastr'
  ])
  .constant('ENDPOINT_URI', 'localhost:8989/SampleJSON')
  .config(function($stateProvider, $urlRouterProvider, $locationProvider, nnyConst) {
    $urlRouterProvider.otherwise('/login');
    $stateProvider.state('login', {
        url: '/login',
        templateUrl: 'areas/login/login.html',
        controller: 'LoginController',
        data: {
          requireLogin: false
        },
      })
      .state('home', {
        url: '/home',
        templateUrl: 'areas/home/home.html',
        controller: 'HomeController',
        data: {
          requireLogin: true
        },
      })
      .state('userprofile', {
        url: '/userprofile',
        templateUrl: 'areas/userprofile/userprofile.html',
        controller: 'UserProfileController',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin, nnyConst.UserRoles.CustomerP]
        }
      })
      .state('merchantprofile', {
        url: '/merchantprofile',
        templateUrl: 'areas/userprofile/merchantProfile.html',
        controller: 'MerchantProfileController',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin, nnyConst.UserRoles.CustomerM]
        }
      })
      .state('account', {
        url: '/account',
        templateUrl: 'areas/account/account.html',
        controller: 'AccountController',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.CustomerP, nnyConst.UserRoles.CustomerM]
        }

      })
      .state('payments', {
        url: '/payments',
        templateUrl: 'areas/payments/payments.html',
        controller: 'PaymentsController',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.CustomerP, nnyConst.UserRoles.CustomerM]
        }

      })
      .state('activity', {
        url: '/activity/{transactionId}',
        templateUrl: 'areas/activity/activity.html',
        controller: 'ActivityController',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin, nnyConst.UserRoles.CustomerP, nnyConst.UserRoles.CustomerM]
        }

      })
      .state('transaction', {
        url: '/transaction/{transactionId}',
        templateUrl: 'areas/activity/transaction.html',
        controller: 'TransactionController',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin, nnyConst.UserRoles.CustomerP, nnyConst.UserRoles.CustomerM]
        }

      })
      .state('createnew', {
        url: '/signup/createnew',
        templateUrl: 'areas/signup/createNewAccount.html',
        controller: 'CreateNewAccountController',
        data: {
          requireLogin: false
        },

      })
      .state('admin', {
        url: '/admin',
        templateUrl: 'areas/admin/admin.html',
        controller: 'AdminController',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin]
        }
      })
      .state('flags', {
        url: '/admin/flags',
        templateUrl: 'areas/admin/flagged/flags.html',
        controller: 'FlagsController',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin]
        }
      })
      .state('flag', {
        url: '/admin/flag/{flagID}',
        templateUrl: 'areas/admin/flagged/flaggedActivity.html',
        controller: 'FlaggedActivityController',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin]
        }
      })
      .state('approvals', {
        url: '/admin/approvals',
        templateUrl: 'areas/admin/approvals/approvals.html',
        controller: 'ApprovalsController',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin]
        }
      })
      .state('approval', {
        url: '/admin/approval/{approvalID}',
        templateUrl: 'areas/admin/approvals/approvalDetails.html',
        controller: 'ApprovalDetailsController',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin]
        }
      })
      .state('listUsers', {
        url: '/admin/users',
        templateUrl: 'areas/admin/manageUsers/viewUsers.html',
        controller: 'ViewUsers',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin]
        }
      })
      .state('viewuser', {
        url: '/admin/user/{userid}',
        templateUrl: 'areas/admin/manageUsers/viewUserProfile.html',
        controller: 'ViewUserProfileController',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin]
        }
      })
      .state('listaccounts', {
        url: '/admin/accounts',
        templateUrl: 'areas/admin/manageAccounts/ViewAccounts.html',
        controller: 'ViewAccountsController',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin]
        }
      })
      .state('account_view', {
        url: '/admin/accounts/{accountid}',
        templateUrl: 'areas/admin/manageAccounts/userAccount.html',
        controller: 'UserAccountController',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin]
        }
      })
      .state('settings', {
        url: '/settings',
        controller: 'SettingsController',
        templateUrl: 'areas/settings/settings.html',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin, nnyConst.UserRoles.CustomerP, nnyConst.UserRoles.CustomerM]
        }
      })
      .state('addbankaccount', {
        url: '/account/addbankaccount',
        controller: 'AddBankAccountController',
        templateUrl: 'areas/account/addBankAccount.html',
        data: {
          requireLogin: true,
          authorizedRoles: [nnyConst.UserRoles.Admin, nnyConst.UserRoles.CustomerP, nnyConst.UserRoles.CustomerM]
        }
      })
      .state('addmerchantaccount', {
        url: '/signup/addmerchantaccount',
        controller: 'AddMerchantAccountController',
        templateUrl: 'areas/signup/addMerchantAccount.html',
        data: {
          requireLogin: false
        }
      })
      .state('recover', {
        url: '/recover',
        controller: 'RecoverAccountController',
        templateUrl: 'areas/login/recoverAccount.html',
        data: {
          requireLogin: false
        }
      })
      .state('chooseprofile', {
        url: '/signup',
        controller: 'ChooseProfileTypeController',
        templateUrl: 'areas/signup/chooseProfileType.html',
        data: {
          requireLogin: false
        }
      })
      .state('exchangerates', {
        url: '/exchangerates',
        controller: 'ExchangeRatesController',
        templateUrl: 'areas/common/exchangeRates.html',
        data: {
          requireLogin: false
        }
      })
      .state('DatePickerController', {
        controller: 'DatePickerController',
        data: {
          requireLogin: false
        },
      })
      .state('OverviewController', {
        controller: 'OverviewController',
        data: {
          requireLogin: false
        },
      });

  })
  .run(function($rootScope, $state, AuthService, $location) {
    $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams, error) {
      console.log("asd");
      if (toState !== undefined && toState.data && toState.data.requireLogin && !AuthService.isLoggedin()) {
        $state.go("login");
        event.preventDefault();
        return;
      } else if (toState.data.authorizedRoles === undefined) {
        return;
      } else if (toState.data.authorizedRoles.indexOf($rootScope.authData.accessLevel) == -1) {
        console.log("1111");
        $state.go("login");
        event.preventDefault();
        return;
      } else {
        console.log("asds");
        return;
      }
      if (!AuthService.isLoggedin()) {
        if (toState.name != "login") {
          $state.go("login");
          event.preventDefault();
        };
      };
    });
  });
