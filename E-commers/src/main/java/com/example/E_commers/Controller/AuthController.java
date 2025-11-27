package com.example.E_commers.Controller;

import com.example.E_commers.Sequrity.AuthService;
import com.example.E_commers.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public Map<String,String> register(@RequestBody User user){
        return authService.register(user);
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody Map<String, String> body){
        return authService.login(body.get("username"), body.get("password"));
    }
}
//    @PostMapping("/register")
//    public Map<String, String> register(@RequestBody User user) {
//        return Map.of("message", "ok");
//    }
//}
