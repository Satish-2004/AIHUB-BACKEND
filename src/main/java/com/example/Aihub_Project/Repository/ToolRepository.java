package com.example.Aihub_Project.Repository;


import com.example.Aihub_Project.Entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToolRepository extends JpaRepository<Tool, Long> {
    Optional<Tool> findByName(String name);
}
