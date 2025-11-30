package com.example.E_commers.Sequrity;

import com.example.E_commers.Repository.UserRepository;
import com.example.E_commers.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwt;
    private final AuthenticationManager auth;

    public Map<String, String> register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        if(user.getRole()== null) user.setRole("ROLE_USER");
        userRepository.save(user);
        String token = jwt.generateToken(user.getUsername(), new HashMap<>());
        return Map.of("token", token);
    }

    public Map<String , String> login(String username, String password){
        auth.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String token = jwt.generateToken(username, new HashMap<>());
        return Map.of("token" , token);
    }

//    public Map<String, String> login(String username, String password) {
//        try {
//            Authentication authentication = auth.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            // Set authenticated user in SecurityContext
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            // Generate JWT token
//            String token = jwt.generateToken(username, new HashMap<>());
//
//            return Map.of("token", token);
//
//        } catch (BadCredentialsException e) {
//            throw new RuntimeException("Invalid username or password");
//        }
//    }

}
