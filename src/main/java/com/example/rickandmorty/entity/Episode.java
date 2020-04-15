package com.example.rickandmorty.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "episodes")
public class Episode {
    @Id
    private Integer id;
    private String name;
    private String airDate;
    private String episode;
    private String url;
    private String created;
}
