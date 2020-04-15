package com.example.rickandmorty.service;

import com.example.rickandmorty.entity.Episode;
import java.util.List;

public interface EpisodeService {
    List<Episode> saveAll(List<Episode> episodes);

    Episode findOneByUrl(String url);

    void deleteAll();
}
