<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Редактирование Профиля</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../resources/css/profile.css" media="screen">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="../resources/css/nicepage.css" media="screen">
    <script src="../resources/js/profile.js?v=005"></script>
    <script src="../resources/imasked.js.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <script class="u-script" type="text/javascript" src="../resources/js/jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="../resources/js/nicepage.js" defer=""></script>
    <meta name="generator" content="Nicepage 5.20.7, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Montserrat:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">
    <meta name="theme-color" content="#6689db">
    <meta property="og:type" content="website">
    <meta data-intl-tel-input-cdn-path="intlTelInput/">

</head>
<body data-path-to-root="./" data-include-products="false" class="u-body u-xl-mode" data-lang="ru">
<#include "header.ftl">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<div class="container bootstrap snippets bootdey">
    <div class="row">
        <div class="profile-nav col-md-3">
            <div class="panel">
                <div class="user-heading round">
                    <a href="#">
                        <img id="avatarImage" src="${user.getAvatar()}" alt>
                    </a>
                    <h1>${user.getUsername()}</h1>
                    <p><a class="__cf_email__" style="color: #0f0f0f">${user.getEmail()}</a></p>
                    <input type="file" id="avatarInput" style="display: none;">
                    <button id="confirmUploadButton">Подтвердить</button>
                </div>
                <ul class="nav nav-pills nav-stacked">
                    <li><a href="${host}/profile"> <i class="fa fa-user"></i> Профиль</a></li>
                    <li class="active"><a href="${host}/edit_profile"> <i class="fa fa-edit"></i> Редактировать Профиль</a></li>
                </ul>
            </div>
        </div>
        <div class="profile-info col-md-9">
            <div class="panel">
                <form>
                    <textarea placeholder="Whats in your mind today?" rows="2"
                              class="form-control input-lg p-text-area"></textarea>
                </form>
                <footer class="panel-footer">
                    <button class="btn btn-warning pull-right">Post</button>
                    <ul class="nav nav-pills">
                        <li>
                            <a href="#"><i class="fa fa-map-marker"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-camera"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class=" fa fa-film"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-microphone"></i></a>
                        </li>
                    </ul>
                </footer>
            </div>
            <div class="panel">
                <div class="bio-graph-heading">
                    ${user.getBio().getValue()}
                </div>
                <div class="panel-body bio-graph-info">
                    <h1>Биография</h1>
                    <div class="row">
                        <div class="bio-row">
                            <span>Имя </span>: <input id="firstName" type="text" value="${user.getFirstName()}">
                        </div>
                        <div class="bio-row">
                            <span>Фамилия </span>: <input id="lastName" type="text" value="${user.getLastName()}">
                        </div>
                        <div class="bio-row">
                            <span>Город </span>:
                            <select id="city" name="city">
                                <option value="" disabled>Выберите город</option>
                                <#list cities as city>
                                    <option value="${city}" <#if user.getCity()??> <#if city == user.getCity().getCityName()>selected</#if></#if>>${city}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="bio-row">
                            <span>День рождения</span>:
                            <input id="birthday" type="text" class="datepicker" data-provide="datepicker" value="${user.getEditProfileBD()}">
                        </div>
                        <div class="bio-row">
                            <span>Роль </span>: <input id="role" type="text" value="${user.getRole()}">
                        </div>
                        <div class="bio-row">
                            <span>Телефон </span>:  <input id="phone" type="tel" autocomplete="tel" placeholder="Phone number" required value="${user.getMobilePhone()}">
                        </div>
                    </div>
                    <button id="saveButton" class="btn btn-success">Сохранить</button>
                </div>
            </div>
            <div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-body">
                                <div class="bio-chart">
                                    <div style="display:inline;width:100px;height:100px;">
                                        <canvas width="100" height="100px"></canvas>
                                        <input class="knob" data-width="100" data-height="100"
                                               data-displayprevious="true" data-thickness=".2" value="35"
                                               data-fgcolor="#e06b7d" data-bgcolor="#e8e8e8"
                                               style="width: 54px; height: 33px; position: absolute; vertical-align: middle; margin-top: 33px; margin-left: -77px; border: 0px; font-weight: bold; font-style: normal; font-variant: normal; font-stretch: normal; font-size: 20px; line-height: normal; font-family: Arial; text-align: center; color: rgb(224, 107, 125); padding: 0px; -webkit-appearance: none; background: none;">
                                    </div>
                                </div>
                                <div class="bio-desk">
                                    <h4 class="red">Envato Website</h4>
                                    <p>Started : 15 July</p>
                                    <p>Deadline : 15 August</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-body">
                                <div class="bio-chart">
                                    <div style="display:inline;width:100px;height:100px;">
                                        <canvas width="100" height="100px"></canvas>
                                        <input class="knob" data-width="100" data-height="100"
                                               data-displayprevious="true" data-thickness=".2" value="63"
                                               data-fgcolor="#4CC5CD" data-bgcolor="#e8e8e8"
                                               style="width: 54px; height: 33px; position: absolute; vertical-align: middle; margin-top: 33px; margin-left: -77px; border: 0px; font-weight: bold; font-style: normal; font-variant: normal; font-stretch: normal; font-size: 20px; line-height: normal; font-family: Arial; text-align: center; color: rgb(76, 197, 205); padding: 0px; -webkit-appearance: none; background: none;">
                                    </div>
                                </div>
                                <div class="bio-desk">
                                    <h4 class="terques">ThemeForest CMS </h4>
                                    <p>Started : 15 July</p>
                                    <p>Deadline : 15 August</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-body">
                                <div class="bio-chart">
                                    <div style="display:inline;width:100px;height:100px;">
                                        <canvas width="100" height="100px"></canvas>
                                        <input class="knob" data-width="100" data-height="100"
                                               data-displayprevious="true" data-thickness=".2" value="75"
                                               data-fgcolor="#96be4b" data-bgcolor="#e8e8e8"
                                               style="width: 54px; height: 33px; position: absolute; vertical-align: middle; margin-top: 33px; margin-left: -77px; border: 0px; font-weight: bold; font-style: normal; font-variant: normal; font-stretch: normal; font-size: 20px; line-height: normal; font-family: Arial; text-align: center; color: rgb(150, 190, 75); padding: 0px; -webkit-appearance: none; background: none;">
                                    </div>
                                </div>
                                <div class="bio-desk">
                                    <h4 class="green">VectorLab Portfolio</h4>
                                    <p>Started : 15 July</p>
                                    <p>Deadline : 15 August</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-body">
                                <div class="bio-chart">
                                    <div style="display:inline;width:100px;height:100px;">
                                        <canvas width="100" height="100px"></canvas>
                                        <input class="knob" data-width="100" data-height="100"
                                               data-displayprevious="true" data-thickness=".2" value="50"
                                               data-fgcolor="#cba4db" data-bgcolor="#e8e8e8"
                                               style="width: 54px; height: 33px; position: absolute; vertical-align: middle; margin-top: 33px; margin-left: -77px; border: 0px; font-weight: bold; font-style: normal; font-variant: normal; font-stretch: normal; font-size: 20px; line-height: normal; font-family: Arial; text-align: center; color: rgb(203, 164, 219); padding: 0px; -webkit-appearance: none; background: none;">
                                    </div>
                                </div>
                                <div class="bio-desk">
                                    <h4 class="purple">Adobe Muse Template</h4>
                                    <p>Started : 15 July</p>
                                    <p>Deadline : 15 August</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript">
</script>
</body>
</html>