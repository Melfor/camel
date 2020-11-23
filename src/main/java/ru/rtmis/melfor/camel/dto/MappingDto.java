package ru.rtmis.melfor.camel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MappingDto {
    private Integer systemId;
    private String id;
    private String first;
    private String second;
}
