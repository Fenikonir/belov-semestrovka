package com.example.demo.database.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private City city;
    private LocalDateTime creationDate;
    private LocalDateTime lastModified;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String role;
    private String mobilePhone;
    private Article bio;

    public User(int id, String username, String email, String password, City city, LocalDateTime creationDate, LocalDateTime lastModified, String firstName, String lastName, LocalDate birthday, String role, String mobilePhone, Article bio) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.city = city;
        this.creationDate = creationDate;
        this.lastModified = lastModified;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.role = role;
        this.mobilePhone = mobilePhone;
        this.bio = bio;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        if (username == null) {
            return "Default user";
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public String getFirstName() {
        if (firstName == null) {
            return "Petr";
        }
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        if (lastName == null) {
            return "Romanov";
        }
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        if (birthday == null) {
            return LocalDate.now();
        }
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        if (role == null) {
            return "User";
        }
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMobilePhone() {
        if (mobilePhone == null) {
            return "+7-(XXX)-XXX-XX-XX";
        }
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMaturBD() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru"));
        String formattedDate = getBirthday().format(formatter);
        return formattedDate;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", city=" + city.getCityName() +
                ", creationDate=" + creationDate +
                ", lastModified=" + lastModified +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", role='" + role + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", bio='" + bio.getValue() + '\'' +
                '}';
    }

    public Article getBio() {
        if (bio == null) {
            return new Article(0, id, "био", "About me");
        }
        return bio;
    }

    public void setBio(Article bio) {
        this.bio = bio;
    }
}