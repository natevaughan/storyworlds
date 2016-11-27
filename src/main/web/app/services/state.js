angular.module('storyworlds.state', ['ui.router'])
.config(['$stateProvider','$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    var abstractHeader = {controller: 'headerCtrl',
                          templateUrl: 'header/header.html'};

    var data = {user:null};

    $stateProvider
        .state('home', {
            url: '/',
            views: {
                header : {template: '', controller: 'headerCtrl'},
                body : {
                    controller: 'landingCtrl',
                    templateUrl: 'landing/landing.html'
                }

            },
            data: data
        })
        .state('create', {
            url: '/create',
            views: {
                header : abstractHeader,
                body : {
                    controller: 'createCtrl',
                    templateUrl: 'create/create.html'
                }
            },
            data: data
        })
        .state('play', {
            url: '/play/:storyworldId',
            views: {
                header : abstractHeader,
                body : {
                    controller: 'playCtrl',
                    templateUrl: 'play/play.html'
                }
            },
            resolve: {
                currentProgress : ['$http', '$stateParams', function($http, $stateParams) {
                    return $http.post("player/play/" + $stateParams.storyworldId, {}).then(function(response) {
                        return response.data;
                })
            }]},
            data: data
        })

}]);