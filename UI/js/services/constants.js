var nnyApp = angular.module('banking');

nnyApp.constant('nnyConst',{
  test : "hi",
  ENDPOINT_URI : "http://192.168.1.134:8080",
  UserRoles : {
    Admin : '3',
    CustomerM : '2',
    CustomerP : '1',
    Stranger : '0'
  }
});
