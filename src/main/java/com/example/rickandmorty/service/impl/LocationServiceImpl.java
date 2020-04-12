package com.example.rickandmorty.service.impl;

import com.example.rickandmorty.entity.Location;
import com.example.rickandmorty.repository.LocationRepository;
import com.example.rickandmorty.service.LocationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> saveAll(List<Location> locations) {
        return locationRepository.saveAll(locations);
    }

    @Override
    public Location findOneByUrl(String url) {
        return locationRepository.findOneByUrl(url);
    }

    @Override
    public void deleteAll() {
        locationRepository.deleteAll();
    }
}
