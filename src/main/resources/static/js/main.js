document.addEventListener("DOMContentLoaded", function() {
  let shortenUnit = new ShortenURLUnit();
  shortenUnit.shortenUrl();
});

function ShortenURLUnit(){}
ShortenURLUnit.prototype = {
  shortenUrl : function(){
    let urlText = document.getElementById('url-input');
    let urlButton = document.getElementById('url-button');
    let result =  document.getElementById('result').lastChild.previousSibling;
    let count =  document.getElementById('count').lastChild.previousSibling;

    var httpRequest = new XMLHttpRequest();
    urlButton.addEventListener('click', function(){
      httpRequest.onload = function() {
        if (httpRequest.readyState == XMLHttpRequest.DONE && httpRequest.status == 200 ) {
          let urlShorten = JSON.parse(httpRequest.responseText);
          result.value = urlShorten.data.shortUrl;
          count.value = urlShorten.data.count;
        }
      }
      let data = {
        original : encodeURI(urlText.value)
      }
      httpRequest.open("POST", "/shortner", true);
      httpRequest.setRequestHeader('Content-Type', 'application/json');
      httpRequest.send(JSON.stringify(data));
    })
  }
}
