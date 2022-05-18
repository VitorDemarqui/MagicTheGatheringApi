package com.zappts.magic_the_gathering_API.service;

import com.zappts.magic_the_gathering_API.dto.ListCollectionDTO;
import com.zappts.magic_the_gathering_API.dto.ListaDTO;
import com.zappts.magic_the_gathering_API.entity.Carta;
import com.zappts.magic_the_gathering_API.entity.ListCollection;
import com.zappts.magic_the_gathering_API.entity.Lista;
import com.zappts.magic_the_gathering_API.exception.cardException.CardNotFoundException;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorNotFoundException;
import com.zappts.magic_the_gathering_API.exception.listException.ListCollectionCardExistOnListException;
import com.zappts.magic_the_gathering_API.exception.listException.ListNotFoundException;
import com.zappts.magic_the_gathering_API.exception.listException.ListOrderByIncorrectException;
import com.zappts.magic_the_gathering_API.exception.listException.ListUserIsNotValidForOperationException;
import com.zappts.magic_the_gathering_API.mapper.ListCollectionMapper;
import com.zappts.magic_the_gathering_API.mapper.ListaMapper;
import com.zappts.magic_the_gathering_API.repository.CartaRepository;
import com.zappts.magic_the_gathering_API.repository.JogadorRepository;
import com.zappts.magic_the_gathering_API.repository.ListCollectionRepository;
import com.zappts.magic_the_gathering_API.repository.ListRepository;
import com.zappts.magic_the_gathering_API.validation.ListaValidation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ListaService {
    private final ListRepository listRepository;
    private final ListaMapper listaMapper = ListaMapper.INSTANCE;
    private final ListaValidation listaValidation;

    private final JogadorRepository jogadorRepository;
    private final ListCollectionRepository listCollectionRepository;
    private final ListCollectionMapper listCollectionMapper = ListCollectionMapper.INSTANCE;
    private final CartaRepository cartaRepository;

    public ListaDTO createList(ListaDTO listDTO) throws JogadorNotFoundException {
        jogadorRepository.findById(listDTO.getJogador().getId())
                .orElseThrow(JogadorNotFoundException::new);

        Lista list = listaMapper.toModel(listDTO);
        Lista savedList = listRepository.save(list);

        return listaMapper.toDTO(savedList);
    }

    public List<ListaDTO> findAllLists() {
        return listRepository.findAll()
                .stream()
                .map(listaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ListaDTO> findById(Integer id) throws ListNotFoundException {
        listRepository.findById(id)
                .orElseThrow(ListNotFoundException::new);

        return listRepository.findById(id)
                .stream()
                .map(listaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Integer idUsuario, Integer idLista) throws ListNotFoundException, ListUserIsNotValidForOperationException, JogadorNotFoundException {
        Lista lista = listRepository.findById(idLista)
                .orElseThrow(ListNotFoundException::new);

        listaValidation.verifyIfUserIsValid(idUsuario, lista.getJogador().getId());

        listRepository.deleteById(idLista);
    }

    public List<ListCollectionDTO> getCardsListById(Integer idList, String orderBy) throws ListNotFoundException, ListOrderByIncorrectException {
        Lista lista = listRepository.findById(idList)
                .orElseThrow(ListNotFoundException::new);

        List<ListCollection> listCollection;
        if(orderBy.equals("value")) {
            listCollection = listCollectionRepository.findAllByListOrderByCardValue(lista.getId());
        } else if (orderBy.equals("name")) {
            listCollection = listCollectionRepository.findAllByListOrderByName(lista.getId());
        } else {
            listCollection = listCollectionRepository.findAllByList(lista);
        }

        return listCollection
                .stream()
                .map(list -> {
                    list.setList(null);
                    return listCollectionMapper.toDTO(list);
                })
                .collect(Collectors.toList());
    }

    public ListCollectionDTO addCardLista(ListCollectionDTO listCollectionDTO, Integer idUsuario) throws ListNotFoundException, ListUserIsNotValidForOperationException, JogadorNotFoundException, CardNotFoundException {
        Integer idLista = listCollectionDTO.getList().getId();
        Integer idCarta = listCollectionDTO.getCarta().getId();

        Lista lista = listRepository.findById(idLista)
                .orElseThrow(ListNotFoundException::new);

        listaValidation.verifyIfUserIsValid(idUsuario, lista.getJogador().getId());

        Carta foundCarta = cartaRepository.findById(idCarta)
                .orElseThrow(CardNotFoundException::new);

        ListCollection listCollection = listCollectionRepository.findByListAndCarta(lista, foundCarta);


        if(listCollection != null) {
            listCollection.setQtdCarta(listCollection.getQtdCarta() + listCollectionDTO.getQtdCarta());
            return listCollectionMapper.toDTO(listCollectionRepository.save(listCollection));
        }

        listCollectionDTO.setCarta(foundCarta);
        listCollectionDTO.setList(lista);
        ListCollection convertList = listCollectionMapper.toModel(listCollectionDTO);
        ListCollection savedList = listCollectionRepository.save(convertList);

        return listCollectionMapper.toDTO(savedList);
    }

    public void deleteCardList(Integer idUsuario, ListCollectionDTO listCollectionDTO) throws ListNotFoundException, JogadorNotFoundException, CardNotFoundException, ListUserIsNotValidForOperationException, ListCollectionCardExistOnListException {
        Lista lista = listRepository.findById(listCollectionDTO.getList().getId())
                .orElseThrow(ListNotFoundException::new);

        Carta foundCard = cartaRepository.findById(listCollectionDTO.getCarta().getId())
                .orElseThrow(CardNotFoundException::new);

        listaValidation.verifyIfUserIsValid(idUsuario, lista.getJogador().getId());
        ListCollection listCollection = listaValidation.verifyIfCardExistOnList(lista,foundCard);
        int qtdArmazenada = listCollection.getQtdCarta();
        int qtdRetirar = listCollectionDTO.getQtdCarta();
        int newQtd = qtdArmazenada - qtdRetirar;

        if((qtdArmazenada - qtdRetirar) < 1) {
            listCollectionRepository.deleteById(listCollection.getId());
        } else {
            listCollection.setQtdCarta(newQtd);
            listCollectionRepository.save(listCollection);
        }
    }
}
