package com.zappts.magic_the_gathering_API.mapper;

import com.zappts.magic_the_gathering_API.dto.IdiomaDTO;
import com.zappts.magic_the_gathering_API.entity.Idioma;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IdiomaMapper {
    IdiomaMapper INSTANCE = Mappers.getMapper(IdiomaMapper.class);

    Idioma toModel(IdiomaDTO idiomaDTO);
    IdiomaDTO toDTO(Idioma idioma);
}
