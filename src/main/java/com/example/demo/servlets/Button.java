package com.example.demo.servlets;

import java.util.List;

public class Button {
    private String label;
    private String link;

    public static List<Button> authUser = null;
    public static List<Button> notAuthUser = null;

    public static List<Button> getAuthButton() {
        if (authUser == null) {
            authUser = List.of(new Button("Главная", "/"), new Button("Контакты", Names.CONTACT_LINK), new Button("Профиль", Names.PROFILE_LINK), new Button("Форум", Names.FORUM_LINK));
        }
        return authUser;
    }

    public static List<Button> getNonAuthButton() {
        if (notAuthUser == null) {
            notAuthUser = List.of(new Button("Главная", "/"), new Button("Авторизация", Names.AUTH_LINK), new Button("Контакты", Names.CONTACT_LINK));
        }
        return notAuthUser;
    }

    public Button(String label, String link) {
        this.label = label;
        this.link = link;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
