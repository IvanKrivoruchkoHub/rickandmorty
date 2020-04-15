package com.example.rickandmorty.service.impl;

import com.example.rickandmorty.entity.Character;
import com.example.rickandmorty.entity.Episode;
import com.example.rickandmorty.entity.Location;
import com.example.rickandmorty.service.CharacterService;
import com.example.rickandmorty.service.EpisodeService;
import com.example.rickandmorty.service.InitService;
import com.example.rickandmorty.service.LocationService;
import com.example.rickandmorty.util.HttpConnection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InitServiceImpl implements InitService {

    @Value("${base.url}")
    private String startingUrl;

    @Autowired
    private HttpConnection httpConnection;

    @Autowired
    private LocationService locationService;

    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private CharacterService characterService;

    @Override
    @PostConstruct
    public void initializeDatabase() {
        addLocation(startingUrl + "location/");
        addEpisodes(startingUrl + "episode/");
        addCharacters(startingUrl + "character/");
    }

    private List<Location> addLocation(String url) {
        List<Location> locations = new ArrayList<>();
        while (!url.equals("")) {
            JSONObject obj = new JSONObject(httpConnection.getJsonResponse(url));
            url = obj.getJSONObject("info").getString("next");
            JSONArray results = obj.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                Location tempLocation = new Location();
                tempLocation.setId(results.getJSONObject(i).getInt("id"));
                tempLocation.setName(results.getJSONObject(i).getString("name"));
                tempLocation.setType(results.getJSONObject(i).getString("type"));
                tempLocation.setDimension(results.getJSONObject(i).getString("dimension"));
                tempLocation.setUrl(results.getJSONObject(i).getString("url"));
                tempLocation.setCreated(results.getJSONObject(i).getString("created"));
                locations.add(tempLocation);
            }
        }
        return locationService.saveAll(locations);
    }

    private List<Episode> addEpisodes(String url) {
        List<Episode> episodes = new ArrayList<>();
        while (!url.equals("")) {
            JSONObject obj = new JSONObject(httpConnection.getJsonResponse(url));
            url = obj.getJSONObject("info").getString("next");
            JSONArray results = obj.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                Episode episode = new Episode();
                episode.setId(results.getJSONObject(i).getInt("id"));
                episode.setAirDate(results.getJSONObject(i).getString("air_date"));
                episode.setCreated(results.getJSONObject(i).getString("created"));
                episode.setEpisode(results.getJSONObject(i).getString("episode"));
                episode.setUrl(results.getJSONObject(i).getString("url"));
                episode.setName(results.getJSONObject(i).getString("name"));
                episodes.add(episode);
            }
        }
        return episodeService.saveAll(episodes);
    }

    private List<Character> addCharacters(String url) {
        List<Character> characters = new ArrayList<>();
        while (!url.equals("")) {
            JSONObject obj = new JSONObject(httpConnection.getJsonResponse(url));
            url = obj.getJSONObject("info").getString("next");
            JSONArray results = obj.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                Character character = new Character();
                character.setId(results.getJSONObject(i).getInt("id"));
                character.setName(results.getJSONObject(i).getString("name"));
                character.setStatus(results.getJSONObject(i).getString("status"));
                character.setSpecies(results.getJSONObject(i).getString("species"));
                character.setType(results.getJSONObject(i).getString("type"));
                character.setGender(results.getJSONObject(i).getString("gender"));
                character.setImage(results.getJSONObject(i).getString("image"));
                character.setUrl(results.getJSONObject(i).getString("url"));
                character.setCreated(results.getJSONObject(i).getString("created"));

                character.setOrigin(locationService.findOneByUrl(
                        results.getJSONObject(i).getJSONObject("origin").getString("url")));
                character.setLocation(locationService.findOneByUrl(
                        results.getJSONObject(i).getJSONObject("location").getString("url")));
                List<Episode> episodes = new ArrayList<>();
                JSONArray episodesArrayJson = results
                        .getJSONObject(i)
                        .getJSONArray("episode");

                for (int j = 0; j < episodesArrayJson.length(); j++) {
                    episodes.add(episodeService.findOneByUrl(episodesArrayJson.getString(j)));
                }
                character.setEpisodes(episodes);
                characters.add(character);
            }
        }
        return characterService.saveAll(characters);
    }
}
