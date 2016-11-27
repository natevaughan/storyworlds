angular.module('storyworlds.playCtrl', [
    'storyworlds.state',
    'storyworlds.messageService'
])
.controller('playCtrl', [
    '$scope',
    '$window',
    'currentProgress',
    'messageService',
    'responseParserService', function($scope, $window, currentProgress,  messageService, responseParserService) {
    $scope.messages = messageService.messages;
    $scope.storyworld = currentProgress.storyworld;
    $scope.command = {text:''};
    $scope.send = messageService.send;
    $scope.send({text:'status'});
    $window.document.getElementById('user-input').focus();
}])