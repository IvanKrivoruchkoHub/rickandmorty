package com.example.rickandmorty.repository;

import com.example.rickandmorty.entity.Character;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
    Character findOneById(Integer id);

    List<Character> findByNameIsContaining(String expression);
}
