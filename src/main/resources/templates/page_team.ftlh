<!doctype html>
<html lang="en">
<#import "patterns/bootstrap.ftl" as boot />
<#import "spring.ftl" as spring />
<#import "patterns/footer_header.ftlh" as fh />

<head>
    <@boot.importBootstrap />
    <@fh.headForHeaderAndFooter />
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${team.name}</title>
</head>
<body>
<div class="wrapper">
    <@fh.header false/>
    <ul class="list-group content">
        <li class="list-group-item">${team.name}</li>
        <li class="list-group-item"><a href="<@spring.url '/user/${team.creator.login}' />">${team.creator.login}</a>
        </li>
        <li class="list-group-item"><a
                    href=" <@spring.url '/teams/${team.name}/participant' />"> <@spring.message 'participant' /></a>
        </li>
    </ul>
    <@fh.footer />
</div>
</body>
</html>