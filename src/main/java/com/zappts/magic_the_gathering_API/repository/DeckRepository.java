package com.zappts.magic_the_gathering_API.repository;

import com.zappts.magic_the_gathering_API.entity.Carta;
import com.zappts.magic_the_gathering_API.entity.Deck;
import com.zappts.magic_the_gathering_API.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeckRepository extends JpaRepository<Deck, Integer> {
    List<Deck> findAllByJogador(Jogador jogador);

    @Query(
            value = "SELECT deck.* FROM deck " +
                    "LEFT JOIN carta on (deck.id_carta = carta.id) " +
                    "WHERE deck.id_jogador = :idJogador ORDER BY carta.value",
            nativeQuery = true
    )
    List<Deck> findAllByJogadorOrderByCardValue(Integer idJogador);

    @Query(
            value = "SELECT deck.* FROM deck " +
                    "LEFT JOIN carta on (deck.id_carta = carta.id) " +
                    "WHERE deck.id_jogador = :idJogador ORDER BY carta.name",
            nativeQuery = true
    )
    List<Deck> findAllByJogadorOrderByCardName(Integer idJogador);

    Deck findByCarta(Carta carta);
}