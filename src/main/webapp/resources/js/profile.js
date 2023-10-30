$(document).ready(function() {
    $("#saveButton").click(function() {
        var data = {
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            city: $("#city").val(),
            birthday: $("#birthday").val(),
            role: $("#role").val(),
            phone: $("#phone").val()
        };
        $.ajax({
            url: "/edit_profile",
            method: "POST",
            data: data,
            success: function(response) {
                console.log(response);
            },
            error: function(error) {
                console.log(error);
            }
        });
    });
});

$(document).ready(function() {
    $(".datepicker").datepicker({
        format: 'yyyy-mm-dd', // Modify the format as per your requirement
        autoclose: true
    });
});

$(document).ready(function() {
    $("#saveButton").click(function() {
        var data = {
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            city: $("#city").val(),
            birthday: $("#birthday").val(),
            role: $("#role").val(),
            phone: $("#phone").val()
        };
        $.ajax({
            url: "/edit_profile",
            method: "POST",
            data: data,
            success: function(response) {
                console.log(response);
            },
            error: function(error) {
                console.log(error);
            }
        });
    });
});
$(document).ready(function() {
    $(".datepicker").datepicker({
        format: 'yyyy-mm-dd', // Modify the format as per your requirement
        autoclose: true
    });
});
document.addEventListener('DOMContentLoaded', function() {
    const avatarImg = document.querySelector('#avatarImage');
    avatarImg.addEventListener('click', function() {
        const avatarInput = document.getElementById('avatarInput');
        avatarInput.click();
    });
    const confirmUploadButton = document.getElementById('confirmUploadButton');
    confirmUploadButton.addEventListener('click', function() {
        const avatarFile = document.getElementById('avatarInput').files[0];
        const formData = new FormData();
        formData.append('avatar', avatarFile);
        fetch('/upload_file', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                console.log(error);
            });
    });
});