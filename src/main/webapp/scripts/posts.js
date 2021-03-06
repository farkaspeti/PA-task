function onLoadPosts() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPostsReceived);
    xhr.open('GET', 'protected/posts');
    xhr.send();
}

function onPostsReceived() {
    const text = this.responseText;
    const postsList = JSON.parse(text);

    const divEl = document.getElementById('post-wall');
    while (divEl.firstChild) {
        divEl.removeChild(divEl.firstChild);
    }
    divEl.appendChild(createPostsList(postsList));
}

function createPostsList(postsList) {
    const ulEl = document.createElement('ul');

    for (let i = 0; i < postsList.length; i++) {
        const post = postsList[i];

        const strongEl = document.createElement('strong');
        strongEl.appendChild(document.createTextNode(`${post.firstName} `));
        strongEl.appendChild(document.createTextNode(`${post.lastName}`));
        const pEl = document.createElement('p');
        pEl.appendChild(document.createTextNode(`${post.content}`));

        const postIdAttr = post.id;

        const liEl = document.createElement('li');
        liEl.setAttribute('id', postIdAttr)
        liEl.appendChild(strongEl);
        liEl.appendChild(pEl);

        ulEl.appendChild(liEl);

        const buttonEl = document.createElement('button');
        buttonEl.textContent = "View Comments";
        buttonEl.setAttribute('post-id', postIdAttr);
        buttonEl.setAttribute('id', 'comment-button');
        buttonEl.addEventListener('click', onLoadComments);
        ulEl.appendChild(buttonEl);
        const pfillerEl = document.createElement("p");
        ulEl.appendChild(pfillerEl);
    }
    return ulEl;
}

function createUpdatePostsList(postsList) {
    const ulEl = document.createElement('ul');

    for (let i = 0; i < postsList.length; i++) {
        const post = postsList[i];

        const strongEl = document.createElement('strong');
        strongEl.appendChild(document.createTextNode(`${post.firstName} `));
        strongEl.appendChild(document.createTextNode(`${post.lastName}`));
        const pEl = document.createElement('p');
        pEl.appendChild(document.createTextNode(`${post.content}`));

        const postIdAttr = post.id;

        const liEl = document.createElement('li');
        liEl.setAttribute('id', postIdAttr)
        liEl.appendChild(strongEl);
        liEl.appendChild(pEl);

        ulEl.appendChild(liEl);

        const buttonEl = document.createElement('button');
        buttonEl.textContent = "Update or Delete";
        buttonEl.setAttribute('post-id', `${post.id}`);
        buttonEl.setAttribute('content', `${post.content}`);
        buttonEl.setAttribute('id', 'updatePost-button');
        buttonEl.addEventListener('click', onUpdatePostsClicked);
        ulEl.appendChild(buttonEl);
        const pfillerEl = document.createElement("p");
        ulEl.appendChild(pfillerEl);
    }
    return ulEl;
}

function onNewPostButtonClicked() {
    const newPostFormEl = document.forms['newPost-form'];
    const postContentEl = newPostFormEl.querySelector('input[name="postContent"]');
    const content = postContentEl.value;

    const userId = getAuthorization().id;
    const userFirstName = getAuthorization().firstName;
    const userLastName = getAuthorization().lastName;

    const params = new URLSearchParams();
    params.append('content', content);
    params.append('userId', userId);
    params.append('userFirstName', userFirstName);
    params.append('userLastName', userLastName);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onLoadPosts);
    xhr.open('POST', 'protected/posts');
    xhr.send(params);
}

function onUpdatePostButtonClicked() {
    showContents(['landing-content', 'post-wall', 'post-creator']);
    const divEl = document.getElementById("postUpdate-content");
    const postId = divEl.dataset.postId;
    const newPostFormEl = document.forms['postUpdate-form'];
    const postContentEl = newPostFormEl.querySelector('input[name="postUpdateContent"]');
    const content = postContentEl.value;

    const params = new URLSearchParams();
    params.append('content', content);
    params.append('postId', postId);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onLoadPosts);
    xhr.open('POST', 'update_posts');
    xhr.send(params);
}

function onUpdatePostsClicked() {
    const content = this.getAttribute('content');
    const postId = this.getAttribute('post-id');
    const divEl = document.getElementById("postUpdate-content");
    divEl.dataset.postId = postId;
    showContents(['landing-content', 'postUpdate-content']);
    const postUpdateFormEl = document.forms['postUpdate-form'];
    const postContentEl = postUpdateFormEl.querySelector('input[name="postUpdateContent"]');
    postContentEl.value = content;

    const updateButtonEl = document.getElementById('updateButton');
    updateButtonEl.addEventListener('click', onUpdatePostButtonClicked);

    const deleteButtonEl = document.getElementById('deleteButton');
    deleteButtonEl.addEventListener('click', onDeletePostClicked);
}

function onPostAClicked() {
    const userId = getAuthorization().id;
    const userType = getAuthorization().userType;
    const params = new URLSearchParams();
    params.append('userId', userId);
    showContents(['landing-content', 'post-modify'])
    if (userType === "ADMIN") {
        const xhr = new XMLHttpRequest();
        xhr.addEventListener('load', onUpdatePostsReceived);
        xhr.open('GET', 'update_posts?' + params.toString());
        xhr.send();

    } else {
        const xhr = new XMLHttpRequest();
        xhr.addEventListener('load', onUpdatePostsReceived);
        xhr.open('GET', 'update_posts?' + params.toString());
        xhr.send();
    }
}

function onUpdatePostsReceived() {
    const text = this.responseText;
    const userType = getAuthorization().userType;
    const postsList = JSON.parse(text);

    const divEl = document.getElementById('post-modify');
    while (divEl.firstChild) {
        divEl.removeChild(divEl.firstChild);
    }
    if (userType === "ADMIN") {
        divEl.appendChild(createAdminUpdatePostsList(postList));
    } else {
        divEl.appendChild(createUpdatePostsList(postsList));
    }
}

function onClosePostUpdateClicked() {
    showContents(['landing-content', 'post-wall', 'post-creator'])
}

function onDeletePostClicked() {
    showContents(['landing-content', 'post-wall', 'post-creator']);
    const divEl = document.getElementById("postUpdate-content");
    const postId = divEl.dataset.postId;
    const params = new URLSearchParams();
    params.append('id', postId);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onLoadPosts);
    xhr.open('POST', 'delete_post');
    xhr.send(params);

}
