package com.example.Aihub_Project.Controller;

import com.example.Aihub_Project.Entity.Tool;
import com.example.Aihub_Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/tools")
    public ResponseEntity<List<Tool>> getUsers(@PathVariable("userId") Long userId){
        return  new ResponseEntity<>(userService.getUserTools(userId), HttpStatus.OK);
    }

    @PostMapping("/userId/{userId}/toolId/{toolId}")
    public void addTool(@PathVariable Long userId, @PathVariable Long toolId){
        System.out.println("Triggered");
        userService.addToolToCart(userId, toolId);
    }
}
