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

        const fileSizeLimit = 5 * 1024 * 1024; // 5MB in bytes
        if (avatarFile.size > fileSizeLimit) {
            console.log("File size exceeds the limit.");
            return;
        }

        // Check if the file format is an image
        const allowedFormats = ["image/jpeg", "image/png", "image/gif", "image/jpg"];
        if (!allowedFormats.includes(avatarFile.type)) {
            console.log("Invalid file format. Only JPEG, PNG, and GIF images are allowed.");
            return;
        }

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

function sendPost() {
    var message = document.getElementById("postMessage").value;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/post", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // Reload the page after sending the message
            location.reload();
        }
    };
    xhr.send("message=" + encodeURIComponent(message));
    document.getElementById("textAreaExample").value = "";
}