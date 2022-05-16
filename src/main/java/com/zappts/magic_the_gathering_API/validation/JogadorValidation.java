package com.zappts.magic_the_gathering_API.validation;

import com.zappts.magic_the_gathering_API.dto.JogadorDTO;
import com.zappts.magic_the_gathering_API.entity.Jogador;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorFieldIsNullOrEmptyException;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorAlreadyRegisteredException;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorNotFoundException;
import com.zappts.magic_the_gathering_API.repository.JogadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class JogadorValidation {

    private final JogadorRepository jogadorRepository;

    public void verifyIfIsAlreadyRegistered(String name, String email) throws JogadorAlreadyRegisteredException {
        Optional<Jogador> optSavedPlayer = jogadorRepository.findByNameAndEmail(name, email);
        if (optSavedPlayer.isPresent()) {
            throw new JogadorAlreadyRegisteredException();
        }
    }

    public void verifyIfIsRegistered(Integer id) throws JogadorNotFoundException {
        jogadorRepository.findById(id)
                .orElseThrow(JogadorNotFoundException::new);
    }

    public void verifyIfFieldsIsNull(JogadorDTO jogadorDTO) throws JogadorFieldIsNullOrEmptyException {
        String name = jogadorDTO.getName();
        String email = jogadorDTO.getEmail();

        if(name == null || name.isEmpty() || name.isBlank()){
            throw new JogadorFieldIsNullOrEmptyException("name");
        } else if (email == null || email.isEmpty() || email.isBlank()) {
            throw new JogadorFieldIsNullOrEmptyException("email");
        }
    }

}
