package com.zappts.magic_the_gathering_API.service;

import com.zappts.magic_the_gathering_API.dto.CartaDTO;
import com.zappts.magic_the_gathering_API.entity.Carta;
import com.zappts.magic_the_gathering_API.entity.Idioma;
import com.zappts.magic_the_gathering_API.exception.cardException.CardAlreadyRegisteredException;
import com.zappts.magic_the_gathering_API.exception.cardException.CardNotFoundException;
import com.zappts.magic_the_gathering_API.mapper.CartaMapper;
import com.zappts.magic_the_gathering_API.repository.CartaRepository;
import com.zappts.magic_the_gathering_API.validation.CardValidation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CartaService {

    private final CartaRepository cartaRepository;
    private final CardValidation cardValidation;
    private final CartaMapper cartaMapper = CartaMapper.INSTANCE;

    public CartaDTO createCard(CartaDTO cartaDTO) throws CardAlreadyRegisteredException {
        String name = cartaDTO.getName();
        String edition = cartaDTO.getEdition();
        Idioma idioma = cartaDTO.getIdioma();
        Boolean foil = cartaDTO.getFoil();

        cardValidation.verifyIfIsRegistered(name, edition, idioma, foil);

        Carta carta = cartaMapper.toModel(cartaDTO);
        Carta savedJogador = cartaRepository.save(carta);

        return cartaMapper.toDTO(savedJogador);
    }

    public List<CartaDTO> listAll() {
        return cartaRepository.findAll()
                .stream()
                .map(cartaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CartaDTO findById(String id) throws CardNotFoundException {
        Integer idConvert = Integer.parseInt(id);

        Carta foundPlayer = cartaRepository.findById(idConvert)
                .orElseThrow(CardNotFoundException::new);
        return cartaMapper.toDTO(foundPlayer);
    }

    public void deleteById(Integer id) throws CardNotFoundException {
        cardValidation.verifyIfIsRegistered(id);
        cartaRepository.deleteById(id);
    }

    public CartaDTO updateCard(Integer id, CartaDTO cartaDTO) throws CardNotFoundException, CardAlreadyRegisteredException {
        cartaDTO.setId(id);
        Carta newCarta = editCard(id, cartaDTO);

        cardValidation.verifyIfIsRegistered(newCarta.getName(), newCarta.getEdition(), newCarta.getIdioma(), newCarta.getFoil());

        cartaRepository.save(newCarta);

        return cartaMapper.toDTO(newCarta);
    }

    private Carta editCard(Integer id, CartaDTO cartaDTO) throws CardNotFoundException {
        String name = cartaDTO.getName();
        String edition = cartaDTO.getEdition();
        Idioma idioma = cartaDTO.getIdioma();
        Boolean foil = cartaDTO.getFoil();
        Double value = cartaDTO.getValue();

        return cartaRepository.findById(id)
                .map(card -> {
                    if(name != null) {
                        card.setName(name);
                    } else if (edition != null) {
                        card.setEdition(edition);
                    } else if (idioma != null) {
                        card.setIdioma(idioma);
                    } else if (foil != null) {
                        card.setFoil(foil);
                    } else if (value != null) {
                        card.setValue(value);
                    }
                    return card;
                })
                .orElseThrow(CardNotFoundException::new);
    }
}
