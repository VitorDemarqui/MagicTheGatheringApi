package com.zappts.magic_the_gathering_API.service;

import com.zappts.magic_the_gathering_API.dto.IdiomaDTO;
import com.zappts.magic_the_gathering_API.entity.Idioma;
import com.zappts.magic_the_gathering_API.exception.idiomaException.IdiomaAlreadyRegisteredException;
import com.zappts.magic_the_gathering_API.exception.idiomaException.IdiomaNotFoundException;
import com.zappts.magic_the_gathering_API.mapper.IdiomaMapper;
import com.zappts.magic_the_gathering_API.repository.IdiomaRepository;
import com.zappts.magic_the_gathering_API.validation.IdiomaValidation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class IdiomaService {


    private final IdiomaRepository idiomaRepository;
    private final IdiomaValidation idiomaValidation;
    private final IdiomaMapper idiomaMapper = IdiomaMapper.INSTANCE;

    public IdiomaDTO createIdioma(IdiomaDTO idiomaDTO) throws IdiomaAlreadyRegisteredException {
        idiomaValidation.verifyIfIsRegistered(idiomaDTO.getName());

        Idioma idioma = idiomaMapper.toModel(idiomaDTO);
        Idioma savedJogador = idiomaRepository.save(idioma);

        return idiomaMapper.toDTO(savedJogador);
    }

    public List<IdiomaDTO> listAll() {
        return idiomaRepository.findAll()
                .stream()
                .map(idiomaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public IdiomaDTO findById(String id) throws IdiomaNotFoundException {
        Integer idConvert = Integer.parseInt(id);

        Idioma foundPlayer = idiomaRepository.findById(idConvert)
                .orElseThrow(IdiomaNotFoundException::new);
        return idiomaMapper.toDTO(foundPlayer);
    }

    public void deleteById(Integer id) {
        idiomaRepository.deleteById(id);
    }

    public IdiomaDTO updateIdioma(Integer id, IdiomaDTO idiomaDTO) throws IdiomaAlreadyRegisteredException {
        idiomaDTO.setId(id);
        Idioma idioma = idiomaMapper.toModel(idiomaDTO);

        idiomaValidation.verifyIfIsRegistered(idiomaDTO.getName());
        idiomaRepository.save(idioma);

        return idiomaMapper.toDTO(idioma);
    }
}
