package com.zappts.magic_the_gathering_API.controller;

import com.zappts.magic_the_gathering_API.dto.CartaDTO;
import com.zappts.magic_the_gathering_API.exception.cardException.CardAlreadyRegisteredException;
import com.zappts.magic_the_gathering_API.exception.cardException.CardNotFoundException;
import com.zappts.magic_the_gathering_API.service.CartaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carta")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CartaController {

    private final CartaService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartaDTO createCard(@RequestBody CartaDTO cartaDTO) throws CardAlreadyRegisteredException {
        return cardService.createCard(cartaDTO);
    }

    @GetMapping
    public List<CartaDTO> listAllCards() {
        return cardService.listAll();
    }

    @GetMapping({"/{id}"})
    public CartaDTO findById(@PathVariable String id) throws CardNotFoundException {
        return cardService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Integer id) throws CardNotFoundException {
        cardService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CartaDTO updateCard(@PathVariable Integer id, @RequestBody CartaDTO cartaDTO) throws CardNotFoundException, CardAlreadyRegisteredException {
        return cardService.updateCard(id, cartaDTO);
    }
}

