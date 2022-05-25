<#macro headForInputForm>
    <link rel="stylesheet" href="<@spring.url '/assets/css/Contact-Form-Clean.css' />">
</#macro>

<#macro inputForm type name placeholder>
<div class="mb-3">
    <input class="form-control" type="${type}" name="${name}" placeholder="${placeholder}">
</div>
</#macro>
