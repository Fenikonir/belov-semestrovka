<!DOCTYPE html>
<!-- Coding By CodingNepal - codingnepalweb.com -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Авторизация и регистрация </title>
    <link rel="stylesheet" href="../resources/nicepage.css" media="screen">
    <script class="u-script" type="text/javascript" src="../resources/jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="../resources/nicepage.js" defer=""></script>
    <meta name="generator" content="Nicepage 5.20.7, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Montserrat:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">
    <meta name="theme-color" content="#6689db">
    <meta property="og:type" content="website">
    <meta data-intl-tel-input-cdn-path="intlTelInput/">
    <link rel="stylesheet" href="../resources/auth.css">
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
</head>
<body data-path-to-root="./" data-include-products="false" class="u-body u-xl-mode" data-lang="ru">
<header class="u-clearfix u-header u-header" id="sec-257e">
    <div class="u-clearfix u-sheet u-sheet-1">
        <nav class="u-menu u-menu-hamburger u-offcanvas u-menu-1" data-responsive-from="XL">
            <div class="menu-collapse" style="font-size: 1rem; letter-spacing: 0px; font-weight: 700;">
                <a class="u-button-style u-custom-border u-custom-border-color u-custom-borders u-custom-left-right-menu-spacing u-custom-padding-bottom u-custom-text-color u-custom-text-hover-color u-custom-top-bottom-menu-spacing u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base"
                   href="#">
                    <svg class="u-svg-link" viewBox="0 0 24 24">
                        <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#menu-hamburger"></use>
                    </svg>
                    <svg class="u-svg-content" version="1.1" id="menu-hamburger" viewBox="0 0 16 16" x="0px" y="0px"
                         xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg">
                        <g>
                            <rect y="1" width="16" height="2"></rect>
                            <rect y="7" width="16" height="2"></rect>
                            <rect y="13" width="16" height="2"></rect>
                        </g>
                    </svg>
                </a>
            </div>
            <div class="u-nav-container">
                <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2">
                    <#list buttons as button>
                        <li class="u-nav-item">
                            <a class="u-button-style u-nav-link" href="${host}${button.link}">${button.label}</a>
                        </li>
                    </#list>
                </ul>
            </div>
            <div class="u-nav-container-collapse">
                <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
                    <div class="u-inner-container-layout u-sidenav-overflow">
                        <div class="u-menu-close"></div>
                        <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2">
                            <#list buttons as button>
                                <li class="u-nav-item">
                                    <a class="u-button-style u-nav-link" href="${host}${button.link}">${button.label}</a>
                                </li>
                            </#list>
                        </ul>
                    </div>
                </div>
                <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
            </div>
        </nav>
    </div>
</header>
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
                <#--                <div class="field input-field">-->
                <#--                    <input type="checkbox" id="remember-me">-->
                <#--                    <label for="remember-me">Запомнить меня</label>-->
                <#--                </div>-->
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
<script src="../resources/auth.js"></script>
<!-- JavaScript -->
</body>
</html>