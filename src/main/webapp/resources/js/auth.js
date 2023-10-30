console.log("JavaScript code is executing.");

const forms = document.querySelector(".forms");
const pwShowHide = document.querySelectorAll(".eye-icon");
const links = document.querySelectorAll(".link");

pwShowHide.forEach(eyeIcon => {
    eyeIcon.addEventListener("click", () => {
        let pwFields = eyeIcon.parentElement.parentElement.querySelectorAll(".password");
        pwFields.forEach(password => {
            if (password.type === "password") {
                password.type = "text";
                eyeIcon.classList.replace("bx-hide", "bx-show");
            } else {
                password.type = "password";
                eyeIcon.classList.replace("bx-show", "bx-hide");
            }
        });
    });
});

links.forEach(link => {
    link.addEventListener("click", e => {
        e.preventDefault();
        forms.classList.toggle("show-signup");
    });
});

// const emailField = document.querySelector('input[name="email"]');
// const passwordField = document.querySelector('input[name="password"]');
// const confirmPasswordField = document.querySelector('input[name="confirmPassword"]');
// const signupForm = document.querySelector('.signup form');

// function showError(inputField, errorMessage) {
//     console.log('Showing error:', errorMessage);
//     const formField = inputField.parentElement;
//     let errorElement = formField.querySelector('.error-message');
//     if (!errorElement) {
//         errorElement = document.createElement('p');
//         errorElement.classList.add('error-message');
//         formField.appendChild(errorElement);
//     }
//     errorElement.textContent = errorMessage;
// }
//
// function removeError(inputField) {
//     console.log('Removing error');
//     const formField = inputField.parentElement;
//     const errorElement = formField.querySelector('.error-message');
//     if (errorElement) {
//         formField.removeChild(errorElement);
//     }
// }
//
// function validateEmail(email) {
//     console.log('Validating email:', email);
//     const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//     const isValid = emailPattern.test(email);
//     console.log('Email validation result:', isValid);
//     return isValid;
// }
//
// function validatePassword(password) {
//     console.log('Validating password:', password);
//     const passwordPattern = /^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$/;
//     const isValid = passwordPattern.test(password);
//     console.log('Password validation result:', isValid);
//     return isValid;
// }
//
// signupForm.addEventListener('submit', (e) => {
//     e.preventDefault();
//     const submitButton = signupForm.querySelector('button[type="submit"]');
//     submitButton.disabled = true;
//     console.log('Form submitted');
//
//     const errorMessages = document.querySelectorAll('.error-message');
//     errorMessages.forEach((errorMessage) => {
//         errorMessage.remove();
//     });
//
//     if (!validateEmail(emailField.value)) {
//         showError(emailField, 'Please enter a valid email address.');
//         submitButton.disabled = false;
//         return;
//     }
//
//     if (!validatePassword(passwordField.value)) {
//         showError(passwordField, 'Please enter a valid password (minimum 8 characters, at least 1 digit).');
//         submitButton.disabled = false;
//         return;
//     }
//
//     if (passwordField.value !== confirmPasswordField.value) {
//         showError(confirmPasswordField, 'Passwords do not match.');
//         submitButton.disabled = false;
//         return;
//     }
//
//     signupForm.submit();
// });