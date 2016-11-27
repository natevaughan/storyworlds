angular.module('storyworlds.messageService', [
    'storyworlds.state',
    'storyworlds.responseParserService'
])
.service('messageService', ['$http', 'responseParserService', function($http, responseParserService) {

    var messages = [];
    var trim = function () {
        if (messages.length > maxMessages) {
            messages.splice(0, messages.length - maxMessages);
        }
    };

    // private count with setter
    var messageCount = 1;
    var maxMessages = 20; // internal and not configurable

    return {
        getMessages : function() {
            return messages.slice(messages.length - messageCount, messages.length);
        },
        push : function(item) {
            messages.push(item);
        },
        send: function(command) {
              if (!command || !command.text)
                  return;
              var text = command.text;
              command.text = '';
              $http.post('player/action', {'command': text })
              .then(function(response) {
                  responseParserService.parse(response);
                  messages.push({text:response.data.message.text});
                  trim();
              }, function(response) {
                  messages.push({text:response.data.message});
                  trim();
              });
          },
        trim : trim,
        setMessageCount : function(number) {
            messageCount = number;
        }
    }

}]);