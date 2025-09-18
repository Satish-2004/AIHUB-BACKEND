package com.example.Aihub_Project.Service;

import com.example.Aihub_Project.Entity.Tool;
import com.example.Aihub_Project.Entity.User;
import com.example.Aihub_Project.Repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ToolService {
    @Autowired
    private ToolRepository toolRepository;

    public List<Tool> getTools(){
        return toolRepository.findAll();
    }


}
