var nnyApp = angular.module('banking');

nnyApp.constant('nnyConst', {
    test: "hi",
    title : " Bank Of Srilanka",
    ENDPOINT_URI: "http://localhost:8080",
    BAD_TOKEN_ERROR: "Full authentication is required to access this resource.",
    UserRoles: {
        Admin: '3',
        CustomerM: '2',
        CustomerP: '1',
        Stranger: '0'
    }
});
