package com.example.rickandmorty.repository;

import com.example.rickandmorty.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location findOneByUrl(String url);
}
