package com.example.Aihub_Project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "tools")
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String rating;
    private String plan;
    private String description;
    private String tags;
    private String category;
    private String icon;
    private String link;

    @ManyToMany(mappedBy = "tools", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("tools")
    private List<User> users = new ArrayList<>();

}
