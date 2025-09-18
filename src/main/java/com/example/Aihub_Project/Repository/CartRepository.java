package com.example.Aihub_Project.Repository;

import com.example.Aihub_Project.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Category, Long> {
}
