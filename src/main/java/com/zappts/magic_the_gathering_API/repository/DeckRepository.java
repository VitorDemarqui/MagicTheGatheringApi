package com.zappts.magic_the_gathering_API.repository;

import com.zappts.magic_the_gathering_API.entity.Carta;
import com.zappts.magic_the_gathering_API.entity.Deck;
import com.zappts.magic_the_gathering_API.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeckRepository extends JpaRepository<Deck, Integer> {
    List<Deck> findAllByJogador(Jogador jogador);

    Deck findByCarta(Carta carta);
}