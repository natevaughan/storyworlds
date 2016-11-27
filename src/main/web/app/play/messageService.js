angular.module('storyworlds.messageService', [
    'storyworlds.state',
    'storyworlds.responseParserService'
])
.service('messageService', ['$http', 'responseParserService', function($http, responseParserService) {

    var messages = [];

    function trimLatest(array, size) {
        if (array.length > size) {
            array.splice(0, array.length - size);
        }
    }

    return {
        messages : messages,
        send: function(command) {
              if (!command || !command.text)
                  return;
              var text = command.text;
              command.text = '';
              $http.post('player/action', {'command': text })
              .then(function(response) {
                  responseParserService.parse(response);
                  messages.push({text:response.data.message.text});
                  trimLatest(messages, 1);
              }, function(response) {
                  messages.push({text:response.data.message});
                  trimLatest(messages, 1);
              });
          }
    }
}]);