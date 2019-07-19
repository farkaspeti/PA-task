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
    }
    return ulEl;
}