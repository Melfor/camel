package ru.rtmis.melfor.camel.service;

import ru.rtmis.melfor.camel.dto.CalcDto;
import ru.rtmis.melfor.camel.dto.MappingDto;

import java.util.Map;

public interface MappingService {

    void createMapping(MappingDto mappingDto);

    CalcDto mapCamelExchangeBodyToCalcDto(Map<String, Object> body);
}
