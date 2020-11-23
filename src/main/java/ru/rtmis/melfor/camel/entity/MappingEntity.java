package ru.rtmis.melfor.camel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MappingEntity {
    private Integer systemId;
    private String id;
    private String first;
    private String second;
}
