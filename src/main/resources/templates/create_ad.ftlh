<#import "patterns/bootstrap.ftl" as boot />
<#import "spring.ftl" as spring />
<#import "patterns/footer_header.ftlh" as hf />
<#import "patterns/form.ftlh" as form />

<!doctype html>
<html lang="en">

<head>
    <@boot.importBootstrap />
    <@hf.headForHeaderAndFooter />
    <@form.headForInputForm />
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><@spring.message 'ad' /></title>
</head>
<body>
<div class="wrapper">
    <@hf.header true />
    <section class="contact-clean content">
        <form method="post">
            <h2 class="text-center"><@spring.message 'create_resume' /></h2>
            <@form.inputForm name="header" type="text" placeholder="Header for your ad" />
            <@form.inputForm name="description" type="text" placeholder="Description for your ad" />
            <@form.inputForm name="contact" type="text" placeholder="your contact" />
            <@form.inputForm name="price" type="value" placeholder="price" />
<#--            <div class="mb-3">-->
<#--                <p>${user.login}</p>-->
<#--                <input type="hidden" value="${user.login}" name="creatorLogin">-->
<#--                <input type="hidden" value="${_csrf.token}" name="${_csrf.parameterName}">-->
<#--                <button class="btn btn-primary" type="submit"><@spring.message 'send' /></button>-->
<#--            </div>-->
        </form>
    </section>
    <@hf.footer />
</div>
</body>
</html>