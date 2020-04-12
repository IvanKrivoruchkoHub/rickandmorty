package com.example.rickandmorty.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "locations")
public class Location {
    @Id
    private Integer id;
    private String name;
    private String type;
    private String dimension;
    private String url;
    private String created;
}
