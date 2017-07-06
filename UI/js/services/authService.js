/* Author : Dushan Galappaththi */
var nnyApp = angular.module('banking');
nnyApp.factory('AuthService', ['$http', 'nnyConst', '$rootScope', '$state', function($http, nnyConst, $rootScope, $state) {

        function requestor(username, password) {
            var result = $http.get('SampleJSON/Auth/auth.json')
                .then(function(res) {
                    return res.data[0];
                })
            // debugger;
            return result;
        }

        function setErrorDialog(response) {
            var errorContent = {
                Title: "",
                Body: ""
            }
            var data = response.data;
            console.log(data);
            switch (response.status) {
                case 401:
                    var error = [];
                    if(data.error !== "null"){
                        error.push(data.error);
                      }
                      else {
                        error.push("Please enter valid Credentials!");
                      }
                    errorContent.Title = "Authentication Error!";
                    errorContent.Body = error;
                    break;
                case -1:
                    var error = [];
                    error.push("Connection Refused or Invalid Request URI");
                    errorContent.Title = "Connection Error";
                    errorContent.Body = error;
                    break;
            }
            $rootScope.ErrorDialog = errorContent;
        }

        function getIsLoggedIn() {
            var authDataLocalStorage = localStorage.getItem("accessToken");
            console.log(authDataLocalStorage);
            if (authDataLocalStorage) {
                $rootScope.authData = JSON.parse(authDataLocalStorage);
                $http.defaults.headers.common['x-auth-token'] = $rootScope.authData.accessToken;
                return true;
            } else {
                return false;
            }
        }
        return {
            authenticate: function() {
                var url = nnyConst.ENDPOINT_URI + "/api/auth";
                var authData = btoa("xyz:xyz");
                console.log(authData);
                var headers = {
                    "Content-Type": "application/json"
                };
                $http.defaults.headers.common['Authorization'] = 'Basic ' + authData;
                return $http.get(nnyConst.ENDPOINT_URI + "/api/auth", {
                    headers: headers
                });
            },
            getAuthToken: function(username, password, cb) {
                var url = nnyConst.ENDPOINT_URI + "/api/auth";
                $http.defaults.headers.common['Content-Type'] = 'application/json';

                $http({
                    method: 'GET',
                    url: url,
                    headers: {
                        'Authorization': 'Basic ' + btoa(username + ":" + password)
                    }
                }).then(function(response) {
                    console.log("REPONSE");
                    console.log(response.status);
                    if (response.status == 200) {
                        var temp = response.data;
                        $rootScope.authData = {
                            accessToken: temp.session,
                            accessLevel: temp.AccessLevel,
                            userId: temp.userId
                        };
                        console.log("Storing Credentials");
                        localStorage.setItem("accessToken", JSON.stringify($rootScope.authData))
                        // Set the x-auth-token globally.
                        $http.defaults.headers.common['x-auth-token'] = temp.session;
                        cb(response);
                    } else {
                        setErrorDialog(response);
                        cb(response);
                    }
                });
            },
            getRequest: function(url, data, cb) {
                // var headers = {"x-auth-token": $rootScope.authData.accessToken};
                // console.log(headers);
                var config = {
                    url: nnyConst.ENDPOINT_URI + url
                };
                if (data != null) {
                    config.method = "POST";
                    config.data = data;
                } else {
                    config.method = "GET";
                }

                $http(config).then(function(response) {
                    console.log(response.data);
                    if (response.status == 401) {
                        $state.go("login");
                        localStorage.clear()
                    };
                    cb(response);
                });
            },
            getExternal: function (url, data, cb) {
              var config = {
                  url: url
              };
              if (data != null) {
                  config.method = "POST";
                  config.data = data;
              } else {
                  config.method = "GET";
              }

              $http(config).then(function(response) {
                  console.log(response.data);
                  if (response.status == 401) {
                      $state.go("login");
                      localStorage.clear()
                  };
                  cb(response);
              });
            },
            isLoggedin: function() {
                return getIsLoggedIn();
            },
            isAuthRoute: function(url) {
                // debugger;
                if (url == "/login") {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }])
    .config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        $httpProvider.interceptors.push(['$q', function($q) {
            return {
                'responseError': function(rejection) {
                    return rejection;
                }
            }
        }])
    }]);
