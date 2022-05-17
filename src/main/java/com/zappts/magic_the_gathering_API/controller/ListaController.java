package com.zappts.magic_the_gathering_API.controller;

import com.zappts.magic_the_gathering_API.dto.DeckDTO;
import com.zappts.magic_the_gathering_API.dto.ListaDTO;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorNotFoundException;
import com.zappts.magic_the_gathering_API.exception.listException.ListNotFoundException;
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

    @GetMapping({"/{id}"})
    public List<ListaDTO> findById(@PathVariable Integer id) throws ListNotFoundException {
        return listaService.findById(id);
    }

    @DeleteMapping({"/{idUsuario}/{idLista}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteByCardDeckId(@PathVariable Integer idUsuario, @PathVariable Integer idLista) throws ListNotFoundException, ListUserIsNotValidForOperationException, JogadorNotFoundException {
        listaService.deleteById(idUsuario, idLista);
    }
}
