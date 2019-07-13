function onSignUpResponse() {
    if (this.status === OK) {
        showContents(['login-content', 'welcome-content']);
    } else if (this.status === BAD_REQUEST) {
        alert("You've provided invalid data");
    }
}

function onSubmitButtonClicked() {
    const signUpFormEl = document.forms['signUp-form'];
    const emailInputEl = signUpFormEl.querySelector('input[name="email"]');
    const firstNameInputEl = signUpFormEl.querySelector('input[name="firstName"]');
    const lastNameInputEl = signUpFormEl.querySelector('input[name="lastName"]');
    const passwordInputEl = signUpFormEl.querySelector('input[name="password"]');

    const email = emailInputEl.value;
    const firstName = firstNameInputEl.value;
    const lastName = lastNameInputEl.value;
    const password = passwordInputEl.value;

    const params = new URLSearchParams();
    params.append('email', email);
    params.append('firstName', firstName);
    params.append('lastName', lastName);
    params.append('password', password);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onSignUpResponse);
    xhr.open('POST', 'register');
    xhr.send(params);

}

function backToLoginButtonClicked() {
    showContents(['login-content', 'welcome-content']);
}

function onSignUpButtonClicked() {
    showContents(['signUp-content', 'welcome-content']);

    const backToLoginButtonEl = document.getElementById('backToLoginButton');
    backToLoginButtonEl.addEventListener('click', backToLoginButtonClicked);

    const submitButtonEl = document.getElementById('submitButton');
    submitButtonEl.addEventListener('click', onSubmitButtonClicked);
}