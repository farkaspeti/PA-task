<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset ="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PA-Blog-Engine</title>
    <link rel="stylesheet" type="text/css" href="index.css" media="all">
    <c:url value="/scripts/index.js" var="indexScriptUrl"/>
    <c:url value="/scripts/login.js" var="loginScriptUrl"/>
    <c:url value="/scripts/profile.js" var="profileScriptUrl"/>
    <c:url value="/scripts/signUp.js" var="signUpScriptUrl"/>
    <script src="${profileScriptUrl}"></script>
    <script src="${indexScriptUrl}"></script>
    <script src="${loginScriptUrl}"></script>
    <script src="${signUpScriptUrl}"></script>
    <link href='https://fonts.googleapis.com/css?family=Aldrich' rel='stylesheet'>
</head>
<body>
<div id="welcome-content" class="content">
    <div style="float: left">
        <p style="font-family: 'Aldrich'; font-size: 42px">HI! this is My PA blog engine!</p>
        <button style="width: 30%" id="toLogin-button">Press to Login</button>
        <button style="width: 30%" id="toSignUp-button">Press to Sign Up</button>
    </div>
    <div id="login-content" class="hidden content modal">
        <form accept-charset=utf-8 id="login-form" onsubmit="return false;" class="modal-content animate"
              action="/action_page.php">
            <div class="imgcontainer"><span style="width: 2%; float: right" id="closeLogin-button" title="Close PopUp"
                                            class="close">&times;</span>
                <h4>
                    <input type="text" placeholder="Enter your email address" name="email" required>
                    <input type="password" placeholder="Enter Password" name="password" required>
                    <p style="padding: 0 20%">
                        <button id="login-button" type="submit">Login</button>
                    </p>
                </h4>
                <p>Hit the sign up button to create a new account!</p>
                <p style="padding: 0 20%">
                    <button id="signUp-button" type="submit">SIGN UP</button>
                </p>
            </div>
        </form>
    </div>

    <div id="signUp-content" class="hidden content modal">
        <form accept-charset=utf-8 id='signUp-form' onsubmit="return false;" class="modal-content animate"
              action="/action_page.php">
            <div class="imgcontainer">
                <span style="width: 2%; float: right" id="closeSignUp-button" title="Close PopUp"
                      class="close">&times;</span>
                <h1>SIGN UP</h1>
                <div><input type="text" placeholder="Enter Last Name" name="lastName" required style="width: 39%">
                    <input type="text" placeholder="Enter Forename" name="firstName" required style="width: 39%"></div>
                <p><input type="password" placeholder="Enter Password" name="password"
                          pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required
                          title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
                </p>
                <p><input type="password" placeholder="Reenter Password" name="passwordre"
                          pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required
                          title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
                </p>
                <p><input type="text" placeholder="Enter your email address" name="email" required></p>
                <p style="padding: 0 20%">
                    <button id='submitButton' type="submit">SUBMIT</button>
                </p>
            </div>
            <p style="padding: 0 20%">
                <button id="backToLoginButton" type="submit">Back to login</button>
            </p>
        </form>
    </div>
</div>
</body>
</html>