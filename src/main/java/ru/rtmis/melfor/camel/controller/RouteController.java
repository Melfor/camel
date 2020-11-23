package ru.rtmis.melfor.camel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.rtmis.melfor.camel.dto.RebuildRouteDto;
import ru.rtmis.melfor.camel.service.DynamicRouteService;

@RestController
@RequiredArgsConstructor
public class RouteController {
    private static final String REBUILD = "/rebuild";
    private static final String SUCCESSFULLY_REBUILT_ROUTE_WITH_NEW_URI =
            "Successfully rebuilt route %s with new uri %s";
    private final DynamicRouteService dynamicRouteService;

    @PostMapping(REBUILD)
    public String rebuildRoute(@RequestBody RebuildRouteDto rebuildRouteDto) {
        dynamicRouteService.rebuild(rebuildRouteDto);
        return String.format(
                SUCCESSFULLY_REBUILT_ROUTE_WITH_NEW_URI,
                rebuildRouteDto.getRouteId(),
                rebuildRouteDto.getUri()
        );
    }
}
