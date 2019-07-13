function onLoadComments() {
    const el = this;
    const postId = el.getAttribute('post-id');

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onCommentsReceived);
    xhr.open('GET','/comments?' + postId);
    xhr.send();
}