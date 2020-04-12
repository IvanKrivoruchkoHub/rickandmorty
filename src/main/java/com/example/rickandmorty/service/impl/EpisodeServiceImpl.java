package com.example.rickandmorty.service.impl;

import com.example.rickandmorty.entity.Episode;
import com.example.rickandmorty.repository.EpisodeRepository;
import com.example.rickandmorty.service.EpisodeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpisodeServiceImpl implements EpisodeService {
    @Autowired
    private EpisodeRepository episodeRepository;

    @Override
    public List<Episode> saveAll(List<Episode> episodes) {
        return episodeRepository.saveAll(episodes);
    }

    @Override
    public Episode findOneByUrl(String url) {
        return episodeRepository.findOneByUrl(url);
    }

    @Override
    public void deleteAll() {
        episodeRepository.deleteAll();
    }
}
