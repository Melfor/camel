package ru.rtmis.melfor.camel.mapper;

import org.mapstruct.Mapper;
import ru.rtmis.melfor.camel.entity.MappingEntity;
import ru.rtmis.melfor.camel.model.MappingModel;

@Mapper
public interface MappingEntityModelMapper {

    MappingEntity modelToEntity(MappingModel model);

    MappingModel entityToModel(MappingEntity entity);
}
