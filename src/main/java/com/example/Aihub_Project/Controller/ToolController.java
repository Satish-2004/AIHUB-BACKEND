package com.example.Aihub_Project.Controller;

import com.example.Aihub_Project.Entity.Tool;
import com.example.Aihub_Project.Entity.User;
import com.example.Aihub_Project.Service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tools")
public class ToolController {

    @Autowired
    private ToolService toolService;

    @GetMapping
    public ResponseEntity<List<Tool>> getAllTools(){
        return new ResponseEntity<>(toolService.getTools(), HttpStatus.OK);
    }


}
