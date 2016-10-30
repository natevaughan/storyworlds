var xhr = new XMLHttpRequest();
xhr.open('GET', 'storyworld/');
xhr.send(null);

xhr.onreadystatechange = function () {
  var DONE = 4; // readyState 4 means the request is done.
  var OK = 200; // status 200 is a successful return.
  if (xhr.readyState === DONE) {
    if (xhr.status === OK) {
      var response = JSON.parse(xhr.responseText);
      console.dir(response);
      response.forEach(function(storyworld) {
        document.getElementById("select-storyworld").innerHTML += storyworld.title;
      })
    } else {
      console.log('Error: ' + xhr.status); // An error occurred during the request.
    }
  }
};