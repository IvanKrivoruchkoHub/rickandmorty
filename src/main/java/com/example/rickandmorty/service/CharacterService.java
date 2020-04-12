package com.example.rickandmorty.service;

import com.example.rickandmorty.entity.Character;
import java.util.List;

public interface CharacterService {
    List<Character> saveAll(List<Character> characters);

    int count();

    Character findOneById(Integer id);

    List<String> findNamesIsContaining(String expression);

    void deleteAll();
}
