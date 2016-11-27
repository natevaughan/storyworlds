app
.controller('headerCtrl', ['$scope', '$http', '$state', function($scope, $http, $state) {
    $scope.data = $state.current.data;
    if (!$state.current.data.user) {
        $http.get('player/').then(function(response) {
            $state.current.data.user = response.data;
        }, function(response) {
            // do something
        });
    }
}])