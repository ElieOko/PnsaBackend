package me.pnsa.app.structure.domain.models;

import java.time.LocalDateTime;
import java.util.List;

public record Structure(
        Long structureId,
        Long userId,
        Long structureTypeId,
        Long provinceId,
        String name,
        String description,
        List<Object> servicesIds,
        List<Object> responsibilities,
        List<Object> address,
        List<Object> phoneNumbers,
        List<Object> emails,
        List<Object> agenda,
        Double latitude,
        Double longitude,
        Boolean isActive,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
        ) {
}
