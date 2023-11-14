function sendMessage() {
    var message = document.getElementById("textAreaExample").value;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/forum", true);
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