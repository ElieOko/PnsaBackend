package me.pnsa.app.structure.domain.models;

public record Service(
        Long serviceId,
        String name,
        Boolean isActive
) {
}
