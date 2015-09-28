/**
 * Created by rang on 2015-09-08.
 *
 */
//main페이지
angular.module('mainModule', [
    'mainModule.controllers'
]);

angular.module('mainModule.controllers', ['angularFileUpload'])
    .controller('mainCtrl', function($scope, $http, FileUploader) {
        var uploader = $scope.uploader = new FileUploader({
            url: '/sceneimage'
        });
        $scope.temp = {};

        uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
            //console.info('onWhenAddingFileFailed', item, filter, options);
        };
        uploader.onAfterAddingFile = function(fileItem) {
            //console.info('onAfterAddingFile', fileItem);
            uploader.uploadAll();
        };
        uploader.onAfterAddingAll = function(addedFileItems) {
            //console.info('onAfterAddingAll', addedFileItems);
        };
        uploader.onBeforeUploadItem = function(item) {
            //console.info('onBeforeUploadItem', item);
        };
        uploader.onProgressItem = function(fileItem, progress) {
            //console.info('onProgressItem', fileItem, progress);
            for(var i = 0; i < $scope.trip.scenes.length; i++ ) {
                var uploadedScene = $scope.trip.scenes[i];
                if(uploadedScene.orderNum == fileItem.orderNum) {
                    uploadedScene.uploadPercent = progress;
                    break;
                }
            }
        };
        uploader.onProgressAll = function(progress) {
            //console.info('onProgressAll', progress);
        };
        uploader.onSuccessItem = function(fileItem, response, status, headers) {
            //console.info('onSuccessItem', fileItem, response, status, headers);
        };
        uploader.onErrorItem = function(fileItem, response, status, headers) {
            //console.info('onErrorItem', fileItem, response, status, headers);
        };
        uploader.onCancelItem = function(fileItem, response, status, headers) {
            //console.info('onCancelItem', fileItem, response, status, headers);
        };
        uploader.onCompleteItem = function(fileItem, response, status, headers) {
            //console.info('onCompleteItem', fileItem, response, status, headers);
            for(var i = 0; i < $scope.trip.scenes.length; i++ ) {
                var uploadedScene = $scope.trip.scenes[i];
                if(uploadedScene.orderNum == fileItem.orderNum) {
                    uploadedScene.picturePath = response.picturePath;
                    uploadedScene.metaTags = response.metaTags;
                    break;
                }
            }
            //console.log($scope.trip);
        };
        uploader.onCompleteAll = function() {
            //console.info('onCompleteAll');
        };

        if(pageData_trips) {
            $scope.trips = pageData_trips;
        }
        $scope.isOpenAddLayer = false;
        $scope.trip = new Trip();


        $scope.saveTrip = function() {
            for(var i = 0; $scope.trip.scenes && i < $scope.trip.scenes.length; i++) {
                var scene = $scope.trip.scenes[i];
                scene.orderNum = i;
            }

            var httpMethod;
            if(!$scope.trip.id) {
                httpMethod = $http.post;
            }else {
                httpMethod = $http.put;
            }
            httpMethod('/trip', $scope.trip).then(
                function(){
                    alert("save");
                    $scope.trip = new Trip();
                    refreshTripList();
                },
                function(response){
                    console.log(response);
                    alert("network error");
                }
            );
        };

        $scope.removeTrip = function() {
            $http.delete('/trip/'+$scope.trip.id).then(
                function() {
                    alert("deleted");
                    $scope.trip = new Trip();
                    refreshTripList();
                },
                function(response) {
                    console.log(response);
                }
            )
        };

        var sceneTempOrderNum = 0;
        $scope.scenePages = [];
        $scope.addScene = function() {
            var scene = new Scene();
            scene.orderNum = sceneTempOrderNum;
            $scope.trip.scenes.push(scene);
            sceneTempOrderNum++;
            if($scope.trip.scenes.length % 2 == 1) {
                var scenePage = [];
                scenePage.push(scene);
                $scope.scenePages.push(scenePage);
            } else {
                $scope.scenePages[$scope.scenePages.length-1].push(scene);
            }
        };
        $scope.removeScene = function(scene) {
            var index = $scope.trip.scenes.indexOf(scene);
            $scope.trip.scenes.splice(index, 1);
            //TODO
        };

        $scope.detail = function(trip) {
            $scope.trip = trip;
            $scope.isOpenAddLayer = true;
        };

        function refreshTripList() {
            $http.get('/trips').then(
                function(response) {
                    if(response.data) {
                        $scope.trips = response.data;
                    } else {console.error(response);}
                },
                function(response) {
                    console.error(response);
                    alert("network error");
                }
            )
        }
    });

