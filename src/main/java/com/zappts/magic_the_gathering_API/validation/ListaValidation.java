package com.zappts.magic_the_gathering_API.validation;

import com.zappts.magic_the_gathering_API.entity.Carta;
import com.zappts.magic_the_gathering_API.entity.ListCollection;
import com.zappts.magic_the_gathering_API.entity.Lista;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorNotFoundException;
import com.zappts.magic_the_gathering_API.exception.listException.ListCollectionCardExistOnListException;
import com.zappts.magic_the_gathering_API.exception.listException.ListUserIsNotValidForOperationException;
import com.zappts.magic_the_gathering_API.repository.JogadorRepository;
import com.zappts.magic_the_gathering_API.repository.ListCollectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class ListaValidation {
    private JogadorRepository jogadorRepository;
    private ListCollectionRepository ListCollectionRepository;

    public void verifyIfUserIsValid(Integer jogadorId, Integer listId) throws JogadorNotFoundException, ListUserIsNotValidForOperationException {
        jogadorRepository.findById(jogadorId)
                .orElseThrow(JogadorNotFoundException::new);

        if (!listId.equals(jogadorId)) {
            throw new ListUserIsNotValidForOperationException();
        }
    }

    public ListCollection verifyIfCardExistOnList(Lista lista, Carta foundCard) throws ListCollectionCardExistOnListException {
        ListCollection listCollection = ListCollectionRepository.findByListAndCarta(lista, foundCard);
        if (listCollection != null) {
            return listCollection;
        } else {
            throw new ListCollectionCardExistOnListException();
        }
    }
}
