<#include "common_profile.ftl">
<div class="container bootstrap snippets bootdey">
    <div class="row">
        <div class="profile-nav col-md-3">
            <div class="panel">
                <div class="user-heading round">
                    <a href="#">
                        <img src="${user.getAvatar()}" alt>
                    </a>
                    <h1>${user.getUsername()}</h1>
                    <p><a class="__cf_email__" style="color: #0f0f0f">${user.getEmail()}</a></p>
                </div>
                <ul class="nav nav-pills nav-stacked">
                    <li class="active"><a href="${host}/profile"> <i class="fa fa-user"></i> Профиль</a></li>
                    <li><a href="${host}/edit_profile"> <i class="fa fa-edit"></i> Редактировать Профиль</a></li>
                </ul>
            </div>
        </div>
        <div class="profile-info col-md-9">
            <div class="panel">
                <div class="bio-graph-heading">
                    ${user.getBio().getValue()}
                </div>
                <div class="panel-body bio-graph-info">
                    <h1>Биография</h1>
                    <div class="row">
                        <div class="bio-row">
                            <p><span>Имя </span>: ${user.getFirstName()}</p>
                        </div>
                        <div class="bio-row">
                            <p><span>Фамилия </span>: ${user.getLastName()}</p>
                        </div>
                        <div class="bio-row">
                            <p><span>Город </span>: <#if user.getCity()??>
                                    ${user.getCity().getCityName()}
                                <#else>
                                    Нет информации о городе
                                </#if></p>
                        </div>
                        <div class="bio-row">
                            <p><span>День рождения</span>: ${user.getMaturBD()}</p>
                        </div>
                        <div class="bio-row">
                            <p><span>Роль </span>: ${user.getRole()}</p>
                        </div>
                        <div class="bio-row">
                            <p><span>Телефон </span>: ${user.getMobilePhone()}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-body">
                                <a href="/buses">
                                    <div class="bio-chart">
                                        <div style="display:inline;width:100px;height:100px;">
                                            <canvas width="100" height="100px"></canvas>
                                            <input class="knob" data-width="100" data-height="100"
                                                   data-displayprevious="true" data-thickness=".2" value="${buses}"
                                                   data-fgcolor="#e06b7d" data-bgcolor="#e8e8e8"
                                                   style="width: 54px; height: 33px; position: absolute; vertical-align: middle; margin-top: 33px; margin-left: -77px; border: 0px; font-weight: bold; font-style: normal; font-variant: normal; font-stretch: normal; font-size: 20px; line-height: normal; font-family: Arial; text-align: center; color: rgb(224, 107, 125); padding: 0px; -webkit-appearance: none; background: none;">
                                        </div>
                                    </div>
                                    <div class="bio-desk">
                                        <h4 class="red">Автобусов</h4>
                                        <p>Started : 15 July</p>
                                        <p>Deadline : 15 August</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-body">
                                <a href="/planes">
                                    <div class="bio-chart">
                                        <div style="display:inline;width:100px;height:100px;">
                                            <canvas width="100" height="100px"></canvas>
                                            <input class="knob" data-width="100" data-height="100"
                                                   data-displayprevious="true" data-thickness=".2" value= ${planes}
                                                   data-fgcolor="#4CC5CD" data-bgcolor="#e8e8e8"
                                                   style="width: 54px; height: 33px; position: absolute; vertical-align: middle; margin-top: 33px; margin-left: -77px; border: 0px; font-weight: bold; font-style: normal; font-variant: normal; font-stretch: normal; font-size: 20px; line-height: normal; font-family: Arial; text-align: center; color: rgb(76, 197, 205); padding: 0px; -webkit-appearance: none; background: none;">
                                        </div>
                                    </div>
                                    <div class="bio-desk">
                                        <h4 class="terques">Самолетов</h4>
                                        <p>Started : 15 July</p>
                                        <p>Deadline : 15 August</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-body">
                                <a href="/trolleys">
                                    <div class="bio-chart">
                                        <div style="display:inline;width:100px;height:100px;">
                                            <canvas width="100" height="100px"></canvas>
                                            <input class="knob" data-width="100" data-height="100"
                                                   data-displayprevious="true" data-thickness=".2" value=${trolleys}
                                                   data-fgcolor="#96be4b" data-bgcolor="#e8e8e8"
                                                   style="width: 54px; height: 33px; position: absolute; vertical-align: middle; margin-top: 33px; margin-left: -77px; border: 0px; font-weight: bold; font-style: normal; font-variant: normal; font-stretch: normal; font-size: 20px; line-height: normal; font-family: Arial; text-align: center; color: rgb(150, 190, 75); padding: 0px; -webkit-appearance: none; background: none;">
                                        </div>
                                    </div>
                                    <div class="bio-desk">
                                        <h4 class="green">Троллейбусов</h4>
                                        <p>Started : 15 July</p>
                                        <p>Deadline : 15 August</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-body">
                                <a href="/trains">
                                    <div class="bio-chart">
                                        <div style="display:inline;width:100px;height:100px;">
                                            <canvas width="100" height="100px"></canvas>
                                            <input class="knob" data-width="100" data-height="100"
                                                   data-displayprevious="true" data-thickness=".2" value=${trains}
                                                   data-fgcolor="#cba4db" data-bgcolor="#e8e8e8"
                                                   style="width: 54px; height: 33px; position: absolute; vertical-align: middle; margin-top: 33px; margin-left: -77px; border: 0px; font-weight: bold; font-style: normal; font-variant: normal; font-stretch: normal; font-size: 20px; line-height: normal; font-family: Arial; text-align: center; color: rgb(203, 164, 219); padding: 0px; -webkit-appearance: none; background: none;">
                                        </div>
                                    </div>
                                    <div class="bio-desk">
                                        <h4 class="purple">Поездов</h4>
                                        <p>Started : 15 July</p>
                                        <p>Deadline : 15 August</p>
                                    </div>
                                </a>
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