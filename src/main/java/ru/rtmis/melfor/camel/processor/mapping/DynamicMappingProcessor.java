package ru.rtmis.melfor.camel.processor.mapping;

import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import ru.rtmis.melfor.camel.dto.CalcDto;
import ru.rtmis.melfor.camel.service.MappingService;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class DynamicMappingProcessor implements Processor {
    private final MappingService mappingService;

    @Override
    public void process(Exchange exchange) throws Exception {
        Map<String, Object> body = (Map<String, Object>) exchange.getIn().getBody(Map.class);
        CalcDto calcDto = mappingService.mapCamelExchangeBodyToCalcDto(body);
        exchange.getIn().setBody(calcDto);
    }
}
