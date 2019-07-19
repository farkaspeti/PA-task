function onLoadComments() {
    const el = this;
    const postId = el.getAttribute('post-id');

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onCommentsReceived);
    xhr.open('GET', '/comments?' + postId);
    xhr.send();
}

function onCommentsReceived() {
    const text = this.responseText;
    const commentsList = JSON.parse(text);
    const postId = commentsList[0].postId;
    const commentIds = document.getElementsByClassName('comments');

    for (let i = 0; i < commentIds.length; i++) {
        const comment = commentIds[i];
        if (comment.getAttribute('id') !== postId) {
            comment.remove();
        }
    }

    const divEl = document.getElementById(postId);
    if (divEl.childNodes.length <= 1) {
        divEl.appendChild(createCommentsList(comments));
    }

}