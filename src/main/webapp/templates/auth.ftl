<!DOCTYPE html>
<!-- Coding By CodingNepal - codingnepalweb.com -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Авторизация и регистрация </title>
    <link rel="stylesheet" href="../resources/css/nicepage.css" media="screen">
    <script class="u-script" type="text/javascript" src="../resources/js/jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="../resources/js/nicepage.js" defer=""></script>
    <meta name="generator" content="Nicepage 5.20.7, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Montserrat:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">
    <meta name="theme-color" content="#6689db">
    <meta property="og:type" content="website">
    <meta data-intl-tel-input-cdn-path="intlTelInput/">
    <link rel="stylesheet" href="../resources/css/auth.css">
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
</head>
<body data-path-to-root="./" data-include-products="false" class="u-body u-xl-mode" data-lang="ru">
<#include "header.ftl">
<section class="container forms">
    <div class="form login">
        <div class="form-content">
            <header>Авторизация</header>
            <form action="/auth" method="post">
                <div class="field input-field">
                    <input type="email" placeholder="Email" class="input" name="email"
                           pattern="^[\w.-]+@[a-zA-Z_-]+?(?:\.[a-zA-Z]{2,6})+$" required>
                </div>
                <div class="field input-field">
                    <input type="password" placeholder="Пароль" class="password" name="password"
                           pattern="^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$" required>
                    <i class='bx bx-hide eye-icon'></i>
                </div>
                <div class="field input-field">
                    <input type="checkbox" id="remember-me" name="rememberMe">
                    <label for="remember-me">Запомнить меня</label>
                </div>
                <div class="form-link">
                    <a href="#" class="forgot-pass">Забыли пароль?</a>
                </div>
                <div class="field button-field">
                    <button type="submit" name="page" value="auth">Авторизация</button>
                </div>
            </form>
            <div class="form-link">
                <span>Нет аккаунта? <a href="#" class="link signup-link">Зарегистрироваться</a></span>
            </div>
        </div>
    </div>
    <!-- Signup Form -->
    <div class="form signup">
        <div class="form-content">
            <header>Регистрация</header>
            <form action="/auth" method="post">
                <div class="field input-field">
                    <input type="email" placeholder="Email" class="input" name="email"
                           pattern="^[\w.-]+@[a-zA-Z_-]+?(?:\.[a-zA-Z]{2,6})+$" required>
                </div>
                <p class="error-message"></p>
                <div class="field input-field">
                    <input type="password" placeholder="Придумайте пароль" class="password" name="password"
                           pattern="^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$" required minlength="8"
                           maxlength="20">
                </div>
                <p class="error-message"></p>
                <div class="field input-field">
                    <input type="password" placeholder="Подтвердите пароль" class="password" name="confirmPassword"
                           pattern="^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$" required minlength="10"
                           maxlength="20">
                    <i class='bx bx-hide eye-icon'></i>
                </div>
                <p class="error-message"></p>
                <div class="field input-field">
                    <select name="city">
                        <option value="" disabled selected>Выберите город</option>
                        <#list cities as city>
                            <option value="${city}">${city}</option>
                        </#list>
                    </select>
                </div>
                <div class="field button-field">
                    <button type="submit" name="page" value="signup">Регистрация</button>
                </div>
            </form>
            <div class="form-link">
                <span>Уже есть аккаунт? <a href="#" class="link login-link">Войти</a></span>
            </div>
        </div>
    </div>
</section>
<script src="../resources/js/auth.js"></script>
</body>
</html>