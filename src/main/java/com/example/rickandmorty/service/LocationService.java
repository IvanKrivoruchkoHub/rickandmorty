package com.example.rickandmorty.service;

import com.example.rickandmorty.entity.Location;
import java.util.List;

public interface LocationService {
    List<Location> saveAll(List<Location> locations);

    Location findOneByUrl(String url);

    void deleteAll();
}
