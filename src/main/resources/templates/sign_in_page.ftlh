<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring/>
<#import "patterns/footer_header.ftlh" as hf />
<#import "patterns/bootstrap.ftl" as boot />

<head>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-client_id"
          content="680160395753-8csri1c6pms5hhmqro91hm17a5af9ga6.apps.googleusercontent.com">
    <@boot.importBootstrap />
    <@hf.headForHeaderAndFooter />
    <link rel="stylesheet" href="<@spring.url '/assets/css/Login-Form-Dark.css' />">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<@hf.header false />
<section class="login-dark">
    <form method="post">
        <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>
        <label for="email"><@spring.message "email.label"/></label>
        <br>
        <input type="text" id="email" name="email">
        <input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">

        <br>
        <label for="password"><@spring.message "password.label"/></label>
        <br>
        <input type="password" id="password" name="password">
        <br>
        <button class="btn btn-primary d-block w-100"
                type="submit"><@spring.message "sign_in_page.registration.title"/></button>
    </form>
    <input type="hidden" id="_csrf" value="${_csrf.token}" name="${_csrf.headerName}">
</section>
<@hf.footer />
<script>
    let csrf = document.getElementById("_csrf");

    function onSignIn(googleUser) {
        let id_token = googleUser.getAuthResponse().id_token;
        let access_token = googleUser.getAuthResponse().access_token
        console.log(access_token)
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/signIn/token');
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onload = function() {
            console.log('Signed in as: ' + xhr.responseText);
        };
        console.log(csrf.name, csrf.value)
        xhr.setRequestHeader(csrf.name, csrf.value)
        xhr.send(id_token)
    }
</script>
</body>
</html>