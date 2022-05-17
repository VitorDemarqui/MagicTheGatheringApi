package com.zappts.magic_the_gathering_API.service;

import com.zappts.magic_the_gathering_API.dto.DeckDTO;
import com.zappts.magic_the_gathering_API.entity.Carta;
import com.zappts.magic_the_gathering_API.entity.Deck;
import com.zappts.magic_the_gathering_API.entity.Jogador;
import com.zappts.magic_the_gathering_API.exception.DeckException.DeckCardValueIsNotValidException;
import com.zappts.magic_the_gathering_API.exception.DeckException.DeckNotFoundException;
import com.zappts.magic_the_gathering_API.exception.DeckException.DeckUserIsNotValidException;
import com.zappts.magic_the_gathering_API.exception.cardException.CardNotFoundException;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorNotFoundException;
import com.zappts.magic_the_gathering_API.mapper.DeckMapper;
import com.zappts.magic_the_gathering_API.repository.CartaRepository;
import com.zappts.magic_the_gathering_API.repository.DeckRepository;
import com.zappts.magic_the_gathering_API.repository.JogadorRepository;
import com.zappts.magic_the_gathering_API.validation.DeckValidation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeckService {
    private final DeckRepository deckRepository;
    private final DeckValidation deckValidation;
    private final DeckMapper deckMapper = DeckMapper.INSTANCE;

    private final JogadorRepository jogadorRepository;
    private final CartaRepository cartaRepository;

    public DeckDTO addCardDeck(@RequestBody DeckDTO deckDTO) throws JogadorNotFoundException, CardNotFoundException, DeckCardValueIsNotValidException {
        Integer qtdDeckDTO = deckDTO.getQtd();
        deckValidation.verifyIfCardValueIsValid(deckDTO.getQtd());

        Jogador jogador = jogadorRepository.findById(deckDTO.getJogador().getId())
                .orElseThrow(JogadorNotFoundException::new);

        Carta carta = cartaRepository.findById(deckDTO.getCarta().getId())
                .orElseThrow(CardNotFoundException::new);

        deckDTO.setCarta(carta);
        deckDTO.setJogador(jogador);

        Deck deckAtivo = deckRepository.findByCarta(carta);

        Deck deck = deckMapper.toModel(deckDTO);

        if(deckAtivo != null) {
            Integer qtdDeckAtivo = deckAtivo.getQtd();
            deck.setQtd(qtdDeckAtivo+qtdDeckDTO);
        }

        Deck savedDeck = deckRepository.save(deck);
        return deckMapper.toDTO(savedDeck);
    }

    public List<DeckDTO> listAllByIdPlayer(Integer id) throws JogadorNotFoundException {
        Jogador jogador = jogadorRepository.findById(id)
                .orElseThrow(JogadorNotFoundException::new);

        return deckRepository.findAllByJogador(jogador)
                .stream()
                .map(cardDeck -> {
                    cardDeck.setJogador(null);
                    return deckMapper.toDTO(cardDeck);
                })
                .collect(Collectors.toList());
    }

    public void deleteById(DeckDTO deckDTO) throws JogadorNotFoundException, DeckCardValueIsNotValidException, DeckNotFoundException, DeckUserIsNotValidException {
        Integer qtdCardDeckDTO = deckDTO.getQtd();

        Deck deck = deckRepository.findById(deckDTO.getId())
                .orElseThrow(DeckNotFoundException::new);
        Integer dbQtdCard = deck.getQtd();


        deckValidation.verifyIfSubtractionIsGreaterThanZero(dbQtdCard, qtdCardDeckDTO);
        deckValidation.verifyIfUserIsValid(deckDTO.getJogador().getId(), deck.getJogador().getId());
        deckValidation.verifyIfCardValueIsValid(qtdCardDeckDTO);

        int newQtdCard = dbQtdCard - qtdCardDeckDTO;

        if (newQtdCard > 0){
            deck.setQtd(newQtdCard);
            deckRepository.save(deck);
        } else {
            deckRepository.deleteById(deckDTO.getId());
        }
    }
}
