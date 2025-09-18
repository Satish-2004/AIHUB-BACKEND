package com.example.Aihub_Project.Controller;

import com.example.Aihub_Project.Entity.Category;
import com.example.Aihub_Project.Entity.Tool;
import com.example.Aihub_Project.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllTools(){
        return new ResponseEntity<>(categoryService.getCategory(), HttpStatus.OK);
    }
}
