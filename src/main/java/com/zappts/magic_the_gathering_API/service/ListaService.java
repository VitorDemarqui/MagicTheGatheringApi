package com.zappts.magic_the_gathering_API.service;

import com.zappts.magic_the_gathering_API.dto.DeckDTO;
import com.zappts.magic_the_gathering_API.dto.ListaDTO;
import com.zappts.magic_the_gathering_API.entity.Jogador;
import com.zappts.magic_the_gathering_API.entity.Lista;
import com.zappts.magic_the_gathering_API.exception.jogadorException.JogadorNotFoundException;
import com.zappts.magic_the_gathering_API.exception.listException.ListNotFoundException;
import com.zappts.magic_the_gathering_API.exception.listException.ListUserIsNotValidForOperationException;
import com.zappts.magic_the_gathering_API.mapper.ListaMapper;
import com.zappts.magic_the_gathering_API.repository.JogadorRepository;
import com.zappts.magic_the_gathering_API.repository.ListRepository;
import com.zappts.magic_the_gathering_API.validation.ListaValidation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ListaService {
    private final ListRepository listRepository;
    private final ListaMapper listaMapper = ListaMapper.INSTANCE;
    private final ListaValidation listaValidation;

    private final JogadorRepository jogadorRepository;

    public ListaDTO createList(ListaDTO listDTO) throws JogadorNotFoundException {
        jogadorRepository.findById(listDTO.getJogador().getId())
                .orElseThrow(JogadorNotFoundException::new);

        Lista list = listaMapper.toModel(listDTO);
        Lista savedList = listRepository.save(list);

        return listaMapper.toDTO(savedList);
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
}
