package ru.rtmis.melfor.camel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalcDto {
    private Integer id;
    private Integer first;
    private Integer second;
}