function onLoadPosts() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPostsReceived);
    xhr.open('GET','protected/posts');
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
        buttonEl.addEventListener('click', onLoadComments);
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

function onPostAClicked() {
    showContents(['landing-content','post-modify'])
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onUpdatePostsReceived);
    xhr.open('GET','protected/update_posts');
    xhr.send();
}
