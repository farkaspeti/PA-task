function onLoadPosts() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPostsReceived);
    xhr.open('GET','posts');
    xhr.send();

}