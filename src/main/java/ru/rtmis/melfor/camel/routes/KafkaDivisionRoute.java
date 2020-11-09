package ru.rtmis.melfor.camel.routes;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.rtmis.melfor.camel.dto.CalcDto;
import ru.rtmis.melfor.camel.processor.DivisionProcessor;

@Setter
@Component
@RequiredArgsConstructor
@ConfigurationProperties("division.route")
public class KafkaDivisionRoute extends RouteBuilder {

    private final DivisionProcessor divisionProcessor;

    private String toRoute;
    private String fromRoute;

    @Override
    public void configure() throws Exception {

        from(fromRoute)
                .log("Read Message from Kafka ${body}")
                .unmarshal().json(JsonLibrary.Jackson, CalcDto.class)
                .log("Unmarshalled message is ${body}")
                .process(divisionProcessor)
                .log("Processed message is ${body}")
                .marshal().json(JsonLibrary.Jackson)
                .log("Marshalled message is ${body}")
                .to(toRoute)
                .log("Sent message to Kafka Result Topic");
    }
}
