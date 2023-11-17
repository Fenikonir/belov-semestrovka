<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>360-degree Image Viewer</title>
    <script src="https://aframe.io/releases/1.2.0/aframe.min.js"></script>
    <style>
        /* Ensuring the A-Frame scene takes the full viewport */
        html, body, a-scene {
            height: 100%;
            width: 100%;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }

        /* Styling the button container */
        #emojiBtnContainer {
            position: absolute;
            bottom: 1%; /* small margin from the bottom */
            left: 50%;
            transform: translateX(-50%);
            display: flex;
            align-items: center;
            justify-content: center;
            transition: transform 0.3s;
        }

        /* Styling individual buttons */
        #emojiBtnContainer button {
            background: rgba(255, 255, 255, 0.5); /* partial opacity */
            border: none;
            border-radius: 15px;
            font-size: 20px;
            margin: 0 5px;
            padding: 10px 15px;
            cursor: pointer;
            transition: background 0.3s;
        }

        /* Button hover effect */
        #emojiBtnContainer button:hover {
            background: rgba(255, 255, 255, 0.8);
        }
      

        /* Making the button container scrollable horizontally */
        #emojiBtnContainer {
            overflow-x: scroll;
            white-space: nowrap;
            -webkit-overflow-scrolling: touch; /* iOS momentum scrolling */
        }
  
        /* Hide scrollbar but still make it scrollable */
        #emojiBtnContainer::-webkit-scrollbar {
            display: none;
        }


      #soundToggle {
          position: absolute;
          top: 1%;
          right: 1%;
          background: rgba(255, 255, 255, 0.5);
          border: none;
          border-radius: 15px;
          padding: 10px 15px;
          font-size: 20px;
          cursor: pointer;
          transition: background 0.3s;
      }

      #soundToggle:hover {
          background: rgba(255, 255, 255, 0.8);
      }

    </style>
</head>
  <body>
    <a-scene embedded vr-mode-ui="enabled: false">
        <a-sky id="sky" src="../resources/360/IMG_9545.webp"></a-sky>
        <a-camera look-controls="reverseMouseDrag: true"></a-camera>
    </a-scene>

    <audio id="audioPlayer"></audio>

    <button id="soundToggle" onclick="toggleSound()">🔊</button>

    <!-- Emoji button container -->
    <div id="emojiBtnContainer">
        <button onclick="changeImage('../resources/360/IMG_9545.webp')">🦄</button>
        <button onclick="changeImage('../resources/360/IMG_9540.webp')">🧚</button>
        <button onclick="changeImage('../resources/360/IMG_9538.webp')">🧜</button>
        <button onclick="changeImage('../resources/360/dingboard.png')">👶</button>
        <button onclick="changeImage('../resources/360/cats.png')">🐱</button>
        <button onclick="changeImage('../resources/360/halloween.png')">🎃</button>
        <button onclick="changeImage('../resources/360/IMG_9561.webp')">☀️</button>
        <button onclick="changeImage('../resources/360/IMG_9575.jpg')">🕒</button>
        <button onclick="changeImage('../resources/360/IMG_9576.jpg')">🎈</button>
        <button onclick="changeImage('../resources/360/IMG_9577.jpg')">🐪</button>
        <button onclick="changeImage('../resources/360/IMG_9578.jpg')">🍭</button>
      <button onclick="changeImage('../resources/360/IMG_9584.webp')">🏯</button>
      <button onclick="changeImage('../resources/360/IMG_9603.webp')">👽</button>
      <button onclick="changeImage('../resources/360/IMG_9590.webp')">🤖</button>
      <button onclick="changeImage('../resources/360/IMG_9597.webp')">⛩️</button>
      <button onclick="changeImage('../resources/360/IMG_9596.webp')">🎶</button>
      <button onclick="changeImage('../resources/360/IMG_9586.webp')">😵‍💫</button>
      <button onclick="changeImage('../resources/360/IMG_9601.webp')">🍔</button>
      <button onclick="changeImage('../resources/360/IMG_9602.webp')">🚀</button>
      <button onclick="changeImage('../resources/360/IMG_9613.jpeg')">↕️</button>
      <button onclick="changeImage('../resources/360/IMG_9614.jpeg')">🍄</button>
      <button onclick="changeImage('../resources/360/IMG_9615.jpeg')">👁️‍🗨️</button>
      <button onclick="changeImage('../resources/360/IMG_9616.jpeg')">🫧</button>
    </div>


      <script>
          let soundEnabled = true;

          function changeImage(imageSrc) {
              const skyElement = document.getElementById('sky');
              skyElement.setAttribute('src', imageSrc);

              const audioPlayer = document.getElementById('audioPlayer');
              const baseName = imageSrc.split('.')[0]; // Get base name without extension
              const audioSrc = "../resources/360/" + baseName + '.mp3';

              // Attempt to play the corresponding mp3 if sound is enabled
              fetch(audioSrc)
                  .then(response => {
                      if (response.ok && soundEnabled) {
                          audioPlayer.src = audioSrc;
                          audioPlayer.play();
                      } else {
                          audioPlayer.pause();
                      }
                  })
                  .catch(() => {
                      audioPlayer.pause();
                  });
          }

          function toggleSound() {
              const audioPlayer = document.getElementById('audioPlayer');
              soundEnabled = !soundEnabled;

              if (soundEnabled) {
                  document.getElementById('soundToggle').textContent = '🔊';
                  if (audioPlayer.src) {
                      audioPlayer.play();
                  }
              } else {
                  document.getElementById('soundToggle').textContent = '🔇';
                  audioPlayer.pause();
              }
          }
        document.addEventListener('DOMContentLoaded', function() {
            const container = document.getElementById('emojiBtnContainer');
            let touchStartX = 0;
            let touchEndX = 0;
            const threshold = 50; // The distance user must swipe to trigger a loop
  
            container.addEventListener('touchstart', function(event) {
                touchStartX = event.touches[0].clientX;
            });
  
            container.addEventListener('touchmove', function(event) {
                touchEndX = event.touches[0].clientX;
                handleSwipe();
            });
  
            function handleSwipe() {
                const buttons = container.querySelectorAll('button');
                const swipeDistance = Math.abs(touchStartX - touchEndX);
  
                if (swipeDistance < threshold) {
                    return; // Exit if swipe distance is less than threshold
                }
  
                if (touchEndX < touchStartX) {
                    // Swiped left
                    const firstButton = buttons[0];
                    container.removeChild(firstButton);
                    container.appendChild(firstButton);
                    touchStartX = touchEndX; // Reset the start position
                } else if (touchEndX > touchStartX) {
                    // Swiped right
                    const lastButton = buttons[buttons.length - 1];
                    container.removeChild(lastButton);
                    container.insertBefore(lastButton, buttons[0]);
                    touchStartX = touchEndX; // Reset the start position
                }
            }
        });


    </script>
</body>
</html>
