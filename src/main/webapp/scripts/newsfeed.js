function onLoadPosts() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPostsReceived);
    xhr.open('GET','posts');
    xhr.send();

}

function onPostsReceived() {
    const text = this.responseText;
    const postsList = JSON.parse(text);

    const divEl = document.getElementById('newsfeed-content');
    while (divEl.firstChild) {
        divEl.removeChild(divEl.firstChild);
    }
    divEl.appendChild(createPostsList(postsList));
}