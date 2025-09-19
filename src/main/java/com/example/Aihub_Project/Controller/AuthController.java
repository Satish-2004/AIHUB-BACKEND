package com.example.Aihub_Project.Controller;

import com.example.Aihub_Project.Dto.AuthResponse;
import com.example.Aihub_Project.Entity.User;
import com.example.Aihub_Project.Repository.UserRepository;
import com.example.Aihub_Project.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody User user){
        return userRepository.findByEmail(user.getEmail()).map(dbUser -> {
            if(passwordEncoder.matches(user.getPassword(), dbUser.getPassword())){
                String token = jwtUtil.generateToken(dbUser.getEmail());
                return ResponseEntity.ok(new AuthResponse(token, dbUser.getId(), dbUser.getName()));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(" Invalid Password ");
            }
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Invalid Email or, Password "));
    }

}
