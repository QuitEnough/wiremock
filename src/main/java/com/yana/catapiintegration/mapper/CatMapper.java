package com.yana.catapiintegration.mapper;

import com.yana.catapiintegration.dto.CatDto;
import com.yana.catapiintegration.model.Cat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatMapper {
    CatDto toDto(Cat cat);
    List<CatDto> toDto(List<Cat> cats);
}
