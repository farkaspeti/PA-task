function onLoadComments() {
    const el = this;
    const postId = el.getAttribute('post-id');

    const params = new URLSearchParams();
    params.append('postId', postId);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onCommentsReceived);
    xhr.open('GET', '/comments?' + params.toString());
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
        divEl.appendChild(createCommentsList(commentsList));
    }

    function createCommentsList(commentsList) {
        const ulEl = document.createElement('ul');
        ulEl.classList.add('comments');

        const h4El = document.createElement('h4');
        h4El.textContent = 'Comments: ';
        ulEl.appendChild(h4El);

        for (let i = 0; i < commentsList.length; i++) {
            const comment = commentsList[i];
            ulEl.setAttribute('id', comment.postId);

            const strongEl = document.createElement('strong');
            strongEl.textContent = comment.name;

            const h5El = document.createElement('h5');
            h5El.textContent = comment.email;

            const pEl = document.createElement('p');
            pEl.appendChild(strongEl);
            pEl.appendChild(document.createTextNode(`: ${comment.commentText}`));
            pEl.appendChild(h5El);

            const liEl = document.createElement('li');
            liEl.appendChild(pEl);

            ulEl.appendChild(liEl);
        }
        return ulEl;
    }
}