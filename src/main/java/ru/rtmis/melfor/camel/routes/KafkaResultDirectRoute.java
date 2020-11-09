package ru.rtmis.melfor.camel.routes;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.rtmis.melfor.camel.dto.ResultDto;

@Setter
@Component
@RequiredArgsConstructor
@ConfigurationProperties("result.route")
public class KafkaResultDirectRoute extends RouteBuilder {
    private String fromRoute;

    @Override
    public void configure() throws Exception {
        from(fromRoute)
                .unmarshal().json(JsonLibrary.Jackson, ResultDto.class)
                .toD("direct:${body.id}");
    }
}
