angular.module('storyworlds.playCtrl', [
    'storyworlds.state',
    'storyworlds.messageService'
])
.controller('playCtrl', ['$scope', 'messageService','$window', 'currentProgress','responseParserService', function($scope, messageService, $window, currentProgress, responseParserService) {
    $scope.messages = [];
    $scope.storyworld = currentProgress.storyworld;
    $scope.command = {text:''};
    $scope.send = messageService.send;
    $scope.send({text:'status'});
    $window.document.getElementById('user-input').focus();
}])