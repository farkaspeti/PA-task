function onLoadPost() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPostsReceived);
    xhr.open('GET','post');
    xhr.send();

}