<!doctype html>
<html lang="en">
<#import "patterns/bootstrap.ftl" as boot />
<#import "spring.ftl" as spring/>

<head>
    <@boot.importBootstrap />
    <meta name="google-site-verification" content="LwdkKw3Q3A6ScT1oC9ZiBhjWgWmtLaJG4f76a8Nh9Xk" />
    <link rel="stylesheet" href="<@spring.url '/assets/css/Highlight-Clean.css' />">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<section class="highlight-clean">
    <div class="container">
        <div class="intro">
            <h2 class="text-center"><@spring.message 'what_you_search' /></h2>
            <p class="text-center"><@spring.message 'what_you_search_desc' /> </p>
        </div>
        <div class="buttons">
            <a class="btn btn-primary" role="button" href="/ads">Ads</a>
            <a class="btn btn-light" role="button" href="/resumes">Resume</a>
        </div>
    </div>
</section>
</body>
</html>