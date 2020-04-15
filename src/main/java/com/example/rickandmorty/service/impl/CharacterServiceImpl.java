package com.example.rickandmorty.service.impl;

import com.example.rickandmorty.entity.Character;
import com.example.rickandmorty.repository.CharacterRepository;
import com.example.rickandmorty.service.CharacterService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public List<Character> saveAll(List<Character> characters) {
        return characterRepository.saveAll(characters);
    }

    @Override
    public int count() {
        return (int) characterRepository.count();
    }

    @Override
    public Character findOneById(Integer id) {
        return characterRepository.findOneById(id);
    }

    @Override
    public List<String> findNamesIsContaining(String expression) {
        return characterRepository
                .findByNameIsContaining(expression)
                .stream()
                .map(Character::getName)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        characterRepository.deleteAll();
    }
}
