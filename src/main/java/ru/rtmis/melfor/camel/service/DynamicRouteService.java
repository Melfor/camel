package ru.rtmis.melfor.camel.service;

import ru.rtmis.melfor.camel.dto.RebuildRouteDto;

public interface DynamicRouteService {

    void rebuild(RebuildRouteDto rebuildRouteDto);
}
