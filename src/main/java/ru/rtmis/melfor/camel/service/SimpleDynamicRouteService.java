package ru.rtmis.melfor.camel.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.camel.CamelContext;
import org.springframework.stereotype.Service;
import ru.rtmis.melfor.camel.dto.RebuildRouteDto;
import ru.rtmis.melfor.camel.routes.DynamicRestDirectRoute;

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
        //doesn't set uri, mb cause of spring boot property autoconfig
        //camelContext.addRouteFromTemplate(routeId, "dynamicTemplate", parameters);
    }
}
