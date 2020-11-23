package ru.rtmis.melfor.camel.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MappingModel {
    private Integer systemId;
    private String id;
    private String first;
    private String second;
}
