package ru.rtmis.melfor.camel.strategy;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import ru.rtmis.melfor.camel.dto.CalcDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class DynamicMappingAggregationStrategy implements AggregationStrategy {

    private static final String ID = "id";
    private static final String FIRST = "first";
    private static final String SECOND = "second";

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        newExchange.getIn().setBody(
                getCalcDto(oldExchange, getMappingParams(newExchange)
                ));
        return newExchange;
    }

    private Map<String, String> getMappingParams(Exchange newExchange) {
        List<Map<String, String>> sqlRequestResult =
                (List<Map<String, String>>) newExchange.getIn().getBody(List.class);
        return sqlRequestResult.get(0);
    }

    private CalcDto getCalcDto(Exchange oldExchange, Map<String, String> mappingParams) {
        return Optional.ofNullable(oldExchange)
                .map(exchange -> exchange.getIn().getBody(Map.class))
                .map(map -> CalcDto
                        .builder()
                        .id((Integer) map.get(mappingParams.get(ID)))
                        .first((Integer) map.get(mappingParams.get(FIRST)))
                        .second((Integer) map.get(mappingParams.get(SECOND)))
                        .build())
                .orElseThrow(IllegalArgumentException::new);
    }
}
