package com.zappts.magic_the_gathering_API.mapper;

import com.zappts.magic_the_gathering_API.dto.DeckDTO;
import com.zappts.magic_the_gathering_API.entity.Deck;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeckMapper {
    DeckMapper INSTANCE = Mappers.getMapper(DeckMapper.class);

    Deck toModel(DeckDTO deckDTO);
    DeckDTO toDTO(Deck deck);
}
