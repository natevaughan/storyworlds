angular
.module('storyworlds', ['storyworlds.state'])
.controller('landingCtrl', ['$scope', '$http', '$state', function($scope, $http, $state) {
    $http.get('storyworld/').then(function(response) {
        $scope.storyworlds = response.data;
    })
    $scope.play = function(storyworld) {
        $state.go('play', {storyworldId : storyworld.id});
    }
}])
.controller('createCtrl', ['$scope', '$http',  function($scope, $http) {
    $scope.name = "nate";
}])
.controller('playCtrl', ['$scope', '$http','$window', 'currentStoryworld', function($scope, $http, $window, currentStoryworld  ) {
    $scope.messages = [];
    $scope.messages.push({text:currentStoryworld.entry.description});
    $scope.command = {text:''};
    $scope.send = function(command) {
        if (!command || !command.text)
            return;
        var text = command.text;
        $scope.messages.push({text:text});
        command.text = '';
        $http.post('player/action', {'command': text })
        .then(function(response) {
            $scope.messages.push({text:response.data.message.text});
        }, function(response) {
            $scope.messages.push({text:response.data.message});
        });
    }
    $scope.storyworld = currentStoryworld;
    $window.document.getElementById('user-input').focus();
}])
angular
.module('storyworlds.state', ['ui.router'])
.config(['$stateProvider','$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('landing', {
            url: '/',
            controller: 'landingCtrl',
            templateUrl: 'app/landing/landing.html'
        })
        .state('create', {
            url: '/create',
            controller: 'createCtrl',
            templateUrl: 'app/create/create.html'
        })
        .state('play', {
            url: '/play/:storyworldId',
            controller: 'playCtrl',
            templateUrl: 'app/play/play.html',
            resolve: {
                currentStoryworld : ['$http', '$stateParams', function($http, $stateParams) {
                    return $http.get("storyworld/" + $stateParams.storyworldId).then(function(response) {
                        return response.data;
                })
            }]}

        })

}]);