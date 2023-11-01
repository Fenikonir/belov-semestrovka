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
    <script src="../resources/js/forum.js"></script>
    <script class="u-script" type="text/javascript" src="../resources/js/nicepage.js" defer=""></script>
    <meta name="theme-color" content="#6689db">
</head>
<body data-path-to-root="./" data-include-products="false" class="u-body u-xl-mode" data-lang="ru">
<#include "header.ftl">
<section style="background-color: #eee;">
    <div class="container py-5">
        <div class="row d-flex justify-content-center">
            <div class="col-md-8 col-lg-6 col-xl-8"> <!-- Increased the col-xl class to col-xl-8 -->
                <div class="card" id="chat1" style="border-radius: 15px;">
                    <div class="card-header d-flex justify-content-between align-items-center p-3 bg-info text-white border-bottom-0"
                         style="border-top-left-radius: 15px; border-top-right-radius: 15px;">
                        <p class="mb-0 fw-bold" style="font-weight: bolder">Обсуждение транспорта</p>
                    </div>
                    <div class="card-body">
                        <div class="d-flex flex-row justify-content-start mb-4">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                                 alt="avatar 1" style="width: 45px; height: 100%;">
                            <div class="p-3 ms-3" style="border-radius: 15px; background-color: rgba(57, 192, 237,.2);">
                                <p class="small mb-0">Hello and thank you for visiting MDBootstrap. Please click the video
                                    below.</p>
                            </div>
                        </div>
                        <div class="d-flex flex-row justify-content-end mb-4">
                            <div class="p-3 me-3 border" style="border-radius: 15px; background-color: #fbfbfb;">
                                <p class="small mb-0">Thank you, I really like your product.</p>
                            </div>
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava2-bg.webp"
                                 alt="avatar 1" style="width: 45px; height: 100%;">
                        </div>
                        <div class="d-flex flex-row justify-content-start mb-4">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                                 alt="avatar 1" style="width: 45px; height: 100%;">
                            <div class="ms-3" style="border-radius: 15px;">
                                <div class="bg-image">
                                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/screenshot1.webp"
                                         style="border-radius: 15px;" alt="video">
                                    <a href="#!">
                                        <div class="mask"></div>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex flex-row justify-content-start mb-4">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                                 alt="avatar 1" style="width: 45px; height: 100%;">
                            <div class="p-3 ms-3" style="border-radius: 15px; background-color: rgba(57, 192, 237,.2);">
                                <p class="small mb-0">...</p>
                            </div>
                        </div>
                        <div class="form-outline">
                            <textarea class="form-control" id="textAreaExample" rows="4"></textarea>
                            <label class="form-label" for="textAreaExample">Вводите сообщение тут</label>
                        </div>
                        <button class="btn btn-primary" id="send-button">Send</button> <!-- Added send button -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>