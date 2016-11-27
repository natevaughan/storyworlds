angular.module('responseParserService', ['storyworlds.state'])
.service('responseParserService', ['$state', function($state) {
    return {
        parse : function(response) {
            return response + "hahaha!";
        },
        add : function(number) {
            return number + 1;
        }
    }
}])