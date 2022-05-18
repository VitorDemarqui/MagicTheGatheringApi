package com.zappts.magic_the_gathering_API.controller;

import com.zappts.magic_the_gathering_API.dto.ListCollectionDTO;
import com.zappts.magic_the_gathering_API.dto.ListaDTO;
import com.zappts.magic_the_gathering_API.exception.cardException.CardNotFoundException;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorNotFoundException;
import com.zappts.magic_the_gathering_API.exception.listException.ListCollectionCardExistOnListException;
import com.zappts.magic_the_gathering_API.exception.listException.ListNotFoundException;
import com.zappts.magic_the_gathering_API.exception.listException.ListOrderByIncorrectException;
import com.zappts.magic_the_gathering_API.exception.listException.ListUserIsNotValidForOperationException;
import com.zappts.magic_the_gathering_API.service.ListaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lista")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ListaController {
    ListaService listaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ListaDTO createLista(@RequestBody @Valid ListaDTO listDTO) throws JogadorNotFoundException {
        return listaService.createList(listDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ListaDTO> findAllLists() {
        return listaService.findAllLists();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public List<ListaDTO> findById(@PathVariable Integer id) throws ListNotFoundException {
        return listaService.findById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteByCardDeckId(@RequestParam Integer idUsuario, @RequestParam Integer idLista) throws ListNotFoundException, ListUserIsNotValidForOperationException, JogadorNotFoundException {
        listaService.deleteById(idUsuario, idLista);
    }

    @GetMapping({"/getCardsListById"})
    @ResponseStatus(HttpStatus.OK)
    public List<ListCollectionDTO> getCardsListById(@RequestParam Integer idList, @RequestParam String orderBy) throws ListNotFoundException, ListOrderByIncorrectException {
        return listaService.getCardsListById(idList, orderBy);
    }

    @PostMapping({"/addCardList"})
    @ResponseStatus(HttpStatus.CREATED)
    public ListCollectionDTO addCardLista(@RequestBody @Valid ListCollectionDTO ListCollectionDto, @RequestParam Integer idUsuario) throws ListUserIsNotValidForOperationException, ListNotFoundException, JogadorNotFoundException, CardNotFoundException {
        return listaService.addCardLista(ListCollectionDto, idUsuario);
    }

    @DeleteMapping("/deleteCardList")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCardList(@RequestParam Integer idUsuario, @RequestBody @Valid ListCollectionDTO listCollectionDTO) throws ListNotFoundException, ListUserIsNotValidForOperationException, JogadorNotFoundException, CardNotFoundException, ListCollectionCardExistOnListException {
        listaService.deleteCardList(idUsuario, listCollectionDTO);
    }
}
