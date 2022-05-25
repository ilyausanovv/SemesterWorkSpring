<!doctype html>
<#import "spring.ftl" as spring/>
<#import "patterns/footer_header.ftlh" as hf />
<#import "patterns/card.ftlh" as card />
<#import "patterns/bootstrap.ftl" as boot />
<html lang="en">

<head>
    <@boot.importBootstrap />
    <@hf.headForHeaderAndFooter />
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><@spring.message 'resume' /></title>
</head>
<body>
<div class="wrapper">
    <@hf.header true />
    <div class="content">
        <div class="card-group" style="margin: 30px">
            <#list resumes as resume>
                <@card.card title=resume.header description=resume.description href="/resumes/${resume.id}" />
            </#list>
        </div>
    </div>
    <@hf.footer />
</div>
</body>
</html>
