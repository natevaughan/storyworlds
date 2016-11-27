angular.module('storyworlds.responseParserService', [
    'storyworlds.state'
])
.service('responseParserService', ['$state', function($state) {
    return {
        parse : function(response) {
            console.log(response.data);
//            if (response.data.createable) {
//                $state.go('home');
//            }
        },
        add : function(number) {
            return number + 1;
        }
    }
}])