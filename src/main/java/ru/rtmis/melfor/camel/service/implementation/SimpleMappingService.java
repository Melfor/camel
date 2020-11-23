package ru.rtmis.melfor.camel.service.implementation;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.rtmis.melfor.camel.dto.CalcDto;
import ru.rtmis.melfor.camel.dto.MappingDto;
import ru.rtmis.melfor.camel.mapper.MappingDtoModelMapper;
import ru.rtmis.melfor.camel.model.MappingModel;
import ru.rtmis.melfor.camel.repository.MappingRepository;
import ru.rtmis.melfor.camel.service.MappingService;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SimpleMappingService implements MappingService {
    private final MappingRepository mappingRepository;
    private static final String SYSTEM_ID = "systemId";

    private static final MappingDtoModelMapper MAPPING_DTO_MODEL_MAPPER =
            Mappers.getMapper(MappingDtoModelMapper.class);

    @Override
    public void createMapping(MappingDto mappingDto) {
        mappingRepository.save(MAPPING_DTO_MODEL_MAPPER.dtoToModel(mappingDto));
    }

    @Override
    public CalcDto mapCamelExchangeBodyToCalcDto(Map<String, Object> body) {
        return Optional.ofNullable(body)
                .map(map -> (Integer) map.get(SYSTEM_ID))
                .map(mappingRepository::getMappingBySystemId)
                .orElseThrow(IllegalArgumentException::new)
                .map(model -> buildCalcDto(body, model))
                .orElseThrow(IllegalArgumentException::new);
    }

    private CalcDto buildCalcDto(Map<String, Object> body, MappingModel model) {
        return CalcDto.builder()
                .id((Integer) body.get(model.getId()))
                .first((Integer) body.get(model.getFirst()))
                .second((Integer) body.get(model.getSecond()))
                .build();
    }
}
