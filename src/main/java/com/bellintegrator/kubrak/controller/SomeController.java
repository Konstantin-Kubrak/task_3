package com.bellintegrator.kubrak.controller;

import com.bellintegrator.kubrak.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class SomeController {

    @GetMapping(value = "/getUser", produces = "application/json")
    public ResponseEntity<User> someGetMethod(){

        return ResponseEntity
                .ok()
                .body(new User(
                        "someGetMethodlogin",
                        "someGetMethodPassword",
                        LocalDateTime.now()));
    }

    @PostMapping(value = "/createUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> somePostMethod(@RequestBody User user){
        if(user.getLogin().isBlank() || user.getPassword().isBlank()){
            return ResponseEntity.internalServerError().build();
        }
        user.setDate(LocalDateTime.now());

        return ResponseEntity.ok().body(user);
    }
}
