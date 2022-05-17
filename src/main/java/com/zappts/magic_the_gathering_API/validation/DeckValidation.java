package com.zappts.magic_the_gathering_API.validation;

import com.zappts.magic_the_gathering_API.entity.Jogador;
import com.zappts.magic_the_gathering_API.exception.deckException.DeckCardValueIsNotValidException;
import com.zappts.magic_the_gathering_API.exception.deckException.DeckUserIsNotValidException;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorNotFoundException;
import com.zappts.magic_the_gathering_API.repository.DeckRepository;
import com.zappts.magic_the_gathering_API.repository.JogadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeckValidation {
    private final JogadorRepository jogadorRepository;

    public void verifyIfCardValueIsValid(Integer qtdCard) throws DeckCardValueIsNotValidException {
        if(qtdCard < 1) {
            throw new DeckCardValueIsNotValidException();
        }
    }

    public void verifyIfSubtractionIsGreaterThanZero(Integer dbQtdCard, Integer qtdCardDeckDTO) throws DeckCardValueIsNotValidException {
        if((dbQtdCard - qtdCardDeckDTO) < 0) {
            throw new DeckCardValueIsNotValidException();
        }
    }

    public void verifyIfUserIsValid(Integer jogadorId, Integer deckId) throws JogadorNotFoundException, DeckUserIsNotValidException {
        Jogador jogador = jogadorRepository.findById(jogadorId)
                .orElseThrow(JogadorNotFoundException::new);

        if (!jogador.getId().equals(deckId)) {
            throw new DeckUserIsNotValidException();
        }
    }
}
