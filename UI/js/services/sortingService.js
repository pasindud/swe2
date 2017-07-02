/* Author : Dushan Galappaththi */
var nnyApp = angular.module('banking');
nnyApp.factory('SortingService', ['$filter', 'nnyConst', '$rootScope', function($filter, nnyConst, $rootScope) {
    function sortObj(recordSet, sortField, isDescending) {
        var sortedObj = $filter('orderBy')(recordSet, sortField, isDescending);
        return sortedObj;
    }

    return {
        sortObjBy: function(recordSet, sortField, isDescending = false) {
            return sortObj(recordSet, sortField, isDescending);
        },
        sortNumber: function() {
            //TODO
            return 1;
        }
    }
}]);