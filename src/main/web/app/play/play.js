app
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