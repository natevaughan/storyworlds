angular
.module('storyworlds', ['storyworlds.state'])
.controller('landingCtrl', function($scope, $http) {
    $scope.name = "nate";
    $http.get("http://localhost:8080/storyworld/").then(function(response) {
        $scope.storyworlds = response.data;
    })
})
.controller('createCtrl', function($scope, $http) {
    $scope.name = "nate";
    $http.get("http://localhost:8080/storyworld/").then(function(response) {
        $scope.storyworlds = response.data;
    })
});

angular
.module('storyworlds.state', ['ui.router'])
.config(['$stateProvider','$urlRouterProvider',function($stateProvider, $urlRouterProvider) {
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

}]);