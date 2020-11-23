package ru.rtmis.melfor.camel.route.dynamic;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.rtmis.melfor.camel.dto.CalcDto;

@Setter
@Component
@RequiredArgsConstructor
@ConfigurationProperties("direct.route")
public class DirectKafkaRoute extends RouteBuilder {
    private String toRoute;

    @Override
    public void configure() throws Exception {
        from("direct:calcRest")
                .streamCaching("true")
                .log("Read Message from Direct ${body}")
                .to(toRoute)
                .log("Sent Message to Kafka ${body}")
                .unmarshal().json(JsonLibrary.Jackson, CalcDto.class)
                .pollEnrich().simple("direct:${body.id}")
                .log("Poll enriched Result ${body}")
                .marshal().json(JsonLibrary.Jackson);
    }
}
