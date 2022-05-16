package com.zappts.magic_the_gathering_API.mapper;

import com.zappts.magic_the_gathering_API.dto.JogadorDTO;
import com.zappts.magic_the_gathering_API.entity.Jogador;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JogadorMapper {
    JogadorMapper INSTANCE = Mappers.getMapper(JogadorMapper.class);

    Jogador toModel(JogadorDTO jogadorDTO);
    JogadorDTO toDTO(Jogador jogador);
}
