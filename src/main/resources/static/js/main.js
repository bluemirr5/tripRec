/**
 * Created by rang on 2015-09-08.
 *
 */
//main페이지
angular.module('mainModule', [
    'mainModule.controllers'
]);

angular.module('mainModule.controllers', [])
    .controller('mainCtrl', function($scope, $http) {
        if(pageData_trips) {
            $scope.trips = pageData_trips;
        }
        $scope.isOpenAddLayer = false;
        $scope.trip = new Trip();

        $scope.addTrip = function() {
            if($scope.trip.scenes) {
                for(var i = 0; i < $scope.trip.scenes.length; i++) {
                    var scene = $scope.trip.scenes[i];
                    scene.orderNum = i;
                }
            }

            $http.post('/addTrip', $scope.trip)
                .then(
                    function(response){
                        console.log('successed');
                        console.log(response);
                    },
                    function(response){
                        console.log('fail');
                        console.log(response);
                    }
            );
        };
        $scope.addScene = function () {
            $scope.trip.scenes.push(new Scene());
        };
    });

