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

        $scope.saveTrip = function() {
            if($scope.trip.scenes) {
                for(var i = 0; i < $scope.trip.scenes.length; i++) {
                    var scene = $scope.trip.scenes[i];
                    scene.orderNum = i;
                }
            }

            if(!$scope.trip.id) {
                $http.post('/trip', $scope.trip)
                    .then(
                        function(response){
                            console.log(response);
                            $http.get('/trips').then(
                                function(response) {
                                console.log('successed');
                                console.log(response);
                                    if(response.data) {
                                        $scope.trips = response.data.resultContent;
                                    }
                                },
                                function(response) {

                                }
                            )
                        },
                        function(response){
                            console.log('fail');
                            console.log(response);
                        }
                );
            }else {
                $http.put('/trip', $scope.trip)
                    .then(
                    function(response){
                        console.log(response);
                        $http.get('/trips').then(
                            function(response) {
                                console.log('successed');
                                console.log(response);
                                if(response.data) {
                                    $scope.trips = response.data.resultContent;
                                }
                            },
                            function(response) {

                            }
                        )
                    },
                    function(response){
                        console.log('fail');
                        console.log(response);
                    }
                );
            }
        };

        $scope.removeTrip = function() {
            $http.delete('/trip/'+$scope.trip.id).then(
                function(response) {
                    console.log(response);
                },
                function(response) {
                    console.log(response);
                }
            )
        };

        $scope.addScene = function () {
            $scope.trip.scenes.push(new Scene());
        };

        $scope.detail = function(trip) {
            $scope.trip = trip;
            $scope.isOpenAddLayer = true;
        }
    });

