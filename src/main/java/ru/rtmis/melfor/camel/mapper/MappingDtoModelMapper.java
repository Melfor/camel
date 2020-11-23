package ru.rtmis.melfor.camel.mapper;

import org.mapstruct.Mapper;
import ru.rtmis.melfor.camel.dto.MappingDto;
import ru.rtmis.melfor.camel.model.MappingModel;

@Mapper
public interface MappingDtoModelMapper {

    MappingDto modelToDto(MappingModel model);

    MappingModel dtoToModel(MappingDto dto);
}
