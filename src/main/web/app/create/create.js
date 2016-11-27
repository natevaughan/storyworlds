angular.module('storyworlds.createCtrl', [
])
.controller('createCtrl', ['$scope', '$http',  function($scope, $http) {
    $scope.storyworld = {
            "@class" : "storyworlds.model.builder.WikiStoryworldBuilder",
            "title" : "",
            "description" : "",
            "entryText" : "",
            "visible" : true,
            "publiclyModifiable" : true,
            'color': '#111111',
            'backgroundColor': '#ffffff',
            "entryBuilder" : {
                "@class" :"storyworlds.model.builder.ImmutableLocationBuilder",
                "description" : ""
            }
        }

        $scope.create = function() {
            $http.post('storyworld/create', $scope.storyworld).then(function(response) {
                    alert(JSON.stringify(response));
                }, function (response) {
                    alert(response);
                });

        };
}]);