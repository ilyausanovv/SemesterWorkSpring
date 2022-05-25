<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring/>
<#import "patterns/footer_header.ftlh" as hf />
<#import "patterns/bootstrap.ftl" as boot />

<head>
    <@boot.importBootstrap />
    <@hf.headForHeaderAndFooter />
    <link rel="stylesheet" href="<@spring.url '/assets/css/Login-Form-Dark.css' />">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><@spring.message "sign_up_page.registration.title"/></title>
</head>
<body>
<@hf.header isAuthenticated=true />
<@spring.bind "userForm"/>
<section class="login-dark">
    <form method="post">
        <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>
        <label for="login"> <@spring.message "login.label"/> </label>
        <br>
        <input type="text" id="login" name="login">
        <br>
        <label> <@spring.message "email.label"/> </label>
        <br>
        <@spring.formInput "userForm.email"/>
        <@spring.showErrors "error"/>
        <br>
        <label for="password"> <@spring.message "password.label"/> </label>
        <br>
        <@spring.formPasswordInput "userForm.password"/>
        <@spring.showErrors "error"/>
        <br>
        <label> <@spring.message "country.label"/> </label>
        <br>
        <@spring.formInput "userForm.country"/>
        <@spring.showErrors "error"/>
        <br>
        <label> <@spring.message "gender.label"/> </label>
        <br>
        <@spring.formInput "userForm.gender"/>
        <@spring.showErrors "error"/>
        <br>
        <input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">
        <button class="btn btn-primary d-block w-100" type="submit">Update</button>
    </form>
</section>
<@hf.footer />
</body>
</html>