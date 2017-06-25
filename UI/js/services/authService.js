var nnyApp = angular.module('banking');

nnyApp.factory('AuthService',['$http','nnyConst',function ($http,nnyConst) {
  var isLoggedin =false;

  function requestor(username,password)
  {
    var result = $http.get('SampleJSON/Auth/auth.json')
    .then(function(res) {
      return res.data[0];
    })
    debugger;
    return result;
  }



  function getIsLoggedIn() {
    return isLoggedin;
  }
  return  {
    authenticate : function () {
      return $http.get('SampleJSON/Auth/auth.json');
    },
    isLoggedin : getIsLoggedIn()
  };
}]);
