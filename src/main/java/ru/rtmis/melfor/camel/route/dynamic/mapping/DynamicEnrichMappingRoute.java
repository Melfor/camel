package ru.rtmis.melfor.camel.route.dynamic.mapping;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.rtmis.melfor.camel.dto.CalcDto;
import ru.rtmis.melfor.camel.strategy.DynamicMappingAggregationStrategy;

import java.util.Map;

import static ru.rtmis.melfor.camel.route.log.RouteLoggingMessages.ENRICHED_BODY_IS_BODY;
import static ru.rtmis.melfor.camel.route.log.RouteLoggingMessages.MARSHALLED_BODY_IS_BODY;
import static ru.rtmis.melfor.camel.route.log.RouteLoggingMessages.POLL_ENRICHED_RESULT_BODY;
import static ru.rtmis.melfor.camel.route.log.RouteLoggingMessages.READ_MESSAGE_FROM_REST_BODY;
import static ru.rtmis.melfor.camel.route.log.RouteLoggingMessages.SENT_BODY_TO_KAFKA_SUBTRACTION_TOPIC;
import static ru.rtmis.melfor.camel.route.log.RouteLoggingMessages.TRUE;
import static ru.rtmis.melfor.camel.route.log.RouteLoggingMessages.UNMARSHALLED_BODY_IS_BODY;

@Setter
@Component
@RequiredArgsConstructor
@ConfigurationProperties("dynamic.enrich.mapping.route")
public class DynamicEnrichMappingRoute extends RouteBuilder {
    private final DynamicMappingAggregationStrategy dynamicMappingAggregationStrategy;
    private String fromRoute;
    private String enrichRoute;
    private String toRoute;
    private String pollEnrichRoute;

    @Override
    public void configure() throws Exception {

        from(fromRoute)
                .streamCaching(TRUE)
                .log(READ_MESSAGE_FROM_REST_BODY)
                .unmarshal().json(JsonLibrary.Jackson, Map.class)
                .log(UNMARSHALLED_BODY_IS_BODY)
                .enrich(enrichRoute, dynamicMappingAggregationStrategy)
                .log(ENRICHED_BODY_IS_BODY)
                .marshal().json(JsonLibrary.Jackson)
                .log(MARSHALLED_BODY_IS_BODY)
                .to(toRoute)
                .log(SENT_BODY_TO_KAFKA_SUBTRACTION_TOPIC)
                .unmarshal().json(JsonLibrary.Jackson, CalcDto.class)
                .pollEnrich().simple(pollEnrichRoute)
                .log(POLL_ENRICHED_RESULT_BODY);
    }
}
