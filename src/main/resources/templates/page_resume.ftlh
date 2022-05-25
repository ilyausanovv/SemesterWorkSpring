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
    <title>${resume.header}</title>
</head>
<body>
<div class="wrapper">
    <@fh.header false/>
    <ul class="list-group content">
        <li class="list-group-item">${resume.header}</li>
        <li class="list-group-item">${resume.description}</li>
        <li class="list-group-item">${resume.contact}</li>
    </ul>
    <@fh.footer />
</div>
</body>
</html>