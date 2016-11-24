angular
.module('storyworlds', ['storyworlds.state'])
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
.controller('landingCtrl', ['$scope', '$http', '$state', function($scope, $http, $state) {
    $http.get('storyworld/').then(function(response) {
        $scope.storyworlds = response.data;
    })
    $scope.play = function(storyworld) {
        $state.go('play', {storyworldId : storyworld.id});
    }
}])
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
}])
.controller('playCtrl', ['$scope', '$http','$window', 'currentProgress', function($scope, $http, $window, currentProgress  ) {
    $scope.messages = [];
    $scope.storyworld = currentProgress.storyworld;
    $scope.command = {text:''};
    $scope.send = function(command) {
        if (!command || !command.text)
            return;
        var text = command.text;
        command.text = '';
        $http.post('player/action', {'command': text })
        .then(function(response) {
            $scope.messages.push({text:response.data.message.text});
            trimLatest($scope.messages, 1);
        }, function(response) {
            $scope.messages.push({text:response.data.message});
            trimLatest($scope.messages, 1);
        });
    }
    function trimLatest(array, size) {
        if (array.length > size) {
            array.splice(0, array.length - size);
        }
    }

    $scope.send({text:'status'});
    $window.document.getElementById('user-input').focus();
}])
angular
.module('storyworlds.state', ['ui.router'])
.config(['$stateProvider','$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    var abstractHeader = {controller: 'headerCtrl',
                          templateUrl: 'app/header/header.html'};

    var data = {user:null};

    $stateProvider
        .state('home', {
            url: '/',
            views: {
                header : {template: '', controller: 'headerCtrl'},
                body : {
                    controller: 'landingCtrl',
                    templateUrl: 'app/landing/landing.html'
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
                    templateUrl: 'app/create/create.html'
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
                    templateUrl: 'app/play/play.html'
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