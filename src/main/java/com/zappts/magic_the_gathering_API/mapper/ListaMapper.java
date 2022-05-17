package com.zappts.magic_the_gathering_API.mapper;

import com.zappts.magic_the_gathering_API.dto.ListaDTO;
import com.zappts.magic_the_gathering_API.entity.Lista;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ListaMapper {
    ListaMapper INSTANCE = Mappers.getMapper(ListaMapper.class);

    Lista toModel(ListaDTO listDTO);
    ListaDTO toDTO(Lista list);
}