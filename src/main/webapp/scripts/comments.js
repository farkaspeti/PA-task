function onLoadComments() {
    const el = this;
    const postId = el.getAttribute('post-id');

    const params = new URLSearchParams();
    params.append('postId', postId);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onCommentsReceived);
    xhr.open('GET', 'comments?' + params.toString());
    xhr.send();
}

function onCommentsReceived() {
    const text = this.responseText;
    const commentsList = JSON.parse(text);
    showContents(['comment-wall','landing-content','post-wall','post-creator']);

    const divEl = document.getElementById('comment-wall');
    while (divEl.firstChild) {
        divEl.removeChild(divEl.firstChild);
    }
    divEl.appendChild(createCommentsList(commentsList));
}

function createCommentsList(commentsList) {
    const ulEl = document.createElement('ul');

    const h4El = document.createElement('h4');
    h4El.textContent = 'Comments: ';
    ulEl.appendChild(h4El);

    for (let i = 0; i < commentsList.length; i++) {
        const comment = commentsList[i];

        const pEl = document.createElement('p');
        pEl.appendChild(document.createTextNode(`${comment.commentText}`));

        const liEl = document.createElement('li');
        liEl.appendChild(pEl);

        ulEl.appendChild(liEl);
    }
    return ulEl;
}

function onCloseCommentButtonClicked() {
    showContents(['landing-content','post-wall','post-creator']);
}
