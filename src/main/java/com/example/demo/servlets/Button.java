package com.example.demo.servlets;

import java.util.List;

public class Button {
    private String label;
    private String link;

    public static List<Button> authUser = null;
    public static List<Button> notAuthUser = null;

    public static List<Button> getAuthButton() {
        if (authUser == null) {
            authUser = List.of(new Button("Главная", ""), new Button("Контакты", Names.contact), new Button("Профиль", Names.profile));
        }
        return authUser;
    }

    public static List<Button> getNonAuthButton() {
        if (notAuthUser == null) {
            notAuthUser = List.of(new Button("Главная", ""), new Button("Авторизация", Names.auth), new Button("Контакты", Names.contact));
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
