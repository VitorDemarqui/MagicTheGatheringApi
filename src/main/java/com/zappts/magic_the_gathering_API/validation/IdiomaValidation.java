package com.zappts.magic_the_gathering_API.validation;

import com.zappts.magic_the_gathering_API.entity.Idioma;
import com.zappts.magic_the_gathering_API.exception.idiomaException.IdiomaAlreadyRegisteredException;
import com.zappts.magic_the_gathering_API.exception.idiomaException.IdiomaNotFoundException;
import com.zappts.magic_the_gathering_API.repository.IdiomaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class IdiomaValidation {
    private final IdiomaRepository idiomaRepository;

    public void verifyIfIsRegistered(String name) throws IdiomaAlreadyRegisteredException {
        Idioma found = idiomaRepository.findByName(name);

        if(found != null) {
            throw new IdiomaAlreadyRegisteredException();
        }
    }

    public void verifyIfIsRegistered(Integer id) throws IdiomaNotFoundException {
        idiomaRepository.findById(id)
                .orElseThrow(IdiomaNotFoundException::new);
    }
}
