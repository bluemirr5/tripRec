/**
 * Created by rang on 2015-09-08.
 *
 */
//main페이지
angular.module('mainModule', [
    'mainModule.controllers'
]);

angular.module('mainModule.controllers', [])
    .controller('mainCtrl', function($scope) {
        $scope.isOpenAddLayer = false;
        if(pageData_trips) {
            $scope.trips = pageData_trips;
        }
    });

