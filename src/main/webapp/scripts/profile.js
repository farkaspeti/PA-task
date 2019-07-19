function onProfileLoad(user) {
    const userFirstNameSpanEl = document.getElementById('user-firstName');
    const userLastNameSpanEl = document.getElementById('user-lastName');
    const userEmailSpanEl = document.getElementById('user-email');

    userFirstNameSpanEl.textContent = user.firstName;
    userLastNameSpanEl.textContent = user.lastName;
    userEmailSpanEl.textContent = user.email;
}

function onProfileAClicked() {
    showContents(['profile-content', 'landing-content']);
}

function onCloseProfileButtonClicked() {
    showContents(['landing-content','post-wall']);
}