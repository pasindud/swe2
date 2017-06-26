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

  var JAVA_ENDPOINT="http://localhost:8080";

  function getAuthToken(username, password) {
    var headers = {"Authorization": "Basic " + btoa( username + ":" + password)};

    $http.get(JAVA_ENDPOINT + "/api/accounts?id=1", {headers: headers}).then(function (response) {
      console.log(response);
    });
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
