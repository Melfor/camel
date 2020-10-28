package ru.rtmis.melfor.camel.routes;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.support.processor.PredicateValidationException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.rtmis.melfor.camel.dto.CalcDto;
import ru.rtmis.melfor.camel.processor.CalcDtoValidationProcessor;
import ru.rtmis.melfor.camel.processor.ExceptionProcessor;
import ru.rtmis.melfor.camel.processor.ValidationProcessor;

@Setter
@Component
@RequiredArgsConstructor
@ConfigurationProperties("rest.route")
public class RestRoute extends RouteBuilder {

    private final CalcDtoValidationProcessor calcDtoValidationProcessor;
    private final ValidationProcessor validationProcessor;
    private final ExceptionProcessor exceptionProcessor;

    private static final String MATH_OPERATION = "mathOperation";
    private static final String SUBTRACTION = "SUBTRACTION";
    private static final String DIVISION = "DIVISION";

    private String fromRoute;
    private String subtractionRoute;
    private String divisionRoute;
    private String errorRoute;

    @Override
    public void configure() throws Exception {

        onException(PredicateValidationException.class)
                .log(LoggingLevel.ERROR, "Validation Exception in the route ${body}")
                .process(exceptionProcessor)
                .log("Processed exception is ${body}")
                .to(errorRoute)
                .log("Sent message to Kafka Error Topic");

        from(fromRoute)
                .streamCaching("true")
                .validate(header(MATH_OPERATION).isNotNull())
                .process(validationProcessor)
                .log("Read Message from Rest ${body}")
                .unmarshal().json(JsonLibrary.Jackson, CalcDto.class)
                .log("Unmarshalled message is ${body}")
                .process(calcDtoValidationProcessor)
                .log("Processed ${body}")
                .marshal().json(JsonLibrary.Jackson)
                .log("Marshalled message is ${body}")
                .choice()
                .when(header(MATH_OPERATION).contains(SUBTRACTION))
                    .to(subtractionRoute)
                    .log("Sent message to Kafka Subtraction Topic")
                .otherwise()
                    .when(header(MATH_OPERATION).contains(DIVISION))
                    .to(divisionRoute)
                    .log("Sent message to Kafka Division Topic")
                .end()
                .setBody(constant("Request was processed successfully"));
    }
}
