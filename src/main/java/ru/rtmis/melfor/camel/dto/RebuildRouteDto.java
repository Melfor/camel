package ru.rtmis.melfor.camel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RebuildRouteDto {
    private String routeId;
    private String uri;
}
