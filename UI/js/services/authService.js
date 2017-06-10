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

  function authenticate() {
    //TODO : token
    var auth = {
      username : "",
      accessLevel : "",
      accessToken : ""
    };

    var result = requestor();

    var accessToken = result.AccessToken;

    if(accessToken != "")
    {
      auth.username = result.Username;
      auth.accessLevel = nnyConst.UserRoles[result.AccessLevel],
      auth.accessToken = accessToken;
      isLoggedin = true;
    }
    else {
      isLoggedin = false;
    }
    return auth;
  }

  function getIsLoggedIn() {
    return isLoggedin;
  }
  return  {
    authenticate : authenticate(),
    isLoggedin : getIsLoggedIn()
  };
}]);
