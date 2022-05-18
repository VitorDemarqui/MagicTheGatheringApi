package com.zappts.magic_the_gathering_API.repository;

import com.zappts.magic_the_gathering_API.entity.Carta;
import com.zappts.magic_the_gathering_API.entity.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartaRepository extends JpaRepository<Carta, Integer> {
    Carta findByNameAndEditionAndIdiomaAndFoil(String name, String edition, Idioma idioma, Boolean foil);

    @Query(
            value = "SELECT * FROM carta " +
                    "ORDER BY name",
            nativeQuery = true
    )
    List<Carta> findAllOrderByCardName();

    @Query(
            value = "SELECT * FROM carta " +
                    "ORDER BY value",
            nativeQuery = true
    )
    List<Carta> findAllOrderByCardValue();
}