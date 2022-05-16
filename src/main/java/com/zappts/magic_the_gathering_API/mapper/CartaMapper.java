package com.zappts.magic_the_gathering_API.mapper;

import com.zappts.magic_the_gathering_API.dto.CartaDTO;
import com.zappts.magic_the_gathering_API.entity.Carta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartaMapper {
    CartaMapper INSTANCE = Mappers.getMapper(CartaMapper.class);

    Carta toModel(CartaDTO cartaDTO);
    CartaDTO toDTO(Carta carta);
}
