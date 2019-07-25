function onProfileLoad(user) {
    const userFirstNameSpanEl = document.getElementById('user-firstName');
    const userLastNameSpanEl = document.getElementById('user-lastName');
    const userEmailSpanEl = document.getElementById('user-email');
    const userTypeSpanEl = document.getElementById('user-type');

    userFirstNameSpanEl.textContent = user.firstName;
    userLastNameSpanEl.textContent = user.lastName;
    userEmailSpanEl.textContent = user.email;
    userTypeSpanEl.textContent = user.userType;
}

function onProfileAClicked() {
    showContents(['profile-content','landing-content','post-wall','post-creator']);
}

function onCloseProfileButtonClicked() {
    showContents(['landing-content','post-wall','post-creator']);
}