var nnyApp = angular.module('banking');
var AUTH_TOKEN = null;
var USER_ID = null;

nnyApp.factory('AuthService',['$http','nnyConst',function ($http,nnyConst) {
  var isLoggedin =false;
  var JAVA_ENDPOINT="http://localhost:8080";

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
    getAuthToken : function (username, password) {
      var headers = {"Authorization": "Basic " + btoa( username + ":" + password)};
      $http.get(JAVA_ENDPOINT + "/api/auth", {headers: headers}).then(function (response) {
        console.log(response.data);
        AUTH_TOKEN = response.data.session;
        USER_ID = response.data.userid;
        // getRequest("/api/accounts?id=1")
      });
    },
    getRequest : function (url, data, cb) {
        var headers = {"x-auth-token": AUTH_TOKEN};
        console.log(headers);
        $http.get(JAVA_ENDPOINT + url, {headers: headers}).then(function (response) {
          console.log(response.data);
          cb(response);
        });
    },
    isLoggedin : getIsLoggedIn()
  };
}]);
