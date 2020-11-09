package ru.rtmis.melfor.camel.routes;

import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;

@RequiredArgsConstructor
public class DynamicRestDirectRoute extends RouteBuilder {
    private final String routeId;
    private final String uri;

    @Override
    public void configure() throws Exception {
        from(uri)
                .routeId(routeId)
                .streamCaching("true")
                .log("Read Message from Rest ${body}")
                .to("direct:calcRest?block=true&timeout=300&synchronous=true");
    }
}
