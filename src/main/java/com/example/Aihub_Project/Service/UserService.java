package com.example.Aihub_Project.Service;

import com.example.Aihub_Project.Entity.Tool;
import com.example.Aihub_Project.Entity.User;
import com.example.Aihub_Project.Repository.ToolRepository;
import com.example.Aihub_Project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ToolRepository toolRepository;

    public User SignupUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean authenticateUser(String email, String password){
        Optional<User> userOpt = userRepository.findByEmail(email);
        if(userOpt.isPresent()){
            User user = userOpt.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    public List<Tool> getUserTools(Long userId){
        try{
            User user = userRepository.findById(userId).get();
            return user.getTools();
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void addToolToCart(Long userId, Long toolId){
        User user=userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found with id "+userId));

        Tool tool=toolRepository.findById(toolId)
                        .orElseThrow(()-> new RuntimeException("Tool Not found with id "+toolId));

        if (!user.getTools().contains(tool)){
            user.getTools().add(tool);
        }

        userRepository.save(user);
    }
}
