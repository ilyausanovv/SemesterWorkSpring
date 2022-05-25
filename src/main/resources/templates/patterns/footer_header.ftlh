<#macro headForHeaderAndFooter>
    <link rel="stylesheet" href="<@spring.url '/assets/css/Footer-Dark.css' />">
    <link rel="stylesheet" href="<@spring.url '/assets/css/Header-Dark.css' />">
    <link rel="stylesheet" href="<@spring.url '/assets/css/styles.css' />">
</#macro>

<#macro header isAuthenticated>
    <header class="header-dark">
        <nav class="navbar navbar-dark navbar-expand-lg navigation-clean-search">
            <div class="container">
                <a class="navbar-brand" href="#">FlowJob</a>
                <button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1">
                    <span class="visually-hidden">Toggle navigation</span>
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navcol-1">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="<@spring.url '/' />">Main page</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#"><@spring.message "service"/></a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="<@spring.url '/resumes' />"><@spring.message "resume"/></a>
                                <a class="dropdown-item" href="<@spring.url '/ads' />"><@spring.message "ads"/></a>
                                <a class="dropdown-item" href="<@spring.url '/teams' />"><@spring.message "team"/></a></div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#"><@spring.message "create"/></a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="<@spring.url '/resume/create' />"><@spring.message "create_resume"/></a>
                                <a class="dropdown-item" href="<@spring.url '/ad/create' />"><@spring.message "ad"/></a>
                                <a class="dropdown-item" href="<@spring.url '/team/create' />"><@spring.message "team"/></a></div>
                        </li>
                    </ul>
                    <form class="me-auto" target="_self">
                        <div class="d-flex align-items-center">
                            <label class="form-label d-flex mb-0" for="search-field"></label>
                        </div>
                    </form>
                    <#if isAuthenticated>
                        <span class="navbar-text"><a class="login" href="<@spring.url '/profile' />"><@spring.message "my_profile" /></a></span>
                    <#else>
                        <span class="navbar-text"><a class="login" href="<@spring.url '/signIn' />"><@spring.message "sign_in_page.registration.title" /></a></span>
                        <a class="btn btn-light action-button" role="button" href="<@spring.url '/signUp' />"> <@spring.message "sign_up_page.registration.title" /></a>
                    </#if>
                </div>
            </div>
        </nav>
    </header>
</#macro>

<#macro footer>
    <footer class="footer-dark footer">

    </footer>
</#macro>
