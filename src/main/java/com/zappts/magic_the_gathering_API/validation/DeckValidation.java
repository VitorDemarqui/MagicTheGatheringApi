package com.zappts.magic_the_gathering_API.validation;

import com.zappts.magic_the_gathering_API.dto.DeckDTO;
import com.zappts.magic_the_gathering_API.entity.Deck;
import com.zappts.magic_the_gathering_API.entity.Jogador;
import com.zappts.magic_the_gathering_API.exception.DeckException.DeckCardValueIsNotValidException;
import com.zappts.magic_the_gathering_API.exception.DeckException.DeckNotFoundException;
import com.zappts.magic_the_gathering_API.exception.DeckException.DeckUserIsNotValidException;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorNotFoundException;
import com.zappts.magic_the_gathering_API.repository.DeckRepository;
import com.zappts.magic_the_gathering_API.repository.JogadorRepository;
import javafx.scene.control.skin.TableHeaderRow;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeckValidation {
    private final DeckRepository deckRepository;
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
