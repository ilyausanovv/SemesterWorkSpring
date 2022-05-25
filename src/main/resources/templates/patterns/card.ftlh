<#macro cardhead>
    <link rel="stylesheet" href="<@spring.url '/assets/css/card.css' />">
</#macro>

<#macro card title href description="" >
        <div class="card-body" style="background-color: #f0f0f0; border: 4px double #444">
            <h4 class="card-title">${title}</h4>
            <p class="card-text">${description}</p>
            <a class="btn btn-primary" role="button" href="${href}"> Подробнее </a>
        </div>
</#macro>