/**
 * Created by rang on 2015-09-08.
 *
 */
//로그인페이지
angular.module('loginModule', [
    'loginModule.controllers'
]);

angular.module('loginModule.controllers', [])
    .controller('loginCtrl', function($scope, $http) {
        $scope.member = new Member();
        $scope.loginClick = function () {
            sendPost("/main", "id="+$scope.member.id+"&password="+$scope.member.password);
        };

        //for test
        $scope.member.id = 'bluemirr5';
        $scope.member.password = '1234';
    });
