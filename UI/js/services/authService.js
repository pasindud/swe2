/* Author : Dushan Galappaththi */
var nnyApp = angular.module('banking');
nnyApp.factory('AuthService',['$http','nnyConst','$rootScope',function ($http,nnyConst,$rootScope) {

  function requestor(username,password)
  {
    var result = $http.get('SampleJSON/Auth/auth.json')
    .then(function(res) {
      return res.data[0];
    })
    // debugger;
    return result;
  }

  function setErrorDialog(status) {
    var errorContent = {
      Title : "",
      Body : ""
    }

    switch(status)
    {
      case 401 :
        var error = [""];
        error.push("Please enter valid Credentials!");
        errorContent.Title = "Invalid Credentials";
        errorContent.Body = error;
        break;
      case -1 :
        var error = [""];
        error.push("Connection Refused or Invalid Request URI");
        errorContent.Title = "Connection Error";
        errorContent.Body = error;
        break;
    }
    $rootScope.ErrorDialog = errorContent;
  }

  function getIsLoggedIn() {
    if($rootScope.authData !== undefined)
    {
      if($rootScope.authData !== "")
      {
        return true;
      }else {
        return false;
      }
    }
    else {
      return false;
    }

  }
  return  {
    authenticate : function () {
      var url = nnyConst.ENDPOINT_URI +"/api/auth";
      var authData = btoa("xyz:xyz");
      console.log(authData);
      var headers = {
        "Content-Type": "application/json"
      };
      $http.defaults.headers.common['Authorization'] = 'Basic ' + authData;
      return $http.get(nnyConst.ENDPOINT_URI + "/api/auth", {headers: headers});
    },
    getAuthToken : function (username, password) {
      var url = nnyConst.ENDPOINT_URI +"/api/auth";
      var authData = btoa(username + ":" + password);
      console.log(authData);
      $http.defaults.headers.common['Authorization'] = 'Basic ' + authData;
      $http.defaults.headers.common['Content-Type'] = 'application/json';
      return $http.get(url).then(function (response) {
        console.log(response);
        if(response.status == 200)
        {
          var temp = response.data;
          console.log(temp);
          $rootScope.authData = {
            accessToken : temp.session,
            accessLevel : temp.AccessLevel,
            userId : temp.userId
          };
        }else {
          setErrorDialog(response.status);
        }
      });
    },
    getRequest : function (url, data, cb) {
      var headers = {"x-auth-token": $rootScope.authData.accessToken};
      console.log(headers);
      $http.get(nnyConst.ENDPOINT_URI + url, {headers: headers}).then(function (response) {
        console.log(response.data);
        cb(response);
      });
    },
    getRequestPost : function (url, data, cb) {
      // var headers = {"x-auth-token": $rootScope.authData.accessToken};
      $http({
          method: 'POST',
          url: nnyConst.ENDPOINT_URI + url,
          data: data
      })
    },
    isLoggedin : function () {
      return getIsLoggedIn();
    },
    isAuthRoute : function(url)
    {
      // debugger;
      if(url == "/login"){
        return true;
      }
      else {
        return false;
      }
    }
  };
}])
.config(['$httpProvider', function($httpProvider) {
  $httpProvider.defaults.useXDomain = true;
  $httpProvider.interceptors.push(['$q', function ($q) {
    return {
      'responseError': function (rejection) {
        return rejection;
      }
    }
  }])
}]);
