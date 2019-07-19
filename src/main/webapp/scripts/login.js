function onLoginResponse() {
    if (this.status === OK) {
        const user = JSON.parse(this.responseText);
        setAuthorization(user);
        if (hasAuthorization()) {
            showContents(['landing-content','post-wall']);
            onProfileLoad(getAuthorization());
            onLoadPosts();
        }
    }else if(this.status === UNAUTHORIZED){
        alert("Your email address or password was incorrect!");
    }
}

function onLoginButtonClicked() {
    const loginFormEl = document.forms['login-form'];

    const emailInputEl = loginFormEl.querySelector('input[name="email"]');
    const passwordInputEl = loginFormEl.querySelector('input[name="password"]');

    const email = emailInputEl.value;
    const password = passwordInputEl.value;

    const params = new URLSearchParams();
    params.append('email', email);
    params.append('password', password);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onLoginResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'login');
    xhr.send(params);
}
function backToSignUpButtonClicked() {
    showContents(['signUp-content', 'welcome-content']);
}
