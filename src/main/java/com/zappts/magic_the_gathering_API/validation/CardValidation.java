package com.zappts.magic_the_gathering_API.validation;

import com.zappts.magic_the_gathering_API.entity.Carta;
import com.zappts.magic_the_gathering_API.entity.Idioma;
import com.zappts.magic_the_gathering_API.exception.cardException.CardAlreadyRegisteredException;
import com.zappts.magic_the_gathering_API.exception.cardException.CardNotFoundException;
import com.zappts.magic_the_gathering_API.repository.CartaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class CardValidation {
    private final CartaRepository cartaRepository;

    public void verifyIfIsRegistered(String name, String edition, Idioma idioma, Boolean foil) throws CardAlreadyRegisteredException {
        Carta found = cartaRepository.findByNameAndEditionAndIdiomaAndFoil(name, edition, idioma, foil);

        if(found != null) {
            throw new CardAlreadyRegisteredException();
        }
    }

    public void verifyIfIsRegistered(Integer id) throws CardNotFoundException {
        cartaRepository.findById(id)
                .orElseThrow(CardNotFoundException::new);
    }
}
