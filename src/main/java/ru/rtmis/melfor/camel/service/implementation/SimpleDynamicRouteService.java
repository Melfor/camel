package ru.rtmis.melfor.camel.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.camel.CamelContext;
import org.springframework.stereotype.Service;
import ru.rtmis.melfor.camel.dto.RebuildRouteDto;
import ru.rtmis.melfor.camel.route.dynamic.routing.DynamicRestDirectRoute;
import ru.rtmis.melfor.camel.service.DynamicRouteService;

@Service
@RequiredArgsConstructor
public class SimpleDynamicRouteService implements DynamicRouteService {
    private final CamelContext camelContext;

    public void rebuild(RebuildRouteDto rebuildRouteDto) {
        String routeId = rebuildRouteDto.getRouteId();
        String uri = rebuildRouteDto.getUri();
        rebuildRoute(routeId, uri);
    }

    @SneakyThrows
    private void rebuildRoute(String routeId, String uri) {
        camelContext.getRouteController().stopRoute(routeId);
        camelContext.removeRoute(routeId);
        camelContext.addRoutes(new DynamicRestDirectRoute(routeId, uri));
    }
}
