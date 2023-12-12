package com.bellintegrator.kubrak.task_6.controller;

import com.bellintegrator.kubrak.task_6.model.User;
import com.bellintegrator.kubrak.task_6.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;

@RestController
public class SomeController {

    @GetMapping(value = "/getUser/{login}", produces = "application/json")
    public ResponseEntity<User> someGetMethod(@PathVariable("login") String login){

        try {
            return ResponseEntity
                    .ok()
                    .body(Service.getUser(login));
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/createUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> somePostMethod(@RequestBody User user){
        if(user.getLogin().isBlank() || user.getPassword().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        user.setDate(LocalDateTime.now());
        try {
            Service.saveUser(user);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().body(user);
    }
}
