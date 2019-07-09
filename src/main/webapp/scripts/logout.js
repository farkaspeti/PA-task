function onLogoutResponse() {
    if (this.status === OK) {
        setUnauthorized();
        clearMessages();
        showContents(['welcome-content'])
    }
}

function onLogoutAClicked() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onLogoutResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/logout');
    xhr.send();
}
