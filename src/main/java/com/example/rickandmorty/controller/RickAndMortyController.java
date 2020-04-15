package com.example.rickandmorty.controller;

import com.example.rickandmorty.entity.Character;
import com.example.rickandmorty.service.CharacterService;
import com.example.rickandmorty.service.EpisodeService;
import com.example.rickandmorty.service.InitService;
import com.example.rickandmorty.service.LocationService;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RickAndMortyController {
    @Autowired
    private CharacterService characterService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private InitService initService;

    @GetMapping("/randCharacter")
    public Character getRandCharacter() {
        Random objGenerator = new Random();
        int randomNumber = objGenerator.nextInt(characterService.count());
        return characterService.findOneById(randomNumber);
    }

    @GetMapping("/characters")
    public List<String> getRandCharacter(@RequestParam(name = "expr") String expression) {
        return characterService.findNamesIsContaining(expression);
    }

    @Scheduled(cron = "0 0 1 L * ?")
    public void updateDb() {
        characterService.deleteAll();
        locationService.deleteAll();
        episodeService.deleteAll();
        initService.initializeDatabase();
    }
}
