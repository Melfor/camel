package ru.rtmis.melfor.camel.repository;

import ru.rtmis.melfor.camel.model.MappingModel;

import java.util.Optional;

public interface MappingRepository {

    void save(MappingModel model);

    Optional<MappingModel> getMappingBySystemId(Integer systemId);
}
