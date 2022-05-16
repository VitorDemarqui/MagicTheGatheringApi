package com.zappts.magic_the_gathering_API.repository;

import com.zappts.magic_the_gathering_API.entity.Carta;
import com.zappts.magic_the_gathering_API.entity.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaRepository extends JpaRepository<Carta, Integer> {
    Carta findByNameAndEditionAndIdiomaAndFoil(String name, String edition, Idioma idioma, Boolean foil);
}