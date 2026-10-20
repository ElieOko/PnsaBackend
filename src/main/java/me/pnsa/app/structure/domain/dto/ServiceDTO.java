package me.pnsa.app.structure.domain.dto;

public record ServiceDTO(
        Long serviceId,
        String name,
        Boolean isActive
) {
}
