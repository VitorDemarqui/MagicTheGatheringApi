package com.zappts.magic_the_gathering_API.controller;

import com.zappts.magic_the_gathering_API.dto.IdiomaDTO;
import com.zappts.magic_the_gathering_API.exception.idiomaException.IdiomaAlreadyRegisteredException;
import com.zappts.magic_the_gathering_API.exception.idiomaException.IdiomaNotFoundException;
import com.zappts.magic_the_gathering_API.service.IdiomaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/idioma")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class IdiomaController {

    private final IdiomaService idiomaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdiomaDTO createIdioma(@RequestBody IdiomaDTO idiomaDTO) throws IdiomaAlreadyRegisteredException {
        return idiomaService.createIdioma(idiomaDTO);
    }

    @GetMapping
    public List<IdiomaDTO> listAllPlayers() {
        return idiomaService.listAll();
    }

    @GetMapping({"/{id}"})
    public IdiomaDTO findById(@PathVariable String id) throws IdiomaNotFoundException {
        return idiomaService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Integer id) {
        idiomaService.deleteById(id);
    }

    @PutMapping("/{id}")
    public IdiomaDTO updateIdioma(@PathVariable Integer id, @RequestBody IdiomaDTO idiomaDTO) throws IdiomaAlreadyRegisteredException {
        return idiomaService.updateIdioma(id, idiomaDTO);
    }
}
