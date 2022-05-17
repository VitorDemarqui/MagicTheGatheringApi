package com.zappts.magic_the_gathering_API.controller;

import com.zappts.magic_the_gathering_API.dto.DeckDTO;
import com.zappts.magic_the_gathering_API.exception.DeckException.DeckCardValueIsNotValidException;
import com.zappts.magic_the_gathering_API.exception.DeckException.DeckNotFoundException;
import com.zappts.magic_the_gathering_API.exception.DeckException.DeckUserIsNotValidException;
import com.zappts.magic_the_gathering_API.exception.cardException.CardNotFoundException;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorNotFoundException;
import com.zappts.magic_the_gathering_API.service.DeckService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/deck")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeckController {
    private final DeckService deckService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public DeckDTO addCardDeck(@RequestBody DeckDTO deckDTO) throws JogadorNotFoundException, CardNotFoundException, DeckCardValueIsNotValidException {
        return deckService.addCardDeck(deckDTO);
    }

    @GetMapping({"/{id}"})
    public List<DeckDTO> listAllByIdPlayer(@PathVariable Integer id) throws JogadorNotFoundException {
        return deckService.listAllByIdPlayer(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteByCardDeckId(@RequestBody DeckDTO deckDTO) throws JogadorNotFoundException, DeckCardValueIsNotValidException, DeckNotFoundException, DeckUserIsNotValidException {
        deckService.deleteById(deckDTO);
    }
}
