package ru.rtmis.melfor.camel.route.simple;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.rtmis.melfor.camel.dto.CalcDto;
import ru.rtmis.melfor.camel.processor.arithmetic.SubtractionProcessor;

@Setter
@Component
@RequiredArgsConstructor
@ConfigurationProperties("subtraction.route")
public class KafkaSubtractionRoute extends RouteBuilder {

    private final SubtractionProcessor subtractionProcessor;

    private String toRoute;
    private String fromRoute;

    @Override
    public void configure() throws Exception {

        from(fromRoute)
                .log("Read Message from Kafka ${body}")
                .unmarshal().json(JsonLibrary.Jackson, CalcDto.class)
                .log("Unmarshalled message is ${body}")
                .process(subtractionProcessor)
                .log("Processed message is ${body}")
                .marshal().json(JsonLibrary.Jackson)
                .log("Marshalled message is ${body}")
                .to(toRoute)
                .log("Sent message to Kafka Result Topic");
    }
}
