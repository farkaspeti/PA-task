const OK = 200;
const BAD_REQUEST = 400;
const UNAUTHORIZED = 401;
const NOT_FOUND = 404;
const INTERNAL_SERVER_ERROR = 500;

let loginContentDivEl;
let profileContentDivEl;
let signUpContentDivEl;
let landingContentDivEl;
let postWallContentDivEl;

function newInfo(targetEl, message) {
    newMessage(targetEl, 'info', message);
}

function newError(targetEl, message) {
    newMessage(targetEl, 'error', message);
}

function newMessage(targetEl, cssClass, message) {
    clearMessages();

    const pEl = document.createElement('p');
    pEl.classList.add('message');
    pEl.classList.add(cssClass);
    pEl.textContent = message;

    targetEl.appendChild(pEl);
}

function clearMessages() {
    const messageEls = document.getElementsByClassName('message');
    for (let i = 0; i < messageEls.length; i++) {
        const messageEl = messageEls[i];
        messageEl.remove();
    }
}

function showContents(ids) {
    const contentEls = document.getElementsByClassName('content');
    for (let i = 0; i < contentEls.length; i++) {
        const contentEl = contentEls[i];
        if (ids.includes(contentEl.id)) {
            contentEl.classList.remove('hidden');
        } else {
            contentEl.classList.add('hidden');
        }
    }
}

function removeAllChildren(el) {
    while (el.firstChild) {
        el.removeChild(el.firstChild);
    }
}

function onNetworkError(response) {
    document.body.remove();
    const bodyEl = document.createElement('body');
    document.appendChild(bodyEl);
    newError(bodyEl, 'Network error, please try reloaing the page');
}

function onOtherResponse(targetEl, xhr) {
    if (xhr.status === NOT_FOUND) {
        newError(targetEl, 'Not found');
        console.error(xhr);
    } else {
        const json = JSON.parse(xhr.responseText);
        if (xhr.status === INTERNAL_SERVER_ERROR) {
            newError(targetEl, `Server error: ${json.message}`);
        } else if (xhr.status === UNAUTHORIZED || xhr.status === BAD_REQUEST) {
            newError(targetEl, json.message);
        } else {
            newError(targetEl, `Unknown error: ${json.message}`);
        }
    }
}

function hasAuthorization() {
    return localStorage.getItem('user') !== null;
}

function setAuthorization(user) {
    return localStorage.setItem('user', JSON.stringify(user));
}

function getAuthorization() {
    return JSON.parse(localStorage.getItem('user'));
}

function setUnauthorized() {
    return localStorage.removeItem('user');
}

function onCloseToWelcomeClicked() {
    showContents(['welcome-content'])
}

function onLoad() {
    loginContentDivEl = document.getElementById('login-content');
    profileContentDivEl = document.getElementById('profile-content');
    signUpContentDivEl = document.getElementById('signUp-content');
    landingContentDivEl = document.getElementById('landing-content');
    postWallContentDivEl = document.getElementById('post-wall');

    const loginButtonEl = document.getElementById('login-button');
    loginButtonEl.addEventListener('click', onLoginButtonClicked);

    const toLoginButtonEl = document.getElementById('toLogin-button');
    toLoginButtonEl.addEventListener('click', backToLoginButtonClicked);

    const signUpButtonEl = document.getElementById('signUp-button');
    signUpButtonEl.addEventListener('click', onSignUpButtonClicked);

    const toSignUpButtonEl = document.getElementById('toSignUp-button');
    toSignUpButtonEl.addEventListener('click', backToSignUpButtonClicked);

    const closeLoginButtonEl = document.getElementById('closeLogin-button');
    closeLoginButtonEl.addEventListener('click', onCloseToWelcomeClicked);

    const closeProfileButtonEl = document.getElementById('closeProfile-button');
    closeProfileButtonEl.addEventListener('click', onCloseProfileButtonClicked);

    const closeSignUpButtonEl = document.getElementById('closeSignUp-button');
    closeSignUpButtonEl.addEventListener('click', onCloseToWelcomeClicked);

    const logoutAEl = document.getElementById('logout-a');
    logoutAEl.addEventListener('click', onLogoutAClicked);

    const profileAEl = document.getElementById('profile-a');
    profileAEl.addEventListener('click', onProfileAClicked);

    const postCreatorButtonEl = document.getElementById('postCreator-button');
    postCreatorButtonEl.addEventListener('click', onNewPostButtonClicked);


    if (hasAuthorization()) {
        onProfileLoad(getAuthorization());
    }
}

document.addEventListener('DOMContentLoaded', onLoad);
