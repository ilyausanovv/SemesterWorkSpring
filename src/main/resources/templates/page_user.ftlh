<!doctype html>
<html lang="`en`">
<#import "patterns/bootstrap.ftl" as boot />
<#import "patterns/footer_header.ftlh" as fh />
<#import "spring.ftl" as spring />

<head>
    <@boot.importBootstrap />
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${user.login}</title>
</head>
<body>
<div class="wrapper">
    <@fh.header false/>
    <ul class="list-group content">
        <li class="list-group-item">${user.login}</li>
        <li class="list-group-item">${user.country}</li>
        <li class="list-group-item">${user.gender}</li>
        <li class="list-group-item"><a href="<@spring.url '/users/${user.login}/teams/' />"> Список команд </a></li>
    </ul>
    <@fh.footer />
</div>
</body>
</html>