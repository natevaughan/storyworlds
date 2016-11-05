var ajax = {
    get : function(url) {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url);
        xhr.send(null);

        xhr.onreadystatechange = function () {
            var DONE = 4;
            var OK = 200;
            if (xhr.readyState === DONE) {
                if (xhr.status === OK) {
                    var response = JSON.parse(xhr.responseText);
                    return response;
                } else {
                    console.log('Error: ' + xhr.status); // An error occurred during the request.
                }
            }
        }
    }
}
console.log(ajax.get('storyworld/'));

