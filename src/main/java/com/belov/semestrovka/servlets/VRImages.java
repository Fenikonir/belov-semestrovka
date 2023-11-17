package com.belov.semestrovka.servlets;

import java.util.List;
import java.util.Random;

public class VRImages {
    private static List<String> vrImages = List.of(
            "IMG_9545.webp",
            "IMG_9540.webp",
            "IMG_9538.webp",
            "dingboard.png",
            "cats.png",
            "halloween.png",
            "IMG_9561.webp",
            "IMG_9575.jpg",
            "IMG_9576.jpg",
            "IMG_9577.jpg",
            "IMG_9578.jpg",
            "IMG_9584.webp",
            "IMG_9603.webp",
            "IMG_9590.webp",
            "IMG_9597.webp",
            "IMG_9596.webp",
            "IMG_9586.webp",
            "IMG_9601.webp",
            "IMG_9602.webp",
            "IMG_9613.jpeg",
            "IMG_9614.jpeg",
            "IMG_9615.jpeg",
            "IMG_9616.jpeg"
    );
    public static String getRandomVRImages() {
        if (vrImages == null || vrImages.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(vrImages.size());
        return "../resources/360/" + vrImages.get(randomIndex);
//        return "Leonardo_Diffusion_XL_360_VR_Transport_0.jpg";
    }
}
