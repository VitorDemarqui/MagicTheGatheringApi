package com.zappts.magic_the_gathering_API.controller;

import com.zappts.magic_the_gathering_API.dto.JogadorDTO;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorAlreadyRegisteredException;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorFieldIsNullOrEmptyException;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorNotFoundException;
import com.zappts.magic_the_gathering_API.service.JogadorService;
import com.zappts.magic_the_gathering_API.validation.JogadorValidation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jogador")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JogadorController {

    private final JogadorService jogadorService;
    private final JogadorValidation jogadorValidation;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JogadorDTO createJogador(@RequestBody JogadorDTO jogadorDTO) throws JogadorAlreadyRegisteredException, JogadorFieldIsNullOrEmptyException {
        jogadorValidation.verifyIfFieldsIsNull(jogadorDTO);
        return jogadorService.createJogador(jogadorDTO);
    }

    @GetMapping
    public List<JogadorDTO> listAllPlayers() {
        return jogadorService.listAll();
    }

    @GetMapping({"/{id}"})
    public JogadorDTO findById(@PathVariable String id) throws JogadorNotFoundException {
        return jogadorService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Integer id) throws JogadorNotFoundException {
        jogadorService.deleteById(id);
    }

    @PutMapping("/{id}")
    public JogadorDTO attJogador(@PathVariable Integer id, @RequestBody JogadorDTO jogadorDTO) throws JogadorNotFoundException, JogadorAlreadyRegisteredException {
        return jogadorService.attJogador(id, jogadorDTO);
    }
}
