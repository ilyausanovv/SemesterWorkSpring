<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring/>
<#import "patterns/footer_header.ftlh" as hf />
<#import "patterns/card.ftlh" as card />
<#import "patterns/bootstrap.ftl" as boot />

<head>
    <@boot.importBootstrap />
    <@hf.headForHeaderAndFooter />
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="wrapper">
    <@hf.header false />
    <div class="card-group" style="margin: 30px">
        <#list teams as team>
            <@card.card title=team.name href="/teams/own"/>
        </#list>
    </div>
    <@hf.footer />
</div>
</body>
</html>