package ru.rtmis.melfor.camel.repository.implementation;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.rtmis.melfor.camel.entity.MappingEntity;
import ru.rtmis.melfor.camel.mapper.MappingEntityModelMapper;
import ru.rtmis.melfor.camel.model.MappingModel;
import ru.rtmis.melfor.camel.repository.MappingRepository;

import java.util.Optional;

import static ru.rtmis.melfor.camel.jooq.schema.Tables.MAPPING;

@Component
@RequiredArgsConstructor
public class JooqMappingRepository implements MappingRepository {

    private static final MappingEntityModelMapper MAPPING_ENTITY_MODEL_MAPPER =
            Mappers.getMapper(MappingEntityModelMapper.class);

    private final DSLContext context;

    @Override
    public void save(MappingModel model) {
        MappingEntity mappingEntity = MAPPING_ENTITY_MODEL_MAPPER.modelToEntity(model);
        Integer systemId = mappingEntity.getSystemId();

        context
                .insertInto(MAPPING)
                .columns(MAPPING.SYSTEM_ID, MAPPING.ID, MAPPING.FIRST, MAPPING.SECOND)
                .values(systemId, mappingEntity.getId(), mappingEntity.getFirst(), mappingEntity.getSecond())
                .execute();
    }

    @Override
    public Optional<MappingModel> getMappingBySystemId(Integer systemId) {

        return context.select()
                .from(MAPPING)
                .where(MAPPING.SYSTEM_ID.eq(systemId))
                .fetchOptionalInto(MappingEntity.class)
                .map(MAPPING_ENTITY_MODEL_MAPPER::entityToModel);
    }
}
