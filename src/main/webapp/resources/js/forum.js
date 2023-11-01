document.addEventListener("DOMContentLoaded", function() {
    var sendButton = document.getElementById("send-button");
    var messageInput = document.getElementById("message-input");
    var messagesContainer = document.getElementById("messages");

    sendButton.addEventListener("click", function() {
        var message = messageInput.value;
        if (message.trim() !== "") {
            var messageElement = document.createElement("div");
            messageElement.textContent = message;
            messagesContainer.appendChild(messageElement);
            messageInput.value = "";
        }
    });
});