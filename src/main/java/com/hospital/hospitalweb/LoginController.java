package com.hospital.hospitalweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")

public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public User login(@RequestBody User loginUser) {

        User user = new User();

        user.setUsername(loginUser.getUsername());

        user.setPassword(loginUser.getPassword());

        return user;
    }
}