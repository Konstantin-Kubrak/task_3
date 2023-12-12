package com.bellintegrator.kubrak.task_6.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class User {

    public String login;
    public String email;
    public String password;
    public LocalDateTime date;

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.date = LocalDateTime.now();
    }
}
