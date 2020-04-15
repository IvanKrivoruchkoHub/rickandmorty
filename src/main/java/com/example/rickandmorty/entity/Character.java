package com.example.rickandmorty.entity;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "characters")
public class Character {
    @Id
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;
    private String url;
    private String created;

    @ManyToOne
    private Location origin;

    @ManyToOne
    private Location location;

    @ManyToMany
    private List<Episode> episodes = new ArrayList<>();
}
