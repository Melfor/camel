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
    private static final String SUCCESS = "Success";
    private final DynamicRouteService dynamicRouteService;

    @PostMapping("/rebuild")
    public String rebuildRoute(@RequestBody RebuildRouteDto rebuildRouteDto) {
        dynamicRouteService.rebuild(rebuildRouteDto);
        return SUCCESS;
    }
}
