package com.zappts.magic_the_gathering_API.validation;

import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorNotFoundException;
import com.zappts.magic_the_gathering_API.exception.listException.ListUserIsNotValidForOperationException;
import com.zappts.magic_the_gathering_API.repository.JogadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class ListaValidation {
    JogadorRepository jogadorRepository;

    public void verifyIfUserIsValid(Integer jogadorId, Integer listId) throws JogadorNotFoundException, ListUserIsNotValidForOperationException {
        jogadorRepository.findById(jogadorId)
                .orElseThrow(JogadorNotFoundException::new);

        if (!listId.equals(jogadorId)) {
            throw new ListUserIsNotValidForOperationException();
        }
    }
}
