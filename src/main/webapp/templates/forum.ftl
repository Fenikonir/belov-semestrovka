<!DOCTYPE html>
<html style="font-size: 16px;" lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <title>Форум</title>
    <link rel="stylesheet" href="../resources/css/nicepage.css" media="screen">
    <link rel="stylesheet" href="../resources/css/Контакты.css" media="screen">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script class="u-script" type="text/javascript" src="../resources/js/jquery.js" defer=""></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="../resources/js/forum.js?v=002"></script>
    <script class="u-script" type="text/javascript" src="../resources/js/nicepage.js" defer=""></script>
    <meta name="theme-color" content="#6689db">
</head>
<body data-path-to-root="./" data-include-products="false" class="u-body u-xl-mode" data-lang="ru">
<#include "header.ftl">
<section style="background-color: #eee;">
    <div class="container py-5">
        <div class="row d-flex justify-content-center">
            <div class="col-md-8 col-lg-6 col-xl-8">
                <div class="card" id="chat1" style="border-radius: 15px;">
                    <div class="card-header d-flex justify-content-between align-items-center p-3 bg-info text-white border-bottom-0"
                         style="border-top-left-radius: 15px; border-top-right-radius: 15px;">
                        <p class="mb-0 fw-bold" style="font-weight: bolder">Обсуждение транспорта</p>
                    </div>
                    <div class="card-body">
                        <#list posts as post>
                            <div class="d-flex flex-row justify-content-start mb-4">
                                <img src="${post.getPhoto()}"
                                     alt="avatar 1" style="width: 45px; height: 100%;">
                                <div class="p-3 ms-3" style="border-radius: 15px; background-color: rgba(57, 192, 237,.2);">
                                    <p class="small mb-0">${post.getValue()}</p>
                                </div>
                            </div>
                        </#list>
                        <div id="message-list">
                            <!-- Existing messages will be added here -->
                        </div>
                        <div class="form-outline">
                            <textarea class="form-control" id="textAreaExample" rows="4"></textarea>
                            <label class="form-label" for="textAreaExample">Введите сообщение тут</label>
                        </div>
                        <button class="btn btn-primary" id="send-button" onclick="sendMessage()">Отправить</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container mt-4">
        <div class="row">
            <div class="col">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <#if (pageNumber > 1)>
                            <li class="page-item">
                                <a class="page-link" href="?page=${pageNumber - 1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </#if>
                        <#list 1..totalPages as page>
                            <li class="page-item <#if page == pageNumber>active</#if>">
                                <a class="page-link" href="?page=${page}">${page}</a>
                            </li>
                        </#list>
                        <#if (pageNumber < totalPages)>
                            <li class="page-item">
                                <a class="page-link" href="?page=${pageNumber + 1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </#if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</section>
</body>
</html>