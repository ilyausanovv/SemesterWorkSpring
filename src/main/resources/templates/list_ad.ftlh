<!doctype html>
<#import "spring.ftl" as spring/>
<#import "patterns/footer_header.ftlh" as hf />
<#import "patterns/card.ftlh" as card />
<#import "patterns/bootstrap.ftl" as boot />
<html lang="en">

<head>
    <@boot.importBootstrap />
    <@hf.headForHeaderAndFooter />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><@spring.message 'ads' /></title>
    <style>
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            padding-top: 100px; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0, 0, 0); /* Fallback color */
            background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
        }


        .modal-content {
            position: relative;
            background-color: #E1BEE7;
            margin: auto;
            padding: 0;
            border: 5px solid #7B1FA2;
            width: 50%;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
            -webkit-animation-name: animatetop;
            -webkit-animation-duration: 0.4s;
            animation-name: animatetop;
            animation-duration: 0.4s
        }


        @-webkit-keyframes animatetop {
            from {
                top: -300px;
                opacity: 0
            }
            to {
                top: 0;
                opacity: 1
            }
        }

        @keyframes animatetop {
            from {
                top: -300px;
                opacity: 0
            }
            to {
                top: 0;
                opacity: 1
            }
        }


        .close {
            color: white;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }

        .modal-header {
            padding: 2px 16px;
            background-color: #9C27B0;
            color: white;
        }

        .modal-body {
            padding: 2px 16px;
        }

        .modal-footer {
            padding: 2px 16px;
            background-color: #9C27B0;
            color: white;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <@hf.header true />
    <div class="card-group content" style="margin: 30px">
        <#list ads as ad>
            <div class="card-body" style="background-color: #f0f0f0; border: 4px double #444">
                <h4 class="card-title">${ad.header}</h4>
                <button class="btn btn-primary ajax" value="${ad.id}"> Подробнее</button>
            </div>
        </#list>
    </div>
    <@hf.footer />


    <div id="myModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2 id="header"></h2>
                <span class="close">&times;</span>
            </div>
            <div class="modal-body">
                <p id="description"></p>
                <p id="price"></p>
            </div>
            <div class="modal-footer">
                <h3 id="contact"></h3>
            </div>
        </div>
    </div>

    <script defer>
        let modal = document.getElementById("myModal");
        let btns = document.getElementsByClassName("ajax");
        let span = document.getElementsByClassName("close")[0];

        for (let btn of btns) {
            let url_id = "/ads/" + btn.value
            btn.onclick = async function () {
                let response = await fetch(url_id);
                let data = await response.json()
                $("#header").html(data.header)
                $("#description").html(data.description)
                $("#price").html(data.price)
                $("#contact").html(data.contact)
                modal.style.display = "block"
            }
        }

        span.onclick = function () {
            modal.style.display = "none";
        }

        window.onclick = function (event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        }
    </script>
</div>
</body>
</html>
