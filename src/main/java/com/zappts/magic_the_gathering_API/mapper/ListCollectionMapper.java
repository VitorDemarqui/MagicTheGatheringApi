package com.zappts.magic_the_gathering_API.mapper;

import com.zappts.magic_the_gathering_API.dto.ListCollectionDTO;
import com.zappts.magic_the_gathering_API.entity.ListCollection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ListCollectionMapper {
    ListCollectionMapper INSTANCE = Mappers.getMapper(ListCollectionMapper.class);

    ListCollection toModel(ListCollectionDTO listCollectionDTO);
    ListCollectionDTO toDTO(ListCollection listCollection);
}
