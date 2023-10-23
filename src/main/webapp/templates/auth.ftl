<!DOCTYPE html>
<!-- Coding By CodingNepal - codingnepalweb.com -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Авторизация и регистрация </title>
    <!-- CSS -->
    <link rel="stylesheet" href="../resources/auth.css">
    <!-- Boxicons CSS -->
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
<section class="container forms">
    <div class="form login">
        <div class="form-content">
            <header>Авторизация</header>
            <form action="/auth" method="post">
                <div class="field input-field">
                    <input type="email" placeholder="Email" class="input" name="email" pattern="^[\w.-]+@[a-zA-Z_-]+?(?:\.[a-zA-Z]{2,6})+$" required>
                </div>
                <div class="field input-field">
                    <input type="password" placeholder="Пароль" class="password" name="password" pattern="^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$" required>
                    <i class='bx bx-hide eye-icon'></i>
                </div>
                <div class="field input-field">
                    <input type="checkbox" id="remember-me">
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
                    <input type="email" placeholder="Email" class="input" name="email" pattern="^[\w.-]+@[a-zA-Z_-]+?(?:\.[a-zA-Z]{2,6})+$" required>
                </div>
                <p class="error-message"></p>

                <div class="field input-field">
                    <input type="password" placeholder="Придумайте пароль" class="password" name="password" pattern="^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$" required minlength="8"
                           maxlength="20">
                </div>
                <p class="error-message"></p>

                <div class="field input-field">
                    <input type="password" placeholder="Подтвердите пароль" class="password" name="confirmPassword" pattern="^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$" required minlength="10"
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
<!-- JavaScript -->
<script src="../resources/auth.js"></script>
</body>
</html>