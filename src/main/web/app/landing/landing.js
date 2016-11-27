app
.controller('landingCtrl', ['$scope', '$http', '$state', function($scope, $http, $state) {
    $http.get('storyworld/').then(function(response) {
        $scope.storyworlds = response.data;
    })
    $scope.play = function(storyworld) {
        $state.go('play', {storyworldId : storyworld.id});
    }
}])