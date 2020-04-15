package com.example.rickandmorty.repository;

import com.example.rickandmorty.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    Episode findOneByUrl(String url);
}
