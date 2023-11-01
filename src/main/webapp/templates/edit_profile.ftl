<#include "common_profile.ftl">
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
                    <li class="active"><a href="${host}/edit_profile"> <i class="fa fa-edit"></i> Редактировать Профиль</a>
                    </li>
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
                            <input id="birthday" type="text" class="datepicker" data-provide="datepicker"
                                   value="${user.getEditProfileBD()}">
                        </div>
                        <div class="bio-row">
                            <span>Роль </span>: <input id="role" type="text" value="${user.getRole()}">
                        </div>
                        <div class="bio-row">
                            <span>Телефон </span>: <input id="phone" type="tel" autocomplete="tel"
                                                          placeholder="Phone number" required
                                                          value="${user.getMobilePhone()}">
                        </div>
                    </div>
                    <button id="saveButton" class="btn btn-success">Сохранить</button>
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