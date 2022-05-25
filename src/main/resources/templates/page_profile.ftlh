<!doctype html>
<#import "patterns/bootstrap.ftl" as boot />
<#import "spring.ftl" as spring />
<#import "patterns/footer_header.ftlh" as fh />

<html lang="en">
<head>
    <@boot.importBootstrap />
    <@fh.headForHeaderAndFooter />
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><@spring.message 'my_profile' /></title>
</head>
<body>
<div class="wrapper">
    <@fh.header true/>
    <div class="content">
        <ul class="list-group">
            <li class="list-group-item">${user.login}</li>
            <li class="list-group-item">${user.country}</li>
            <li class="list-group-item">${user.gender}</li>
        </ul>
        <form method="post" action="<@spring.url '/logout' />">
            <input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">
            <button class="btn btn-primary" type="submit"><@spring.message 'logout' /></button>
        </form>
        <form method="post" action="<@spring.url '/profile/delete' />">
            <input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">
            <button class="btn btn-primary" type="submit"><@spring.message 'delete_user' /></button>
        </form>
        <div class="list-group">
            <a href="<@spring.url '/profile/update' />" class="list-group-item">
                <h4 class="list-group-item-heading"><@spring.message 'update_user' /></h4>
            </a>
            <a href="<@spring.url '/team/own' />" class="list-group-item">
                <h4 class="list-group-item-heading"><@spring.message 'team' /></h4>
            </a>
            <a href="<@spring.url '/ad/own' />" class="list-group-item">
                <h4 class="list-group-item-heading"><@spring.message 'ad' /></h4>
            </a>
            <a href="<@spring.url '/resume/own' />" class="list-group-item">
                <h4 class="list-group-item-heading"><@spring.message 'resume' /></h4>
            </a>
        </div>
    </div>
    <@fh.footer />
</div>
</body>
</html>