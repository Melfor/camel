package ru.rtmis.melfor.camel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.rtmis.melfor.camel.dto.MappingDto;
import ru.rtmis.melfor.camel.service.MappingService;

@RestController
@RequiredArgsConstructor
public class MappingController {
    private static final String MAPPING = "/mapping";
    private static final String SUCCESSFULLY_CREATED_MAPPING =
            "Successfully created mapping %s";
    private final MappingService mappingService;

    @PostMapping(MAPPING)
    public String createMapping(@RequestBody MappingDto mappingDto) {
        mappingService.createMapping(mappingDto);
        return String.format(
                SUCCESSFULLY_CREATED_MAPPING,
                mappingDto
        );
    }
}
