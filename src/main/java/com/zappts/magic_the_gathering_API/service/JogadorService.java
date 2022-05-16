package com.zappts.magic_the_gathering_API.service;

import com.zappts.magic_the_gathering_API.dto.JogadorDTO;
import com.zappts.magic_the_gathering_API.entity.Jogador;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorAlreadyRegisteredException;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorNotFoundException;
import com.zappts.magic_the_gathering_API.mapper.JogadorMapper;
import com.zappts.magic_the_gathering_API.repository.JogadorRepository;
import com.zappts.magic_the_gathering_API.validation.JogadorValidation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final JogadorMapper jogadorMapper = JogadorMapper.INSTANCE;
    private final JogadorValidation jogadorValidation;

    public JogadorDTO createJogador(JogadorDTO jogadorDTO) throws JogadorAlreadyRegisteredException {
        String name = jogadorDTO.getName();
        String email = jogadorDTO.getEmail();

        jogadorValidation.verifyIfIsAlreadyRegistered(name, email);

        Jogador jogador = jogadorMapper.toModel(jogadorDTO);
        Jogador savedJogador = jogadorRepository.save(jogador);

        return jogadorMapper.toDTO(savedJogador);
    }

    public List<JogadorDTO> listAll() {
        return jogadorRepository.findAll()
                .stream()
                .map(jogadorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public JogadorDTO findById(String id) throws JogadorNotFoundException {
        Integer idConvert = Integer.parseInt(id);

        Jogador foundPlayer = jogadorRepository.findById(idConvert)
                .orElseThrow(JogadorNotFoundException::new);
        return jogadorMapper.toDTO(foundPlayer);
    }

    public void deleteById(Integer id) throws JogadorNotFoundException {
        jogadorValidation.verifyIfIsRegistered(id);
        jogadorRepository.deleteById(id);
    }

    public JogadorDTO attJogador(Integer id, JogadorDTO jogadorDTO) throws JogadorNotFoundException, JogadorAlreadyRegisteredException {
        Jogador newjogador = createNewJogador(id, jogadorDTO);

        jogadorValidation.verifyIfIsAlreadyRegistered(newjogador.getName(), newjogador.getEmail());
        Jogador savedJogador = jogadorRepository.save(newjogador);

        return jogadorMapper.toDTO(savedJogador);
    }

    public Jogador createNewJogador(Integer id, JogadorDTO jogadorDTO) throws JogadorNotFoundException {
        String name = jogadorDTO.getName();
        String email = jogadorDTO.getEmail();

        return jogadorRepository.findById(id)
                .map(player ->{
                    if(name != null || !name.isEmpty() || !name.isBlank()) {
                        player.setName(name);
                    } else if (email != null || email.isEmpty() || !email.isBlank()) {
                        player.setEmail(email);
                    }
                    return player;
                })
                .orElseThrow(JogadorNotFoundException::new);

    }
}
